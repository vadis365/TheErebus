package erebus.block;

import erebus.tileentity.TileEntityOfferingAltar;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockOfferingAltar extends BlockContainer
{
    public BlockOfferingAltar()
    {
        super(Material.rock);
        setBlockName("offeringAltar");
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public int getRenderType()
    {
        return -1;
    }

    public TileEntity createNewTileEntity(World w, int m)
    {
        return new TileEntityOfferingAltar();
    }
}
