package erebus.tileentity;

import erebus.ModBlocks;
import erebus.block.BlockErebusPortal;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPortal extends TileEntity
{
    public boolean renderBeam()
    {
        return worldObj.getBlock(xCoord, yCoord + 1, zCoord) == ModBlocks.portalErebus;
    }

    public void updateEntity()
    {
        BlockErebusPortal p = (BlockErebusPortal) worldObj.getBlock(xCoord, yCoord, zCoord);
        Block topBlock = worldObj.getBlock(xCoord, yCoord + 1, zCoord);
        if (topBlock instanceof BlockErebusPortal)
        {
            if (!p.isPatternValid(worldObj, xCoord, yCoord, zCoord))
            {
                //todo stuff
                worldObj.setBlockToAir(xCoord, yCoord, zCoord);
                worldObj.setBlockToAir(xCoord, yCoord + 1, zCoord);
            }
        }
    }
}
