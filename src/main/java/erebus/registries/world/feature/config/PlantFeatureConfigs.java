package erebus.registries.world.feature.config;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import erebus.world.feature.mushroom.config.*;
import erebus.world.feature.mushroom.config.population.GiantMushroomPopulationFeatureConfiguration;
import erebus.world.feature.mushroom.config.population.GlowshroomPopulationFeatureConfiguration;
import erebus.world.feature.mushroom.config.population.SmallMushroomPopulationFeatureConfiguration;
import erebus.world.feature.mushroom.config.population.VanillaGiantMushroomPopulationFeatureConfiguration;
import erebus.world.feature.plant.config.*;
import erebus.world.feature.plant.config.population.*;
import erebus.world.feature.tree.population.config.*;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;
import java.util.Map;

import static erebus.registries.world.feature.ModFeatures.CONFIGS;

public class PlantFeatureConfigs {
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SMALL_MUSHROOM_POPULATION_CONFIG = CONFIGS.register("small_mushroom_population", SmallMushroomPopulationFeatureConfiguration::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> JUNGLE_BAMBOO_POPULATION_CONFIG = CONFIGS.register("jungle_bamboo_population", JungleBambooPopulationFeatureConfiguration::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> JUNGLE_TREE_POPULATION_CONFIG = CONFIGS.register("jungle_tree_population", JungleTreePopulationFeatureConfiguration::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> JUNGLE_VINE_POPULATION_CONFIG = CONFIGS.register("jungle_vine_population", JungleVinePopulationFeatureConfiguration::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> JUNGLE_CROP_POPULATION_CONFIG = CONFIGS.register("jungle_crop_population", JungleCropPopulationFeatureConfiguration::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DESERT_SHRUB_CONFIG = CONFIGS.register("desert_shrub", DesertShrubFeatureConfiguration::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DROUGHTED_SHRUB_CONFIG = CONFIGS.register("droughted_shrub", DroughtedShrubFeatureConfiguration::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> OUTBACK_GRASS_CONFIG = CONFIGS.register("outback_grass", OutbackGrassFeatureConfiguration::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> OUTBACK_ACACIA_CONFIG = CONFIGS.register("outback_acacia_population", () -> new OutbackTreePopulationFeatureConfiguration(OutbackTreePopulationFeatureConfiguration.Kind.ACACIA));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> OUTBACK_BALSAM_CONFIG = CONFIGS.register("outback_balsam_population", () -> new OutbackTreePopulationFeatureConfiguration(OutbackTreePopulationFeatureConfiguration.Kind.BALSAM));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> OUTBACK_EUCALYPTUS_CONFIG = CONFIGS.register("outback_eucalyptus_population", () -> new OutbackTreePopulationFeatureConfiguration(OutbackTreePopulationFeatureConfiguration.Kind.EUCALYPTUS));

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> OUTBACK_SOIL_CONFIG = CONFIGS.register("outback_soil", OutbackSoilFeatureConfiguration::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SAVANNAH_ACACIA_CONFIG = CONFIGS.register("savannah_acacia_population", () -> new SavannahTreePopulationFeatureConfiguration(SavannahTreePopulationFeatureConfiguration.Kind.ACACIA));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SAVANNAH_ASPER_CONFIG = CONFIGS.register("savannah_asper_population", () -> new SavannahTreePopulationFeatureConfiguration(SavannahTreePopulationFeatureConfiguration.Kind.ASPER));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SAVANNAH_BAOBAB_CONFIG = CONFIGS.register("savannah_baobab_population", () -> new SavannahTreePopulationFeatureConfiguration(SavannahTreePopulationFeatureConfiguration.Kind.BAOBAB));

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PETRIFIED_FALLEN_LOGS_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> WILD_MANDRAKE_CONFIG;

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ALGAE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> BAMBOO_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> BAMBOO_SAVANNAH_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> BIG_LOGS_X_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> BIG_LOGS_Z_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> FERN_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_RANDOM_CONFIG;
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
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DARK_CAPPED_MUSHROOM_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DUTCH_CAP_MUSHROOM_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GRANDMAS_SHOES_MUSHROOM_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> KAIZERS_FINGERS_MUSHROOM_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SARCASTIC_CZECH_MUSHROOM_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> MELON_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SWAMP_REEDS_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> MOSS_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SWAMP_UPPER_MOSS_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> MOULD_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> NETTLE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PRICKLY_PEAR_POPULATION_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> PRICKLY_PEAR_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ROTTEN_TREE_STUMP_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SWAMP_BUSH_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> TURNIP_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> VINES_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_MUSHROOM_POPULATION_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> VANILLA_GIANT_MUSHROOM_POPULATION_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GLOWSHROOM_POPULATION_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> FALLEN_LOG_POPULATION_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> WEEPING_BLUEBELL_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> TALL_BLOOM_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SUNDEW_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> FIDDLE_HEAD_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SWAMP_PLANT_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> MIRE_CORAL_CONFIG;

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GRASS_CONFIG;

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GIANT_FLOWER_POPULATION_CONFIG;

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DARK_FRUIT_VINE_POPULATION_CONFIG;

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> CYPRESS_POPULATION_CONFIG;

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ELYSIAN_FOREST_POPULATION_CONFIG;

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SWAMP_TREE_POPULATION_CONFIG;

    static {
        PETRIFIED_FALLEN_LOGS_CONFIG = CONFIGS.register("petrified_fallen_logs", PetrifiedLogPopulationFeatureConfiguration::new);
        WILD_MANDRAKE_CONFIG = CONFIGS.register("wild_mandrake", WildMandrakePopulationFeatureConfiguration::new);
        SWAMP_TREE_POPULATION_CONFIG = CONFIGS.register("swamp_tree_population", SwampTreePopulationFeatureConfiguration::new);
        ELYSIAN_FOREST_POPULATION_CONFIG = CONFIGS.register("elysian_forest_population", ElysianForestPopulationFeatureConfiguration::new);
        CYPRESS_POPULATION_CONFIG = CONFIGS.register("cypress_population", CypressPopulationFeatureConfiguration::new);
        DARK_FRUIT_VINE_POPULATION_CONFIG = CONFIGS.register("dark_fruit_vine_population", DarkFruitVinePopulationFeatureConfiguration::new);
        GIANT_FLOWER_POPULATION_CONFIG = CONFIGS.register("giant_flower_population", GiantFlowerPopulationFeatureConfiguration::new);
        GRASS_CONFIG = CONFIGS.register("grass", GrassPopulationFeatureConfiguration::new);
        WEEPING_BLUEBELL_CONFIG = CONFIGS.register("weeping_bluebell", () -> new TallFlowerPopulationFeatureConfiguration(ModBlocks.WEEPING_BLUEBELL, 5));
        FIDDLE_HEAD_CONFIG = CONFIGS.register("fiddle_head", () -> new GroundPlantPopulationFeatureConfiguration(ModBlocks.FIDDLE_HEAD, Map.of(ModBiomes.SUBMERGED_SWAMP_KEY, 16, ModBiomes.UNDERGROUND_JUNGLE_KEY, 50)));
        MIRE_CORAL_CONFIG = CONFIGS.register("mire_coral", () -> new GroundPlantPopulationFeatureConfiguration(ModBlocks.MIRE_CORAL, Map.of(ModBiomes.SUBMERGED_SWAMP_KEY, 4)));
        SWAMP_PLANT_CONFIG = CONFIGS.register("swamp_plant", () -> new GroundPlantPopulationFeatureConfiguration(ModBlocks.SWAMP_PLANT, Map.of(ModBiomes.SUBMERGED_SWAMP_KEY, 40)));
        SUNDEW_CONFIG = CONFIGS.register("sundew", () -> new TallFlowerPopulationFeatureConfiguration(ModBlocks.SUNDEW, 8, List.of(ModBiomes.SUBMERGED_SWAMP_KEY)));
        TALL_BLOOM_CONFIG = CONFIGS.register("tall_bloom", () -> new TallFlowerPopulationFeatureConfiguration(ModBlocks.TALL_BLOOM, 15));
        FALLEN_LOG_POPULATION_CONFIG = CONFIGS.register("fallen_log_population", FallenLogPopulationFeatureConfiguration::new);
        GLOWSHROOM_POPULATION_CONFIG = CONFIGS.register("glowshroom_population", GlowshroomPopulationFeatureConfiguration::new);
        VANILLA_GIANT_MUSHROOM_POPULATION_CONFIG = CONFIGS.register("vanilla_giant_mushroom_population", VanillaGiantMushroomPopulationFeatureConfiguration::new);
        GIANT_MUSHROOM_POPULATION_CONFIG = CONFIGS.register("giant_mushroom_population", GiantMushroomPopulationFeatureConfiguration::new);
        ALGAE_CONFIG = CONFIGS.register("algae", AlgaePopulationFeatureConfiguration::new);
        BAMBOO_CONFIG = CONFIGS.register("bamboo", () -> new BambooFeatureConfiguration(13, false));
        BAMBOO_SAVANNAH_CONFIG = CONFIGS.register("bamboo_savannah", () -> new BambooFeatureConfiguration(7, true));
        BIG_LOGS_X_CONFIG = CONFIGS.register("big_logs_x", () -> new BigLogsFeatureConfiguration(Direction.NORTH, ModBlocks.LOG_ROTTEN));
        BIG_LOGS_Z_CONFIG = CONFIGS.register("big_logs_z", () -> new BigLogsFeatureConfiguration(Direction.EAST, ModBlocks.LOG_ROTTEN));
        FERN_CONFIG = CONFIGS.register("fern", FernPopulationFeatureConfiguration::new);
        GIANT_FLOWER_RANDOM_CONFIG = CONFIGS.register("giant_flower_random", () -> new GiantFlowerFeatureConfiguration());
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
        DARK_CAPPED_MUSHROOM_CONFIG = CONFIGS.register("dark_capped_mushroom", DarkCappedMushroomFeatureConfiguration::new);
        DUTCH_CAP_MUSHROOM_CONFIG = CONFIGS.register("dutch_cap_mushroom", DutchCapMushroomFeatureConfiguration::new);
        GRANDMAS_SHOES_MUSHROOM_CONFIG = CONFIGS.register("grandmas_shoes_mushroom", GrandmasShoesMushroomFeatureConfiguration::new);
        KAIZERS_FINGERS_MUSHROOM_CONFIG = CONFIGS.register("kaizers_fingers_mushroom", KaizersFingersMushroomFeatureConfiguration::new);
        SARCASTIC_CZECH_MUSHROOM_CONFIG = CONFIGS.register("sarcastic_czech_mushroom", SarcasticCzechMushroomFeatureConfiguration::new);
        MELON_CONFIG = CONFIGS.register("melon", MelonFeatureConfiguration::new);
        SWAMP_REEDS_CONFIG = CONFIGS.register("swamp_reeds", SwampReedFeatureConfiguration::new);
        MOSS_CONFIG = CONFIGS.register("moss", () -> new MossPopulationFeatureConfiguration());
        SWAMP_UPPER_MOSS_CONFIG = CONFIGS.register("swamp_upper_moss", () -> new MossPopulationFeatureConfiguration(true));
        MOULD_CONFIG = CONFIGS.register("mould", () -> new MossPatchFeatureConfiguration(ModBlocks.MOULD));
        NETTLE_CONFIG = CONFIGS.register("nettle", NettlePopulationFeatureConfiguration::new);
        PRICKLY_PEAR_POPULATION_CONFIG = CONFIGS.register("prickly_pear_population", PricklyPearPopulationFeatureConfiguration::new);
        PRICKLY_PEAR_CONFIG = CONFIGS.register("prickly_pear", PricklyPearPatchFeatureConfiguration::new);
        ROTTEN_TREE_STUMP_CONFIG = CONFIGS.register("rotten_tree_stump", RottenStumpPopulationFeatureConfiguration::new);
        SWAMP_BUSH_CONFIG = CONFIGS.register("swamp_bush", SwampBushPopulationFeatureConfiguration::new);
        TURNIP_CONFIG = CONFIGS.register("turnip", TurnipFeatureConfiguration::new);
        VINES_CONFIG = CONFIGS.register("vines", SwampVinePopulationFeatureConfiguration::new);
    }

    public static void init() {
    }
}
