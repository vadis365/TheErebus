package erebus.world.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRiverErebus extends GenLayer {

	public GenLayerRiverErebus(long seed, GenLayer parentGenLayer) {
		super(seed);
		super.parent = parentGenLayer;
	}

	@Override
	public int[] getInts(int x, int z, int sizeX, int sizeZ) {
		int[] ints = IntCache.getIntCache(sizeX * sizeZ);

		for (int zz = 0; zz < sizeZ; ++zz)
			for (int xx = 0; xx < sizeX; ++xx)
				ints[xx + zz * sizeX] = -1;

		return ints;
	}
}
