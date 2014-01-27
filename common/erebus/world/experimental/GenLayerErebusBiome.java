package erebus.world.experimental;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerErebusBiome extends GenLayerErebus {

	private final BiomeGenBase[] allowedBiomes;

	public GenLayerErebusBiome(long seed, GenLayerErebus parentGenLayer, WorldType worldType) {
		super(seed);
		allowedBiomes = worldType.getBiomesForWorldType();
		parent = parentGenLayer;
	}

	@Override
	public int[] getInts(int par1, int par2, int par3, int par4) {
		int[] var5 = parent.getInts(par1, par2, par3, par4);
		int[] var6 = IntCache.getIntCache(par3 * par4);

		for (int var7 = 0; var7 < par4; ++var7)
			for (int var8 = 0; var8 < par3; ++var8) {
				initChunkSeed(var8 + par1, var7 + par2);
				int var9 = var5[var8 + var7 * par3];

				if (var9 == 0)
					var6[var8 + var7 * par3] = 0;
				else if (var9 == BiomeGenBase.mushroomIsland.biomeID)
					var6[var8 + var7 * par3] = var9;
				else if (var9 == 1)
					var6[var8 + var7 * par3] = allowedBiomes[nextInt(allowedBiomes.length)].biomeID;
				else {
					int var10 = allowedBiomes[nextInt(allowedBiomes.length)].biomeID;

					if (var10 == BiomeGenBase.taiga.biomeID)
						var6[var8 + var7 * par3] = var10;
					else
						var6[var8 + var7 * par3] = BiomeGenBase.icePlains.biomeID;
				}
			}

		return var6;
	}
}
