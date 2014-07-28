package erebus.tileentity;

import net.minecraft.tileentity.TileEntity;

public class TileEntitySiloIntake extends TileEntity {
	@Override
	public boolean canUpdate() {
		return false;
	}
	// Because efficiency is for wimps
}