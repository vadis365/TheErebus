package erebus.world.structure;

import java.util.Random;

import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.world.ChunkProviderErebus;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.MapGenBase;

public class MapGenErebusRavine extends MapGenBase {

	private final float[] field_75046_d = new float[1024];

	protected void generateRavine(long seed, int x, int z, Block[] blocks, double par6, double par8, double seed0, float seed2, float seed3, float seed4, int seed5, int seed6, double seed7) {
		Random random = new Random(seed);
		double blockCoordX = x * 16 + 8;
		double blockCoordZ = z * 16 + 8;
		float f3 = 0.0F;
		float f4 = 0.0F;

		if (seed6 <= 0) {
			int j1 = range * 16 - 16;
			seed6 = j1 - random.nextInt(j1 >> 2);
		}

		boolean flag = false;

		if (seed5 == -1) {
			seed5 = seed6 / 2;
			flag = true;
		}

		float f5 = 1.0F;

		for (int k1 = 0; k1 < 128; ++k1) {
			if (k1 == 0 || random.nextInt(3) == 0)
				f5 = 1.0F + random.nextFloat() * random.nextFloat() * 1.0F;

			field_75046_d[k1] = f5 * f5;
		}

		for (; seed5 < seed6; ++seed5) {
			double d6 = 1.5D + MathHelper.sin(seed5 * (float) Math.PI / seed6) * seed2 * 1.0F;
			double d7 = d6 * seed7;
			d6 *= random.nextFloat() * 0.25D + 0.75D;
			d7 *= random.nextFloat() * 0.25D + 0.75D;
			float f6 = MathHelper.cos(seed4);
			float f7 = MathHelper.sin(seed4);
			par6 += MathHelper.cos(seed3) * f6;
			par8 += f7;
			seed0 += MathHelper.sin(seed3) * f6;
			seed4 *= 0.7F;
			seed4 += f4 * 0.05F;
			seed3 += f3 * 0.05F;
			f4 *= 0.8F;
			f3 *= 0.5F;
			f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
			f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

			if (flag || random.nextInt(4) != 0) {
				double d8 = par6 - blockCoordX;
				double d9 = seed0 - blockCoordZ;
				double d10 = seed6 - seed5;
				double d11 = seed2 + 2.0F + 16.0F;

				if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11)
					return;

				if (par6 >= blockCoordX - 16.0D - d6 * 2.0D && seed0 >= blockCoordZ - 16.0D - d6 * 2.0D && par6 <= blockCoordX + 16.0D + d6 * 2.0D && seed0 <= blockCoordZ + 16.0D + d6 * 2.0D) {
					int minX = MathHelper.floor_double(par6 - d6) - x * 16 - 1;
					int maxX = MathHelper.floor_double(par6 + d6) - x * 16 + 1;
					int minY = MathHelper.floor_double(par8 - d7) - 1;
					int maxY = MathHelper.floor_double(par8 + d7) + 1;
					int minZ = MathHelper.floor_double(seed0 - d6) - z * 16 - 1;
					int maxZ = MathHelper.floor_double(seed0 + d6) - z * 16 + 1;

					if (minX < 0)
						minX = 0;

					if (maxX > 16)
						maxX = 16;

					if (minY < 1)
						minY = 1;

					if (maxY > 120)
						maxY = 120;

					if (minZ < 0)
						minZ = 0;

					if (maxZ > 16)
						maxZ = 16;

					boolean flag1 = false;

					for (int posX = minX; !flag1 && posX < maxX; ++posX)
						for (int posZ = minZ; !flag1 && posZ < maxZ; ++posZ)
							for (int posY = maxY + 1; !flag1 && posY >= minY - 1; --posY) {
								int arrayIndex = (posX * 16 + posZ) * 128 + posY;

								if (posY >= 0 && posY < 128) {
									if (isOceanBlock(blocks, arrayIndex, posX, posY, posZ, x, z))
										flag1 = true;

									if (posY != minY - 1 && posX != minX && posX != maxX - 1 && posZ != minZ && posZ != maxZ - 1)
										posY = minY;
								}
							}

					if (!flag1) {
						for (int posX = minX; posX < maxX; ++posX) {
							double d12 = (posX + x * 16 + 0.5D - par6) / d6;

							for (int posZ = minZ; posZ < maxZ; ++posZ) {
								double d13 = (posZ + z * 16 + 0.5D - seed0) / d6;
								int arrayIndex = (posX * 16 + posZ) * 128 + maxY;
								boolean flag2 = false;

								if (d12 * d12 + d13 * d13 < 1.0D)
									for (int posY = maxY - 1; posY >= minY; --posY) {
										double d14 = (posY + 0.5D - par8) / d7;

										if ((d12 * d12 + d13 * d13) * field_75046_d[posY] + d14 * d14 / 6.0D < 1.0D) {
											if (isTopBlock(blocks, arrayIndex, posX, posY, posZ, x, z))
												flag2 = true;

											digBlock(blocks, arrayIndex, posX, posY, posZ, x, z, flag2);
										}

										--arrayIndex;
									}
							}
						}

						if (flag)
							break;
					}
				}
			}
		}
	}

	@Override
	protected void func_151538_a(World world, int x, int z, int par4, int par5, Block[] blocks) {
		if (rand.nextInt(50) == 0) {
			double d0 = x * 16 + rand.nextInt(16);
			double d1 = rand.nextInt(rand.nextInt(28) + 8) + 10;
			double d2 = z * 16 + rand.nextInt(16);

			float f = rand.nextFloat() * (float) Math.PI * 2.0F;
			float f1 = (rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
			float f2 = (rand.nextFloat() * 2.0F + rand.nextFloat()) * 2.0F;
			generateRavine(rand.nextLong(), par4, par5, blocks, d0, d1, d2, f2, f, f1, 0, 0, 3.0D);
		}
	}

	protected boolean isOceanBlock(Block[] blocks, int index, int x, int y, int z, int chunkX, int chunkZ) {
		return blocks[index] == Blocks.flowing_water || blocks[index] == Blocks.water;
	}

	private boolean isTopBlock(Block[] blocks, int index, int x, int y, int z, int chunkX, int chunkZ) {
		return blocks[index] == worldObj.getBiomeGenForCoords(x + chunkX * 16, z + chunkZ * 16).topBlock;
	}

	protected void digBlock(Block[] blocks, int index, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop) {
		BiomeGenBase biome = worldObj.getBiomeGenForCoords(x + chunkX * 16, z + chunkZ * 16);

		if (y < 3)
			blocks[index] = Blocks.bedrock;
		else if (y < 4)
			blocks[index] = ModBlocks.umberstone;
		else if (y < 10 && biome.biomeID == ModBiomes.volcanicDesertID)
			blocks[index] = Blocks.flowing_lava;
		else if (y < ChunkProviderErebus.swampWaterHeight - 1 && biome.biomeID == ModBiomes.submergedSwampID)
			blocks[index] = Blocks.flowing_water;
		else {
			blocks[index] = Blocks.air;

			if (foundTop && blocks[index - 1] == biome.fillerBlock)
				blocks[index - 1] = biome.topBlock;
		}
	}
}
