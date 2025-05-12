package erebus.registries.helpers;

import erebus.world.feature.ErebusFeature;
import erebus.world.feature.bush.ErebusBushFeature;
import erebus.world.feature.tree.ErebusTree;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;
import java.util.function.Supplier;

public class ModFeatureHelpers {

    private static BootstrapContext<ConfiguredFeature<?, ?>> configuredContext;
    private static BootstrapContext<PlacedFeature> placedContext;

    protected static void setConfiguredContext(BootstrapContext<ConfiguredFeature<?, ?>> configuredContext) {
        ModFeatureHelpers.configuredContext = configuredContext;
    }

    protected static void setPlacedContext(BootstrapContext<PlacedFeature> placedContext) {
        ModFeatureHelpers.placedContext = placedContext;
    }

    protected static <FC extends FeatureConfiguration, F extends Feature<FC>> void registerConfiguredFeature(ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        configuredContext.register(key, new ConfiguredFeature<>(feature, config));
    }

    protected static <F extends ErebusFeature> void registerPlacedFeature(F feature) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = placedContext.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> configured = configuredFeatures.getOrThrow(feature.getConfiguredResourceKey());

        placedContext.register(feature.getPlacedResourceKey(), new PlacedFeature(configured, List.copyOf(feature.getPlacementModifiers())));
    }

    protected static <T extends ErebusTree> void registerConfiguredTree(T tree) {
        registerConfiguredFeature(tree.getConfiguredResourceKey(), Feature.TREE, tree.getTreeConfiguration());
    }

    protected static <B extends ErebusBushFeature> void registerConfiguredBush(B bush) {
        registerConfiguredFeature(bush.getConfiguredResourceKey(), Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK,
                bush.getConfiguration(),
                bush.plantedOn()
                )
        );
    }

    protected static <F extends ErebusFeature> void registerConfiguredOre(F feature, RuleTest test, Supplier<? extends Block> block, int veinSize) {
        registerConfiguredFeature(feature.getConfiguredResourceKey(), Feature.ORE, new OreConfiguration(test, block.get().defaultBlockState(), veinSize));
    }

    protected static void registerSimpleConfiguredPlant(BootstrapContext<ConfiguredFeature<?, ?>> context, ErebusFeature feature, Supplier<? extends Block> block, int tries) {
        registerConfiguredFeature(
                feature.getConfiguredResourceKey(),
                Feature.FLOWER,
                patch(block.get(), tries)
        );
    }

    protected static <F extends ErebusFeature> void registerConfiguredFeatureWithConfig(F feature, Supplier<Feature<NoneFeatureConfiguration>> config) {
        registerConfiguredFeature(feature.getConfiguredResourceKey(), config.get(), FeatureConfiguration.NONE);
    }

    private static RandomPatchConfiguration patch(Block block, int tries) {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block))));
    }

    private static RandomPatchConfiguration patch(Block block, int tries, List<Block> whitelist) {
        return FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block)), whitelist, tries);
    }
}
