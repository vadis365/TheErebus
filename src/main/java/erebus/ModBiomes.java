package erebus;

import java.util.ArrayList;

import erebus.lib.Reference;
import erebus.world.biomes.BiomeBaseErebus;
import erebus.world.biomes.BiomeElysianFields;
import erebus.world.biomes.BiomeFungalForest;
import erebus.world.biomes.BiomeSubmergedSwamp;
import erebus.world.biomes.BiomeSubterraneanSavannah;
import erebus.world.biomes.BiomeUlteriorOutback;
import erebus.world.biomes.BiomeUndergroundJungle;
import erebus.world.biomes.BiomeVolcanicDesert;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@SuppressWarnings("WeakerAccess")
@ObjectHolder(Reference.MOD_ID)
public class ModBiomes {
	public static ArrayList<BiomeBaseErebus> BIOME_LIST = new ArrayList<BiomeBaseErebus>();
	// CREATE BIOMES
	public static BiomeBaseErebus UNDERGROUND_JUNGLE;
	public static BiomeBaseErebus VOLCANIC_DESERT;
	public static BiomeBaseErebus SUBTERRANEAN_SAVANNAH;
	public static BiomeBaseErebus ELYSIAN_FIELDS;
	public static BiomeBaseErebus ULTERIOR_OUTBACK;
	public static BiomeBaseErebus FUNGAL_FOREST;
	public static BiomeBaseErebus SUBMERGED_SWAMP;
	public static BiomeBaseErebus FIELDS_SUB_FOREST;
	
	public static void init() {
		
		UNDERGROUND_JUNGLE = new BiomeUndergroundJungle(new BiomeProperties("Underground Jungle"));
		VOLCANIC_DESERT = new BiomeVolcanicDesert(new BiomeProperties("Volcanic Desert"));
		SUBTERRANEAN_SAVANNAH = new BiomeSubterraneanSavannah(new BiomeProperties("Subterranean Savannah"));
		ELYSIAN_FIELDS = new BiomeElysianFields(new BiomeProperties("Elysian Fields"));
		ULTERIOR_OUTBACK = new BiomeUlteriorOutback(new BiomeProperties("Ulterior Outback"));
		FUNGAL_FOREST = new BiomeFungalForest(new BiomeProperties("Fungal Forest"));
		SUBMERGED_SWAMP = new BiomeSubmergedSwamp(new BiomeProperties("Submerged Swamp"));
		FIELDS_SUB_FOREST = new BiomeElysianFields.BiomeElysianForest(new BiomeProperties("Elysian Forest"));
		
		// MUTATIONS
		UNDERGROUND_JUNGLE.getMutationForBiome(UNDERGROUND_JUNGLE);
		VOLCANIC_DESERT.getMutationForBiome(VOLCANIC_DESERT);
		SUBTERRANEAN_SAVANNAH.getMutationForBiome(SUBTERRANEAN_SAVANNAH);
		ELYSIAN_FIELDS.getMutationForBiome(ELYSIAN_FIELDS);
		ULTERIOR_OUTBACK.getMutationForBiome(ULTERIOR_OUTBACK);
		FUNGAL_FOREST.getMutationForBiome(FUNGAL_FOREST);
		SUBMERGED_SWAMP.getMutationForBiome(SUBMERGED_SWAMP);
		FIELDS_SUB_FOREST.getMutationForBiome(FIELDS_SUB_FOREST);
	}

	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	public static class RegistrationHandler {

		@SubscribeEvent
		public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
			ModBiomes.init();
			final IForgeRegistry<Biome> registry = event.getRegistry();
			
			registerBiome(registry, UNDERGROUND_JUNGLE, "underground_jungle", BiomeManager.BiomeType.WARM, 22, BiomeDictionary.Type.HOT, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.JUNGLE);
			registerBiome(registry, VOLCANIC_DESERT, "volcanic_desert", BiomeManager.BiomeType.WARM, 16, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
			registerBiome(registry, SUBTERRANEAN_SAVANNAH, "subterranean_savannah", BiomeManager.BiomeType.WARM, 20, BiomeDictionary.Type.SAVANNA);
			registerBiome(registry, ELYSIAN_FIELDS, "elysian_fields", BiomeManager.BiomeType.WARM, 20, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.PLAINS);
			registerBiome(registry, ULTERIOR_OUTBACK, "ulterior_outback", BiomeManager.BiomeType.WARM, 15, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY);
			registerBiome(registry, FUNGAL_FOREST, "fungal_forest", BiomeManager.BiomeType.WARM, 12, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.MUSHROOM, BiomeDictionary.Type.SPOOKY);
			registerBiome(registry, SUBMERGED_SWAMP, "submerged_swamp", BiomeManager.BiomeType.WARM, 20, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER, BiomeDictionary.Type.SWAMP);
			registerBiome(registry, FIELDS_SUB_FOREST, "fields_sub_forest", BiomeManager.BiomeType.WARM, 0, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MAGICAL);
		}

		private static <T extends Biome> void registerBiome(final IForgeRegistry<Biome> registry, final T biome, final String biomeName, final BiomeManager.BiomeType biomeType, final int weight, final BiomeDictionary.Type... types) {
			registry.register(biome.setRegistryName(Reference.MOD_ID, biomeName));
			BiomeDictionary.addTypes(biome, types);
			//BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));
			for(int x = 0; x < weight; x++)
				BIOME_LIST.add((BiomeBaseErebus) biome); //temp hack for now
		}
	}
}
