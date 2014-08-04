package erebus.block.altars;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGaeanKeystone extends Block
{
    public BlockGaeanKeystone()
    {
        super(Material.rock);
        setBlockName("gaeanKeystone");
        setBlockTextureName("erebus:gaeanKeystone");
        setHardness(3.0f);
    }
}
