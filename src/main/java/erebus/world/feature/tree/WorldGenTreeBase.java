package erebus.world.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.blocks.EnumWood;

public abstract class WorldGenTreeBase extends WorldGenerator {

	protected Block log;
	protected Block leaves;

	public WorldGenTreeBase(EnumWood wood) {
		log = wood.getLog();
		leaves = wood.getLeaves();
	}
}