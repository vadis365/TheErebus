package erebus;

import java.util.Random;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.BiomeProperties;
import net.minecraftforge.fml.common.registry.GameRegistry;
import erebus.lib.Reference;
import erebus.world.biomes.BiomeBaseErebus;
import erebus.world.biomes.BiomeElysianFields;
import erebus.world.biomes.BiomeFungalForest;
import erebus.world.biomes.BiomeSubmergedSwamp;
import erebus.world.biomes.BiomeSubterraneanSavannah;
import erebus.world.biomes.BiomeUlteriorOutback;
import erebus.world.biomes.BiomeUndergroundJungle;
import erebus.world.biomes.BiomeVolcanicDesert;
import erebus.world.loot.WeightedList;

public class ModBiomes {
	public static WeightedList<BiomeBaseErebus> biomeList = new WeightedList<BiomeBaseErebus>();
	public static BiomeBaseErebus undergroundJungle;
	public static BiomeBaseErebus volcanicDesert;
	public static BiomeBaseErebus subterraneanSavannah;
	public static BiomeBaseErebus elysianFields;
	public static BiomeBaseErebus ulteriorOutback;
	public static BiomeBaseErebus fungalForest;
	public static BiomeBaseErebus submergedSwamp;

	public static int undergroundJungleID;
	public static int volcanicDesertID;
	public static int subterraneanSavannahID;
	public static int elysianFieldsID;
	public static int ulteriorOutbackID;
	public static int fungalForestID;
	public static int submergedSwampID;

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
	public static int fieldsSubForestID;

	public static void init() {
		for (int id : new int[] { undergroundJungleID, volcanicDesertID, subterraneanSavannahID, elysianFieldsID, ulteriorOutbackID, fungalForestID, submergedSwampID, jungleSubLakeID, jungleSubAsperGroveID, desertSubCharredForestID, savannahSubRockyWastelandID, savannahSubAsperGroveID, savannahSubSteppeID, fieldsSubForestID })
			if (id >= 128)
				throw new RuntimeException("Erebus biome IDs cannot be higher than 127!");

		// CREATE BIOMES

		undergroundJungle = new BiomeUndergroundJungle(new BiomeProperties("Underground Jungle"));
		volcanicDesert = new BiomeVolcanicDesert(new BiomeProperties("Volcanic Desert"));
		subterraneanSavannah = new BiomeSubterraneanSavannah(new BiomeProperties("Subterranean Savannah"));
		elysianFields = new BiomeElysianFields(new BiomeProperties("Elysian Fields"));
		ulteriorOutback = new BiomeUlteriorOutback(new BiomeProperties("Ulterior Outback"));
		fungalForest = new BiomeFungalForest(new BiomeProperties("Fungal Forest"));
		submergedSwamp = new BiomeSubmergedSwamp(new BiomeProperties("Submerged Swamp"));
		fieldsSubForest = new BiomeElysianFields.BiomeElysianForest(new BiomeProperties("Elysian Forest"));
	/*	GameRegistry.register(undergroundJungle.setRegistryName(new ResourceLocation(Reference.MOD_ID, "Underground Jungle")));
		GameRegistry.register(volcanicDesert.setRegistryName(new ResourceLocation(Reference.MOD_ID, "Volcanic Desert")));
		GameRegistry.register(subterraneanSavannah.setRegistryName(new ResourceLocation(Reference.MOD_ID, "Subterranean Savannah")));
		GameRegistry.register(elysianFields.setRegistryName(new ResourceLocation(Reference.MOD_ID, "Elysian Fields")));
		GameRegistry.register(ulteriorOutback.setRegistryName(new ResourceLocation(Reference.MOD_ID, "Ulterior Outback")));
		GameRegistry.register(fungalForest.setRegistryName(new ResourceLocation(Reference.MOD_ID, "Fungal Forest")));
		GameRegistry.register(submergedSwamp.setRegistryName(new ResourceLocation(Reference.MOD_ID, "Submerged Swamp")));
		GameRegistry.register(fieldsSubForest.setRegistryName(new ResourceLocation(Reference.MOD_ID, "Elysian Forest")));
		*/
		BiomeGenBase.registerBiome(undergroundJungleID, "erebus:undergroundJungle", undergroundJungle);
		BiomeGenBase.registerBiome(volcanicDesertID, "erebus:volcanicDesert", volcanicDesert);
		BiomeGenBase.registerBiome(subterraneanSavannahID, "erebus:subterraneanSavannah", subterraneanSavannah);
		BiomeGenBase.registerBiome(elysianFieldsID, "erebus:elysianFields", elysianFields);
		BiomeGenBase.registerBiome(ulteriorOutbackID, "erebus:ulteriorOutback", ulteriorOutback);
		BiomeGenBase.registerBiome(fungalForestID, "erebus:fungalForest", fungalForest);
		BiomeGenBase.registerBiome(fieldsSubForestID, "erebus:fieldsSubForest", fieldsSubForest);
		BiomeGenBase.registerBiome(submergedSwampID, "erebus:submergedSwamp", submergedSwamp);
		// MUTATIONS

		undergroundJungle.isMutation();
		volcanicDesert.isMutation();
		subterraneanSavannah.isMutation();
		elysianFields.isMutation();
		ulteriorOutback.isMutation();
		fungalForest.isMutation();
		submergedSwamp.isMutation();

		fieldsSubForest.isMutation();
		
		System.out.println("undergroundJungleID: " + BiomeGenBase.getIdForBiome(undergroundJungle));
		System.out.println("volcanicDesertID: " + BiomeGenBase.getIdForBiome(volcanicDesert));
		System.out.println("subterraneanSavannahID: " + BiomeGenBase.getIdForBiome(subterraneanSavannah));
		System.out.println("elysianFieldsID: " + BiomeGenBase.getIdForBiome(elysianFields));
		System.out.println("ulteriorOutbackID: " + BiomeGenBase.getIdForBiome(ulteriorOutback));
		System.out.println("fungalForestID: " + BiomeGenBase.getIdForBiome(fungalForest));
		System.out.println("fieldsSubForestID: " + BiomeGenBase.getIdForBiome(fieldsSubForest));
		System.out.println("submergedSwampID: " + BiomeGenBase.getIdForBiome(submergedSwamp));
	}
}
