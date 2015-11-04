package erebus.world.feature.decoration;

import java.util.Random;

import erebus.ModBlocks;
import erebus.world.feature.plant.WorldGenAlgae;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSwampWater extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int radius = rand.nextInt(8) + 8;
		int depth = rand.nextInt(3) + 3;
		for (int x1 = x - radius; x1 <= x + radius; x1++)
			for (int z1 = z - radius; z1 <= z + radius; z1++)
				for (int y1 = y; y1 > y - depth; y1--) {
					double dSq = Math.pow(x1 - x, 2.0D) + Math.pow(z1 - z, 2.0D) + Math.pow(y1 - y, 2.0D);
					if (Math.round(Math.sqrt(dSq)) < radius) {
						if (dSq >= Math.pow(radius - 2, 2.0D)) {
							if (world.getBlock(x1, y1, z1) != Blocks.water)
								world.setBlock(x1, y1, z1, ModBlocks.mud, 0, 2);
							else
								world.setBlock(x1, y1, z1, Blocks.water, 0, 3);
						} else if (dSq < Math.pow(radius - 3, 2.0D))
							world.setBlock(x1, y1, z1, Blocks.water, 0, 3);
						if (dSq <= Math.pow(radius, 2.0D) && dSq >= Math.pow(radius - 3, 2.0D))
							if (world.getBlock(x1, y1, z1) != Blocks.water)
								if (rand.nextInt(3) != 0 ? world.setBlock(x1, y, z1, Blocks.water, 0, 3) : world.setBlock(x1, y, z1, Blocks.grass, 0, 2))
									;

						if (dSq <= Math.pow(radius - 2, 2.0D)) {
							world.setBlock(x1, y - depth, z1, ModBlocks.mud, 0, 2);
							int length = rand.nextInt(13) + 20;
							for (int yy = y - depth - 1; yy > y - depth - 1 - length; --yy)
								if (world.getBlock(x, yy, z) == Blocks.air)
									world.setBlock(x1, yy, z1, Blocks.dirt, 0, 2);
								else
									break;
						}

					}
				}

		WorldGenAlgae genAlgae = new WorldGenAlgae();
		for (int attempt = 0; attempt < 5; attempt++)
			genAlgae.generate(world, rand, x + rand.nextInt(8) - rand.nextInt(8) + 8, y + 2 + rand.nextInt(6), z + rand.nextInt(8) - rand.nextInt(8) + 8);

		WorldGenWaterlily genLily = new WorldGenWaterlily();
		for (int attempt = 0; attempt < 20; attempt++)
			genLily.generate(world, rand, x + rand.nextInt(8) - rand.nextInt(8) + 8, y + 2 + rand.nextInt(6), z + rand.nextInt(8) - rand.nextInt(8) + 8);

		return true;
	}

}
