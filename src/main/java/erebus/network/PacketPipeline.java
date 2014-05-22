package erebus.network;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.FMLOutboundHandler.OutboundTarget;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@ChannelHandler.Sharable
public class PacketPipeline extends MessageToMessageCodec<FMLProxyPacket,AbstractPacket>{
	private static PacketPipeline instance;
	
	public static void initializePipeline(){
		if (instance != null)throw new RuntimeException("Packet pipeline has already been registered!");
		instance = new PacketPipeline();
		instance.load();
	}
	
	private EnumMap<Side,FMLEmbeddedChannel> channels;
	private LinkedList<Class<? extends AbstractPacket>> packets = new LinkedList<Class<? extends AbstractPacket>>();
	
	private PacketPipeline(){}
	
	private void load(){
		channels = NetworkRegistry.INSTANCE.newChannel("hee",instance);
		
		try{
			ClassPath path = ClassPath.from(PacketPipeline.class.getClassLoader());
			
			for(ClassInfo clsInfo:path.getTopLevelClasses("chylex.hee.packets.client")){
				Class cls = clsInfo.load();
				if (!cls.getName().endsWith("__"))packets.add(cls);
			}
			
			for(ClassInfo clsInfo:path.getTopLevelClasses("chylex.hee.packets.server")){
				Class cls = clsInfo.load();
				if (!cls.getName().endsWith("__"))packets.add(cls);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
		Collections.sort(packets,new Comparator<Class<? extends AbstractPacket>>(){
			@Override
			public int compare(Class<? extends AbstractPacket> cls1, Class<? extends AbstractPacket> cls2){
				int res = String.CASE_INSENSITIVE_ORDER.compare(cls1.getCanonicalName(),cls2.getCanonicalName());
				if (res == 0)res = cls1.getCanonicalName().compareTo(cls2.getCanonicalName());
				return res;
			}
		});
	}
	
	@Override
	protected void encode(ChannelHandlerContext ctx, AbstractPacket packet, List<Object> out) throws Exception{
		ByteBuf buffer = Unpooled.buffer();
		byte index = (byte)packets.indexOf(packet.getClass());
		if (index==-1)throw new UnsupportedOperationException("Tried to send a non-registered packet!");
		
		buffer.writeByte(index);
		packet.write(ctx,buffer);
		FMLProxyPacket proxyPacket = new FMLProxyPacket(buffer.copy(),ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get());
		out.add(proxyPacket);
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, FMLProxyPacket packet, List<Object> out) throws Exception{
		ByteBuf payload = packet.payload();
		Class<? extends AbstractPacket> cls = packets.get(payload.readByte());
		if (cls == null)throw new UnsupportedOperationException("Tried to handle a non-registered packet!");
		
		AbstractPacket pkt = cls.newInstance();
		pkt.read(ctx,payload.slice());
		switch(FMLCommonHandler.instance().getEffectiveSide()){
			case CLIENT:
				pkt.handle(Side.CLIENT,getClientPlayer());
				break;

			case SERVER:
				INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
				pkt.handle(Side.SERVER,((NetHandlerPlayServer)netHandler).playerEntity);
				break;
		}
		out.add(pkt);
	}
	
	@SideOnly(Side.CLIENT)
	private EntityPlayer getClientPlayer(){
		return Minecraft.getMinecraft().thePlayer;
	}
	
	public static void sendToAll(AbstractPacket packet){
		FMLEmbeddedChannel channel = instance.channels.get(Side.SERVER);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.ALL);
		channel.writeAndFlush(packet);
	}
	
	public static void sendToPlayer(EntityPlayer player, AbstractPacket packet){
		FMLEmbeddedChannel channel = instance.channels.get(Side.SERVER);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.PLAYER);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
		channel.writeAndFlush(packet);
	}
	
	public static void sendToAllAround(int dimension, double x, double y, double z, double range, AbstractPacket packet){
		FMLEmbeddedChannel channel = instance.channels.get(Side.SERVER);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.ALLAROUNDPOINT);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(new TargetPoint(dimension,x,y,z,range));
		channel.writeAndFlush(packet);
	}
	
	public static void sendToAllAround(Entity entity, double range, AbstractPacket packet){
		FMLEmbeddedChannel channel = instance.channels.get(Side.SERVER);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.ALLAROUNDPOINT);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(new TargetPoint(entity.dimension,entity.posX,entity.posY,entity.posZ,range));
		channel.writeAndFlush(packet);
	}
	
	public static void sendToAllAround(TileEntity tile, double range, AbstractPacket packet){
		FMLEmbeddedChannel channel = instance.channels.get(Side.SERVER);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.ALLAROUNDPOINT);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(new TargetPoint(tile.getWorldObj().provider.dimensionId,tile.xCoord+0.5D,tile.yCoord+0.5D,tile.zCoord+0.5D,range));
		channel.writeAndFlush(packet);
	}
	
	public static void sendToDimension(int dimension, AbstractPacket packet){
		FMLEmbeddedChannel channel = instance.channels.get(Side.SERVER);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.DIMENSION);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimension);
		channel.writeAndFlush(packet);
	}
	
	public static void sendToServer(AbstractPacket message){
		FMLEmbeddedChannel channel = instance.channels.get(Side.CLIENT);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.TOSERVER);
		channel.writeAndFlush(message);
    }
}
