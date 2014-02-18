package erebus;

import net.minecraft.world.biome.BiomeGenBase;
import erebus.world.biomes.BiomeGenBaseErebus;
import erebus.world.biomes.BiomeGenBetweenlands;
import erebus.world.biomes.BiomeGenElysianFields;
import erebus.world.biomes.BiomeGenFungalForest;
import erebus.world.biomes.BiomeGenSubterraneanSavannah;
import erebus.world.biomes.BiomeGenUlteriorOutback;
import erebus.world.biomes.BiomeGenUndergroundJungle;
import erebus.world.biomes.BiomeGenVolcanicDesert;
import erebus.world.loot.WeightedList;

public class ModBiomes {
	public static WeightedList<BiomeGenBaseErebus> biomeList = new WeightedList<BiomeGenBaseErebus>();
	
	public static BiomeGenBase undergroundJungle;
	public static BiomeGenBase volcanicDesert;
	public static BiomeGenBase subterraneanSavannah;
	public static BiomeGenBase elysianFields;
	public static BiomeGenBase ulteriorOutback;
	public static BiomeGenBase fungalForest;
	public static BiomeGenBase betweenlands;
	
	public static int undergroundJungleID;
	public static int volcanicDesertID;
	public static int subterraneanSavannahID;
	public static int elysianFieldsID;
	public static int ulteriorOutbackID;
	public static int fungalForestID;
	public static int betweenlandsID;

	public static void init() {
		undergroundJungle = new BiomeGenUndergroundJungle(undergroundJungleID).setWeight(25).setColor(5470985).setTemperatureRainfall(1.35F, 0.9F).setBiomeName("Undergound Jungle");
		volcanicDesert = new BiomeGenVolcanicDesert(volcanicDesertID).setWeight(15).setColor(5470985).setDisableRain().setTemperatureRainfall(2.2F, 0.2F).setBiomeName("Volcanic Desert");
		subterraneanSavannah = new BiomeGenSubterraneanSavannah(subterraneanSavannahID).setWeight(22).setColor(5470985).setDisableRain().setTemperatureRainfall(0.95F, 0.05F).setBiomeName("Subterranean Savannah");
		elysianFields = new BiomeGenElysianFields(elysianFieldsID).setWeight(20).setColor(8365877).setDisableRain().setTemperatureRainfall(0.85F, 0.5F).setBiomeName("Elysian Fields");
		ulteriorOutback = new BiomeGenUlteriorOutback(ulteriorOutbackID).setWeight(15).setBiomeName("Ulterior Outback");
		fungalForest = new BiomeGenFungalForest(fungalForestID).setWeight(10).setBiomeName("Fungal Forest");
		betweenlands = new BiomeGenBetweenlands(betweenlandsID).setWeight(15).setBiomeName("Betweenlands");
		
		// TODO
		/*BiomeDictionary.registerBiomeType(undergroundJungle, BiomeDictionary.Type.JUNGLE);
		BiomeDictionary.registerBiomeType(volcanicDesert, BiomeDictionary.Type.DESERT);
		BiomeDictionary.registerBiomeType(subterraneanSavannah, BiomeDictionary.Type.PLAINS);
		BiomeDictionary.registerBiomeType(elysianFields, BiomeDictionary.Type.PLAINS);*/
	}
}
