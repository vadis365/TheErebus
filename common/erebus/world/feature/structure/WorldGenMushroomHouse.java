package erebus.world.feature.structure;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.entity.EntitySporeling;

// @formatter:off
public class WorldGenMushroomHouse extends WorldGenerator {
	int airX, airZ, doorX, doorZ;

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {

		for (int x1 = x - 6; x1 <= x + 6; x1++)
			for (int z1 = z - 6; z1 <= z + 6; z1++)
				for (int y1 = y + 1; y1 < y + 6; y1++) {
					if (!world.isAirBlock(x1, y1, z1))
						return false;
				}

		for (int x1 = x - 6; x1 <= x + 6; x1++)
			for (int z1 = z - 6; z1 <= z + 6; z1++) {
				if (world.getBlockId(x1, y, z1) != Block.grass.blockID)
					return false;
			}

		for (int x1 = x - 6; x1 <= x + 6; x1++)
			for (int z1 = z - 6; z1 <= z + 6; z1++)
				for (int y1 = y; y1 < y + 6; y1++) {
					double dSq = Math.pow(x1 - x, 2.0D)
							+ Math.pow(z1 - z, 2.0D) + Math.pow(y1 - y, 2.0D);
					if (Math.round(Math.sqrt(dSq)) < 6)
						if ((dSq >= Math.pow(6 - 2, 2.0D)) || (y1 == y))
							world.setBlock(x1, y1, z1,
									ModBlocks.planksErebus.blockID, 10, 3);
						else
							world.setBlock(x1, y1, z1, 0);
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
		
		world.setBlock(x + airX, y + 1, z + airZ, 0);
		world.setBlock(x + airX, y + 2, z + airZ, 0);
		world.setBlock(x + doorX, y + 1, z + doorZ, Block.doorWood.blockID, direction, 3);
		world.setBlock(x + doorX, y + 2, z + doorZ, Block.doorWood.blockID, direction + 8, 3);

		for (int a = 0; a < 10; a++) {
			EntitySporeling entitySporeling = new EntitySporeling(world);
			entitySporeling.setPosition(x + (rand.nextFloat()*0.03D -rand.nextFloat()*0.03D), y + 1, z +(rand.nextFloat()*0.03D-rand.nextFloat()*0.03D));
			world.spawnEntityInWorld(entitySporeling);
		}
		return true;
	}
}
// @formatter:on
