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
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;
import java.util.function.Supplier;

public class ModFeatureHelpers {

    protected static <FC extends FeatureConfiguration, F extends Feature<FC>> void registerConfiguredFeature(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }

    protected static <F extends ErebusFeature> void registerPlacedFeature(BootstrapContext<PlacedFeature> context, F feature) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> configured = configuredFeatures.getOrThrow(feature.getConfiguredResourceKey());

        context.register(feature.getPlacedResourceKey(), new PlacedFeature(configured, List.copyOf(feature.getPlacementModifiers())));
    }

    protected static <T extends ErebusTree> void registerConfiguredTree(BootstrapContext<ConfiguredFeature<?, ?>> context, T tree) {
        registerConfiguredFeature(context, tree.getConfiguredResourceKey(), Feature.TREE, tree.getTreeConfiguration());
    }

    protected static <B extends ErebusBushFeature> void registerConfiguredBush(BootstrapContext<ConfiguredFeature<?, ?>> context, B bush) {
        registerConfiguredFeature(context, bush.getConfiguredResourceKey(), Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK,
                bush.getConfiguration(),
                bush.plantedOn()
                )
        );
    }

    protected static <F extends ErebusFeature> void registerConfiguredOre(BootstrapContext<ConfiguredFeature<?, ?>> context, F feature, RuleTest test, Supplier<? extends Block> block, int veinSize) {
        registerConfiguredFeature(context, feature.getConfiguredResourceKey(), Feature.ORE, new OreConfiguration(test, block.get().defaultBlockState(), veinSize));
    }

    protected static void registerSimpleConfiguredPlant(BootstrapContext<ConfiguredFeature<?, ?>> context, ErebusFeature feature, Supplier<? extends Block> block) {
        registerConfiguredFeature(
                context,
                feature.getConfiguredResourceKey(),
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,
                        6,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(block.get().defaultBlockState()))
                        )
                )
        );
    }
}
