package erebus;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import erebus.world.biomes.BiomeGenBaseErebus;
import erebus.world.biomes.BiomeGenElysianFields;
import erebus.world.biomes.BiomeGenSubterraneanSavannah;
import erebus.world.biomes.BiomeGenUndergroundJungle;
import erebus.world.biomes.BiomeGenVolcanicDesert;
import erebus.world.loot.WeightedList;

public class ModBiomes {
	public static WeightedList<BiomeGenBaseErebus> biomeList = new WeightedList<BiomeGenBaseErebus>();
	
	public static BiomeGenBase undergroundJungle;
	public static BiomeGenBase volcanicDesert;
	public static BiomeGenBase subterraneanSavannah;
	public static BiomeGenBase elysianFields;
	
	public static int undergroundJungleID;
	public static int volcanicDesertID;
	public static int subterraneanSavannahID;
	public static int elysianFieldsID;

	public static void init() {
		undergroundJungle = new BiomeGenUndergroundJungle(undergroundJungleID).setColor(5470985).func_76733_a(5470985).setTemperatureRainfall(1.35F, 0.9F).setBiomeName("Undergound Jungle");
		volcanicDesert = new BiomeGenVolcanicDesert(volcanicDesertID).setColor(5470985).func_76733_a(5470985).setDisableRain().setTemperatureRainfall(2.2F, 0.2F).setBiomeName("Volcanic Desert");
		subterraneanSavannah = new BiomeGenSubterraneanSavannah(subterraneanSavannahID).setColor(5470985).func_76733_a(5470985).setDisableRain().setTemperatureRainfall(0.95F, 0.05F).setBiomeName("Subterranean Savannah");
		elysianFields = new BiomeGenElysianFields(elysianFieldsID).setColor(8365877).func_76733_a(8365877).setDisableRain().setTemperatureRainfall(0.1F, 0.52F).setBiomeName("Elysian Fields");
		
		BiomeDictionary.registerBiomeType(undergroundJungle, BiomeDictionary.Type.JUNGLE);
		BiomeDictionary.registerBiomeType(volcanicDesert, BiomeDictionary.Type.DESERT);
		BiomeDictionary.registerBiomeType(subterraneanSavannah, BiomeDictionary.Type.PLAINS);
		BiomeDictionary.registerBiomeType(elysianFields, BiomeDictionary.Type.PLAINS);
	}
}
