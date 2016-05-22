package erebus.blocks;

import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class BlockStairsErebus extends BlockStairs {

	protected BlockStairsErebus(IBlockState modelState) {
		super(modelState);
		setLightOpacity(0);
		setCreativeTab(ModTabs.BLOCKS);
	}

	public static Block createWooden(IBlockState modelState) {
		Block block = new BlockStairsErebus(modelState);
		Blocks.FIRE.setFireInfo(block, 5, 5);
		block.setHarvestLevel("axe", 0);
		return block;
	}
}