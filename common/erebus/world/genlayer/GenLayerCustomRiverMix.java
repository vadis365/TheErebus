package erebus.world.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerCustomRiverMix extends GenLayer{
	private GenLayer biomePatternGeneratorChain;

	public GenLayerCustomRiverMix(long par1, GenLayer par3GenLayer){
		super(par1);
		this.biomePatternGeneratorChain=par3GenLayer;
	}

	@Override
	public void initWorldGenSeed(long par1){
		this.biomePatternGeneratorChain.initWorldGenSeed(par1);
		super.initWorldGenSeed(par1);
	}

	@Override
	public int[] getInts(int x, int z, int sizeX, int sizeZ){
		int[] biomeInts=biomePatternGeneratorChain.getInts(x,z,sizeX,sizeZ);
		int[] newBiomeInts=IntCache.getIntCache(sizeX*sizeZ);

		for(int i1=0; i1<sizeX*sizeZ; ++i1){
			newBiomeInts[i1]=biomeInts[i1];
		}

		return newBiomeInts;
	}
}