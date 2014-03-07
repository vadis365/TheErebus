package erebus.world.feature.plant;
import java.util.Random;

import erebus.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGiantFlowers extends WorldGenerator
{
	private int[] stem={2,2,3,3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,5,5,6,6,0,7,1,6,0,0,0,1,1,1,2,3,4,5,6,6,6,7,7,1,6,0,7,3,3,3,3,1,3,5,2,3,4,3,3,3,3,2,3,4,1,5,3,3,3,3};
	protected int[] GetValidSpawnBlocks() {
		return new int[] {
			Block.grass.blockID
		};
	}

	public boolean LocationIsValidSpawn(World world, int i, int j, int k){
		int distanceToAir = 0;
		int checkID = world.getBlockId(i, j, k);

		while (checkID != 0){
			distanceToAir++;
			checkID = world.getBlockId(i, j + distanceToAir, k);
		}

		if (distanceToAir > 0){
			return false;
		}
		j += distanceToAir - 1;

		int blockID = world.getBlockId(i, j, k);
		int blockIDAbove = world.getBlockId(i, j+1, k);
		int blockIDBelow = world.getBlockId(i, j-1, k);
		for (int x : GetValidSpawnBlocks()){
			if (blockIDAbove != 0){
				return false;
			}
			if (blockID == x){
				return true;
			}else if (blockID == Block.snow.blockID && blockIDBelow == x){
				return true;
			}
		}
		return false;
	}

	public WorldGenGiantFlowers() { }

	public boolean generate(World world, Random rand, int i, int j, int k) {
		//check that each corner is one of the valid spawn blocks
		if(!LocationIsValidSpawn(world, i, j, k) || !LocationIsValidSpawn(world, i + 7, j, k) || !LocationIsValidSpawn(world, i + 7, j, k + 6) || !LocationIsValidSpawn(world, i, j, k + 6))
		{
			return false;
		}
		
	/*	for(int airX=i;airX<i+7;airX++) {
			for(int airY=j;airY<j+10;airY++) {
				for(int airZ=k;airZ<k+7;airZ++) {
					System.out.println("airX: "+airX+" airY: "+airY+" airZ: "+airZ);
					setBlock(world, airX, airY, airZ, 0);
				}
			}
			System.out.println("X: "+i+" Y: "+j+" Z: "+k);
			
		}*/
		for (int stemGen=0;stemGen<23;stemGen++) {
			setBlockAndMetadata(world, i+ stem[stemGen], j+ stem[stemGen+23], k+stem[stemGen+46], ModBlocks.erebusFlower.blockID, 1);
			System.out.println("Stem AT X: "+i+" Y: "+j+" Z: "+k);
		}
/*
//STEM
 * X 2,2,3,3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,5,5,6,6
 * Y 0,7,1,6,0,0,0,1,1,1,2,3,4,5,6,6,6,7,7,1,6,0,7
 * Z 3,3,3,3,1,3,5,2,3,4,3,3,3,3,2,3,4,1,5,3,3,3,3
 * 
		setBlockAndMetadata(world, i + 2, j + 0, k + 3, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 2, j + 7, k + 3, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 3, j + 1, k + 3, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 3, j + 6, k + 3, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 4, j + 0, k + 1, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 4, j + 0, k + 3, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 4, j + 0, k + 5, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 4, j + 1, k + 2, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 4, j + 1, k + 3, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 4, j + 1, k + 4, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 4, j + 2, k + 3, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 4, j + 3, k + 3, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 4, j + 4, k + 3, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 4, j + 5, k + 3, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 4, j + 6, k + 2, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 4, j + 6, k + 3, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 4, j + 6, k + 4, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 4, j + 7, k + 1, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 4, j + 7, k + 5, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 5, j + 1, k + 3, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 5, j + 6, k + 3, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 6, j + 0, k + 3, Block.cloth.blockID, 5);
		setBlockAndMetadata(world, i + 6, j + 7, k + 3, Block.cloth.blockID, 5);
		
//PETALS	
		setBlockAndMetadata(world, i + 1, j + 9, k + 2, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 1, j + 9, k + 3, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 1, j + 9, k + 4, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 1, j + 10, k + 2, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 1, j + 10, k + 4, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 2, j + 8, k + 2, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 2, j + 8, k + 3, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 2, j + 8, k + 4, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 2, j + 9, k + 1, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 2, j + 9, k + 3, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 2, j + 9, k + 5, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 3, j + 7, k + 2, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 3, j + 7, k + 3, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 3, j + 7, k + 4, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 3, j + 8, k + 1, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 3, j + 8, k + 3, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 3, j + 8, k + 5, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 3, j + 9, k + 0, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 3, j + 9, k + 6, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 3, j + 10, k + 0, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 3, j + 10, k + 6, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 4, j + 7, k + 2, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 4, j + 7, k + 3, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 4, j + 7, k + 4, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 4, j + 8, k + 1, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 4, j + 8, k + 2, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 4, j + 8, k + 3, Block.cloth.blockID, 11);//
		setBlockAndMetadata(world, i + 4, j + 8, k + 4, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 4, j + 8, k + 5, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 4, j + 9, k + 0, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 4, j + 9, k + 1, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 4, j + 9, k + 5, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 4, j + 9, k + 6, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 5, j + 7, k + 2, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 5, j + 7, k + 3, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 5, j + 7, k + 4, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 5, j + 8, k + 1, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 5, j + 8, k + 3, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 5, j + 8, k + 5, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 5, j + 9, k + 0, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 5, j + 9, k + 6, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 5, j + 10, k + 0, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 5, j + 10, k + 6, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 6, j + 8, k + 2, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 6, j + 8, k + 3, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 6, j + 8, k + 4, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 6, j + 9, k + 1, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 6, j + 9, k + 3, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 6, j + 9, k + 5, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 7, j + 9, k + 2, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 7, j + 9, k + 3, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 7, j + 9, k + 4, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 7, j + 10, k + 2, Block.cloth.blockID, 11);
		setBlockAndMetadata(world, i + 7, j + 10, k + 4, Block.cloth.blockID, 11);
		
// STIGMA
		setBlockAndMetadata(world, i + 4, j + 9, k + 3, Block.cloth.blockID, 0);

*/

		return true;
	}
}