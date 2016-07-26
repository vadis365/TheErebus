package erebus.world.feature.decoration;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenQuickSand extends WorldGenerator { // TODO tweak?

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int y = 0;
		while (world.isAirBlock(pos.add(0, y, 0)) && y > 2)
			--y;

		if (world.getBlockState(pos).getBlock() != Blocks.GRASS)
			return false;

		world.setBlockState(pos, ModBlocks.QUICK_SAND.getDefaultState());
		world.setBlockState(pos.add(0,  y - 1, 0), ModBlocks.QUICK_SAND.getDefaultState());
		world.setBlockState(pos.add(0, y - 2, 0), ModBlocks.QUICK_SAND.getDefaultState());

		world.setBlockState(pos.add(0, y, - 1), ModBlocks.QUICK_SAND.getDefaultState());
		world.setBlockState(pos.add(0, y - 1, - 1), ModBlocks.QUICK_SAND.getDefaultState());

		world.setBlockState(pos.add(0, y, 1), ModBlocks.QUICK_SAND.getDefaultState());
		world.setBlockState(pos.add(0, y - 1, 1), ModBlocks.QUICK_SAND.getDefaultState());

		world.setBlockState(pos.add(- 1, y, 0), ModBlocks.QUICK_SAND.getDefaultState());
		world.setBlockState(pos.add(- 1, y - 1, 0), ModBlocks.QUICK_SAND.getDefaultState());

		world.setBlockState(pos.add(1, y, 0), ModBlocks.QUICK_SAND.getDefaultState());
		world.setBlockState(pos.add(1, y - 1, 0), ModBlocks.QUICK_SAND.getDefaultState());

		world.setBlockState(pos.add(- 2, y, 0), ModBlocks.QUICK_SAND.getDefaultState());
		world.setBlockState(pos.add(- 1, y, - 1), ModBlocks.QUICK_SAND.getDefaultState());

		world.setBlockState(pos.add(2, y, 0), ModBlocks.QUICK_SAND.getDefaultState());
		world.setBlockState(pos.add(1, y, 1), ModBlocks.QUICK_SAND.getDefaultState());

		world.setBlockState(pos.add(0, y, - 2), ModBlocks.QUICK_SAND.getDefaultState());
		world.setBlockState(pos.add(- 1, y, 1), ModBlocks.QUICK_SAND.getDefaultState());

		world.setBlockState(pos.add(0, y, 2), ModBlocks.QUICK_SAND.getDefaultState());
		world.setBlockState(pos.add(1, y, - 1), ModBlocks.QUICK_SAND.getDefaultState());

		// Top Layer
		if (rand.nextBoolean())
			world.setBlockState(pos.add(1, y - 2, 0), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(0, y - 2, 1), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(0, y - 2, - 1), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(- 1, y - 2, 0), ModBlocks.QUICK_SAND.getDefaultState());

		// Middle Layer
		if (rand.nextBoolean())
			world.setBlockState(pos.add(2, y - 1, 0), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(1, y - 1, 1), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(0, y - 1, 2), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(- 1, y - 1, 1), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(- 2, y - 1, 0), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(1, y - 1, - 1), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(0, y - 1, - 2), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(- 1, y - 1, - 1), ModBlocks.QUICK_SAND.getDefaultState());

		// Bottom Layer
		if (rand.nextBoolean())
			world.setBlockState(pos.add(3, y, 0), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(2, y, 1), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(1, y, 2), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(- 3, y, 0), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(- 2, y, 1), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(- 1, y, 2), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(0, y, - 3), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(2, y, - 1), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(1, y, - 2), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(0, y, 3), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(- 2, y, - 1), ModBlocks.QUICK_SAND.getDefaultState());

		if (rand.nextBoolean())
			world.setBlockState(pos.add(- 1, y, - 2), ModBlocks.QUICK_SAND.getDefaultState());

		return true;
	}
}