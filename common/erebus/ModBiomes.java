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
		undergroundJungle = new BiomeGenUndergroundJungle(undergroundJungleID);
		volcanicDesert = new BiomeGenVolcanicDesert(volcanicDesertID);
		subterraneanSavannah = new BiomeGenSubterraneanSavannah(subterraneanSavannahID);
		elysianFields = new BiomeGenElysianFields(elysianFieldsID);
		ulteriorOutback = new BiomeGenUlteriorOutback(ulteriorOutbackID);
		fungalForest = new BiomeGenFungalForest(fungalForestID);
		betweenlands = new BiomeGenBetweenlands(betweenlandsID);
	}
}
