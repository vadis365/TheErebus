package erebus.world.feature.plant;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBamboo extends WorldGenerator {
	private final int bambooAmount;
	private final boolean checkForWater;

	public WorldGenBamboo(int bambooAmount, boolean checkForWater) {
		this.bambooAmount = bambooAmount;
		this.checkForWater = checkForWater;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (checkForWater) {
			boolean canSpawn = false;

			for (int waterAttempt = 0; waterAttempt < 40; waterAttempt++)
				if (world.getBlock(x + rand.nextInt(8) - rand.nextInt(8), y + rand.nextInt(3) - rand.nextInt(6), z + rand.nextInt(8) - rand.nextInt(8)).getMaterial() == Material.water) {
					canSpawn = true;
					break;
				}

			if (!canSpawn)
				return false;
		}

		for (int xx, yy, zz, attempt = 0, bambooPlaced = 0; attempt < bambooAmount * 2 && bambooPlaced < bambooAmount; attempt++) {
			xx = x + rand.nextInt(8) - rand.nextInt(8);
			zz = z + rand.nextInt(8) - rand.nextInt(8);

			for (yy = y - 4; yy <= y + 4; yy++)
				if (world.isAirBlock(xx, yy, zz) && world.getBlock(xx, yy - 1, zz) == Blocks.grass) {
					world.setBlock(xx, yy, zz, ModBlocks.bambooCrop);

					for (int bambooY = 1, bambooHeight = rand.nextInt(6) + 4; bambooY < bambooHeight; bambooY++)
						if (world.isAirBlock(xx, yy + bambooY, zz))
							world.setBlock(xx, yy + bambooY, zz, ModBlocks.bambooCrop);
						else
							break;

					++bambooPlaced;
					break;
				}
		}

		return true;
	}
}