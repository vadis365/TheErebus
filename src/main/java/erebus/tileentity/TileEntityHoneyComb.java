package erebus.tileentity;

import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityHoneyComb extends TileEntityBasicInventory {

	public TileEntityHoneyComb() {
		super(27, "");
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public String getInventoryName() {
		return "Honey Comb X:" + xCoord + " Y:" + yCoord + " Z:" + zCoord;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getAABBPool().getAABB(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1);
	}
}