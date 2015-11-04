package erebus.network;

import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class AbstractPacket {
	public abstract void write(ByteBuf buffer);

	public abstract void read(ByteBuf buffer);

	public abstract void handle(Side side, World world, EntityPlayer player);
}
