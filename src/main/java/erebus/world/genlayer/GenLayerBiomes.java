package erebus.world.genlayer;

import erebus.ModBiomes;
import erebus.world.biomes.BiomeBaseErebus;
import erebus.world.loot.WeightedList;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomes extends GenLayerErebus {

	private final WeightedList<BiomeBaseErebus> biomesToGenerate;
	private final int totalWeight;

	public GenLayerBiomes(long seed, GenLayer parentGenLayer) {
		super(seed);
		biomesToGenerate = ModBiomes.biomeList;
		totalWeight = biomesToGenerate.getTotalWeight();
		parent = parentGenLayer;
	}

	@Override
	public int[] getInts(int x, int z, int sizeX, int sizeZ) {
		parent.getInts(x, z, sizeX, sizeZ);
		int[] ints = IntCache.getIntCache(sizeX * sizeZ);

		for (int zz = 0; zz < sizeZ; ++zz)
			for (int xx = 0; xx < sizeX; ++xx) {
				initChunkSeed(xx + x, zz + z);
				ints[xx + zz * sizeX] = biomesToGenerate.getRandomItem(nextInt(totalWeight)).biomeID;
			}

		return ints;
	}
}
