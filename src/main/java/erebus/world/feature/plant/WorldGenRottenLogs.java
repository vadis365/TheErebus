package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.lib.EnumWood;

public class WorldGenRottenLogs extends WorldGenerator {
	
	private int length = -1;
	private int baseRadius = -1;
	private int	maxRadius =	6;
	private int	maxHeight =	15;
	private int direction;
	protected Block log;
	public WorldGenRottenLogs(int length, int baseRadius, byte direction)
	{
		this.length = length;
		this.baseRadius = baseRadius;
		this.direction = direction;
		log = EnumWood.Rotten.getLog();
	}
	
	public boolean generate(World world, Random rand, int x, int y, int z) {
	/*	//Not done this bit yet - do not touch
		for (int x1 = x - baseRadius; x1 <= x + baseRadius; x1++)
			for (int z1 = z - baseRadius; z1 <= z + baseRadius; z1++)
				for (int y1 = y + 1; y1 < y + length; y1++) {
					if (!world.isAirBlock(x1, y1, z1))
						return false;
				}
				
		*/

		// Trunk N/S
		if(direction == 1) {
			
		for (int zz = z-length; length + z >= zz; zz++) {
			for (int i = baseRadius * - 1; i <= baseRadius; ++i) {
				for (int j = baseRadius * -1; j <= baseRadius; ++j) {
					double dSq = (i * i) + (j * j);
					if (Math.round(Math.sqrt(dSq)) == baseRadius) {
						world.setBlock(x + i, y + j + baseRadius, zz, log, 11, 3);
						if(rand.nextInt(12) == 0)
							world.setBlock(x + i, y + j + baseRadius,  zz, Blocks.air);
						if(zz == z - length && rand.nextInt(2) == 0||zz == z + length && rand.nextInt(2) == 0)
							world.setBlock(x + i,  y + j + baseRadius, zz, Blocks.air);
					} else {
						world.setBlock(x + i, y + j + baseRadius, zz, Blocks.air);
					}
				}
			}
		}
		
		}
		else {
		// Trunk E/W
		for (int xx = x-length; length + x >= xx; xx++) {
			for (int i = baseRadius * - 1; i <= baseRadius; ++i) {
				for (int j = baseRadius * -1; j <= baseRadius; ++j) {
					double dSq = (i * i) + (j * j);
					if (Math.round(Math.sqrt(dSq)) == baseRadius) {
						world.setBlock(xx , y + j +baseRadius, z + i, log, 4, 3);
						if(rand.nextInt(12) == 0)
							world.setBlock(xx, y + j + baseRadius, z + i, Blocks.air);
						if(xx == x - length && rand.nextInt(2) == 0||xx == x + length && rand.nextInt(2) == 0)
							world.setBlock(xx, y + j + baseRadius, z + i, Blocks.air);
					} else {
						world.setBlock(xx , y+j+baseRadius, z + i, Blocks.air);
					}
				}
			}
		}
			
		}
		return true;
	}
}
