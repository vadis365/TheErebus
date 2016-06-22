package erebus.world.feature.decoration;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

public class WorldGenSwampWater extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		int radius = rand.nextInt(8) + 8;
		int depth = rand.nextInt(3) + 3;
		for (int x1 = x - radius; x1 <= x + radius; x1++)
			for (int z1 = z - radius; z1 <= z + radius; z1++)
				for (int y1 = y; y1 > y - depth; y1--) {
					double dSq = Math.pow(x1 - x, 2.0D) + Math.pow(z1 - z, 2.0D) + Math.pow(y1 - y, 2.0D);
					if (Math.round(Math.sqrt(dSq)) < radius) {
						if (dSq >= Math.pow(radius - 2, 2.0D)) {
							if (world.getBlockState(new BlockPos(x1, y1, z1)) != Blocks.WATER.getDefaultState())
								world.setBlockState(new BlockPos(x1, y1, z1), ModBlocks.UMBERSTONE.getDefaultState(), 2); // ModBlocks.MUD
							else
								world.setBlockState(new BlockPos(x1, y1, z1), Blocks.WATER.getDefaultState(), 3);
						} else if (dSq < Math.pow(radius - 3, 2.0D))
							world.setBlockState(new BlockPos(x1, y1, z1), Blocks.WATER.getDefaultState(), 3);
						if (dSq <= Math.pow(radius, 2.0D) && dSq >= Math.pow(radius - 3, 2.0D))
							if (world.getBlockState(new BlockPos(x1, y1, z1)) != Blocks.WATER.getDefaultState())
								if (rand.nextInt(3) != 0 ? world.setBlockState(new BlockPos(x1, y, z1), Blocks.WATER.getDefaultState(), 3) : world.setBlockState(new BlockPos(x1, y, z1), Blocks.GRASS.getDefaultState(), 2))
									;

						if (dSq <= Math.pow(radius - 2, 2.0D)) {
							world.setBlockState(new BlockPos(x1, y - depth, z1), ModBlocks.UMBERSTONE.getDefaultState(), 2); // ModBlocks.MUD
							int length = rand.nextInt(13) + 20;
							for (int yy = y - depth - 1; yy > y - depth - 1 - length; --yy)
								if (world.getBlockState(new BlockPos(x, yy, z)) == Blocks.AIR.getDefaultState())
									world.setBlockState(new BlockPos(x1, yy, z1), Blocks.DIRT.getDefaultState(), 2);
								else
									break;
						}

					}
				}
/*
		WorldGenAlgae genAlgae = new WorldGenAlgae();
		for (int attempt = 0; attempt < 5; attempt++)
			genAlgae.generate(world, rand, x + rand.nextInt(8) - rand.nextInt(8) + 8, y + 2 + rand.nextInt(6), z + rand.nextInt(8) - rand.nextInt(8) + 8);
*/
		WorldGenWaterlily genLily = new WorldGenWaterlily();
		for (int attempt = 0; attempt < 20; attempt++)
			genLily.generate(world, rand, new BlockPos(x + rand.nextInt(8) - rand.nextInt(8) + 8, y + 2 + rand.nextInt(6), z + rand.nextInt(8) - rand.nextInt(8) + 8));

		return true;
	}

}
