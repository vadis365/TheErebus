package erebus.world.feature.tree;

import java.util.Random;

import erebus.lib.EnumWood;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

public class WorldGenCypressTree extends WorldGenTreeBase {

	public WorldGenCypressTree() {
		super(EnumWood.Cypress);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		float treeRand = rand.nextFloat();

		if (treeRand >= 0.5F)
			return generateMediumTree(world, rand, x, y, z);
		else if (treeRand >= 0.3F)
			return generateLargeTree(world, rand, x, y, z);
		else
			return generateSmallTree(world, rand, x, y, z);
	}

	private boolean checkEmptyArea(World world, int x, int y, int z, int totalHeight, int rad) {
		if (world.getBlock(x, y, z).getMaterial() != Material.air)
			return false;

		for (int xx = x - rad; xx <= x + rad; xx++)
			for (int zz = z - rad; zz <= z + rad; zz++)
				for (int yy = y + 1; yy <= y + totalHeight; yy++)
					if (world.getBlock(xx, yy, zz).getMaterial() != Material.air)
						return false;

		return true;
	}

	/*
	 * SMALL
	 */

	private boolean generateSmallTree(World world, Random rand, int x, int y, int z) {
		int trunkH = rand.nextInt(4) + 5;
		int nakedTrunkH = rand.nextInt(2) + 1;
		int leafH = trunkH - nakedTrunkH;

		if (!checkEmptyArea(world, x, y, z, trunkH + 2, 2))
			return false;

		for (int yy = y; yy <= y + trunkH; yy++)
			world.setBlock(x, yy, z, log, 0, 2);
		for (int yy = y + trunkH + 1; yy <= y + trunkH + 2; yy++)
			world.setBlock(x, yy, z, leaves, 0, 2);

		for (int a = 0; a < 4; a++) {
			for (int yy = y + nakedTrunkH; yy <= y + trunkH; yy++)
				world.setBlock(x + Direction.offsetX[a], yy, z + Direction.offsetZ[a], leaves, 0, 2);

			if (leafH - 4 > 1)
				for (int yy = y + nakedTrunkH + 2; yy <= y + trunkH - 2; yy++) {
					if ((yy == y + nakedTrunkH + 2 || yy == y + trunkH - 2) && leafH - 4 > 3 && rand.nextInt(11) == 0)
						continue;
					world.setBlock(x + Direction.offsetX[a] * 2, yy, z + Direction.offsetZ[a] * 2, leaves, 0, 2);
				}
		}

		for (int a = 0; a < 2; a++)
			for (int b = 0; b < 2; b++)
				for (int yy = y + nakedTrunkH + 1; yy <= y + trunkH - 1; yy++) {
					if ((yy == y + nakedTrunkH + 1 || yy == y + trunkH - 1) && leafH - 2 > 3 && rand.nextInt(8) == 0)
						continue;
					world.setBlock(x - 1 + 2 * a, yy, z - 1 + 2 * b, leaves, 0, 2);
				}

		return true;
	}

	/*
	 * MEDIUM
	 */

	private boolean generateMediumTree(World world, Random rand, int x, int y, int z) {
		int trunkH = rand.nextInt(5) + 8;
		int nakedTrunkH = rand.nextInt(3) + 1;
		int leafH = trunkH - nakedTrunkH;

		if (!checkEmptyArea(world, x, y, z, trunkH + 3, 3))
			return false;

		for (int yy = y; yy <= y + trunkH; yy++)
			world.setBlock(x, yy, z, log, 0, 2);
		for (int yy = y + trunkH + 1; yy <= y + trunkH + 3; yy++)
			world.setBlock(x, yy, z, leaves, 0, 2);

		for (int a = 0; a < 4; a++) {
			for (int yy = y + nakedTrunkH; yy <= y + trunkH + 1; yy++)
				world.setBlock(x + Direction.offsetX[a], yy, z + Direction.offsetZ[a], leaves, 0, 2);
			for (int yy = y + nakedTrunkH + 1; yy <= y + trunkH - 2; yy++)
				world.setBlock(x + Direction.offsetX[a] * 2, yy, z + Direction.offsetZ[a] * 2, leaves, 0, 2);

			if (leafH - 7 > 1)
				for (int yy = y + nakedTrunkH + 3; yy <= y + trunkH - 4; yy++) {
					if ((yy == y + nakedTrunkH + 3 || yy == y + trunkH - 4) && leafH - 7 > 3 && rand.nextInt(10) == 0)
						continue;
					world.setBlock(x + Direction.offsetX[a] * 3, yy, z + Direction.offsetZ[a] * 3, leaves, 0, 2);
				}
		}

		for (int a = 0; a < 2; a++)
			for (int b = 0; b < 2; b++) {
				for (int yy = y + nakedTrunkH + 1; yy <= y + trunkH - 1; yy++) {
					if ((yy == y + nakedTrunkH + 1 || yy == y + trunkH - 1) && rand.nextInt(14) == 0)
						continue;
					world.setBlock(x - 1 + 2 * a, yy, z + -1 + 2 * b, leaves, 0, 2);
				}

				for (int yy = y + nakedTrunkH + 2; yy <= y + trunkH - 3; yy++) {
					boolean canSkip = yy == y + nakedTrunkH + 2 || yy == y + trunkH - 3;
					if (!canSkip || rand.nextInt(10) != 0)
						world.setBlock(x - 2 + 4 * a, yy, z + -1 + 2 * b, leaves, 0, 2);
					if (!canSkip || rand.nextInt(10) != 0)
						world.setBlock(x - 1 + 2 * a, yy, z + -2 + 4 * b, leaves, 0, 2);
				}
			}

		return true;
	}

	/*
	 * LARGE
	 */

	private boolean generateLargeTree(World world, Random rand, int x, int y, int z) {
		int trunkH = rand.nextInt(6) + 12;
		int nakedTrunkH = rand.nextInt(3) + 2;
		int leafH = trunkH - nakedTrunkH;

		if (!checkEmptyArea(world, x, y, z, trunkH + 4, 3))
			return false;

		for (int yy = y; yy <= y + trunkH; yy++) {
			world.setBlock(x, yy, z, log, 0, 2);

			if (yy <= y + trunkH - 2)
				for (int a = 0; a < 4; a++) {
					boolean genLog = yy >= y + trunkH - 3 && rand.nextBoolean() || yy < y + trunkH - 3;
					world.setBlock(x + Direction.offsetX[a], yy, z + Direction.offsetZ[a], genLog ? log : leaves, genLog ? 0 : 0, 2);
				}
		}

		for (int yy = y + trunkH + 1; yy <= y + trunkH + 4; yy++)
			world.setBlock(x, yy, z, leaves, 0, 2);

		for (int a = 0; a < 4; a++) {
			for (int yy = y + trunkH - 1; yy <= y + trunkH + 2; yy++) {
				if (yy == y + trunkH + 2 && rand.nextInt(5) == 0)
					continue;
				world.setBlock(x + Direction.offsetX[a], yy, z + Direction.offsetZ[a], leaves, 0, 2);
			}

			for (int yy = y + nakedTrunkH; yy <= y + trunkH; yy++) {
				if (yy == y + trunkH && rand.nextInt(6) == 0)
					continue;
				world.setBlock(x + Direction.offsetX[a] * 2, yy, z + Direction.offsetZ[a] * 2, leaves, 0, 2);
			}

			for (int yy = y + nakedTrunkH + 2; yy <= y + trunkH - 3; yy++)
				world.setBlock(x + Direction.offsetX[a] * 3, yy, z + Direction.offsetZ[a] * 3, leaves, 0, 2);
		}

		for (int a = 0; a < 2; a++)
			for (int b = 0; b < 2; b++) {
				for (int yy = y + nakedTrunkH; yy <= y + trunkH + 1; yy++)
					world.setBlock(x - 1 + 2 * a, yy, z - 1 + 2 * b, leaves, 0, 2);

				for (int yy = y + nakedTrunkH + 1; yy <= y + trunkH - 2; yy++) {
					boolean canSkip = yy == y + nakedTrunkH + 1 || yy == y + trunkH - 2;
					if (!canSkip || rand.nextInt(10) != 0)
						world.setBlock(x - 2 + 4 * a, yy, z - 1 + 2 * b, leaves, 0, 2);
					if (!canSkip || rand.nextInt(10) != 0)
						world.setBlock(x - 1 + 2 * a, yy, z - 2 + 4 * b, leaves, 0, 2);
				}

				for (int yy = y + nakedTrunkH + 3; yy <= y + trunkH - 3; yy++) {
					if (yy == y + trunkH - 3 && rand.nextInt(7) == 0)
						continue;
					world.setBlock(x - 2 + 4 * a, yy, z - 2 + 4 * b, leaves, 0, 2);
				}

				if (leafH - 9 > 1)
					for (int yy = y + nakedTrunkH + 4; yy <= y + trunkH - 5; yy++) {
						boolean canSkip = (yy == y + nakedTrunkH + 4 || yy == y + trunkH - 5) && leafH - 9 > 2;
						if (!canSkip || rand.nextInt(12) != 0)
							world.setBlock(x - 3 + 6 * a, yy, z - 1 + 2 * b, leaves, 0, 2);
						if (!canSkip || rand.nextInt(12) != 0)
							world.setBlock(x - 1 + 2 * a, yy, z - 3 + 6 * b, leaves, 0, 2);
					}
			}

		return true;
	}
}