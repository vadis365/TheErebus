package erebus.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.block.ErebusPortal;

public class TileEntityGaeanKeystone extends TileEntity {
	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;

		if (worldObj.getTotalWorldTime() % 20L == 0L)
			if (!ErebusPortal.isPatternValid(worldObj, xCoord, yCoord - 2, zCoord) || !hasPortals()) {
				if (worldObj.getBlock(xCoord, yCoord - 1, zCoord) == ModBlocks.portal)
					worldObj.setBlockToAir(xCoord, yCoord - 1, zCoord);
				if (worldObj.getBlock(xCoord, yCoord - 2, zCoord) == ModBlocks.portal)
					worldObj.setBlockToAir(xCoord, yCoord - 2, zCoord);
				if (getBlockMetadata() != 0) {
					worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
					markDirty();
				}
			}
	}

	private boolean hasPortals() {
		return worldObj.getBlock(xCoord, yCoord - 1, zCoord) == ModBlocks.portal && worldObj.getBlock(xCoord, yCoord - 2, zCoord) == ModBlocks.portal;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return Double.MAX_VALUE;
	}
}