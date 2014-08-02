package erebus.block.silo;

import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityBasicInventory;

public class TileEntitySiloTank extends TileEntityBasicInventory {

	private boolean active;

	public TileEntitySiloTank() {
		super(27, "container.siloTank");
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1);
	}

	public void setActive(boolean state) {
		active = state;
	}
	
	public boolean getActive() {
		return active;
	}
}
