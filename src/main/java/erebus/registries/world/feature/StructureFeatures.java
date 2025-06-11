package erebus.registries.world.feature;

import erebus.registries.helpers.ModFeatureHelpers;
import erebus.world.feature.old_structure.AntlionDungeonFeature;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static erebus.registries.world.feature.config.StructureFeatureConfigs.ANTLION_DUNGEON_CONFIG;

public class StructureFeatures extends ModFeatureHelpers {

    public static final AntlionDungeonFeature ANTLION_DUNGEON = new AntlionDungeonFeature("antlion_dungeon");

    public static void initConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        setConfiguredContext(context);
        registerConfiguredFeatureWithConfig(ANTLION_DUNGEON, ANTLION_DUNGEON_CONFIG);
    }

    public static void initPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        setPlacedContext(context);
        registerPlacedFeature(ANTLION_DUNGEON);
    }
}
