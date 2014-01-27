package erebus.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import erebus.ModBiomes;
import erebus.world.genlayer.GenLayerErebus;

public class WorldChunkManagerErebus extends WorldChunkManager {

	private final float hellTemperature;
	private final List biomesToSpawnIn;
	public static ArrayList<BiomeGenBase> allowedBiomes = new ArrayList<BiomeGenBase>(Arrays.asList(ModBiomes.underjungle, ModBiomes.undersavannah));
	private final BiomeCache biomeCache;
	private final GenLayer biomeIndexLayer;
	private final GenLayer genBiomes;

	private final float rainfall;

	public WorldChunkManagerErebus(float temperature, float rain, World world) {
		biomesToSpawnIn = new ArrayList(allowedBiomes);
		hellTemperature = temperature;
		rainfall = rain;
		biomeCache = new BiomeCache(this);
		GenLayer[] layers = GenLayerErebus.initializeAllBiomeGenerators(world.getSeed(), world.getWorldInfo().getTerrainType());
		layers = getModdedBiomeGenerators(world.getWorldInfo().getTerrainType(), world.getSeed(), layers);
		genBiomes = layers[0];
		biomeIndexLayer = layers[1];
	}

	@Override
	public BiomeGenBase getBiomeGenAt(int chunkX, int chunkZ) {
		return biomeCache.getBiomeGenAt(chunkX, chunkZ);
	}

	@Override
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase biomesForGeneration[], int x, int z, int sizeX, int sizeZ) {
		IntCache.resetIntCache();

		if (biomesForGeneration == null || biomesForGeneration.length < sizeX * sizeZ)
			biomesForGeneration = new BiomeGenBase[sizeX * sizeZ];

		int[] var6 = genBiomes.getInts(x, z, sizeX, sizeZ);

		for (int var7 = 0; var7 < sizeX * sizeZ; ++var7)
			biomesForGeneration[var7] = BiomeGenBase.biomeList[var6[var7]];

		return biomesForGeneration;
	}

	@Override
	public float[] getTemperatures(float temperatureArray[], int x, int z, int sizeX, int sizeZ) {
		if (temperatureArray == null || temperatureArray.length < sizeX * sizeZ)
			temperatureArray = new float[sizeX * sizeZ];

		Arrays.fill(temperatureArray, 0, sizeX * sizeZ, hellTemperature);
		return temperatureArray;
	}

	@Override
	public float[] getRainfall(float rainfallArray[], int x, int z, int sizeX, int sizeZ) {
		if (rainfallArray == null || rainfallArray.length < sizeX * sizeZ)
			rainfallArray = new float[sizeX * sizeZ];

		Arrays.fill(rainfallArray, 0, sizeX * sizeZ, rainfall);
		return rainfallArray;
	}

	@Override
	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase biomesForGeneration[], int x, int z, int sizeX, int sizeZ) {
		return this.getBiomeGenAt(biomesForGeneration, x, z, sizeX, sizeZ, true);
	}

	@Override
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] biomesForGeneration, int x, int z, int sizeX, int sizeZ, boolean useCache) {
		IntCache.resetIntCache();

		if (biomesForGeneration == null || biomesForGeneration.length < sizeX * sizeZ)
			biomesForGeneration = new BiomeGenBase[sizeX * sizeZ];

		if (useCache && sizeX == 16 && sizeZ == 16 && (x & 15) == 0 && (z & 15) == 0) {
			BiomeGenBase[] var9 = biomeCache.getCachedBiomes(x, z);
			System.arraycopy(var9, 0, biomesForGeneration, 0, sizeX * sizeZ);
			return biomesForGeneration;
		} else {
			int[] var7 = biomeIndexLayer.getInts(x, z, sizeX, sizeZ);
			for (int var8 = 0; var8 < sizeX * sizeZ; ++var8)
				biomesForGeneration[var8] = BiomeGenBase.biomeList[var7[var8]];

			return biomesForGeneration;
		}
	}

	@Override
	public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random rand) {
		IntCache.resetIntCache();
		int var6 = par1 - par3 >> 2;
		int var7 = par2 - par3 >> 2;
		int var8 = par1 + par3 >> 2;
		int var9 = par2 + par3 >> 2;
		int var10 = var8 - var6 + 1;
		int var11 = var9 - var7 + 1;
		int[] var12 = genBiomes.getInts(var6, var7, var10, var11);
		ChunkPosition var13 = null;
		int var14 = 0;

		for (int var15 = 0; var15 < var10 * var11; ++var15) {
			int var16 = var6 + var15 % var10 << 2;
			int var17 = var7 + var15 / var10 << 2;
			BiomeGenBase var18 = BiomeGenBase.biomeList[var12[var15]];

			if (par4List.contains(var18) && (var13 == null || rand.nextInt(var14 + 1) == 0)) {
				var13 = new ChunkPosition(var16, 0, var17);
				++var14;
			}
		}

		return var13;
	}

	@Override
	public boolean areBiomesViable(int par1, int par2, int par3, List par4List) {
		IntCache.resetIntCache();
		int var5 = par1 - par3 >> 2;
		int var6 = par2 - par3 >> 2;
		int var7 = par1 + par3 >> 2;
		int var8 = par2 + par3 >> 2;
		int var9 = var7 - var5 + 1;
		int var10 = var8 - var6 + 1;
		int[] var11 = genBiomes.getInts(var5, var6, var9, var10);

		for (int var12 = 0; var12 < var9 * var10; ++var12) {
			BiomeGenBase var13 = BiomeGenBase.biomeList[var11[var12]];

			if (!par4List.contains(var13))
				return false;
		}

		return true;
	}

	@Override
	public List getBiomesToSpawnIn() {
		return biomesToSpawnIn;
	}

	@Override
	public void cleanupCache() {
		biomeCache.cleanupCache();
	}

	@Override
	public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) {
		WorldTypeEvent.InitBiomeGens event = new WorldTypeEvent.InitBiomeGens(worldType, seed, original);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		return event.newBiomeGens;
	}
}
