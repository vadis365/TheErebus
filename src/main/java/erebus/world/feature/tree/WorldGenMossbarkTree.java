package erebus.world.feature.tree;

import java.util.Random;

import erebus.lib.EnumWood;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenMossbarkTree extends WorldGenTreeBase {

	private static final int[] offsetX = new int[] { -1, 1, 0, 0 };
	private static final int[] offsetZ = new int[] { 0, 0, -1, 1 };

	public WorldGenMossbarkTree() {
		super(EnumWood.Mossbark);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int[] middleBranchHeights = new int[4];
		int[] outsideBranchHeights = new int[4];

		int stumpHeight = 4 + rand.nextInt(3), maxBranchHeight = 0;
		for (int a = 0; a < 4; a++) {
			middleBranchHeights[a] = 1 + rand.nextInt(2);
			outsideBranchHeights[a] = middleBranchHeights[a] + rand.nextInt(4);

			if (middleBranchHeights[a] + outsideBranchHeights[a] > maxBranchHeight)
				maxBranchHeight = middleBranchHeights[a] + outsideBranchHeights[a];
		}

		// check for space

		int maxHeight = stumpHeight + maxBranchHeight + 2;

		if (y <= 0 || y + maxHeight > 255)
			return false;
		int testY;

		for (testY = y + 1; testY < y + 3; ++testY)
			if (!world.isAirBlock(x, testY, z))
				return false;

		for (testY = y + 3; testY <= y + 1 + maxHeight; ++testY) {
			for (int testX = x - 4; testX <= x + 4; ++testX)
				for (int testZ = z - 1; testZ <= z + 1; ++testZ)
					if (!world.isAirBlock(testX, testY, testZ))
						return false;

			for (int testX = x - 1; testX <= x + 1; ++testX)
				for (int testZ = z - 4; testZ <= z + 4; ++testZ)
					if (!world.isAirBlock(testX, testY, testZ))
						return false;
		}

		if (world.getBlock(x, y - 1, z) != Blocks.dirt && world.getBlock(x, y - 1, z) != Blocks.grass)
			return false;

		// generate tree

		generateStump(world, rand, x, y, z, stumpHeight);
		y += stumpHeight;
		for (int a = 0; a < 4; a++)
			generateMiddleBranch(world, rand, x + offsetX[a] * 2, y, z + offsetZ[a] * 2, middleBranchHeights[a]);
		for (int a = 0; a < 4; a++)
			generateOutsideBranch(world, rand, x + offsetX[a] * 3, y + middleBranchHeights[a], z + offsetZ[a] * 3, outsideBranchHeights[a]);

		return true;
	}

	public void generateStump(World world, Random random, int x, int y, int z, int height) {
		for (int yy = 0; yy < height; ++yy) {
			world.setBlock(x, y + yy, z, log, 0, 3);

			if (yy == height - 1) {
				world.setBlock(x, y + yy, z + 1, log, 8, 3);
				world.setBlock(x, y + yy, z - 1, log, 8, 3);
				world.setBlock(x + 1, y + yy, z, log, 4, 3);
				world.setBlock(x - 1, y + yy, z, log, 4, 3);
				break;
			}
		}
	}

	public void generateMiddleBranch(World world, Random rand, int x, int y, int z, int height) {
		for (int yy = 0; yy < 2; ++yy)
			world.setBlock(x, y - 1 - yy, z, leaves, 0, 3);
		for (int yy = 0; yy < height; ++yy)
			world.setBlock(x, y + yy, z, log, 0, 3);
	}

	public void generateOutsideBranch(World world, Random rand, int x, int y, int z, int height) {
		for (int yy = 0; yy < 2; ++yy) {
			world.setBlock(x, y - 1 - yy, z, leaves, 0, 3);
			world.setBlock(x, y + height + yy, z, leaves, 0, 3);
		}

		for (int yy = 0; yy < height; ++yy) {
			if (yy < height - 1)
				world.setBlock(x, y + yy, z, log, 0, 3);

			for (int a = 0; a < 4; a++)
				world.setBlock(x + offsetX[a], y + yy, z + offsetZ[a], leaves, 0, 3);
		}
	}
}