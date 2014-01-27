package erebus.world.biomes;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerSwampRivers;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerErebus extends GenLayer {

	public GenLayerErebus(long seed) {
		super(seed);
	}

	public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType) {
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
		GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland);
		byte b0 = 4;

		if (worldType == WorldType.LARGE_BIOMES)
			b0 = 6;
		b0 = getModdedBiomeSize(worldType, b0);

		GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayeraddmushroomisland, 0);
		GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);
		genlayer = GenLayerZoom.magnify(1000L, genlayerriverinit, b0 + 2);
		GenLayerRiver genlayerriver = new GenLayerRiver(1L, genlayer);
		GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
		GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genlayeraddmushroomisland, 0);
		GenLayerBiome genlayerbiome = new GenLayerBiome(200L, genlayer1, worldType);
		genlayer1 = GenLayerZoom.magnify(1000L, genlayerbiome, 2);
		Object object = new GenLayerHills(1000L, genlayer1);

		for (int j = 0; j < b0; ++j) {
			object = new GenLayerZoom((long) (1000 + j), (GenLayer) object);

			if (j == 0)
				object = new GenLayerAddIsland(3L, (GenLayer) object);

			if (j == 1)
				object = new GenLayerShore(1000L, (GenLayer) object);

			if (j == 1)
				object = new GenLayerSwampRivers(1000L, (GenLayer) object);
		}

		GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, (GenLayer) object);
		GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, genlayersmooth1, genlayersmooth);
		GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
		genlayerrivermix.initWorldGenSeed(seed);
		genlayervoronoizoom.initWorldGenSeed(seed);
		return new GenLayer[] { genlayerrivermix, genlayervoronoizoom, genlayerrivermix };
	}
}
