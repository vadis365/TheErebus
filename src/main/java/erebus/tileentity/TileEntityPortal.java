package erebus.tileentity;

import net.minecraft.tileentity.TileEntity;
import erebus.ModBlocks;

public class TileEntityPortal extends TileEntity {

	@Override
	public boolean canUpdate() {
		return false;
	}

	public boolean renderBeam() {
		return worldObj.getBlock(xCoord, yCoord + 1, zCoord) == ModBlocks.portalErebus;
	}
}
