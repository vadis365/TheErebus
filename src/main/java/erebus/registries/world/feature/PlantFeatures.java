package erebus.registries.world.feature;

import erebus.registries.blocks.providers.PlantBlocks;
import erebus.registries.helpers.ModFeatureHelpers;
import erebus.world.feature.bush.HeartBerryBushFeature;
import erebus.world.feature.bush.JadeBerryBushFeature;
import erebus.world.feature.bush.SwampBerryBushFeature;
import erebus.world.feature.plant.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static erebus.registries.world.feature.config.PlantFeatureConfigs.*;

public class PlantFeatures extends ModFeatureHelpers {

    //NEW
    public static AlgaeFeature ALGAE = new AlgaeFeature("algae");
    public static BambooFeature BAMBOO = new BambooFeature("bamboo");
    public static BigLogsFeature BIG_LOGS = new BigLogsFeature("big_logs");
    public static GiantFlowerFeature GIANT_FLOWER_BLACK = new GiantFlowerFeature("giant_flower_black");
    public static GiantFlowerFeature GIANT_FLOWER_RED = new GiantFlowerFeature("giant_flower_red");
    public static GiantFlowerFeature GIANT_FLOWER_BROWN = new GiantFlowerFeature("giant_flower_brown");
    public static GiantFlowerFeature GIANT_FLOWER_BLUE = new GiantFlowerFeature("giant_flower_blue");
    public static GiantFlowerFeature GIANT_FLOWER_PURPLE = new GiantFlowerFeature("giant_flower_purple");
    public static GiantFlowerFeature GIANT_FLOWER_CYAN = new GiantFlowerFeature("giant_flower_cyan");
    public static GiantFlowerFeature GIANT_FLOWER_LIGHT_GRAY = new GiantFlowerFeature("giant_flower_light_gray");
    public static GiantFlowerFeature GIANT_FLOWER_GRAY = new GiantFlowerFeature("giant_flower_gray");
    public static GiantFlowerFeature GIANT_FLOWER_PINK = new GiantFlowerFeature("giant_flower_pink");
    public static GiantFlowerFeature GIANT_FLOWER_YELLOW = new GiantFlowerFeature("giant_flower_yellow");
    public static GiantFlowerFeature GIANT_FLOWER_LIGHT_BLUE = new GiantFlowerFeature("giant_flower_light_blue");
    public static GiantFlowerFeature GIANT_FLOWER_MAGENTA = new GiantFlowerFeature("giant_flower_magenta");
    public static GiantFlowerFeature GIANT_FLOWER_ORANGE = new GiantFlowerFeature("giant_flower_orange");
    public static GiantFlowerFeature GIANT_FLOWER_WHITE = new GiantFlowerFeature("giant_flower_white");
    public static GiantMushroomFeature GIANT_MUSHROOM = new GiantMushroomFeature("giant_mushroom");
    public static MelonFeature MELON = new MelonFeature("melon");
    public static MossPatchFeature MOSS = new MossPatchFeature("moss");
    public static MossPatchFeature MOULD = new MossPatchFeature("mould");
    public static NettlePatchFeature NETTLE = new NettlePatchFeature("nettle");
    public static PricklyPearPatchFeature PRICKLY_PEAR = new PricklyPearPatchFeature("prickly_pear");
    public static RottenTreeStumpFeature ROTTEN_TREE_STUMP = new RottenTreeStumpFeature("rotten_tree_stump");
    public static SwampBushFeature SWAMP_BUSH = new SwampBushFeature("swamp_bush");
    public static TurnipFeature TURNIP = new TurnipFeature("turnip");
    public static VinesFeature VINES = new VinesFeature("vines");

    // Old
    public static ErebusPlantFeature SWAMP_PLANT = new ErebusPlantFeature("swamp_plant");
    public static ErebusPlantFeature FIRE_BLOOM = new ErebusPlantFeature("fire_bloom");
    public static ErebusPlantFeature FIDDLE_HEAD = new ErebusPlantFeature("fiddle_head");
    public static ErebusPlantFeature BULLRUSH = new ErebusPlantFeature("bullrush");
    public static ErebusPlantFeature WEEPING_BLUEBELL = new ErebusPlantFeature("weeping_bluebell");
    public static ErebusPlantFeature SUNDEW = new ErebusPlantFeature("sundew");
    public static ErebusPlantFeature TALL_BLOOM = new ErebusPlantFeature("tall_bloom");
    public static ErebusPlantFeature TANGLED_STALK = new ErebusPlantFeature("tangled_stalk");
    public static ErebusPlantFeature HIGH_CAPPED_MUSHROOM = new ErebusPlantFeature("high_capped_mushroom");
    public static ErebusPlantFeature FERN = new ErebusPlantFeature("fern");
    public static ErebusPlantFeature TALL_FERN = new ErebusPlantFeature("tall_fern");

    public static SwampBerryBushFeature SWAMP_BERRY_BUSH;
    public static HeartBerryBushFeature HEART_BERRY_BUSH;
    public static JadeBerryBushFeature JADE_BERRY_BUSH;

    public static void initConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        setConfiguredContext(context);
        registerConfiguredFeatureWithConfig(ALGAE, ALGAE_CONFIG);
        registerConfiguredFeatureWithConfig(BAMBOO, BAMBOO_CONFIG);
        registerConfiguredFeatureWithConfig(BIG_LOGS, BIG_LOGS_CONFIG);
        registerConfiguredFeatureWithConfig(GIANT_FLOWER_BLACK, GIANT_FLOWER_BLACK_CONFIG);
        registerConfiguredFeatureWithConfig(GIANT_FLOWER_RED, GIANT_FLOWER_RED_CONFIG);
        registerConfiguredFeatureWithConfig(GIANT_FLOWER_BROWN, GIANT_FLOWER_BROWN_CONFIG);
        registerConfiguredFeatureWithConfig(GIANT_FLOWER_BLUE, GIANT_FLOWER_BLUE_CONFIG);
        registerConfiguredFeatureWithConfig(GIANT_FLOWER_PURPLE, GIANT_FLOWER_PURPLE_CONFIG);
        registerConfiguredFeatureWithConfig(GIANT_FLOWER_CYAN, GIANT_FLOWER_CYAN_CONFIG);
        registerConfiguredFeatureWithConfig(GIANT_FLOWER_LIGHT_GRAY, GIANT_FLOWER_LIGHT_GRAY_CONFIG);
        registerConfiguredFeatureWithConfig(GIANT_FLOWER_GRAY, GIANT_FLOWER_GRAY_CONFIG);
        registerConfiguredFeatureWithConfig(GIANT_FLOWER_PINK, GIANT_FLOWER_PINK_CONFIG);
        registerConfiguredFeatureWithConfig(GIANT_FLOWER_YELLOW, GIANT_FLOWER_YELLOW_CONFIG);
        registerConfiguredFeatureWithConfig(GIANT_FLOWER_LIGHT_BLUE, GIANT_FLOWER_LIGHT_BLUE_CONFIG);
        registerConfiguredFeatureWithConfig(GIANT_FLOWER_MAGENTA, GIANT_FLOWER_MAGENTA_CONFIG);
        registerConfiguredFeatureWithConfig(GIANT_FLOWER_ORANGE, GIANT_FLOWER_ORANGE_CONFIG);
        registerConfiguredFeatureWithConfig(GIANT_FLOWER_WHITE, GIANT_FLOWER_WHITE_CONFIG);
        registerConfiguredFeatureWithConfig(GIANT_MUSHROOM, GIANT_MUSHROOM_CONFIG);
        registerConfiguredFeatureWithConfig(MELON, MELON_CONFIG);
        registerConfiguredFeatureWithConfig(MOSS, MOSS_CONFIG);
        registerConfiguredFeatureWithConfig(MOULD, MOULD_CONFIG);
        registerConfiguredFeatureWithConfig(NETTLE, NETTLE_CONFIG);
        registerConfiguredFeatureWithConfig(PRICKLY_PEAR, PRICKLY_PEAR_CONFIG);
        registerConfiguredFeatureWithConfig(ROTTEN_TREE_STUMP, ROTTEN_TREE_STUMP_CONFIG);
        registerConfiguredFeatureWithConfig(SWAMP_BUSH, SWAMP_BUSH_CONFIG);
        registerConfiguredFeatureWithConfig(TURNIP, TURNIP_CONFIG);
        registerConfiguredFeatureWithConfig(VINES, VINES_CONFIG);

        SWAMP_BERRY_BUSH = new SwampBerryBushFeature();
        HEART_BERRY_BUSH = new HeartBerryBushFeature();
        JADE_BERRY_BUSH = new JadeBerryBushFeature();

        registerConfiguredBush(SWAMP_BERRY_BUSH);
        registerConfiguredBush(HEART_BERRY_BUSH);
        registerConfiguredBush(JADE_BERRY_BUSH);

        registerSimpleConfiguredPlant(context, SWAMP_PLANT, PlantBlocks.SWAMP_PLANT, 64);
        registerSimpleConfiguredPlant(context, FIRE_BLOOM, PlantBlocks.FIRE_BLOOM, 64);
        registerSimpleConfiguredPlant(context, FIDDLE_HEAD, PlantBlocks.FIDDLE_HEAD, 64);
        registerSimpleConfiguredPlant(context, BULLRUSH, PlantBlocks.BULLRUSH, 64);
        registerSimpleConfiguredPlant(context, WEEPING_BLUEBELL, PlantBlocks.WEEPING_BLUEBELL, 64);
        registerSimpleConfiguredPlant(context, SUNDEW, PlantBlocks.SUNDEW, 64);
        registerSimpleConfiguredPlant(context, TALL_BLOOM, PlantBlocks.TALL_BLOOM, 64);
        registerSimpleConfiguredPlant(context, TANGLED_STALK, PlantBlocks.TANGLED_STALK, 64);
        registerSimpleConfiguredPlant(context, HIGH_CAPPED_MUSHROOM, PlantBlocks.HIGH_CAPPED_MUSHROOM, 64);
        registerSimpleConfiguredPlant(context, FERN, PlantBlocks.FERN, 32);
        registerSimpleConfiguredPlant(context, TALL_FERN, PlantBlocks.TALL_FERN, 32);
    }

    public static void initPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        setPlacedContext(context);
        registerPlacedFeature(ALGAE);
        registerPlacedFeature(BAMBOO);
        registerPlacedFeature(BIG_LOGS);
        registerPlacedFeature(GIANT_FLOWER_BLACK);
        registerPlacedFeature(GIANT_FLOWER_RED);
        registerPlacedFeature(GIANT_FLOWER_BROWN);
        registerPlacedFeature(GIANT_FLOWER_BLUE);
        registerPlacedFeature(GIANT_FLOWER_PURPLE);
        registerPlacedFeature(GIANT_FLOWER_CYAN);
        registerPlacedFeature(GIANT_FLOWER_LIGHT_GRAY);
        registerPlacedFeature(GIANT_FLOWER_GRAY);
        registerPlacedFeature(GIANT_FLOWER_PINK);
        registerPlacedFeature(GIANT_FLOWER_YELLOW);
        registerPlacedFeature(GIANT_FLOWER_LIGHT_BLUE);
        registerPlacedFeature(GIANT_FLOWER_MAGENTA);
        registerPlacedFeature(GIANT_FLOWER_ORANGE);
        registerPlacedFeature(GIANT_FLOWER_WHITE);
        registerPlacedFeature(GIANT_MUSHROOM);
        registerPlacedFeature(MELON);
        registerPlacedFeature(MOSS);
        registerPlacedFeature(MOULD);
        registerPlacedFeature(NETTLE);
        registerPlacedFeature(PRICKLY_PEAR);
        registerPlacedFeature(ROTTEN_TREE_STUMP);
        registerPlacedFeature(SWAMP_BUSH);
        registerPlacedFeature(TURNIP);
        registerPlacedFeature(VINES);

        registerPlacedFeature(SWAMP_BERRY_BUSH);
        registerPlacedFeature(HEART_BERRY_BUSH);
        registerPlacedFeature(JADE_BERRY_BUSH);

        registerPlacedFeature(SWAMP_PLANT);
        registerPlacedFeature(FIRE_BLOOM);
        registerPlacedFeature(FIDDLE_HEAD);
        registerPlacedFeature(BULLRUSH);
        registerPlacedFeature(WEEPING_BLUEBELL);
        registerPlacedFeature(SUNDEW);
        registerPlacedFeature(TALL_BLOOM);
        registerPlacedFeature(TANGLED_STALK);
        registerPlacedFeature(HIGH_CAPPED_MUSHROOM);
        registerPlacedFeature(FERN);
        registerPlacedFeature(TALL_FERN);
    }
}
