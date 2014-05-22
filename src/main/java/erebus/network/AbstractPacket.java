package erebus.network;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.relauncher.Side;

public abstract class AbstractPacket{
	protected static Random rand = new Random();
	
	public abstract void write(ChannelHandlerContext ctx, ByteBuf buffer);
	public abstract void read(ChannelHandlerContext ctx, ByteBuf buffer);
	public abstract void handle(Side side, EntityPlayer player);
}
