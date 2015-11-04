package erebus.world.feature.decoration;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenQuickSand extends WorldGenerator { // TODO tweak?

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		while (world.isAirBlock(x, y, z) && y > 2)
			--y;

		if (world.getBlock(x, y, z) != Blocks.grass)
			return false;

		world.setBlock(x, y, z, ModBlocks.quickSand);
		world.setBlock(x, y - 1, z, ModBlocks.quickSand);
		world.setBlock(x, y - 2, z, ModBlocks.quickSand);

		world.setBlock(x, y, z - 1, ModBlocks.quickSand);
		world.setBlock(x, y - 1, z - 1, ModBlocks.quickSand);

		world.setBlock(x, y, z + 1, ModBlocks.quickSand);
		world.setBlock(x, y - 1, z + 1, ModBlocks.quickSand);

		world.setBlock(x - 1, y, z, ModBlocks.quickSand);
		world.setBlock(x - 1, y - 1, z, ModBlocks.quickSand);

		world.setBlock(x + 1, y, z, ModBlocks.quickSand);
		world.setBlock(x + 1, y - 1, z, ModBlocks.quickSand);

		world.setBlock(x - 2, y, z, ModBlocks.quickSand);
		world.setBlock(x - 1, y, z - 1, ModBlocks.quickSand);

		world.setBlock(x + 2, y, z, ModBlocks.quickSand);
		world.setBlock(x + 1, y, z + 1, ModBlocks.quickSand);

		world.setBlock(x, y, z - 2, ModBlocks.quickSand);
		world.setBlock(x - 1, y, z + 1, ModBlocks.quickSand);

		world.setBlock(x, y, z + 2, ModBlocks.quickSand);
		world.setBlock(x + 1, y, z - 1, ModBlocks.quickSand);

		// Top Layer
		if (rand.nextBoolean())
			world.setBlock(x + 1, y - 2, z + 0, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x + 0, y - 2, z + 1, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x - 0, y - 2, z - 1, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x - 1, y - 2, z + 0, ModBlocks.quickSand);

		// Middle Layer
		if (rand.nextBoolean())
			world.setBlock(x + 2, y - 1, z + 0, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x + 1, y - 1, z + 1, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x - 0, y - 1, z + 2, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x - 1, y - 1, z + 1, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x - 2, y - 1, z - 0, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x + 1, y - 1, z - 1, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x - 0, y - 1, z - 2, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x - 1, y - 1, z - 1, ModBlocks.quickSand);

		// Bottom Layer
		if (rand.nextBoolean())
			world.setBlock(x + 3, y, z + 0, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x + 2, y, z + 1, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x + 1, y, z + 2, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x - 3, y, z + 0, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x - 2, y, z + 1, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x - 1, y, z + 2, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x + 0, y, z - 3, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x + 2, y, z - 1, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x + 1, y, z - 2, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x - 0, y, z + 3, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x - 2, y, z - 1, ModBlocks.quickSand);

		if (rand.nextBoolean())
			world.setBlock(x - 1, y, z - 2, ModBlocks.quickSand);

		return true;
	}
}