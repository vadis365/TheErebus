package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.blocks.EnumWood;

public class WorldGenBamboo extends WorldGenerator {
	private int bambooAmount = -1;
	private boolean checkForWater = true;
	private boolean isFarmed = false;
	
	public WorldGenBamboo(int bambooAmount, boolean checkForWater) {
		this.bambooAmount = bambooAmount;
		this.checkForWater = checkForWater;
		this.isFarmed = false;
	}

	public WorldGenBamboo(boolean isFarmed, boolean checkForWater) {
		this.checkForWater = checkForWater;
		this.isFarmed = isFarmed;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		if (checkForWater) {
			boolean canSpawn = false;

			for (int waterAttempt = 0; waterAttempt < 40; waterAttempt++)
				if (world.getBlockState(new BlockPos(x + rand.nextInt(8) - rand.nextInt(8), y + rand.nextInt(3) - rand.nextInt(6), z + rand.nextInt(8) - rand.nextInt(8))).getMaterial() == Material.WATER) {
					canSpawn = true;
					break;
				}

			if (!canSpawn)
				return false;
		}

		if (!isFarmed) {
			for (int xx, yy, zz, attempt = 0, bambooPlaced = 0; attempt < bambooAmount * 2 && bambooPlaced < bambooAmount; attempt++) {
				xx = x + rand.nextInt(8) - rand.nextInt(8);
				zz = z + rand.nextInt(8) - rand.nextInt(8);

				for (yy = y - 4; yy <= y + 4; yy++)
					if (world.isAirBlock(new BlockPos(xx, yy, zz)) && world.getBlockState(new BlockPos(xx, yy - 1, zz)) == Blocks.GRASS.getDefaultState()) {
						world.setBlockState(new BlockPos(xx, yy, zz), EnumWood.BAMBOO.getLog().getDefaultState());

						for (int bambooY = 1, bambooHeight = rand.nextInt(6) + 4; bambooY < bambooHeight; bambooY++)
							if (world.isAirBlock(new BlockPos(xx, yy + bambooY, zz)))
								world.setBlockState(new BlockPos(xx, yy + bambooY, zz), EnumWood.BAMBOO.getLog().getDefaultState());
							else
								break;

						++bambooPlaced;
						break;
					}
			}
		}
		else {
			for (int bambooY = 1, bambooHeight = rand.nextInt(6) + 4; bambooY < bambooHeight; bambooY++)
				if (world.isAirBlock(new BlockPos(pos.getX(), pos.getY() + bambooY, pos.getZ())))
					world.setBlockState(new BlockPos(pos.getX(), pos.getY() + bambooY, pos.getZ()), EnumWood.BAMBOO.getLog().getDefaultState());
				else
					break;
		}
			return true;
		
	}
}