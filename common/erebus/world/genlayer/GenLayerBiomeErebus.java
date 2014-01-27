package erebus.world.genlayer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import erebus.ModBiomes;

public class GenLayerBiomeErebus extends GenLayer {

	private final BiomeGenBase[] allowedBiomes;

	public GenLayerBiomeErebus(long seed, GenLayer parentGenLayer) {
		super(seed);
		allowedBiomes = new BiomeGenBase[] { ModBiomes.underjungle, ModBiomes.underdesert, ModBiomes.undersavannah };
		parent = parentGenLayer;
	}

	@Override
	public int[] getInts(int par1, int par2, int par3, int par4) {
		int[] aint1 = IntCache.getIntCache(par3 * par4);

		for (int i1 = 0; i1 < par4; ++i1)
			for (int j1 = 0; j1 < par3; ++j1) {
				initChunkSeed(j1 + par1, i1 + par2);
				BiomeGenBase biome = allowedBiomes[nextInt(allowedBiomes.length)];
				aint1[j1 + i1 * par3] = biome.biomeID;
			}
		return aint1;
	}
}
