package erebus.network;
import io.netty.buffer.ByteBuf;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.relauncher.Side;

public abstract class AbstractPacket{
	protected static Random rand = new Random();
	
	public abstract void write(ByteBuf buffer);
	public abstract void read(ByteBuf buffer);
	public abstract void handle(Side side, EntityPlayer player);
}
