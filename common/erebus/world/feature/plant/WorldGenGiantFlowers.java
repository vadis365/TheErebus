package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

public class WorldGenGiantFlowers extends WorldGenerator {
	private int[] stemXZ = { 2, 5, 3, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 2, 6, 3, 3, 3, 3, 3, 1, 3, 5, 2, 3, 4, 3, 3, 3, 3, 2, 3, 4, 1, 5, 3, 3, 3, 3 };
	private int[] stemY = { 0, 1, 1, 0, 0, 0, 0, 1, 1, 1,  2, 3, 4, 5, 6, 6, 6, 7, 7, 6, 7, 7, 6 };
	private int[] petalX = { 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7 };
	private int[] petalY = { 9, 9, 9, 10, 10, 8, 8, 8, 9, 9, 9, 7, 7, 7, 8, 8, 8, 9, 9, 10, 10, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9, 9, 7, 7, 7, 8, 8, 8, 9, 9, 10, 10, 8, 8, 8, 9, 9, 9, 9, 9, 9, 10, 10 };
	private int[] petalZ = { 2, 3, 4, 2, 4, 2, 3, 4, 1, 3, 5, 2, 3, 4, 1, 3, 5, 0, 6, 0, 6, 2, 3, 4, 1, 2, 3, 4, 5, 0, 1, 5, 6, 2, 3, 4, 1, 3, 5, 0, 6, 0, 6, 2, 3, 4, 1, 3, 5, 2, 3, 4, 2, 4 };
	Random rand = new Random();
	public int colour= rand.nextInt(14) + 2;
	public int stemHeight= rand.nextInt(5);
	private boolean rainbow = false;
	
	protected int[] getValidSpawnBlocks() {
		return new int[] { Block.grass.blockID };
	}

	public boolean locationIsValidSpawn(World world, int i, int j, int k) {
		int distanceToAir = 0;
		int checkID = world.getBlockId(i, j, k);

		while (checkID != 0) {
			distanceToAir++;
			checkID = world.getBlockId(i, j + distanceToAir, k);
		}

		if (distanceToAir > 0) {
			return false;
		}
		j += distanceToAir - 1;

		int blockID = world.getBlockId(i, j, k);
		int blockIDAbove = world.getBlockId(i, j + 1, k);
		int blockIDBelow = world.getBlockId(i, j - 1, k);
		for (int x : getValidSpawnBlocks()) {
			if (blockIDAbove != 0) {
				return false;
			}
			if (blockID == x) {
				return true;
			} else if (blockID == Block.snow.blockID && blockIDBelow == x) {
				return true;
			}
		}
		return false;
	}

	public WorldGenGiantFlowers() {
	}

	@Override
	public boolean generate(World world, Random rand, int i, int j, int k) {
		if (!locationIsValidSpawn(world, i, j, k) || !locationIsValidSpawn(world, i + 7, j, k) || !locationIsValidSpawn(world, i + 7, j, k + 7) || !locationIsValidSpawn(world, i, j, k + 7)) {
			return false;
		}

		for (int airX = i; airX < i + 7; airX++) {
			for (int airY = j; airY < j + 11 -stemHeight; airY++) {
				for (int airZ = k; airZ < k + 7; airZ++) {
					if (!world.isAirBlock(airX, airY, airZ))
						return false;
				}
			}
		}
		
		for (int stemGenBase = 0; stemGenBase < 10; stemGenBase++) {
			setBlockAndMetadata(world, i + stemXZ[stemGenBase], j + stemY[stemGenBase], k + stemXZ[stemGenBase + 23], ModBlocks.erebusFlower.blockID, 1);
		}
		
		for (int stemGen = 10 + stemHeight; stemGen < 23; stemGen++) {
			setBlockAndMetadata(world, i + stemXZ[stemGen], j + stemY[stemGen] -stemHeight, k + stemXZ[stemGen + 23], ModBlocks.erebusFlower.blockID, 1);
		}

		for (int petalGen = 0; petalGen < 54; petalGen++) {
			setBlockAndMetadata(world, i + petalX[petalGen], j + petalY[petalGen] -stemHeight, k + petalZ[petalGen], ModBlocks.erebusFlower.blockID, getFlowerColour());
		}

		setBlockAndMetadata(world, i + 4, j + 9 -stemHeight, k + 3, ModBlocks.erebusFlower.blockID, 0);
		return true;
	}
	
	public void setFlowerColour(int type) {
		colour = type;
	}
	public void setRainbow(boolean allColours) {
		rainbow = allColours;
	}
	
	public int getFlowerColour() {
		if(rainbow)
			return rand.nextInt(14) + 2;
		return colour;
	}
}
