package erebus.world;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import erebus.ModBlocks;
import erebus.world.biomes.BiomeGenBaseErebus;
import erebus.world.feature.WorldGenSpiderDungeons;
import erebus.world.structure.MapGenErebusRavine;

public class ChunkProviderErebus implements IChunkProvider {

	private final World worldObj;

	private final Random rand;

	private final NoiseGeneratorOctaves netherNoiseGen1;
	private final NoiseGeneratorOctaves netherNoiseGen2;
	private final NoiseGeneratorOctaves netherNoiseGen3;
	private final NoiseGeneratorOctaves netherNoiseGen6;
	private final NoiseGeneratorOctaves netherNoiseGen7;
	private final NoiseGeneratorOctaves noiseGen4;
	private double[] noiseArray;
	private double[] stoneNoise;
	private double[] noiseData1;
	private double[] noiseData2;
	private double[] noiseData3;
	private double[] noiseData4;
	private double[] noiseData5;

	private BiomeGenBase[] biomesForGeneration;

	private final MapGenBase netherCaveGenerator;
	private final MapGenBase ravineGenerator;

	public ChunkProviderErebus(World world, long seed) {
		worldObj = world;

		rand = new Random(seed);

		netherNoiseGen1 = new NoiseGeneratorOctaves(rand, 16);
		netherNoiseGen2 = new NoiseGeneratorOctaves(rand, 16);
		netherNoiseGen3 = new NoiseGeneratorOctaves(rand, 8);
		noiseGen4 = new NoiseGeneratorOctaves(rand, 4);
		netherNoiseGen6 = new NoiseGeneratorOctaves(rand, 10);
		netherNoiseGen7 = new NoiseGeneratorOctaves(rand, 16);
		stoneNoise = new double[256];

		netherCaveGenerator = new MapGenCavesHell();
		ravineGenerator = new MapGenErebusRavine();
	}

	public void generateTerrain(int x, int z, byte[] blocks) {
		byte byte0 = 4;
		byte byte1 = 32;
		int i = byte0 + 1;
		byte byte2 = 17;
		int j = byte0 + 1;
		biomesForGeneration = worldObj.getWorldChunkManager().getBiomesForGeneration(biomesForGeneration, x * 4 - 2, z * 4 - 2, i + 5, j + 5);
		noiseArray = initializeNoiseField(noiseArray, x * byte0, 0, z * byte0, i, byte2, j);

		for (int k = 0; k < byte0; k++)
			for (int l = 0; l < byte0; l++)
				for (int i1 = 0; i1 < 16; i1++) {
					double d = 0.125D;
					double d1 = noiseArray[((k + 0) * j + l + 0) * byte2 + i1 + 0];
					double d2 = noiseArray[((k + 0) * j + l + 1) * byte2 + i1 + 0];
					double d3 = noiseArray[((k + 1) * j + l + 0) * byte2 + i1 + 0];
					double d4 = noiseArray[((k + 1) * j + l + 1) * byte2 + i1 + 0];
					double d5 = (noiseArray[((k + 0) * j + l + 0) * byte2 + i1 + 1] - d1) * d;
					double d6 = (noiseArray[((k + 0) * j + l + 1) * byte2 + i1 + 1] - d2) * d;
					double d7 = (noiseArray[((k + 1) * j + l + 0) * byte2 + i1 + 1] - d3) * d;
					double d8 = (noiseArray[((k + 1) * j + l + 1) * byte2 + i1 + 1] - d4) * d;

					for (int j1 = 0; j1 < 8; j1++) {
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for (int k1 = 0; k1 < 4; k1++) {
							int l1 = k1 + k * 4 << 11 | 0 + l * 4 << 7 | i1 * 8 + j1;
							char c = '\200';
							double d14 = 0.25D;
							double d15 = d10;
							double d16 = (d11 - d10) * d14;

							for (int i2 = 0; i2 < 4; i2++) {
								int j2 = 0;

								// Underground Water
								if (i1 * 8 + j1 < byte1)
									j2 = ModBlocks.umberstone.blockID;

								if (d15 > 0.0D)
									j2 = ModBlocks.umberstone.blockID;

								blocks[l1] = (byte) j2;
								l1 += c;
								d15 += d16;
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
	}

	@Override
	public Chunk loadChunk(int x, int z) {
		return provideChunk(x, z);
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		rand.setSeed(x * 341873128712L + z * 132897987541L);
		byte[] blocks = new byte[32768];
		biomesForGeneration = worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, x * 16, z * 16, 16, 16);
		generateTerrain(x, z, blocks);
		replaceBlocksForBiome(x, z, blocks, biomesForGeneration);

		netherCaveGenerator.generate(this, worldObj, x, z, blocks);
		ravineGenerator.generate(this, worldObj, x, z, blocks);

		Chunk chunk = new Chunk(worldObj, blocks, x, z);
		byte[] biomeArrayReference = chunk.getBiomeArray();

		for (int k = 0; k < biomeArrayReference.length; ++k)
			biomeArrayReference[k] = (byte) biomesForGeneration[k].biomeID;

		chunk.generateSkylightMap();
		chunk.resetRelightChecks();
		return chunk;
	}

	private double[] initializeNoiseField(double[] par1ArrayOfDouble, int x, int y, int z, int sizeX, int sizeY, int sizeZ) {
		if (par1ArrayOfDouble == null)
			par1ArrayOfDouble = new double[sizeX * sizeY * sizeZ];

		double d = 684.41200000000003D;
		double d1 = 2053.2359999999999D;
		noiseData4 = netherNoiseGen6.generateNoiseOctaves(noiseData4, x, y, z, sizeX, 1, sizeZ, 1.0D, 0.0D, 1.0D);
		noiseData5 = netherNoiseGen7.generateNoiseOctaves(noiseData5, x, y, z, sizeX, 1, sizeZ, 100D, 0.0D, 100D);
		noiseData1 = netherNoiseGen3.generateNoiseOctaves(noiseData1, x, y, z, sizeX, sizeY, sizeZ, d / 80D, d1 / 60D, d / 80D);
		noiseData2 = netherNoiseGen1.generateNoiseOctaves(noiseData2, x, y, z, sizeX, sizeY, sizeZ, d, d1, d);
		noiseData3 = netherNoiseGen2.generateNoiseOctaves(noiseData3, x, y, z, sizeX, sizeY, sizeZ, d, d1, d);
		int i = 0;
		int j = 0;
		double ad[] = new double[sizeY];

		for (int k = 0; k < sizeY; k++) {
			ad[k] = Math.cos(k * Math.PI * 6D / sizeY) * 2D;
			double d2 = k;

			if (k > sizeY / 2)
				d2 = sizeY - 1 - k;

			if (d2 < 4D) {
				d2 = 4D - d2;
				ad[k] -= d2 * d2 * d2 * 10D;
			}
		}

		for (int l = 0; l < sizeX; l++)
			for (int i1 = 0; i1 < sizeZ; i1++) {
				double d3 = (noiseData4[j] + 256D) / 512D;

				if (d3 > 1.0D)
					d3 = 1.0D;

				double d4 = 0.0D;
				double d5 = noiseData5[j] / 8000D;

				if (d5 < 0.0D)
					d5 = -d5;

				d5 = d5 * 3D - 3D;

				if (d5 < 0.0D) {
					d5 /= 2D;

					if (d5 < -1D)
						d5 = -1D;

					d5 /= 1.3999999999999999D;
					d5 /= 2D;
					d3 = 0.0D;
				} else {
					if (d5 > 1.0D)
						d5 = 1.0D;

					d5 /= 6D;
				}

				d3 += 0.5D;
				d5 = d5 * sizeY / 16D;
				j++;

				for (int j1 = 0; j1 < sizeY; j1++) {
					double d6 = 0.0D;
					double d7 = ad[j1];
					double d8 = noiseData2[i] / 512D;
					double d9 = noiseData3[i] / 512D;
					double d10 = (noiseData1[i] / 10D + 1.0D) / 2D;

					if (d10 < 0.0D)
						d6 = d8;
					else if (d10 > 1.0D)
						d6 = d9;
					else
						d6 = d8 + (d9 - d8) * d10;

					d6 -= d7;

					if (j1 > sizeY - 4) {
						double d11 = (j1 - (sizeY - 4)) / 3F;
						d6 = d6 * (1.0D - d11) + -10D * d11;
					}

					if (j1 < d4) {
						double d12 = (d4 - j1) / 4D;

						if (d12 < 0.0D)
							d12 = 0.0D;

						if (d12 > 1.0D)
							d12 = 1.0D;

						d6 = d6 * (1.0D - d12) + -10D * d12;
					}

					par1ArrayOfDouble[i] = d6;
					i++;
				}
			}

		return par1ArrayOfDouble;
	}

	public void replaceBlocksForBiome(int x, int z, byte[] blocks, BiomeGenBase[] biomes) {
		ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, x, z, blocks, biomes);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.getResult() == Result.DENY)
			return;

		byte var5 = 0;
		double var6 = 0.03125D;
		stoneNoise = noiseGen4.generateNoiseOctaves(stoneNoise, x * 16, z * 16, 0, 16, 16, 1, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);

		for (int xInChunk = 0; xInChunk < 16; ++xInChunk)
			for (int zInChunk = 0; zInChunk < 16; ++zInChunk) {
				BiomeGenBase biome = biomes[zInChunk + xInChunk * 16];
				float var11 = biome.getFloatTemperature();
				int var12 = (int) (stoneNoise[xInChunk + zInChunk * 16] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
				int var13 = -1;
				byte topBlock = biome.topBlock;
				byte fillerBlock = biome.fillerBlock;

				for (int yInChunk = 127; yInChunk >= 0; --yInChunk) {
					int var17 = (zInChunk * 16 + xInChunk) * 128 + yInChunk;

					if (yInChunk <= 0 + rand.nextInt(5) || yInChunk >= 127 - rand.nextInt(5))
						blocks[var17] = (byte) Block.bedrock.blockID;
					else {
						byte var18 = blocks[var17];

						if (var18 == 0)
							var13 = -1;
						else if (var18 == ModBlocks.umberstone.blockID || var18 == ModBlocks.umberstoneID - 256)
							if (var13 == -1) {
								if (var12 <= 0) {
									topBlock = 0;
									fillerBlock = (byte) ModBlocks.umberstone.blockID;
								} else if (yInChunk >= var5 - 4 && yInChunk <= var5 + 1) {
									topBlock = biome.topBlock;
									fillerBlock = biome.fillerBlock;
								}

								if (yInChunk < var5 && topBlock == 0)
									if (var11 < 0.15F)
										topBlock = (byte) Block.ice.blockID;
									else
										topBlock = (byte) Block.waterStill.blockID;

								var13 = var12;

								if (yInChunk >= var5 - 1)
									blocks[var17] = topBlock;
								else
									blocks[var17] = fillerBlock;
							} else if (var13 > 0) {
								--var13;
								blocks[var17] = fillerBlock;

								if (var13 == 0 && fillerBlock == Block.sand.blockID) {
									var13 = rand.nextInt(4);
									fillerBlock = (byte) Block.sandStone.blockID;
								}
							}
					}
				}
			}
	}

	@Override
	public void populate(IChunkProvider chunkProvider, int x, int z) {
		BlockSand.fallInstantly = true;

		int worldCoordX = x * 16;
		int worldCoordZ = z * 16;

		BiomeGenBase b = worldObj.getBiomeGenForCoords(worldCoordX, worldCoordZ);
		if (b instanceof BiomeGenBaseErebus) {
			BiomeGenBaseErebus biome = (BiomeGenBaseErebus) b;
			biome.generateTerrain(worldObj, rand, chunkProvider, worldCoordX, worldCoordZ);
			biome.generateDefault(worldObj, rand, chunkProvider, worldCoordX, worldCoordZ);
		}

		for (int attempt = 0; attempt < 14; ++attempt)
			new WorldGenSpiderDungeons().generate(worldObj, rand, worldCoordX + rand.nextInt(16) + 8, rand.nextInt(128), worldCoordZ + rand.nextInt(16) + 8);

		BlockSand.fallInstantly = false;
	}

	@Override
	public void recreateStructures(int x, int z) {
	}

	@Override
	public ChunkPosition findClosestStructure(World world, String structureIdentifier, int x, int y, int z) {
		return null;
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) {
		BiomeGenBase biome = worldObj.getBiomeGenForCoords(x, z);
		return biome == null ? null : biome.getSpawnableList(creatureType);
	}

	@Override
	public String makeString() {
		return "ErebusRandomLevelSource";
	}

	@Override
	public boolean chunkExists(int x, int z) {
		return true;
	}

	@Override
	public boolean saveChunks(boolean mode, IProgressUpdate progressUpdate) {
		return true;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public void saveExtraData() {
	}
}
