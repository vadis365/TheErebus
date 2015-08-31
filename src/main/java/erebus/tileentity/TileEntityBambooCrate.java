package erebus.tileentity;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityBambooCrate extends TileEntityBasicInventory {

	public TileEntityBambooCrate() {
		super(27, "container.bambooCrate");
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord, zCoord - 1, xCoord + 2, yCoord + 2, zCoord + 2);
	}
}