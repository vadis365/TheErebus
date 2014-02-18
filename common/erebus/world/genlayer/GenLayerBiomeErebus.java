package erebus.world.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import erebus.ModBiomes;
import erebus.world.biomes.BiomeBaseErebus;
import erebus.world.loot.WeightedList;

public class GenLayerBiomeErebus extends GenLayer{

	private final WeightedList<BiomeBaseErebus> biomesToGenerate;
	private final int biomeAmount;

	public GenLayerBiomeErebus(long seed, GenLayer parentGenLayer){
		super(seed);
		biomesToGenerate=ModBiomes.biomeList;
		biomeAmount=biomesToGenerate.size();
		parent=parentGenLayer;
	}

	@Override
	public int[] getInts(int x, int z, int sizeX, int sizeZ){
		int[] ints=IntCache.getIntCache(sizeX*sizeZ);

		for(int zz=0; zz<sizeZ; ++zz){
			for(int xx=0; xx<sizeX; ++xx){
				initChunkSeed(xx+x,zz+z);
				ints[xx+zz*sizeX]=biomesToGenerate.getRandomItem(nextInt(biomesToGenerate.getTotalWeight())).biomeID;
			}
		}

		return ints;
	}
}
