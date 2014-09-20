package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

public class WorldGenRottenLogs extends WorldGenerator {
	
	private int height = -1;
	private int baseRadius = -1;
	public WorldGenRottenLogs(int height, int baseRadius)
	{
		this.height = height;
		this.baseRadius = baseRadius;
	}
	
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int radius = baseRadius -1;
		int yy=y;

		// Trunk
		for (; height + y >= yy; yy++) {
			for (int i = radius * - 1; i <= radius; ++i) {
				for (int j = radius * -1; j <= radius; ++j) {
					double dSq = (i * i) + (j * j);
					if (Math.round(Math.sqrt(dSq)) == radius) {
						world.setBlock(x + i, yy, z + j, ModBlocks.rottenWood);
					} else {
						world.setBlock(x + i, yy, z + j, Blocks.air);
					}
				}
			}
		}
		
		// Randomised root Base
		for (int i = baseRadius * - 1; i <= baseRadius; ++i) {
			for (int j = baseRadius * - 1; j <= baseRadius; ++j) {
				double dSq = (i * i) + (j * j);
				if (Math.round(Math.sqrt(dSq)) <= baseRadius) {
					world.setBlock(x + i, y, z + j, ModBlocks.rottenWood);
					world.setBlock(x + i + rand.nextInt(2) - 1, y, z + j + rand.nextInt(2) - 1, ModBlocks.rottenWood);
					world.setBlock(x + i, y + rand.nextInt(2), z + j, ModBlocks.rottenWood);
				}
			}
		}
		return false;
	}
}
