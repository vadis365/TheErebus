package erebus.world.feature.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.lib.EnumWood;

public abstract class WorldGenTreeBase extends WorldGenerator {

	protected Block log;
	protected Block leaves;
	protected final EnumWood wood;

	public WorldGenTreeBase(EnumWood wood) {
		this.wood = wood;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (log == null)
			log = wood.getLog();
		if (leaves == null)
			leaves = wood.getleaves();
		return false;
	}
}