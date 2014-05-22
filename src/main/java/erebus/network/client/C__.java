package erebus.network.client;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.entity.EntityClientPlayerMP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.network.AbstractClientPacket;

// @formatter:off
public class C__ extends AbstractClientPacket{
	
	public C__(){}
	
	public C__(Object o){
		
	}
	
	@Override
	public void write(ChannelHandlerContext ctx, ByteBuf buffer){
		
	}

	@Override
	public void read(ChannelHandlerContext ctx, ByteBuf buffer){
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected void handle(EntityClientPlayerMP player){
		
	}
}
//@formatter:on
