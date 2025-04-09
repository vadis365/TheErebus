package erebus.registries.helpers;

import erebus.world.tree.ErebusTree;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class ModConfiguredFeatureHelpers {

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }

    protected static <T extends ErebusTree> void registerTree(BootstrapContext<ConfiguredFeature<?, ?>> context, T tree) {
        register(context, tree.getResourceKey(), Feature.TREE, tree.getTreeConfiguration());
    }
}
