package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenVinesErebus extends WorldGenerator {
	int maxVineHeight;
	int variation;

	public WorldGenVinesErebus(int height, int heightVariation) {
		maxVineHeight = height;
		variation = heightVariation;
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {
		int xx = x;

		for (int zz = z; y < maxVineHeight + rand.nextInt(variation) - rand.nextInt(variation); ++y) {
			if (world.isAirBlock(x, y, z)) {
				for (int j1 = 2; j1 <= 5; ++j1) {
					if (Blocks.vine.canPlaceBlockOnSide(world, x, y, z, j1)) {
						world.setBlock(x, y, z, Blocks.vine, 1 << Direction.facingToDirection[Facing.oppositeSide[j1]], 2);
						break;
					}
				}
			} else {
				x = xx + rand.nextInt(4) - rand.nextInt(4);
				z = zz + rand.nextInt(4) - rand.nextInt(4);
			}
		}

		return true;
	}
}