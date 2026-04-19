package erebus.registries.helpers;

import erebus.registries.data.tags.ModBlockTags;
import erebus.world.feature.ErebusFeature;
import erebus.world.feature.bush.ErebusBushFeature;
import erebus.world.feature.tree.ErebusTree;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.function.Supplier;

public class ModFeatureHelpers {

    private static BootstrapContext<ConfiguredFeature<?, ?>> configuredContext;
    private static BootstrapContext<PlacedFeature> placedContext;

    private static final RuleTest umberstoneReplaceables = new TagMatchTest(ModBlockTags.UMBERSTONE_ORE_REPLACEABLES);

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
        registerConfiguredFeature(bush.getConfiguredResourceKey(), Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(bush.getBush())));
    }

    protected static <F extends ErebusFeature> void registerConfiguredOre(F feature, Supplier<? extends Block> ore, int veinSize) {
        registerConfiguredFeature(feature.getConfiguredResourceKey(), Feature.ORE, new OreConfiguration(umberstoneReplaceables, ore.get().defaultBlockState(), veinSize));
    }

    protected static void registerSimpleConfiguredPlant(ErebusFeature feature, Supplier<? extends Block> block) {
        registerConfiguredFeature(feature.getConfiguredResourceKey(), Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block.get())));
    }

    protected static <F extends ErebusFeature> void registerConfiguredFeatureWithConfig(F feature, Supplier<Feature<NoneFeatureConfiguration>> config) {
        registerConfiguredFeature(feature.getConfiguredResourceKey(), config.get(), FeatureConfiguration.NONE);
    }
}
