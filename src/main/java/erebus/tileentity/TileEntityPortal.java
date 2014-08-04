package erebus.tileentity;

import erebus.ModBlocks;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPortal extends TileEntity
{
    //This is a very nice class, I took me ages to write this super advanced class

    public boolean renderBeam()
    {
        return worldObj.getBlock(xCoord, yCoord + 1, zCoord) == ModBlocks.portalErebus;
    }
}
