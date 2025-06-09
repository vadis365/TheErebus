package erebus.registries.world.feature;

import erebus.registries.helpers.ModFeatureHelpers;
import erebus.world.feature.old_structure.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static erebus.registries.world.feature.config.StructureFeatureConfigs.*;

public class StructureFeatures extends ModFeatureHelpers {

    public static final AntlionDungeonFeature ANTLION_DUNGEON = new AntlionDungeonFeature("antlion_dungeon");
    public static final AntlionLairFeature ANTLION_LAIR = new AntlionLairFeature("antlion_lair");
    public static final DragonflyDungeonFeature DRAGONFLY_DUNGEON = new DragonflyDungeonFeature("dragonfly_dungeon");
    public static final DungPileFeature DUNG_PILE = new DungPileFeature("dung_pile");
    public static final LocustShrineFeature LOCUST_SHRINE = new LocustShrineFeature("locust_shrine");
    public static final SpiderDungeonFeature SPIDER_DUNGEON = new SpiderDungeonFeature("spider_dungeon");
    public static final SwampHutFeature SWAMP_HUT = new SwampHutFeature("swamp_hut");
    public static final WaspDungeonFeature WASP_DUNGEON = new WaspDungeonFeature("wasp_dungeon");

    public static void initConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        setConfiguredContext(context);
        registerConfiguredFeatureWithConfig(ANTLION_DUNGEON, ANTLION_DUNGEON_CONFIG);
        registerConfiguredFeatureWithConfig(ANTLION_LAIR, ANTLION_LAIR_CONFIG);
        registerConfiguredFeatureWithConfig(DRAGONFLY_DUNGEON, DRAGONFLY_DUNGEON_CONFIG);
        registerConfiguredFeatureWithConfig(DUNG_PILE, DUNG_PILE_CONFIG);
        registerConfiguredFeatureWithConfig(LOCUST_SHRINE, LOCUST_SHRINE_CONFIG);
        registerConfiguredFeatureWithConfig(SPIDER_DUNGEON, SPIDER_DUNGEON_CONFIG);
        registerConfiguredFeatureWithConfig(SWAMP_HUT, SWAMP_HUT_CONFIG);
        registerConfiguredFeatureWithConfig(WASP_DUNGEON, WASP_DUNGEON_CONFIG);
    }

    public static void initPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        setPlacedContext(context);
        registerPlacedFeature(ANTLION_DUNGEON);
        registerPlacedFeature(ANTLION_LAIR);
        registerPlacedFeature(DRAGONFLY_DUNGEON);
        registerPlacedFeature(DUNG_PILE);
        registerPlacedFeature(LOCUST_SHRINE);
        registerPlacedFeature(SPIDER_DUNGEON);
        registerPlacedFeature(SWAMP_HUT);
        registerPlacedFeature(WASP_DUNGEON);
    }
}
