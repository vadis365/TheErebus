package erebus.world.experimental;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;

public abstract class GenLayerErebus extends GenLayer {

	public GenLayerErebus(long seed) {
		super(seed);
	}

	public static GenLayerErebus[] initializeAllBiomeGenerators(long seed, WorldType worldType) {
		GenLayerErebusIsland var3 = new GenLayerErebusIsland(1L);
		GenLayerErebusFuzzyZoom var9 = new GenLayerErebusFuzzyZoom(2000L, var3);
		GenLayerErebusAddIsland var10 = new GenLayerErebusAddIsland(1L, var9);
		GenLayerErebusZoom var11 = new GenLayerErebusZoom(2001L, var10);
		var10 = new GenLayerErebusAddIsland(2L, var11);
		GenLayerErebusAddSnow var12 = new GenLayerErebusAddSnow(2L, var10);
		var11 = new GenLayerErebusZoom(2002L, var12);
		var10 = new GenLayerErebusAddIsland(3L, var11);
		var11 = new GenLayerErebusZoom(2003L, var10);
		var10 = new GenLayerErebusAddIsland(4L, var11);
		GenLayerErebusAddMushroomIsland var16 = new GenLayerErebusAddMushroomIsland(5L, var10);
		byte var4 = 4;

		if (worldType == WorldType.LARGE_BIOMES)
			var4 = 6;
		var4 = getModdedBiomeSize(worldType, var4);

		GenLayerErebus var5 = GenLayerErebusZoom.func_75915_a(1000L, var16, 0);
		GenLayerErebusRiverInit var13 = new GenLayerErebusRiverInit(100L, var5);
		var5 = GenLayerErebusZoom.func_75915_a(1000L, var13, var4 + 2);
		GenLayerErebusRiver var14 = new GenLayerErebusRiver(1L, var5);
		GenLayerErebusSmooth var15 = new GenLayerErebusSmooth(1000L, var14);
		GenLayerErebus var6 = GenLayerErebusZoom.func_75915_a(1000L, var16, 0);
		GenLayerErebusBiome var17 = new GenLayerErebusBiome(200L, var6, worldType);
		var6 = GenLayerErebusZoom.func_75915_a(1000L, var17, 2);
		Object var18 = new GenLayerErebusHills(1000L, var6);

		for (int var7 = 0; var7 < var4; ++var7) {
			var18 = new GenLayerErebusZoom((long) (1000 + var7), (GenLayerErebus) var18);

			if (var7 == 0)
				var18 = new GenLayerErebusAddIsland(3L, (GenLayerErebus) var18);

			if (var7 == 1)
				var18 = new GenLayerErebusShore(1000L, (GenLayerErebus) var18);

			if (var7 == 1)
				var18 = new GenLayerErebusSwampRivers(1000L, (GenLayerErebus) var18);
		}

		GenLayerErebusSmooth var19 = new GenLayerErebusSmooth(1000L, (GenLayerErebus) var18);
		GenLayerErebusRiverMix var20 = new GenLayerErebusRiverMix(100L, var19, var15);
		GenLayerErebusVoronoiZoom var8 = new GenLayerErebusVoronoiZoom(10L, var20);
		var20.initWorldGenSeed(seed);
		var8.initWorldGenSeed(seed);
		return new GenLayerErebus[] { var20, var8, var20 };
	}
}
