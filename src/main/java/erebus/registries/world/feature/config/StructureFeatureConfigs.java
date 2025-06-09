package erebus.registries.world.feature.config;

import erebus.world.feature.old_structure.config.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;

import static erebus.registries.world.feature.ModFeatures.CONFIGS;

public class StructureFeatureConfigs {
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ANTLION_DUNGEON_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ANTLION_LAIR_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DRAGONFLY_DUNGEON_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DUNG_PILE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> LOCUST_SHRINE_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SPIDER_DUNGEON_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SWAMP_HUT_CONFIG;
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> WASP_DUNGEON_CONFIG;

    static {
        ANTLION_DUNGEON_CONFIG = CONFIGS.register("antlion_dungeon", AntlionDungeonFeatureConfiguration::new);
        ANTLION_LAIR_CONFIG = CONFIGS.register("antlion_lair", AntlionLairFeatureConfiguration::new);
        DRAGONFLY_DUNGEON_CONFIG = CONFIGS.register("dragonfly_dungeon", DragonflyDungeonFeatureConfiguration::new);
        DUNG_PILE_CONFIG = CONFIGS.register("dung_pile", DungPileFeatureConfiguration::new);
        LOCUST_SHRINE_CONFIG = CONFIGS.register("locust_shrine", LocustShrineFeatureConfiguration::new);
        SPIDER_DUNGEON_CONFIG = CONFIGS.register("spider_dungeon", SpiderDungeonFeatureConfiguration::new);
        SWAMP_HUT_CONFIG = CONFIGS.register("swamp_hut", SwampHutFeatureConfiguration::new);
        WASP_DUNGEON_CONFIG = CONFIGS.register("wasp_dungeon", WaspDungeonFeatureConfiguration::new);
    }

    public static void init() {
    }
}
