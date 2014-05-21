package erebus.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockStairsErebus extends BlockStairs {

	public BlockStairsErebus(int id, Block block, int meta) {
		super(id, block, meta);
		setHardness(2.0F);
		setLightOpacity(0);
	}

	public BlockStairsErebus(int id, Block block) {
		this(id, block, 0);
	}
}