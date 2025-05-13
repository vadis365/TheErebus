package erebus.registries.world.feature;

import erebus.registries.helpers.ModFeatureHelpers;
import erebus.world.feature.misc.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static erebus.registries.world.feature.config.DecorationFeatureConfigs.*;

public class DecorationFeatures extends ModFeatureHelpers {
    public static AmberGroundFeature AMBER_GROUND = new AmberGroundFeature();
    public static AmberUmberstoneFeature AMBER_UMBERSTONE = new AmberUmberstoneFeature();
    public static DesertRockGneissFeature DESERT_ROCK_GNEISS = new DesertRockGneissFeature();
    public static GasVentFeature GAS_VENT = new GasVentFeature();
    public static LakeWithEdgeFeature LAVA_LAKE = new LakeWithEdgeFeature("lava_lake");
    public static LakeWithEdgeFeature WATER_LAKE = new LakeWithEdgeFeature("water_lake");
    public static PetrifiedTreeFeature PETRIFIED_TREE_BROWN_SMALL = new PetrifiedTreeFeature("petrified_tree_brown_small", 30);
    public static PetrifiedTreeFeature PETRIFIED_TREE_BROWN_MEDIUM = new PetrifiedTreeFeature("petrified_tree_brown_medium", 5);
    public static PetrifiedTreeFeature PETRIFIED_TREE_BROWN_LARGE = new PetrifiedTreeFeature("petrified_tree_brown_large", 5);
    public static PetrifiedTreeFeature PETRIFIED_TREE_RED_SMALL = new PetrifiedTreeFeature("petrified_tree_red_small", 30);
    public static PetrifiedTreeFeature PETRIFIED_TREE_RED_MEDIUM = new PetrifiedTreeFeature("petrified_tree_red_medium", 5);
    public static PetrifiedTreeFeature PETRIFIED_TREE_RED_LARGE = new PetrifiedTreeFeature("petrified_tree_red_large", 5);
    public static PondFeature POND = new PondFeature();
    public static QuickSandFeature QUICK_SAND = new QuickSandFeature();
    public static RedGemFeature RED_GEM = new RedGemFeature();
    public static RockSpikeFeature ROCK_SPIKE = new RockSpikeFeature();
    public static RottenAcaciaFeature ROTTEN_ACACIA = new RottenAcaciaFeature();
    public static SavannahRockFeature SAVANNAH_ROCK = new SavannahRockFeature();
    public static ScorchedWoodFeature SCORCHED_WOOD = new ScorchedWoodFeature();

    public static void initConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        setConfiguredContext(context);

        registerConfiguredFeatureWithConfig(AMBER_GROUND, AMBER_GROUND_CONFIG);
        registerConfiguredFeatureWithConfig(AMBER_UMBERSTONE, AMBER_UMBERSTONE_CONFIG);
        registerConfiguredFeatureWithConfig(DESERT_ROCK_GNEISS, DESERT_ROCK_GNEISS_CONFIG);
        registerConfiguredFeatureWithConfig(GAS_VENT, GAS_VENT_CONFIG);
        registerConfiguredFeatureWithConfig(WATER_LAKE, WATER_LAKE_CONFIG);
        registerConfiguredFeatureWithConfig(LAVA_LAKE, LAVA_LAKE_CONFIG);
        registerConfiguredFeatureWithConfig(PETRIFIED_TREE_BROWN_SMALL, PETRIFIED_TREE_BROWN_SMALL_CONFIG);
        registerConfiguredFeatureWithConfig(PETRIFIED_TREE_BROWN_MEDIUM, PETRIFIED_TREE_BROWN_MEDIUM_CONFIG);
        registerConfiguredFeatureWithConfig(PETRIFIED_TREE_BROWN_LARGE, PETRIFIED_TREE_BROWN_LARGE_CONFIG);
        registerConfiguredFeatureWithConfig(PETRIFIED_TREE_RED_SMALL, PETRIFIED_TREE_RED_SMALL_CONFIG);
        registerConfiguredFeatureWithConfig(PETRIFIED_TREE_RED_MEDIUM, PETRIFIED_TREE_RED_MEDIUM_CONFIG);
        registerConfiguredFeatureWithConfig(PETRIFIED_TREE_RED_LARGE, PETRIFIED_TREE_RED_LARGE_CONFIG);
        registerConfiguredFeatureWithConfig(POND, POND_CONFIG);
        registerConfiguredFeatureWithConfig(QUICK_SAND, QUICK_SAND_CONFIG);
        registerConfiguredFeatureWithConfig(RED_GEM, RED_GEM_FEATURE_CONFIG);
        registerConfiguredFeatureWithConfig(ROCK_SPIKE, ROCK_SPIKE_CONFIG);
        registerConfiguredFeatureWithConfig(ROTTEN_ACACIA, ROTTEN_ACACIA_CONFIG);
        registerConfiguredFeatureWithConfig(SAVANNAH_ROCK, SAVANNAH_ROCK_CONFIG);
        registerConfiguredFeatureWithConfig(SCORCHED_WOOD, SCORCHED_WOOD_CONFIG);
    }

    public static void initPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        setPlacedContext(context);
        registerPlacedFeature(AMBER_GROUND);
        registerPlacedFeature(AMBER_UMBERSTONE);
        registerPlacedFeature(DESERT_ROCK_GNEISS);
        registerPlacedFeature(GAS_VENT);
        registerPlacedFeature(WATER_LAKE);
        registerPlacedFeature(LAVA_LAKE);
        registerPlacedFeature(PETRIFIED_TREE_BROWN_SMALL);
        registerPlacedFeature(PETRIFIED_TREE_BROWN_MEDIUM);
        registerPlacedFeature(PETRIFIED_TREE_BROWN_LARGE);
        registerPlacedFeature(PETRIFIED_TREE_RED_SMALL);
        registerPlacedFeature(PETRIFIED_TREE_RED_MEDIUM);
        registerPlacedFeature(PETRIFIED_TREE_RED_LARGE);
        registerPlacedFeature(POND);
        registerPlacedFeature(QUICK_SAND);
        registerPlacedFeature(RED_GEM);
        registerPlacedFeature(ROCK_SPIKE);
        registerPlacedFeature(ROTTEN_ACACIA);
        registerPlacedFeature(SAVANNAH_ROCK);
        registerPlacedFeature(SCORCHED_WOOD);
    }
}
