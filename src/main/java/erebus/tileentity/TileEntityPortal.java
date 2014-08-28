package erebus.tileentity;

import net.minecraft.tileentity.TileEntity;
import erebus.ModBlocks;

public class TileEntityPortal extends TileEntity
{
	public boolean renderBeam()
	{
		return worldObj.getBlock(xCoord, yCoord + 1, zCoord) == ModBlocks.portal;
	}

	@Override
	public void updateEntity()
	{
		if (worldObj.isRemote)
		{
			return;
		}

		if (worldObj.getTotalWorldTime() % 20L == 0L)
		{
			if (!ModBlocks.portal.isPatternValid(worldObj, xCoord, yCoord, zCoord))
			{
				worldObj.setBlockToAir(xCoord, yCoord, zCoord);
				worldObj.setBlockToAir(xCoord, yCoord + 1, zCoord);
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord + 2, zCoord, 0, 2);
			}
		}
	}
}
