package erebus.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import erebus.ModTabs;
import erebus.lib.Reference;

public class BlockStairsBase extends BlockStairs
{

	public BlockStairsBase(Block block, int meta)
	{
		super(block, meta);
		setHardness(2.0F);
		setLightOpacity(0);
		setCreativeTab(ModTabs.blocks);
		setBlockName(Reference.MOD_ID + ".stairsBase" + meta);
	}
}