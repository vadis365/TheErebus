package erebus.registries.world.feature.config;

import erebus.world.feature.old_structure.config.AntlionDungeonFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;

import static erebus.registries.world.feature.ModFeatures.CONFIGS;

public class StructureFeatureConfigs {
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ANTLION_DUNGEON_CONFIG;

    static {
        ANTLION_DUNGEON_CONFIG = CONFIGS.register("antlion_dungeon", AntlionDungeonFeatureConfiguration::new);
    }

    public static void init() {
    }
}
