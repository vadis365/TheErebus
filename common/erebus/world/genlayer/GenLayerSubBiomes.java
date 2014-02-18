package erebus.world.genlayer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import erebus.world.biomes.BiomeBaseErebus;

public class GenLayerSubBiomes extends GenLayer{
	public GenLayerSubBiomes(long seed, GenLayer parentGenLayer){
		super(seed);
		this.parent=parentGenLayer;
	}

	@Override
	public int[] getInts(int x, int z, int sizeX, int sizeZ){
		int[] currentBiomeInts=parent.getInts(x-1,z-1,sizeX+2,sizeZ+2);
		int[] biomeInts=IntCache.getIntCache(sizeX*sizeZ);

		for(int zz=0; zz<sizeZ; ++zz){
			for(int xx=0; xx<sizeX; ++xx){
				initChunkSeed(xx+x,zz+z);
				int biomeID=currentBiomeInts[xx+1+(zz+1)*(sizeX+2)];

				BiomeBaseErebus biome=(BiomeBaseErebus)BiomeGenBase.biomeList[biomeID];
				BiomeBaseErebus subBiome=biome.getRandomSubBiome(nextInt(101));

				if (subBiome==null){
					biomeInts[xx+zz*sizeX]=biomeID;
				}
				else{
					int i2=currentBiomeInts[xx+1+(zz+1-1)*(sizeX+2)];
					int j2=currentBiomeInts[xx+1+1+(zz+1)*(sizeX+2)];
					int k2=currentBiomeInts[xx+1-1+(zz+1)*(sizeX+2)];
					int l2=currentBiomeInts[xx+1+(zz+1+1)*(sizeX+2)];

					if (i2==biomeID&&j2==biomeID&&k2==biomeID&&l2==biomeID){
						biomeInts[xx+zz*sizeX]=subBiome.biomeID;
					}
					else{
						biomeInts[xx+zz*sizeX]=biomeID;
					}
				}
			}
		}

		return biomeInts;
	}
}
