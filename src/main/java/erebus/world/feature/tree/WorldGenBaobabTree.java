package erebus.world.feature.tree;

import java.util.Random;

import net.minecraft.world.World;
import erebus.lib.EnumWood;

public class WorldGenBaobabTree extends WorldGenTreeBase {

	public WorldGenBaobabTree() {
		super(EnumWood.Baobab);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int radius = rand.nextInt(2) + 3;
		int height = rand.nextInt(radius) + 12;
		int leafRadius = radius;
		int maxHeight = height + 2;
		int maxRadius = radius + 2;
		
		for (int xx = x - maxRadius; xx <= x + maxRadius; xx++)
			for (int zz = z - maxRadius; zz <= z + maxRadius; zz++)
				for (int yy = y + 1; yy < y + maxHeight; yy++) {
					if (!world.isAirBlock(xx, yy, zz))
						return false;
				}

		for (int yy = y; yy < y + height; ++yy) {
			if (yy % 5 == 0 && radius != 1)
				--radius;

			for (int i = radius * -1; i <= radius; ++i)
				for (int j = radius * -1; j <= radius; ++j) {
					double dSq = (i * i) + (j * j);
					if (Math.round(Math.sqrt(dSq)) <= radius)
						world.setBlock(x + i, yy, z + j, log);
				}

			if (yy == y + height - 1) {
				createLeaves(world, rand, x + rand.nextInt(5) - 2, yy + 2, z + rand.nextInt(5) - 2, leafRadius - 1);
				createLeaves(world, rand, x + rand.nextInt(5) - 2, yy + 1, z + rand.nextInt(5) - 2, leafRadius);
				createLeaves(world, rand, x + rand.nextInt(5) - 2, yy, z + rand.nextInt(5) - 2, leafRadius);
			}
		}

		return true;
	}

	public void createLeaves(World world, Random rand, int x, int y, int z, int radius) {
		int height = 1;
		for (int xx = x - radius; xx <= x + radius; xx++)
			for (int zz = z - radius; zz <= z + radius; zz++)
				for (int yy = y - height; yy < y + height; yy++) {
					double dSq = Math.pow(xx - x, 2.0D) + Math.pow(zz - z, 2.0D) + Math.pow(yy - y, 2.0D);
					if (Math.round(Math.sqrt(dSq)) <= radius)
						if (rand.nextInt(4) == 0 && Math.round(Math.sqrt(dSq)) < radius)
							world.setBlock(xx, yy, zz, log);
						else
							world.setBlock(xx, yy, zz, leaves);
				}
	}
}