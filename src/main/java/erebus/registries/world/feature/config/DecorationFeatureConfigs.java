package erebus.registries.world.feature.config;

import erebus.registries.blocks.providers.OreBlocks;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.UmberstoneBlocks;
import erebus.world.feature.misc.config.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Random;

import static erebus.registries.world.feature.ModFeatures.CONFIGS;

public class DecorationFeatureConfigs {

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> AMBER_GROUND_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> AMBER_UMBERSTONE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DESERT_ROCK_GNEISS_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GAS_VENT_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> LAVA_LAKE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> WATER_LAKE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_BROWN_SMALL_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_BROWN_MEDIUM_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_BROWN_LARGE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_RED_SMALL_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_RED_MEDIUM_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_TREE_RED_LARGE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> POND_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> QUICK_SAND_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> RED_GEM_FEATURE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ROCK_SPIKE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ROTTEN_ACACIA_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SAVANNAH_ROCK_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SCORCHED_WOOD_CONFIG;
    private static final Random random = new Random();

    static {
        AMBER_GROUND_CONFIG = CONFIGS.register("amber_ground", AmberGroundFeatureConfiguration::new);
        AMBER_UMBERSTONE_CONFIG = CONFIGS.register("amber_umberstone", AmberUmberstoneFeatureConfiguration::new);
        DESERT_ROCK_GNEISS_CONFIG = CONFIGS.register("desert_rock_gneiss", DesertRockGneissFeatureConfiguration::new);
        GAS_VENT_CONFIG = CONFIGS.register("gas_vent", GasVentFeatureConfiguration::new);
        LAVA_LAKE_CONFIG = CONFIGS.register("lava_lake", () -> new LakeWithEdgeFeatureConfiguration(Blocks.LAVA, UmberstoneBlocks.VOLCANIC_ROCK));
        WATER_LAKE_CONFIG = CONFIGS.register("water_lake", () -> new LakeWithEdgeFeatureConfiguration(Blocks.WATER, OtherBlocks.MUD));

        PETRIFIED_TREE_BROWN_SMALL_CONFIG = CONFIGS.register("petrified_tree_brown_small", () -> new PetrifiedTreeFeatureConfiguration(
                6 + random.nextInt(5),
                1,
                UmberstoneBlocks.PETRIFIED_BARK_BROWN
        ));
        PETRIFIED_TREE_BROWN_MEDIUM_CONFIG = CONFIGS.register("petrified_tree_brown_medium", () -> new PetrifiedTreeFeatureConfiguration(
                11 + random.nextInt(4),
                2,
                UmberstoneBlocks.PETRIFIED_BARK_BROWN,
                UmberstoneBlocks.PETRIFIED_LOG_INNER,
                OreBlocks.ORE_PETRIFIED_QUARTZ
        ));
        PETRIFIED_TREE_BROWN_LARGE_CONFIG = CONFIGS.register("petrified_tree_brown_large", () -> new PetrifiedTreeFeatureConfiguration(
                16 + random.nextInt(10),
                3,
                UmberstoneBlocks.PETRIFIED_BARK_BROWN,
                UmberstoneBlocks.PETRIFIED_LOG_INNER,
                OreBlocks.ORE_PETRIFIED_QUARTZ
        ));

        PETRIFIED_TREE_RED_SMALL_CONFIG = CONFIGS.register("petrified_tree_red_small", () -> new PetrifiedTreeFeatureConfiguration(
                6 + random.nextInt(5),
                1,
                UmberstoneBlocks.PETRIFIED_BARK_RED
        ));
        PETRIFIED_TREE_RED_MEDIUM_CONFIG = CONFIGS.register("petrified_tree_red_medium", () -> new PetrifiedTreeFeatureConfiguration(
                11 + random.nextInt(4),
                2,
                UmberstoneBlocks.PETRIFIED_BARK_RED,
                UmberstoneBlocks.PETRIFIED_LOG_INNER,
                OreBlocks.ORE_PETRIFIED_QUARTZ
        ));
        PETRIFIED_TREE_RED_LARGE_CONFIG = CONFIGS.register("petrified_tree_red_large", () -> new PetrifiedTreeFeatureConfiguration(
                16 + random.nextInt(10),
                3,
                UmberstoneBlocks.PETRIFIED_BARK_RED,
                UmberstoneBlocks.PETRIFIED_LOG_INNER,
                OreBlocks.ORE_PETRIFIED_QUARTZ
        ));

        POND_CONFIG = CONFIGS.register("pond", () -> new PondFeatureConfiguration(10));
        QUICK_SAND_CONFIG = CONFIGS.register("quick_sand", QuickSandFeatureConfiguration::new);
        RED_GEM_FEATURE_CONFIG = CONFIGS.register("red_gem", RedGemFeatureConfiguration::new);
        ROCK_SPIKE_CONFIG = CONFIGS.register("rock_spike", RockSpikeFeatureConfiguration::new);
        ROTTEN_ACACIA_CONFIG = CONFIGS.register("rotten_acacia", RottenAcaciaFeatureConfiguration::new);
        SAVANNAH_ROCK_CONFIG = CONFIGS.register("savannah_rock", SavannahRockFeatureConfiguration::new);
        SCORCHED_WOOD_CONFIG = CONFIGS.register("scorched_wood", ScorchedWoodFeatureConfiguration::new);
    }

    public static void init() {
    }
}
