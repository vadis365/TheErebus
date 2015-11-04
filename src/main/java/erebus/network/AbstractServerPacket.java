package erebus.network;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public abstract class AbstractServerPacket extends AbstractPacket {

	@Override
	public void handle(Side side, World world, EntityPlayer player) {
		if (side == Side.SERVER)
			handle(world, (EntityPlayerMP) player);
		else
			throw new UnsupportedOperationException("Tried to handle server packet on client side!");
	}

	protected abstract void handle(World world, EntityPlayerMP player);
}