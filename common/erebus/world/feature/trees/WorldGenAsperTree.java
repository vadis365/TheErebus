package erebus.world.feature.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.block.BlockLeavesErebus;
import erebus.block.BlockLogErebus;

public class WorldGenAsperTree extends WorldGenerator {

	private static final int[] offsetX = new int[] { -1, 1, 0, 0 };
	private static final int[] offsetZ = new int[] { 0, 0, -1, 1 };

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		int height = random.nextInt(2) + 4; // top leaves don't count

		// check for space

		if (y <= 0 || y + height > 255)
			return false;
		int testY;

		for (testY = y + 1; testY <= y + height + 1; ++testY)
			for (int testX = x - 2; testX <= x + 2; ++testX)
				for (int testZ = z - 2; testZ <= z + 2; ++testZ)
					if (!world.isAirBlock(testX, testY, testZ))
						return false;

		if (world.getBlockId(x, y - 1, z) != Block.dirt.blockID && world.getBlockId(x, y - 1, z) != Block.grass.blockID)
			return false;

		// generate tree

		for (int yy = 0; yy < height; yy++) {
			world.setBlock(x, y + yy, z, ModBlocks.logErebusGroup2.blockID, BlockLogErebus.dataAsper, 3);

			if (yy == height - 1)
				continue;

			for (int extraWood = 0, extraWoodAttempt = 0; extraWoodAttempt < 5 && extraWood < 3; extraWoodAttempt++) {
				int dir = random.nextInt(4);

				if (yy > 0 && !world.isAirBlock(x + offsetX[dir], y + yy - 1, z + offsetZ[dir]))
					continue;

				world.setBlock(x + offsetX[dir], y + yy, z + offsetZ[dir], ModBlocks.logErebusGroup2.blockID, BlockLogErebus.dataAsper + (dir < 2 ? 4 : 8), 3);
				if (yy > 0 && random.nextInt(2) == 0)
					world.setBlock(x + offsetX[dir] * 2, y + yy, z + offsetZ[dir] * 2, ModBlocks.leavesErebus.blockID, BlockLeavesErebus.dataAsperDecay, 3);
				++extraWood;
			}
		}

		double centerY = 2D + (height - 2D) * 0.5D;
		for (int yy = 1; yy < height; yy++)
			for (int xx = -1; xx <= 1; xx++)
				for (int zz = -1; zz <= 1; zz++) {
					if (xx == 0 && zz == 0)
						continue;

					double dist = Math.sqrt(xx * xx + Math.pow(centerY - yy, 2) + zz * zz);
					if ((dist <= 1.5D || random.nextDouble() > dist - 1.5D) && world.isAirBlock(x + xx, y + yy, z + zz))
						world.setBlock(x + xx, y + yy, z + zz, ModBlocks.leavesErebus.blockID, BlockLeavesErebus.dataAsperDecay, 3);
				}

		for (int a = 0; a < 4; a++)
			world.setBlock(x + offsetX[a], y + height, z + offsetZ[a], ModBlocks.leavesErebus.blockID, BlockLeavesErebus.dataAsperDecay, 3);
		world.setBlock(x, y + height, z, ModBlocks.leavesErebus.blockID, BlockLeavesErebus.dataAsperDecay, 3);

		return true;
	}
}
