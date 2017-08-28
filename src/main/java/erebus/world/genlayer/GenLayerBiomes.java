package erebus.world.genlayer;

import java.util.ArrayList;

import erebus.ModBiomes;
import erebus.world.biomes.BiomeBaseErebus;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomes extends GenLayerErebus {

	private final ArrayList<BiomeBaseErebus> biomesToGenerate;
	//private final int totalWeight;

	public GenLayerBiomes(long seed, GenLayer parentGenLayer) {
		super(seed);
		biomesToGenerate = ModBiomes.BIOME_LIST;
		//totalWeight = biomesToGenerate.getTotalWeight();
		parent = parentGenLayer;
	}

	@Override
	public int[] getInts(int x, int z, int sizeX, int sizeZ) {
		parent.getInts(x, z, sizeX, sizeZ);
		int[] ints = IntCache.getIntCache(sizeX * sizeZ);

		for (int zz = 0; zz < sizeZ; ++zz)
			for (int xx = 0; xx < sizeX; ++xx) {
				initChunkSeed(xx + x, zz + z);
				ints[xx + zz * sizeX] = Biome.getIdForBiome(biomesToGenerate.get(nextInt(biomesToGenerate.size()))); //temp hack for now
			}

		return ints;
	}
}
