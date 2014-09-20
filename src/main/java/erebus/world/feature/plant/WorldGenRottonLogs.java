package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenRottonLogs extends WorldGenerator {
	
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int baseRadius = rand.nextInt(3) + 3;
		int radius = baseRadius -1;

		// Trunk
		for (int y1 = y + rand.nextInt(5) + 5; y1 >= y; y1--) {
			for (int i = radius * -1; i <= radius; ++i) {
				for (int j = radius * -1; j <= radius; ++j) {
					double dSq = (i * i) + (j * j);
					if (Math.round(Math.sqrt(dSq)) == radius) {
						world.setBlock(x + i, y1, z + j, Blocks.dirt);
					} else {
						world.setBlock(x + i, y1, z + j, Blocks.air);
					}
				}
			}
		}
		
		// Randomised root Base
		for (int i = baseRadius * -1; i <= baseRadius; ++i) {
			for (int j = baseRadius * -1; j <= baseRadius; ++j) {
				double dSq = (i * i) + (j * j);
				if (Math.round(Math.sqrt(dSq)) <= baseRadius) {
					world.setBlock(x + i, y, z + j, Blocks.dirt);
					world.setBlock(x + i + rand.nextInt(2) - 1, y, z + j + rand.nextInt(2)-1, Blocks.dirt);
					world.setBlock(x + i, y + rand.nextInt(2), z + j, Blocks.dirt);
				}
			}
		}
		return false;
	}
}
