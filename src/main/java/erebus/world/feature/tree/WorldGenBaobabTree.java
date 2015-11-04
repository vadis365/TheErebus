package erebus.world.feature.tree;

import java.util.Random;

import erebus.lib.EnumWood;
import net.minecraft.world.World;

public class WorldGenBaobabTree extends WorldGenTreeBase {

	public WorldGenBaobabTree() {
		super(EnumWood.Baobab);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int radius = rand.nextInt(2) + 3;
		int height = rand.nextInt(radius) + 12;
		int maxHeight = height + 2;
		int maxRadius = radius + 2;

		for (int xx = x - maxRadius; xx <= x + maxRadius; xx++)
			for (int zz = z - maxRadius; zz <= z + maxRadius; zz++)
				for (int yy = y + 1; yy < y + maxHeight; yy++)
					if (!world.isAirBlock(xx, yy, zz))
						return false;

		for (int yy = y; yy < y + height; ++yy) {
			if (yy % 5 == 0 && radius != 1)
				--radius;

			for (int i = radius * -1; i <= radius; ++i)
				for (int j = radius * -1; j <= radius; ++j) {
					double dSq = i * i + j * j;
					if (Math.round(Math.sqrt(dSq)) <= radius && yy <= y + height - 2)
						world.setBlock(x + i, yy, z + j, log, 0, 2);
				}

			if (yy == y + height - 2) {
				createBranch(world, rand, x + radius + 1, yy - rand.nextInt(3), z, 1);
				createBranch(world, rand, x - radius - 1, yy - rand.nextInt(3), z, 2);
				createBranch(world, rand, x, yy - rand.nextInt(3), z + radius + 1, 3);
				createBranch(world, rand, x, yy - rand.nextInt(3), z - radius - 1, 4);
			}
		}

		return true;
	}

	private void createBranch(World world, Random rand, int x, int y, int z, int dir) {
		int branchLength = rand.nextInt(2) + 2;
		int meta = dir;
		for (int i = 0; i <= branchLength; ++i) {

			if (i >= 2) {
				y++;
				meta = 0;
			}

			if (dir == 1) {
				world.setBlock(x + i, y, z, log, meta == 0 ? 0 : 4, 2);
				if (i == branchLength)
					createLeaves(world, rand, x + i, y + 1, z, branchLength);
			}

			if (dir == 2) {
				world.setBlock(x - i, y, z, log, meta == 0 ? 0 : 4, 2);
				if (i == branchLength)
					createLeaves(world, rand, x - i, y + 1, z, branchLength);
			}

			if (dir == 3) {
				world.setBlock(x, y, z + i, log, meta == 0 ? 0 : 8, 2);
				if (i == branchLength)
					createLeaves(world, rand, x, y + 1, z + i, branchLength);
			}

			if (dir == 4) {
				world.setBlock(x, y, z - i, log, meta == 0 ? 0 : 8, 2);
				if (i == branchLength)
					createLeaves(world, rand, x, y + 1, z - i, branchLength);
			}
		}
	}

	public void createLeaves(World world, Random rand, int x, int y, int z, int radius) {
		int height = 2;
		for (int xx = x - radius; xx <= x + radius; xx++)
			for (int zz = z - radius; zz <= z + radius; zz++)
				for (int yy = y; yy < y + height; yy++) {
					double dSq = Math.pow(xx - x, 2.0D) + Math.pow(zz - z, 2.0D) + Math.pow(yy - y, 2.0D);
					if (Math.round(Math.sqrt(dSq)) <= radius)
						if (Math.round(Math.sqrt(dSq)) == 0)
							world.setBlock(xx, yy, zz, log, 0, 2);
						else
							world.setBlock(xx, yy, zz, leaves);
				}
	}
}