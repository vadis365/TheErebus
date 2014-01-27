package erebus.world.experimental;

import net.minecraft.world.gen.layer.IntCache;

public class GenLayerErebusFuzzyZoom extends GenLayerErebus {

	public GenLayerErebusFuzzyZoom(long seed, GenLayerErebus parentGenLayer) {
		super(seed);
		super.parent = parentGenLayer;
	}

	@Override
	public int[] getInts(int par1, int par2, int par3, int par4) {
		int var5 = par1 >> 1;
		int var6 = par2 >> 1;
		int var7 = (par3 >> 1) + 3;
		int var8 = (par4 >> 1) + 3;
		int[] var9 = parent.getInts(var5, var6, var7, var8);
		int[] var10 = IntCache.getIntCache(var7 * 2 * var8 * 2);
		int var11 = var7 << 1;
		int var13;

		for (int var12 = 0; var12 < var8 - 1; ++var12) {
			var13 = var12 << 1;
			int var14 = var13 * var11;
			int var15 = var9[0 + (var12 + 0) * var7];
			int var16 = var9[0 + (var12 + 1) * var7];

			for (int var17 = 0; var17 < var7 - 1; ++var17) {
				initChunkSeed(var17 + var5 << 1, var12 + var6 << 1);
				int var18 = var9[var17 + 1 + (var12 + 0) * var7];
				int var19 = var9[var17 + 1 + (var12 + 1) * var7];
				var10[var14] = var15;
				var10[var14++ + var11] = this.choose(var15, var16);
				var10[var14] = this.choose(var15, var18);
				var10[var14++ + var11] = this.choose(var15, var18, var16, var19);
				var15 = var18;
				var16 = var19;
			}
		}

		int[] var20 = IntCache.getIntCache(par3 * par4);

		for (var13 = 0; var13 < par4; ++var13)
			System.arraycopy(var10, (var13 + (par2 & 1)) * (var7 << 1) + (par1 & 1), var20, var13 * par3, par3);

		return var20;
	}

	protected int choose(int par1, int par2) {
		return nextInt(2) == 0 ? par1 : par2;
	}

	protected int choose(int par1, int par2, int par3, int par4) {
		int var5 = nextInt(4);
		return var5 == 0 ? par1 : var5 == 1 ? par2 : var5 == 2 ? par3 : par4;
	}
}
