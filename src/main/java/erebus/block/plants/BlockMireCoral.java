package erebus.block.plants;

import erebus.block.BlockSimple;
import net.minecraft.block.material.Material;

public class BlockMireCoral extends BlockSimple {

	public BlockMireCoral() {
		super(Material.plants);
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}
}
