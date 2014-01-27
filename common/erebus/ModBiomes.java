package erebus;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import erebus.world.biomes.BiomeGenCavern;
import erebus.world.biomes.BiomeGenUndergroundDesert;
import erebus.world.biomes.BiomeGenUndergroundJungle;
import erebus.world.biomes.BiomeGenUndergroundSavannah;

public class ModBiomes {
	public static int jungleID;
	public static int desertID;
	public static int savannahID;
	public static int cavernID;

	public static BiomeGenBase underjungle;
	public static BiomeGenBase underdesert;
	public static BiomeGenBase undersavannah;
	public static BiomeGenBase cavern;

	public static void init() {
		underjungle = new BiomeGenUndergroundJungle(jungleID).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(1.35F, 0.9F).setBiomeName("Undergound Jungle");
		underdesert = new BiomeGenUndergroundDesert(desertID).setColor(5470985).func_76733_a(5470985).setDisableRain().setTemperatureRainfall(2.2F, 0.2F).setBiomeName("Volcanic Desert");
		undersavannah = new BiomeGenUndergroundSavannah(savannahID).setColor(5470985).func_76733_a(5470985).setDisableRain().setTemperatureRainfall(0.95F, 0.05F).setBiomeName("Subterranean Savannah");
		cavern = new BiomeGenCavern(cavernID).setColor(5470985).func_76733_a(5470985).setDisableRain().setTemperatureRainfall(2.2F, 0.05F).setBiomeName("Cavern");

		registerBiomes();
	}

	private static void registerBiomes() {
		BiomeDictionary.registerBiomeType(underjungle, BiomeDictionary.Type.JUNGLE);
		BiomeDictionary.registerBiomeType(underdesert, BiomeDictionary.Type.DESERT);
		BiomeDictionary.registerBiomeType(undersavannah, BiomeDictionary.Type.PLAINS);
		BiomeDictionary.registerBiomeType(cavern, BiomeDictionary.Type.MOUNTAIN);
	}
}
