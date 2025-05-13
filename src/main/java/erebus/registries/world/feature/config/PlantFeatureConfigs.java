package erebus.registries.world.feature.config;

import erebus.registries.blocks.providers.PlantBlocks;
import erebus.registries.blocks.providers.WoodBlocks;
import erebus.world.feature.plant.config.*;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;

import static erebus.registries.world.feature.ModFeatures.CONFIGS;

public class PlantFeatureConfigs {

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ALGAE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> BAMBOO_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> BIG_LOGS_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_BLACK_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_RED_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_BROWN_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_BLUE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_PURPLE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_CYAN_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_LIGHT_GRAY_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_GRAY_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_PINK_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_YELLOW_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_LIGHT_BLUE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_MAGENTA_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_ORANGE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_WHITE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_MUSHROOM_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> MELON_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> MOSS_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> MOULD_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> NETTLE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PRICKLY_PEAR_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ROTTEN_TREE_STUMP_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SWAMP_BUSH_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> TURNIP_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> VINES_CONFIG;

    static {
        ALGAE_CONFIG = CONFIGS.register("algae", AlgaeFeatureConfiguration::new);
        BAMBOO_CONFIG = CONFIGS.register("bamboo", () -> new BambooFeatureConfiguration(false, false));
        BIG_LOGS_CONFIG = CONFIGS.register("big_logs", () -> new BigLogsFeatureConfiguration(5, 5, Direction.NORTH, WoodBlocks.LOG_MAHOGANY));
        GIANT_FLOWER_BLACK_CONFIG = CONFIGS.register("giant_flower_black", () -> new GiantFlowerFeatureConfiguration(0));
        GIANT_FLOWER_RED_CONFIG = CONFIGS.register("giant_flower_red", () -> new GiantFlowerFeatureConfiguration(1));
        GIANT_FLOWER_BROWN_CONFIG = CONFIGS.register("giant_flower_brown", () -> new GiantFlowerFeatureConfiguration(2));
        GIANT_FLOWER_BLUE_CONFIG = CONFIGS.register("giant_flower_blue", () -> new GiantFlowerFeatureConfiguration(3));
        GIANT_FLOWER_PURPLE_CONFIG = CONFIGS.register("giant_flower_purple", () -> new GiantFlowerFeatureConfiguration(4));
        GIANT_FLOWER_CYAN_CONFIG = CONFIGS.register("giant_flower_cyan", () -> new GiantFlowerFeatureConfiguration(5));
        GIANT_FLOWER_LIGHT_GRAY_CONFIG = CONFIGS.register("giant_flower_light_gray", () -> new GiantFlowerFeatureConfiguration(6));
        GIANT_FLOWER_GRAY_CONFIG = CONFIGS.register("giant_flower_gray", () -> new GiantFlowerFeatureConfiguration(7));
        GIANT_FLOWER_PINK_CONFIG = CONFIGS.register("giant_flower_pink", () -> new GiantFlowerFeatureConfiguration(8));
        GIANT_FLOWER_YELLOW_CONFIG = CONFIGS.register("giant_flower_yellow", () -> new GiantFlowerFeatureConfiguration(9));
        GIANT_FLOWER_LIGHT_BLUE_CONFIG = CONFIGS.register("giant_flower_light_blue", () -> new GiantFlowerFeatureConfiguration(10));
        GIANT_FLOWER_MAGENTA_CONFIG = CONFIGS.register("giant_flower_magenta", () -> new GiantFlowerFeatureConfiguration(11));
        GIANT_FLOWER_ORANGE_CONFIG = CONFIGS.register("giant_flower_orange", () -> new GiantFlowerFeatureConfiguration(12));
        GIANT_FLOWER_WHITE_CONFIG = CONFIGS.register("giant_flower_white", () -> new GiantFlowerFeatureConfiguration(13));
        GIANT_MUSHROOM_CONFIG = CONFIGS.register("giant_mushroom", GiantMushroomFeatureConfiguration::new);
        MELON_CONFIG = CONFIGS.register("melon", MelonFeatureConfiguration::new);
        MOSS_CONFIG = CONFIGS.register("moss", () -> new MossPatchFeatureConfiguration(PlantBlocks.MOSS_DOWN));
        MOULD_CONFIG = CONFIGS.register("mould", () -> new MossPatchFeatureConfiguration(PlantBlocks.MOULD_DOWN));
        NETTLE_CONFIG = CONFIGS.register("nettle", NettlePatchFeatureConfiguration::new);
        PRICKLY_PEAR_CONFIG = CONFIGS.register("prickly_pear", PricklyPearPatchFeatureConfiguration::new);
        ROTTEN_TREE_STUMP_CONFIG = CONFIGS.register("rotten_tree_stump", () -> new RottenTreeStumpFeatureConfiguration(5, 5));
        SWAMP_BUSH_CONFIG = CONFIGS.register("swamp_bush", SwampBushFeatureConfiguration::new);
        TURNIP_CONFIG = CONFIGS.register("turnip", TurnipFeatureConfiguration::new);
        VINES_CONFIG = CONFIGS.register("vines", VinesFeatureConfiguration::new);
    }

    public static void init() {
    }
}
