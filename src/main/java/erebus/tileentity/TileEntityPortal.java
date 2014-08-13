package erebus.tileentity;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import erebus.ModBlocks;
import erebus.block.BlockErebusPortal;

public class TileEntityPortal extends TileEntity
{
	public boolean renderBeam()
	{
		return worldObj.getBlock(xCoord, yCoord + 1, zCoord) == ModBlocks.portal;
	}

	@Override
	public void updateEntity()
	{
		BlockErebusPortal p;
		Block b = worldObj.getBlock(xCoord, yCoord, zCoord);
		Block topBlock = worldObj.getBlock(xCoord, yCoord + 1, zCoord);
		if (topBlock instanceof BlockErebusPortal)
		{
			if (b instanceof BlockErebusPortal)
			{
				p = (BlockErebusPortal) b;
			} else
			{
				return;
			}
			if (!p.isPatternValid(worldObj, xCoord, yCoord, zCoord))
			{
				for (int l = 0; l < 80; ++l)
				{
					worldObj.spawnParticle("hugeexplosion", xCoord + Math.random(), yCoord + Math.random(), zCoord + Math.random(), 0.0D, 0.0D, 0.0D);
				}
				worldObj.setBlockToAir(xCoord, yCoord, zCoord);
				worldObj.setBlockToAir(xCoord, yCoord + 1, zCoord);
			}
		}
	}
}
