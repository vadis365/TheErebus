package erebus.registries.world.feature.config;

import erebus.registries.blocks.ModBlocks;
import erebus.world.feature.misc.config.*;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;

import static erebus.registries.world.feature.ModFeatures.CONFIGS;

public class DecorationFeatureConfigs {
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> AMBER_GROUND_POPULATION_CONFIG = CONFIGS.register("amber_ground_population", () -> new AmberPopulationFeature(true));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> AMBER_UMBERSTONE_POPULATION_CONFIG = CONFIGS.register("amber_umberstone_population", () -> new AmberPopulationFeature(false));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> RED_GEM_POPULATION_CONFIG = CONFIGS.register("red_gem_population", RedGemPopulationFeature::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> HANGING_WEB_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_DUST_LAYER_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_SURFACE_CONFIG;

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> AMBER_GROUND_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> AMBER_UMBERSTONE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DESERT_ROCK_GNEISS_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DESERT_ROCK_GNEISS_POPULATION_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GAS_VENT_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> LAVA_LAKE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ACID_LAKE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> WATER_LAKE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_BROWN_SMALL_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_BROWN_SMALL_POPULATION_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_BROWN_MEDIUM_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_BROWN_MEDIUM_POPULATION_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_BROWN_LARGE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_BROWN_LARGE_POPULATION_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_RED_SMALL_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_RED_SMALL_POPULATION_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_RED_MEDIUM_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_RED_MEDIUM_POPULATION_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_RED_LARGE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_RED_LARGE_POPULATION_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> CEILING_LAVA_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SCORCHED_WOOD_POPULATION_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SWAMP_MUD_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> POND_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> POND_POPULATION_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> QUICK_SAND_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> RED_GEM_FEATURE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ROCK_SPIKE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ROCK_SPIKE_POPULATION_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ROTTEN_ACACIA_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SAVANNAH_ROCK_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SCORCHED_WOOD_CONFIG;

    static {
        HANGING_WEB_CONFIG = CONFIGS.register("hanging_web", HangingWebFeatureConfiguration::new);
        PETRIFIED_DUST_LAYER_CONFIG = CONFIGS.register("petrified_dust_layer", PetrifiedDustLayerFeatureConfiguration::new);
        PETRIFIED_SURFACE_CONFIG = CONFIGS.register("petrified_surface", PetrifiedSurfaceFeatureConfiguration::new);
        AMBER_GROUND_CONFIG = CONFIGS.register("amber_ground", AmberGroundFeatureConfiguration::new);
        AMBER_UMBERSTONE_CONFIG = CONFIGS.register("amber_umberstone", AmberUmberstoneFeatureConfiguration::new);
        DESERT_ROCK_GNEISS_POPULATION_CONFIG = CONFIGS.register("desert_rock_gneiss_population", DesertRockGneissPopulationFeatureConfiguration::new);
        DESERT_ROCK_GNEISS_CONFIG = CONFIGS.register("desert_rock_gneiss", DesertRockGneissFeatureConfiguration::new);
        GAS_VENT_CONFIG = CONFIGS.register("gas_vent", GasVentPopulationFeatureConfiguration::new);
        LAVA_LAKE_CONFIG = CONFIGS.register("lava_lake", () -> new LakePopulationFeature(false));
        ACID_LAKE_CONFIG = CONFIGS.register("acid_lake", () -> new LakePopulationFeature(true));
        WATER_LAKE_CONFIG = CONFIGS.register("water_lake", () -> new LakeWithEdgeFeatureConfiguration(Blocks.WATER, ModBlocks.MUD));

        PETRIFIED_TREE_BROWN_SMALL_CONFIG = CONFIGS.register("petrified_tree_brown_small", () -> new PetrifiedTreeFeatureConfiguration(
                UniformInt.of(6, 10),
                1,
                ModBlocks.PETRIFIED_BARK_BROWN
        ));
        PETRIFIED_TREE_BROWN_MEDIUM_CONFIG = CONFIGS.register("petrified_tree_brown_medium", () -> new PetrifiedTreeFeatureConfiguration(
                UniformInt.of(11, 14),
                2,
                ModBlocks.PETRIFIED_BARK_BROWN,
                ModBlocks.PETRIFIED_LOG_INNER,
                ModBlocks.ORE_PETRIFIED_QUARTZ
        ));
        PETRIFIED_TREE_BROWN_LARGE_CONFIG = CONFIGS.register("petrified_tree_brown_large", () -> new PetrifiedTreeFeatureConfiguration(
                UniformInt.of(16, 25),
                3,
                ModBlocks.PETRIFIED_BARK_BROWN,
                ModBlocks.PETRIFIED_LOG_INNER,
                ModBlocks.ORE_PETRIFIED_QUARTZ
        ));

        PETRIFIED_TREE_RED_SMALL_CONFIG = CONFIGS.register("petrified_tree_red_small", () -> new PetrifiedTreeFeatureConfiguration(
                UniformInt.of(6, 10),
                1,
                ModBlocks.PETRIFIED_BARK_RED
        ));
        PETRIFIED_TREE_RED_MEDIUM_CONFIG = CONFIGS.register("petrified_tree_red_medium", () -> new PetrifiedTreeFeatureConfiguration(
                UniformInt.of(11, 14),
                2,
                ModBlocks.PETRIFIED_BARK_RED,
                ModBlocks.PETRIFIED_LOG_INNER,
                ModBlocks.ORE_PETRIFIED_QUARTZ
        ));
        PETRIFIED_TREE_RED_LARGE_CONFIG = CONFIGS.register("petrified_tree_red_large", () -> new PetrifiedTreeFeatureConfiguration(
                UniformInt.of(16, 25),
                3,
                ModBlocks.PETRIFIED_BARK_RED,
                ModBlocks.PETRIFIED_LOG_INNER,
                ModBlocks.ORE_PETRIFIED_QUARTZ
        ));

        PETRIFIED_TREE_BROWN_SMALL_POPULATION_CONFIG = CONFIGS.register("petrified_tree_brown_small_population", () -> new PetrifiedTreePopulationFeatureConfiguration(true, PETRIFIED_TREE_BROWN_SMALL_CONFIG));
        PETRIFIED_TREE_BROWN_MEDIUM_POPULATION_CONFIG = CONFIGS.register("petrified_tree_brown_medium_population", () -> new PetrifiedTreePopulationFeatureConfiguration(false, PETRIFIED_TREE_BROWN_MEDIUM_CONFIG));
        PETRIFIED_TREE_BROWN_LARGE_POPULATION_CONFIG = CONFIGS.register("petrified_tree_brown_large_population", () -> new PetrifiedTreePopulationFeatureConfiguration(false, PETRIFIED_TREE_BROWN_LARGE_CONFIG));
        PETRIFIED_TREE_RED_SMALL_POPULATION_CONFIG = CONFIGS.register("petrified_tree_red_small_population", () -> new PetrifiedTreePopulationFeatureConfiguration(true, PETRIFIED_TREE_RED_SMALL_CONFIG));
        PETRIFIED_TREE_RED_MEDIUM_POPULATION_CONFIG = CONFIGS.register("petrified_tree_red_medium_population", () -> new PetrifiedTreePopulationFeatureConfiguration(false, PETRIFIED_TREE_RED_MEDIUM_CONFIG));
        PETRIFIED_TREE_RED_LARGE_POPULATION_CONFIG = CONFIGS.register("petrified_tree_red_large_population", () -> new PetrifiedTreePopulationFeatureConfiguration(false, PETRIFIED_TREE_RED_LARGE_CONFIG));
        CEILING_LAVA_CONFIG = CONFIGS.register("ceiling_lava", CeilingLavaFeatureConfiguration::new);
        SCORCHED_WOOD_POPULATION_CONFIG = CONFIGS.register("scorched_wood_population", ScorchedWoodPopulationFeature::new);
        SWAMP_MUD_CONFIG = CONFIGS.register("swamp_mud", SwampMudFeatureConfiguration::new);
        POND_CONFIG = CONFIGS.register("pond", PondFeatureConfiguration::new);
        POND_POPULATION_CONFIG = CONFIGS.register("pond_population", PondPopulationFeature::new);
        QUICK_SAND_CONFIG = CONFIGS.register("quick_sand", QuickSandPopulationFeatureConfiguration::new);
        RED_GEM_FEATURE_CONFIG = CONFIGS.register("red_gem", RedGemFeatureConfiguration::new);
        ROCK_SPIKE_POPULATION_CONFIG = CONFIGS.register("rock_spike_population", RockSpikePopulationFeatureConfiguration::new);
        ROCK_SPIKE_CONFIG = CONFIGS.register("rock_spike", RockSpikeFeatureConfiguration::new);
        ROTTEN_ACACIA_CONFIG = CONFIGS.register("rotten_acacia", RottenAcaciaPopulationFeatureConfiguration::new);
        SAVANNAH_ROCK_CONFIG = CONFIGS.register("savannah_rock", SavannahRockPopulationFeatureConfiguration::new);
        SCORCHED_WOOD_CONFIG = CONFIGS.register("scorched_wood", ScorchedWoodFeatureConfiguration::new);
    }

    public static void init() {
    }
}
