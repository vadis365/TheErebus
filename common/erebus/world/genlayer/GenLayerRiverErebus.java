package erebus.world.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRiverErebus extends GenLayer {

	public GenLayerRiverErebus(long seed, GenLayer parentGenLayer) {
		super(seed);
		super.parent = parentGenLayer;
	}

	@Override
	public int[] getInts(int par1, int par2, int par3, int par4) {
		int[] aint1 = IntCache.getIntCache(par3 * par4);

		for (int i2 = 0; i2 < par4; ++i2)
			for (int j2 = 0; j2 < par3; ++j2)
				aint1[j2 + i2 * par3] = -1;

		return aint1;
	}
}
