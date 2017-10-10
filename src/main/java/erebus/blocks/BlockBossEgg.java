package erebus.blocks;

import erebus.ModTabs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockBossEgg extends BlockSimple {

	public BlockBossEgg() {
		super(Material.ROCK, "pickaxe", 0, SoundType.STONE);
		setHardness(2.0F);
		setCreativeTab(ModTabs.BLOCKS);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}
