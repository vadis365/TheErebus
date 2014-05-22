package erebus.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockStairsErebus extends BlockStairs {

	public BlockStairsErebus(Block block, int meta) {
		super(block, meta);
		setHardness(2.0F);
		setLightOpacity(0);
	}

	public BlockStairsErebus(Block block) {
		this(block, 0);
	}
}