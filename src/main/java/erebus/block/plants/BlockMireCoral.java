package erebus.block.plants;

import net.minecraft.block.material.Material;
import erebus.block.BlockSimple;

public class BlockMireCoral extends BlockSimple {

	public BlockMireCoral() {
		super(Material.plants);
	}
	
	@Override
    public int getRenderType()
    {
        return 1;
    }
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
}
