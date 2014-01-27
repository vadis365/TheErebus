package erebus.tileentity;

import net.minecraft.tileentity.TileEntity;

public class TileEntityLadder extends TileEntity {
	@Override
	public boolean canUpdate(){
		return false;
	}

	// Because efficiency is for wimps
}