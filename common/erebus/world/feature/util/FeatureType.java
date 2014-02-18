package erebus.world.feature.util;
import erebus.ModBiomes;
import erebus.world.biomes.BiomeBaseErebus;

public enum FeatureType{
	AMBER_GROUND, AMBER_UMBERSTONE, REDGEM;
	
	public final boolean canGenerateIn(BiomeBaseErebus biome){
		switch(this){
			case AMBER_GROUND:
			case AMBER_UMBERSTONE:
				return biome==ModBiomes.undergroundJungle || biome==ModBiomes.subterraneanSavannah;
			default:
				return true;
		}
	}
}
