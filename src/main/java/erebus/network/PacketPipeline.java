package erebus.network;

import java.lang.reflect.Field;
import java.util.EnumMap;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.FMLOutboundHandler.OutboundTarget;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.lib.Reference;
import gnu.trove.map.hash.TByteObjectHashMap;
import gnu.trove.map.hash.TObjectByteHashMap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketPipeline {

	private static PacketPipeline instance;

	public static void initializePipeline() {
		if (instance != null)
			throw new RuntimeException("Packet pipeline has already been registered!");
		instance = new PacketPipeline();
		instance.load();
	}

	private FMLEventChannel eventDrivenChannel;
	private EnumMap<Side, FMLEmbeddedChannel> channels;

	private final TByteObjectHashMap<Class<? extends AbstractPacket>> idToPacket = new TByteObjectHashMap<Class<? extends AbstractPacket>>();
	private final TObjectByteHashMap<Class<? extends AbstractPacket>> packetToId = new TObjectByteHashMap<Class<? extends AbstractPacket>>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void load() {
		eventDrivenChannel = NetworkRegistry.INSTANCE.newEventDrivenChannel(Reference.CHANNEL);
		eventDrivenChannel.register(this);

		try {
			Field channelField = FMLEventChannel.class.getDeclaredField("channels");
			channelField.setAccessible(true);
			channels = (EnumMap) channelField.get(eventDrivenChannel);

			int id = -1;

			ClassPath path = ClassPath.from(PacketPipeline.class.getClassLoader());

			for (ClassInfo clsInfo : path.getTopLevelClasses("erebus.network.client")) {
				Class cls = clsInfo.load();
				if (!cls.getName().endsWith("__"))
					registerPacket(++id, cls);
			}

			for (ClassInfo clsInfo : path.getTopLevelClasses("erebus.network.server")) {
				Class cls = clsInfo.load();
				if (!cls.getName().endsWith("__"))
					registerPacket(++id, cls);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void registerPacket(int id, Class<? extends AbstractPacket> cls) {
		idToPacket.put((byte) id, cls);
		packetToId.put(cls, (byte) id);
	}

	private FMLProxyPacket writePacket(AbstractPacket packet) {
		ByteBuf buffer = Unpooled.buffer();
		buffer.writeByte(packetToId.get(packet.getClass()));
		packet.write(buffer);
		return new FMLProxyPacket(buffer, Reference.CHANNEL);
	}

	private void readPacket(FMLProxyPacket fmlPacket, Side side) {
		ByteBuf buffer = fmlPacket.payload();

		try {
			AbstractPacket packet = idToPacket.get(buffer.readByte()).newInstance();
			packet.read(buffer.slice());

			switch (side) {
				case CLIENT:
					packet.handle(Side.CLIENT, getClientWorld(), getClientPlayer());
					break;

				case SERVER:
					packet.handle(Side.SERVER, ((NetHandlerPlayServer) fmlPacket.handler()).playerEntity.worldObj, ((NetHandlerPlayServer) fmlPacket.handler()).playerEntity);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SideOnly(Side.CLIENT)
	private World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}

	@SideOnly(Side.CLIENT)
	private EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}

	// EVENTS AND DISPATCHING

	@SubscribeEvent
	public void onClientPacketReceived(ClientCustomPacketEvent e) {
		readPacket(e.packet, Side.CLIENT);
	}

	@SubscribeEvent
	public void onServerPacketReceived(ServerCustomPacketEvent e) {
		readPacket(e.packet, Side.SERVER);
	}

	public static void sendToAll(AbstractPacket packet) {
		FMLEmbeddedChannel channel = instance.channels.get(Side.SERVER);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.ALL);
		channel.writeAndFlush(instance.writePacket(packet)).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
	}

	public static void sendToPlayer(EntityPlayer player, AbstractPacket packet) {
		FMLEmbeddedChannel channel = instance.channels.get(Side.SERVER);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.PLAYER);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
		channel.writeAndFlush(instance.writePacket(packet)).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
	}

	public static void sendToAllAround(int dimension, double x, double y, double z, double range, AbstractPacket packet) {
		FMLEmbeddedChannel channel = instance.channels.get(Side.SERVER);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.ALLAROUNDPOINT);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(new TargetPoint(dimension, x, y, z, range));
		channel.writeAndFlush(instance.writePacket(packet)).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
	}

	public static void sendToAllAround(Entity entity, double range, AbstractPacket packet) {
		FMLEmbeddedChannel channel = instance.channels.get(Side.SERVER);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.ALLAROUNDPOINT);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, range));
		channel.writeAndFlush(instance.writePacket(packet)).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
	}

	public static void sendToAllAround(TileEntity tile, double range, AbstractPacket packet) {
		FMLEmbeddedChannel channel = instance.channels.get(Side.SERVER);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.ALLAROUNDPOINT);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(new TargetPoint(tile.getWorldObj().provider.dimensionId, tile.xCoord + 0.5D, tile.yCoord + 0.5D, tile.zCoord + 0.5D, range));
		channel.writeAndFlush(instance.writePacket(packet)).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
	}

	public static void sendToDimension(int dimension, AbstractPacket packet) {
		FMLEmbeddedChannel channel = instance.channels.get(Side.SERVER);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.DIMENSION);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimension);
		channel.writeAndFlush(instance.writePacket(packet)).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
	}

	public static void sendToServer(AbstractPacket packet) {
		FMLEmbeddedChannel channel = instance.channels.get(Side.CLIENT);
		channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.TOSERVER);
		channel.writeAndFlush(instance.writePacket(packet)).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
	}
}