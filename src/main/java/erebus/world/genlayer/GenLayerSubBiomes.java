package erebus.world.genlayer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerSubBiomes extends GenLayerErebus {

	public Biome biome;
	public Biome subBiome;

	public GenLayerSubBiomes(long seed, GenLayer parentGenLayer, Biome biomeIn, Biome subBiomeIn) {
		super(seed);
		parent = parentGenLayer;
		biome = biomeIn;
		subBiome = subBiomeIn;
	}

	@Override
	public int[] getInts(int x, int z, int sizeX, int sizeZ) {
		int[] currentBiomeInts = parent.getInts(x - 1, z - 1, sizeX + 2, sizeZ + 2);
		int[] biomeInts = IntCache.getIntCache(sizeX * sizeZ);

		for (int zz = 0; zz < sizeZ; ++zz) {
			for (int xx = 0; xx < sizeX; ++xx) {
				initChunkSeed(xx + x, zz + z);

				biomeInts[xx + zz * sizeX] = currentBiomeInts[xx + 1 + (zz + 1) * (sizeX + 2)];

				boolean surrounded = true;

				for (int xo = -1; xo <= 1; xo++) {
					for (int zo = -1; zo <= 1; zo++) {
						if (xo != 0 || zo != 0) {
							int biomeID = currentBiomeInts[xx + 1 + xo + (zz + 1 + zo) * (sizeX + 2)];
							Biome biome = Biome.getBiome(biomeID);
							if (biome != this.biome) {
								surrounded = false;
								break;
							}
						}
					}
				}

				if (surrounded && this.nextInt(10) == 0) {
					biomeInts[xx + zz * sizeX] = Biome.getIdForBiome(this.subBiome);
				}
			}
		}
		return biomeInts;
	}
}
