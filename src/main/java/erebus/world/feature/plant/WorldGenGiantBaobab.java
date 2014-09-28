package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.lib.EnumWood;

public class WorldGenGiantBaobab extends WorldGenerator {
	
	private int height = -1;
	private int baseRadius = -1;
	protected Block log;
	public WorldGenGiantBaobab(int height, int baseRadius)
	{
		this.height = height;
		this.baseRadius = baseRadius;
		log = EnumWood.Baobab.getLog();
	}
	
	public boolean generate(World world, Random rand, int x, int y, int z) {
		
		for (int x1 = x - baseRadius; x1 <= x + baseRadius; x1++)
			for (int z1 = z - baseRadius; z1 <= z + baseRadius; z1++)
				for (int y1 = y + 1; y1 < y + height; y1++) {
					if (!world.isAirBlock(x1, y1, z1))
						return false;
				}
				
		
		int radius = baseRadius -1;
		int layer1 = 4;
		int layer2 = 7;
		int layer3 = 9;
		int layer4 = 19;
		int layer5 = 27;

		// Trunk
		for (int yy = y; height + y >= yy; yy++) {
			for (int i = radius * - 1; i <= radius; ++i) {
				for (int j = radius * - 1; j <= radius; ++j) {
					double dSq = (i * i) + (j * j);
					if(yy <= y + layer1 && yy < y + layer2) {
						if (Math.round(Math.sqrt(dSq)) == radius || Math.round(Math.sqrt(dSq)) <= radius -1 &&  yy <= y + 2 ){
							world.setBlock(x + i, yy, z + j, log);
						}
						else {
							world.setBlock(x + i, yy, z + j, Blocks.air);
						}
						world.setBlock(x, yy, z, log);
					} 
					
					if(yy <= y + layer2 && yy > y + layer1) {
						if (Math.round(Math.sqrt(dSq)) == radius -1) {
							world.setBlock(x + i, yy, z + j, log);
						}
						else {
							world.setBlock(x + i, yy, z + j, Blocks.air);
						}
						world.setBlock(x, yy, z, log);
					}
					
					if(yy <= y + layer3 && yy > y + layer2) {
						if (Math.round(Math.sqrt(dSq)) == radius -2) {
							world.setBlock(x + i, yy, z + j, log);
						}
						else {
							world.setBlock(x + i, yy, z + j, Blocks.air);
						}
						world.setBlock(x, yy, z, log);
					}
					
					if(yy <= y + layer4 && yy > y + layer3) {
						world.setBlock(x, y + 10, z, log);
						world.setBlock(x, y + 11, z, log);
						if (Math.round(Math.sqrt(dSq)) <= radius -12 && Math.round(Math.sqrt(dSq)) > radius -13) {
							world.setBlock(x + i, y + 12, z + j, Blocks.web);
						}
						if (Math.round(Math.sqrt(dSq)) == radius -3 || Math.round(Math.sqrt(dSq)) <= radius -3 && Math.round(Math.sqrt(dSq)) > radius -12 &&  yy >= y + 9 && yy <= y + 12) {
							world.setBlock(x + i, yy, z + j, log);
						}
						else {
							world.setBlock(x + i, yy, z + j, Blocks.air);
						}
						
					}
					
					if(yy <= y + layer5 && yy > y + layer4) {
						if (Math.round(Math.sqrt(dSq)) == radius -2|| Math.round(Math.sqrt(dSq)) <= radius -3 && yy >= y + 20 && yy <= y + 21) {
							world.setBlock(x + i, yy, z + j, log);
						}
						else {
							world.setBlock(x + i, yy, z + j, Blocks.air);
						}
					}

					// 2nd floor gap in web shape
					if (Math.round(Math.sqrt(dSq)) < radius -3 && Math.round(Math.sqrt(dSq)) %2==0  && yy == y + 21) {
						if(x + i != x && z + j != z)
							world.setBlock(x + i, yy, z + j, Blocks.web);
					}
				}
			}
		}
		
		//other internal bits like vines and holes needed
		
		//ground floor vines
		for (int yy = y + 3; 11 + y >= yy; yy++) {
			world.setBlock(x + 1, yy, z, Blocks.vine, 2, 3);
			world.setBlock(x - 1, yy, z, Blocks.vine, 8, 3);
			world.setBlock(x, yy, z + 1, Blocks.vine, 4, 3);
			world.setBlock(x, yy, z - 1, Blocks.vine, 1, 3);
		}
		
		//2nd floor holes for ladders
		world.setBlock(x + 9, y + 20, z, Blocks.air);
		world.setBlock(x - 9, y + 20, z, Blocks.air);
		world.setBlock(x, y + 20, z + 9, Blocks.air);
		world.setBlock(x, y + 20, z - 9, Blocks.air);
		world.setBlock(x + 9, y + 21, z, Blocks.air);
		world.setBlock(x - 9, y + 21, z, Blocks.air);
		world.setBlock(x, y + 21, z + 9, Blocks.air);
		world.setBlock(x, y + 21, z - 9, Blocks.air);
		
		//1stfloor vines
		for (int yy = y + 13; 21 + y >= yy; yy++) {
			world.setBlock(x - 9, yy, z, Blocks.vine, 2, 3);
			world.setBlock(x + 9, yy, z, Blocks.vine, 8, 3);
			world.setBlock(x, yy, z - 9, Blocks.vine, 4, 3);
			world.setBlock(x, yy, z + 9, Blocks.vine, 1, 3);
		}
		
		return true;
	}
}
