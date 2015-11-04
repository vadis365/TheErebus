package erebus.world.feature.decoration;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMire extends WorldGenerator {

	private final Block fillerFluid;

	public WorldGenMire(Block filler) {
		fillerFluid = filler;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		x -= 8;

		for (z -= 8; y > 5 && world.isAirBlock(x, y, z); --y) {
		}

		if (y <= 4)
			return false;
		else {
			y -= 4;
			boolean[] aboolean = new boolean[2048];
			int l = rand.nextInt(4) + 4;
			int i1;

			for (i1 = 0; i1 < l; ++i1) {
				double d0 = rand.nextDouble() * 6.0D + 3.0D;
				double d1 = rand.nextDouble() * 4.0D + 2.0D;
				double d2 = rand.nextDouble() * 6.0D + 3.0D;
				double d3 = rand.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
				double d4 = rand.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
				double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

				for (int j1 = 1; j1 < 15; ++j1)
					for (int k1 = 1; k1 < 15; ++k1)
						for (int l1 = 1; l1 < 7; ++l1) {
							double d6 = (j1 - d3) / (d0 / 2.0D);
							double d7 = (l1 - d4) / (d1 / 2.0D);
							double d8 = (k1 - d5) / (d2 / 2.0D);
							double d9 = d6 * d6 + d7 * d7 + d8 * d8;

							if (d9 < 1.0D)
								aboolean[(j1 * 16 + k1) * 8 + l1] = true;
						}
			}

			int i2;
			int j2;
			boolean flag;

			for (i1 = 0; i1 < 16; ++i1)
				for (j2 = 0; j2 < 16; ++j2)
					for (i2 = 0; i2 < 8; ++i2) {
						flag = !aboolean[(i1 * 16 + j2) * 8 + i2] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + i2] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + i2] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + i2] || j2 > 0 && aboolean[(i1 * 16 + j2 - 1) * 8 + i2] || i2 < 7 && aboolean[(i1 * 16 + j2) * 8 + i2 + 1] || i2 > 0 && aboolean[(i1 * 16 + j2) * 8 + i2 - 1]);

						if (flag) {
							Material material = world.getBlock(x + i1, y + i2, z + j2).getMaterial();

							if (i2 >= 4 && material.isLiquid())
								return false;

							if (i2 < 4 && !material.isSolid() && world.getBlock(x + i1, y + i2, z + j2) != fillerFluid)
								return false;
						}
					}

			for (i1 = 0; i1 < 16; ++i1)
				for (j2 = 0; j2 < 16; ++j2)
					for (i2 = 0; i2 < 8; ++i2)
						if (aboolean[(i1 * 16 + j2) * 8 + i2])
							world.setBlock(x + i1, y + i2, z + j2, i2 >= 4 ? Blocks.air : fillerFluid, 0, 2);

			for (i1 = 0; i1 < 16; ++i1)
				for (j2 = 0; j2 < 16; ++j2)
					for (i2 = 4; i2 < 8; ++i2)
						if (aboolean[(i1 * 16 + j2) * 8 + i2] && world.getBlock(x + i1, y + i2 - 1, z + j2) == Blocks.dirt && world.getSavedLightValue(EnumSkyBlock.Sky, x + i1, y + i2, z + j2) > 0) {
							BiomeGenBase biomegenbase = world.getBiomeGenForCoords(x + i1, z + j2);

							if (biomegenbase.topBlock == Blocks.mycelium)
								world.setBlock(x + i1, y + i2 - 1, z + j2, Blocks.mycelium, 0, 2);
							else
								world.setBlock(x + i1, y + i2 - 1, z + j2, Blocks.grass, 0, 2);
						}

			if (fillerFluid.getMaterial() == Material.water)
				for (i1 = 0; i1 < 16; ++i1)
					for (j2 = 0; j2 < 16; ++j2) {
						for (i2 = 0; i2 < 8; ++i2) {
							flag = !aboolean[(i1 * 16 + j2) * 8 + i2] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + i2] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + i2] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + i2] || j2 > 0 && aboolean[(i1 * 16 + j2 - 1) * 8 + i2] || i2 < 7 && aboolean[(i1 * 16 + j2) * 8 + i2 + 1] || i2 > 0 && aboolean[(i1 * 16 + j2) * 8 + i2 - 1]);

							if (flag && (i2 < 4 || rand.nextInt(2) != 0) && world.getBlock(x + i1, y + i2, z + j2).getMaterial().isSolid()) {
								world.setBlock(x + i1, y + i2, z + j2, ModBlocks.quickSand, 0, 2);
								if (rand.nextInt(10) == 0)
									world.setBlock(x + i1, y + 2, z + j2, ModBlocks.mireCoral, 0, 2);
							}
						}

						byte b0 = 4;

						if (world.isBlockFreezable(x + i1, y + b0, z + j2))
							world.setBlock(x + i1, y + b0, z + j2, Blocks.ice, 0, 2);
					}

			return true;
		}
	}

	public double getDistance(double x, double y, double z, double x2, double y2, double z2) {
		return Math.pow(x - x2, 2D) + Math.pow(y - y2, 2D) + Math.pow(z - z2, 2D);
	}
}
