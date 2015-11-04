package erebus.world.feature.tree;

import erebus.lib.EnumWood;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class WorldGenTreeBase extends WorldGenerator {

	protected Block log;
	protected Block leaves;

	public WorldGenTreeBase(EnumWood wood) {
		log = wood.getLog();
		leaves = wood.getLeaves();
	}
}