package erebus.block;

import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockStairsBase extends BlockStairs {

	public BlockStairsBase(Block block, int meta) {
		super(block, meta);
		setHardness(2.0F);
		setLightOpacity(0);
		setCreativeTab(ModTabs.blocks);
	}
}