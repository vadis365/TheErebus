package erebus.world.feature.tree;

import erebus.blocks.BlockLeavesErebus;
import erebus.blocks.EnumWood;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class WorldGenTreeBase extends WorldGenerator {

	protected Block log;
	protected IBlockState leaves;

	public WorldGenTreeBase(EnumWood wood) {
		log = wood.getLog();
		leaves = wood.getLeaves().getDefaultState().withProperty(BlockLeavesErebus.CHECK_DECAY, false);
	}
}