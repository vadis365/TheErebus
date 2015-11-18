package erebus;

import erebus.world.biomes.BiomeBaseErebus;
import erebus.world.biomes.BiomeElysianFields;
import erebus.world.biomes.BiomeFungalForest;
import erebus.world.biomes.BiomeSubmergedSwamp;
import erebus.world.biomes.BiomeSubterraneanSavannah;
import erebus.world.biomes.BiomeUlteriorOutback;
import erebus.world.biomes.BiomeUndergroundJungle;
import erebus.world.biomes.BiomeVolcanicDesert;
import erebus.world.loot.WeightedList;
import net.minecraft.world.biome.BiomeGenBase;

public class ModBiomes {
	public static WeightedList<BiomeBaseErebus> biomeList = new WeightedList<BiomeBaseErebus>();

	public static BiomeBaseErebus undergroundJungle;
	public static BiomeBaseErebus volcanicDesert;
	public static BiomeBaseErebus subterraneanSavannah;
	public static BiomeBaseErebus elysianFields;
	public static BiomeBaseErebus ulteriorOutback;
	public static BiomeBaseErebus fungalForest;
	public static BiomeBaseErebus submergedSwamp;

	public static int undergroundJungleID = 100;
	public static int volcanicDesertID = 101;
	public static int subterraneanSavannahID = 102;
	public static int elysianFieldsID = 103;
	public static int ulteriorOutbackID = 104;
	public static int fungalForestID = 105;
	public static int submergedSwampID = 106;
	public static int fieldsSubForestID = 107;

	public static BiomeBaseErebus jungleSubLake;
	public static BiomeBaseErebus jungleSubAsperGrove;
	public static BiomeBaseErebus desertSubCharredForest;
	public static BiomeBaseErebus savannahSubRockyWasteland;
	public static BiomeBaseErebus savannahSubAsperGrove;
	public static BiomeBaseErebus savannahSubSteppe;
	public static BiomeBaseErebus fieldsSubForest;

	public static int jungleSubLakeID;
	public static int jungleSubAsperGroveID;
	public static int desertSubCharredForestID;
	public static int savannahSubRockyWastelandID;
	public static int savannahSubAsperGroveID;
	public static int savannahSubSteppeID;

	public static void init() {
		for (int id : new int[] { undergroundJungleID, volcanicDesertID, subterraneanSavannahID, elysianFieldsID, ulteriorOutbackID, fungalForestID, submergedSwampID, fieldsSubForestID }) {
			if (id >= 128 || id < 0)
				throw new IllegalArgumentException("Erebus biome IDs cannot be higher than 127 or smaller than 0!");
			BiomeGenBase biome = BiomeGenBase.getBiomeGenArray()[id];
			if (biome != null)
				throw new IllegalArgumentException("Erebus can not use biome ID " + id + " because it's being used by " + biome + ". Please choose a different one.");
		}

		// CREATE BIOMES

		undergroundJungle = new BiomeUndergroundJungle(undergroundJungleID);
		volcanicDesert = new BiomeVolcanicDesert(volcanicDesertID);
		subterraneanSavannah = new BiomeSubterraneanSavannah(subterraneanSavannahID);
		elysianFields = new BiomeElysianFields(elysianFieldsID);
		ulteriorOutback = new BiomeUlteriorOutback(ulteriorOutbackID);
		fungalForest = new BiomeFungalForest(fungalForestID);
		submergedSwamp = new BiomeSubmergedSwamp(submergedSwampID);
		fieldsSubForest = new BiomeElysianFields.BiomeElysianForest(fieldsSubForestID);

		// MUTATIONS

		undergroundJungle.createMutation();
		volcanicDesert.createMutation();
		subterraneanSavannah.createMutation();
		elysianFields.createMutation();
		ulteriorOutback.createMutation();
		fungalForest.createMutation();
		submergedSwamp.createMutation();

		fieldsSubForest.createMutation();
	}

	public static void postInit() {
		for (int id : new int[] { undergroundJungleID, volcanicDesertID, subterraneanSavannahID, elysianFieldsID, ulteriorOutbackID, fungalForestID, submergedSwampID, fieldsSubForestID }) {
			BiomeGenBase biome = BiomeGenBase.getBiomeGenArray()[id];
			if (!(biome instanceof BiomeBaseErebus))
				throw new IllegalArgumentException("Erebus biome with id " + id + " was replaced by " + biome + ". This is likely an ID conflict or a bug in another mod!");
		}
	}
}