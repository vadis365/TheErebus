package erebus.world.genlayer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

public abstract class GenLayerErebus extends GenLayer {

	private long worldGenSeed;
	protected GenLayerErebus parent;
	private long chunkSeed;
	private long baseSeed;

	public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType) {
		seed+=1;
		
		GenLayerIsland genlayerisland = new GenLayerIsland(1L);
		GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(2000L, genlayerisland);
		GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(1L, genlayerfuzzyzoom);
		GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
		genlayeraddisland = new GenLayerAddIsland(2L, genlayerzoom);
		GenLayerAddSnow genlayeraddsnow = new GenLayerAddSnow(2L, genlayeraddisland);
		genlayerzoom = new GenLayerZoom(2002L, genlayeraddsnow);
		genlayeraddisland = new GenLayerAddIsland(3L, genlayerzoom);
		genlayerzoom = new GenLayerZoom(2003L, genlayeraddisland);
		genlayeraddisland = new GenLayerAddIsland(4L, genlayerzoom);
		//GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland);
		byte biomeSize = 4;

		if (worldType == WorldType.LARGE_BIOMES)
			biomeSize = 6;
		biomeSize = getModdedBiomeSize(worldType, biomeSize);

		GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayeraddisland, 0);
		GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);
		genlayer = GenLayerZoom.magnify(1000L, genlayerriverinit, biomeSize + 2);

		GenLayerRiverErebus genlayerriver = new GenLayerRiverErebus(1L, genlayer);

		GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);

		GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genlayeraddisland, 0);

		GenLayerBiomeErebus genlayerbiome = new GenLayerBiomeErebus(200L, genlayer1);

		genlayer1 = GenLayerZoom.magnify(1000L, genlayerbiome, 2);
		Object object = new GenLayerHills(1000L, genlayer1);

		for (int j = 0; j < biomeSize; ++j) {
			object = new GenLayerZoom((long) (1000 + j), (GenLayer) object);

			if (j == 0)
				object = new GenLayerAddIsland(3L, (GenLayer) object);

			if (j == 1)
				object = new GenLayerShore(1000L, (GenLayer) object);

			/*if (j == 1)
				object = new GenLayerSwampRivers(1000L, (GenLayer) object);*/
		}

		// TODO find the fucker that makes random stripes of mixed biome blocks
		GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, (GenLayer) object);
		GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, genlayersmooth1, genlayersmooth);
		GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
		genlayerrivermix.initWorldGenSeed(seed);
		genlayervoronoizoom.initWorldGenSeed(seed);
		return new GenLayer[] { genlayerrivermix, genlayervoronoizoom, genlayerrivermix };
	}

	public GenLayerErebus(long seed) {
		super(seed);
	}

	@Override
	public void initWorldGenSeed(long seed) {
		worldGenSeed = seed;

		if (parent != null)
			parent.initWorldGenSeed(seed);

		worldGenSeed *= worldGenSeed * 6364136223846793005L + 1442695040888963407L;
		worldGenSeed += baseSeed;
		worldGenSeed *= worldGenSeed * 6364136223846793005L + 1442695040888963407L;
		worldGenSeed += baseSeed;
		worldGenSeed *= worldGenSeed * 6364136223846793005L + 1442695040888963407L;
		worldGenSeed += baseSeed;
	}

	@Override
	public void initChunkSeed(long x, long z) {
		chunkSeed = worldGenSeed;
		chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
		chunkSeed += x;
		chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
		chunkSeed += z;
		chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
		chunkSeed += x;
		chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
		chunkSeed += z;
	}

	@Override
	protected int nextInt(int range) {
		int j = (int) ((chunkSeed >> 24) % range);

		if (j < 0)
			j += range;

		chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
		chunkSeed += worldGenSeed;
		return j;
	}

	@Override
	public abstract int[] getInts(int x, int z, int sizeX, int sizeZ);

	public static byte getModdedBiomeSize(WorldType worldType, byte original) {
		WorldTypeEvent.BiomeSize event = new WorldTypeEvent.BiomeSize(worldType, original);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		return event.newSize;
	}
}
