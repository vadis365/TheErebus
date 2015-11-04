package erebus.world.feature.tree;

import java.util.Random;

import erebus.lib.EnumWood;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenAsperTree extends WorldGenTreeBase {

	private static final int[] offsetX = new int[] { -1, 1, 0, 0 };
	private static final int[] offsetZ = new int[] { 0, 0, -1, 1 };

	public WorldGenAsperTree() {
		super(EnumWood.Asper);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int height = rand.nextInt(2) + 4; // top leaves don't count

		// check for space
		if (y <= 0 || y + height > 255)
			return false;
		int testY;

		for (testY = y + 1; testY <= y + height + 1; ++testY)
			for (int testX = x - 2; testX <= x + 2; ++testX)
				for (int testZ = z - 2; testZ <= z + 2; ++testZ)
					if (!world.isAirBlock(testX, testY, testZ))
						return false;

		if (world.getBlock(x, y - 1, z) != Blocks.dirt && world.getBlock(x, y - 1, z) != Blocks.grass)
			return false;

		// generate tree
		for (int yy = 0; yy < height; yy++) {
			world.setBlock(x, y + yy, z, log, 0, 3);

			if (yy == height - 1)
				continue;

			for (int extraWood = 0, extraWoodAttempt = 0; extraWoodAttempt < 5 && extraWood < 3; extraWoodAttempt++) {
				int dir = rand.nextInt(4);

				if (yy > 0 && !world.isAirBlock(x + offsetX[dir], y + yy - 1, z + offsetZ[dir]))
					continue;

				world.setBlock(x + offsetX[dir], y + yy, z + offsetZ[dir], log, dir < 2 ? 4 : 8, 3);
				if (yy > 0 && rand.nextBoolean())
					world.setBlock(x + offsetX[dir] * 2, y + yy, z + offsetZ[dir] * 2, leaves, 0, 3);
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
					if ((dist <= 1.5D || rand.nextDouble() > dist - 1.5D) && world.isAirBlock(x + xx, y + yy, z + zz))
						world.setBlock(x + xx, y + yy, z + zz, leaves, 0, 3);
				}

		for (int a = 0; a < 4; a++)
			world.setBlock(x + offsetX[a], y + height, z + offsetZ[a], leaves, 0, 3);
		world.setBlock(x, y + height, z, leaves, 0, 3);

		return true;
	}
}