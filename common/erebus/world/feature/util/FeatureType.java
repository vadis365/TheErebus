package erebus.world.feature.util;
import erebus.ModBiomes;
import erebus.world.biomes.BiomeGenBaseErebus;

public enum FeatureType{
	AMBER_GROUND, AMBER_UMBERSTONE;
	
	public final boolean canGenerateIn(BiomeGenBaseErebus biome){
		switch(this){
			case AMBER_GROUND:
			case AMBER_UMBERSTONE:
				return biome==ModBiomes.undergroundJungle || biome==ModBiomes.subterraneanSavannah;
		}
		
		return true;
	}
}
