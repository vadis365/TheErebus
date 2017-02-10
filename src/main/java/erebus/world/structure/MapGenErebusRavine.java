package erebus.world.structure;

import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.world.ChunkProviderErebus;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;

import java.util.Random;

public class MapGenErebusRavine extends MapGenBase {
	protected static final IBlockState BLOCK_AIR = Blocks.AIR.getDefaultState();
	private final float[] field_75046_d = new float[1024];

	@Override
    public void generate(World worldIn, int x, int z, ChunkPrimer primer) {
        int i = this.range;
		this.world = worldIn;
		this.rand.setSeed(worldIn.getSeed());
        long j = this.rand.nextLong();
        long k = this.rand.nextLong();

        for (int l = x - i; l <= x + i; ++l)
        {
            for (int i1 = z - i; i1 <= z + i; ++i1)
            {
                long j1 = (long)l * j;
                long k1 = (long)i1 * k;
                this.rand.setSeed(j1 ^ k1 ^ worldIn.getSeed());
                this.recursiveGenerate(worldIn, l, i1, x, z, primer);
            }
        }
    }

	protected void generateRavine(long seed, int x, int z, ChunkPrimer chunkPrimer, double par6, double par8, double seed0, float seed2, float seed3, float seed4, int seed5, int seed6, double seed7) {
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
			float f6 = net.minecraft.util.math.MathHelper.cos(seed4);
			float f7 = net.minecraft.util.math.MathHelper.sin(seed4);
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
					int minX = MathHelper.floor(par6 - d6) - x * 16 - 1;
					int maxX = MathHelper.floor(par6 + d6) - x * 16 + 1;
					int minY = MathHelper.floor(par8 - d7) - 1;
					int maxY = MathHelper.floor(par8 + d7) + 1;
					int minZ = MathHelper.floor(seed0 - d6) - z * 16 - 1;
					int maxZ = MathHelper.floor(seed0 + d6) - z * 16 + 1;

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
							for (int posY = maxY + 1; !flag1 && posY >= minY - 1; --posY)
								if (posY >= 0 && posY < 128) {
									if (isOceanBlock(chunkPrimer, posX, posY, posZ, x, z))
										flag1 = true;

									if (posY != minY - 1 && posX != minX && posX != maxX - 1 && posZ != minZ && posZ != maxZ - 1)
										posY = minY;
								}

					if (!flag1) {
						for (int posX = minX; posX < maxX; ++posX) {
							double d12 = (posX + x * 16 + 0.5D - par6) / d6;

							for (int posZ = minZ; posZ < maxZ; ++posZ) {
								double d13 = (posZ + z * 16 + 0.5D - seed0) / d6;
								boolean flag2 = false;

								if (d12 * d12 + d13 * d13 < 1.0D)
									for (int posY = maxY - 1; posY >= minY; --posY) {
										double d14 = (posY + 0.5D - par8) / d7;

										if ((d12 * d12 + d13 * d13) * field_75046_d[posY] + d14 * d14 / 6.0D < 1.0D) {
											if (isTopBlock(chunkPrimer, posX, posY, posZ, x, z))
												flag2 = true;

											digBlock(chunkPrimer, posX, posY, posZ, x, z, flag2);
										}
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
	protected void recursiveGenerate(World world, int x, int z, int par4, int par5, ChunkPrimer chunkPrimerIn) {
		if (rand.nextInt(50) == 0) {
			double d0 = x * 16 + rand.nextInt(16);
			double d1 = rand.nextInt(rand.nextInt(28) + 8) + 10;
			double d2 = z * 16 + rand.nextInt(16);

			float f = rand.nextFloat() * (float) Math.PI * 2.0F;
			float f1 = (rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
			float f2 = (rand.nextFloat() * 2.0F + rand.nextFloat()) * 2.0F;
			generateRavine(rand.nextLong(), par4, par5, chunkPrimerIn, d0, d1, d2, f2, f, f1, 0, 0, 1.0D);
		}
	}

	protected boolean isOceanBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ) {
		net.minecraft.block.Block block = data.getBlockState(x, y, z).getBlock();
		return block == Blocks.FLOWING_WATER || block == Blocks.WATER;
	}

	private boolean isExceptionBiome(Biome biome) {
		// this may do something at some point
		return false;
	}

	private boolean isTopBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ) {
		Biome biome = world.getBiome(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
		IBlockState state = data.getBlockState(x, y, z);
		return state.getBlock() == biome.topBlock;
	}

	protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop) {
		Biome biome = world.getBiome(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
		IBlockState top = biome.topBlock;
		IBlockState filler = biome.fillerBlock;
		IBlockState state = data.getBlockState(x, y, z);
		if (state.getBlock() == ModBlocks.UMBERSTONE || state.getBlock() == top.getBlock() || state.getBlock() == filler.getBlock()) {
			if (y < 3)
				data.setBlockState(x, y, z, Blocks.BEDROCK.getDefaultState());
			else if (y < 4)
				data.setBlockState(x, y, z, ModBlocks.UMBERSTONE.getDefaultState());
			else if (y < 10 && Biome.getIdForBiome(biome) == Biome.getIdForBiome(ModBiomes.volcanicDesert))
				data.setBlockState(x, y, z, Blocks.FLOWING_LAVA.getDefaultState());
			else if (y < ChunkProviderErebus.swampWaterHeight - 1 && Biome.getIdForBiome(biome) == Biome.getIdForBiome(ModBiomes.submergedSwamp))
				data.setBlockState(x, y, z, Blocks.FLOWING_WATER.getDefaultState());
			else {
				data.setBlockState(x, y, z, Blocks.AIR.getDefaultState());

				if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == filler.getBlock())
					data.setBlockState(x, y - 1, z, top.getBlock().getDefaultState());
			}
        }
	}
}
