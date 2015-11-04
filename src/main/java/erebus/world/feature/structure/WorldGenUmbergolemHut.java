package erebus.world.feature.structure;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenUmbergolemHut extends WorldGenerator {

	private int airX, airZ, doorX, doorZ;

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {

		for (int xx = x - 6; xx <= x + 6; xx++)
			for (int zz = z - 6; zz <= z + 6; zz++)
				for (int yy = y + 1; yy < y + 10; yy++)
					if (!world.isAirBlock(xx, yy, zz))
						return false;
		/*
				for (int xx = x - 6; xx <= x + 6; xx++)
					for (int zz = z - 6; zz <= z + 6; zz++) {
						if (world.getBlock(xx, y, zz) != Blocks.grass)
							return false;
					}
		*/
		for (int xx = x - 6; xx <= x + 6; xx++)
			for (int zz = z - 6; zz <= z + 6; zz++)
				for (int yy = y; yy < y + 6; yy++) {
					double dSqDome = Math.pow(xx - x, 2.0D) + Math.pow(zz - z, 2.0D) + Math.pow(yy - y, 2.0D);
					double dSqCylinder = Math.pow(xx - x, 2.0D) + Math.pow(zz - z, 2.0D);
					if (Math.round(Math.sqrt(dSqDome)) < 6)
						if (dSqDome >= Math.pow(6 - 2, 2.0D))
							world.setBlock(xx, yy + 4, zz, ModBlocks.scorchedPlanks, 0, 3);
						else
							world.setBlock(xx, yy, zz, Blocks.air, 0, 2);
					if (Math.round(Math.sqrt(dSqCylinder)) < 6)
						if (dSqCylinder >= Math.pow(6 - 2, 2.0D) && yy < y + 4 || yy == y)
							world.setBlock(xx, yy, zz, ModBlocks.umberstone, 1, 3);
				}

		int direction = rand.nextInt(4);

		if (direction == 0) {
			airX = 5;
			airZ = 0;
			doorX = 4;
			doorZ = 0;
		}
		if (direction == 1) {
			airX = 0;
			airZ = 5;
			doorX = 0;
			doorZ = 4;
		}
		if (direction == 2) {
			airX = -5;
			airZ = 0;
			doorX = -4;
			doorZ = 0;
		}
		if (direction == 3) {
			airX = 0;
			airZ = -5;
			doorX = 0;
			doorZ = -4;
		}

		world.setBlock(x + airX, y + 1, z + airZ, Blocks.air, 0, 2);
		world.setBlock(x + airX, y + 2, z + airZ, Blocks.air, 0, 2);
		world.setBlock(x + doorX, y + 1, z + doorZ, ModBlocks.doorScorched, direction, 3);
		world.setBlock(x + doorX, y + 2, z + doorZ, ModBlocks.doorScorched, direction + 8, 3);

		return true;
	}
}