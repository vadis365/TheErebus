package erebus.block.silo;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityBasicInventory;
import net.minecraft.util.AxisAlignedBB;

public class TileEntitySiloTank extends TileEntityBasicInventory {

	private boolean active;

	public TileEntitySiloTank() {
		super(104, "");
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public String getInventoryName() {
		return "Silo Location X:" + xCoord + " Y:" + yCoord + " Z:" + zCoord;
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
