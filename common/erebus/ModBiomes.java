package erebus;

import erebus.world.biomes.BiomeBaseErebus;
import erebus.world.biomes.BiomeBetweenlands;
import erebus.world.biomes.BiomeElysianFields;
import erebus.world.biomes.BiomeFungalForest;
import erebus.world.biomes.BiomeSubterraneanSavannah;
import erebus.world.biomes.BiomeUlteriorOutback;
import erebus.world.biomes.BiomeUndergroundJungle;
import erebus.world.biomes.BiomeVolcanicDesert;
import erebus.world.biomes.sub.BiomeLake;
import erebus.world.loot.WeightedList;

public class ModBiomes {
	public static WeightedList<BiomeBaseErebus> biomeList = new WeightedList<BiomeBaseErebus>();
	
	public static BiomeBaseErebus undergroundJungle;
	public static BiomeBaseErebus volcanicDesert;
	public static BiomeBaseErebus subterraneanSavannah;
	public static BiomeBaseErebus elysianFields;
	public static BiomeBaseErebus ulteriorOutback;
	public static BiomeBaseErebus fungalForest;
	public static BiomeBaseErebus betweenlands;
	
	public static int undergroundJungleID;
	public static int volcanicDesertID;
	public static int subterraneanSavannahID;
	public static int elysianFieldsID;
	public static int ulteriorOutbackID;
	public static int fungalForestID;
	public static int betweenlandsID;
	
	public static BiomeBaseErebus jungleSubLake;
	public static BiomeBaseErebus jungleSubAsperGrove;
	public static BiomeBaseErebus desertSubCharredForest;
	public static BiomeBaseErebus savannahSubRockyWasteland;
	public static BiomeBaseErebus savannahSubAsperGrove;
	public static BiomeBaseErebus savannahSubSteppe;
	
	public static int jungleSubLakeID;
	public static int jungleSubAsperGroveID;
	public static int desertSubCharredForestID;
	public static int savannahSubRockyWastelandID;
	public static int savannahSubAsperGroveID;
	public static int savannahSubSteppeID;

	public static void init() {
		undergroundJungle = new BiomeUndergroundJungle(undergroundJungleID);
		volcanicDesert = new BiomeVolcanicDesert(volcanicDesertID);
		subterraneanSavannah = new BiomeSubterraneanSavannah(subterraneanSavannahID);
		elysianFields = new BiomeElysianFields(elysianFieldsID);
		ulteriorOutback = new BiomeUlteriorOutback(ulteriorOutbackID);
		fungalForest = new BiomeFungalForest(fungalForestID);
		betweenlands = new BiomeBetweenlands(betweenlandsID);
		
		jungleSubLake = new BiomeLake(jungleSubLakeID);
	}
}
