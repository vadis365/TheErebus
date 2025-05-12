package erebus.registries.world.feature;

import erebus.Erebus;
import erebus.world.feature.misc.config.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFeatureConfigurations {

    public static final DeferredRegister<Feature<?>> CONFIGS = DeferredRegister.create(BuiltInRegistries.FEATURE, Erebus.MODID);

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> AMBER_GROUND_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> AMBER_UMBERSTONE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DESERT_ROCK_GNEISS_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GAS_VENT_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> LAKE_WITH_EDGE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> POND_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> QUICK_SAND_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> RED_GEM_FEATURE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ROCK_SPIKE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ROTTEN_ACACIA_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SAVANNAH_ROCK_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SCORCHED_WOOD_CONFIG;

    static {
        AMBER_GROUND_CONFIG = CONFIGS.register("amber_ground", AmberGroundFeatureConfiguration::new);
        AMBER_UMBERSTONE_CONFIG = CONFIGS.register("amber_umberstone", AmberUmberstoneFeatureConfiguration::new);
        DESERT_ROCK_GNEISS_CONFIG = CONFIGS.register("desert_rock_gneiss", DesertRockGneissFeatureConfiguration::new);
        GAS_VENT_CONFIG = CONFIGS.register("gas_vent", GasVentFeatureConfiguration::new);
        LAKE_WITH_EDGE_CONFIG = CONFIGS.register("lake_with_edge", LakeWithEdgeFeatureConfiguration::new);
        POND_CONFIG = CONFIGS.register("pond", PondFeatureConfiguration::new);
        QUICK_SAND_CONFIG = CONFIGS.register("quick_sand", QuickSandFeatureConfiguration::new);
        RED_GEM_FEATURE_CONFIG = CONFIGS.register("red_gem", RedGemFeatureConfiguration::new);
        ROCK_SPIKE_CONFIG = CONFIGS.register("rock_spike", RockSpikeFeatureConfiguration::new);
        ROTTEN_ACACIA_CONFIG = CONFIGS.register("rotten_acacia", RottenAcaciaFeatureConfiguration::new);
        SAVANNAH_ROCK_CONFIG = CONFIGS.register("savannah_rock", SavannahRockFeatureConfiguration::new);
        SCORCHED_WOOD_CONFIG = CONFIGS.register("scorched_wood", ScorchedWoodFeatureConfiguration::new);
    }
}
