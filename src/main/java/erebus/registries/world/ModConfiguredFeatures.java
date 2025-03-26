package erebus.registries.world;

import com.google.common.collect.ImmutableList;
import erebus.Erebus;
import erebus.registries.ModBlocks;
import erebus.world.tree.decorator.LeaveThornDecorator;
import erebus.world.tree.decorator.TrunkThornDecorator;
import erebus.world.tree.trunkplacer.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.function.Supplier;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?,?>> ASPER_KEY = registerKey("asper");
    public static final ResourceKey<ConfiguredFeature<?,?>> BALSAM_KEY = registerKey("balsam");
    public static final ResourceKey<ConfiguredFeature<?,?>> BAMBOO_KEY = registerKey("bamboo");
    public static final ResourceKey<ConfiguredFeature<?,?>> BAOBAB_KEY = registerKey("baobab");
    public static final ResourceKey<ConfiguredFeature<?,?>> CYPRESS_KEY = registerKey("cypress");
    public static final ResourceKey<ConfiguredFeature<?,?>> EUCALYPTUS_KEY = registerKey("eucalyptus");
    public static final ResourceKey<ConfiguredFeature<?,?>> GIANT_EUCALYPTUS_KEY = registerKey("giant_eucalyptus");
    public static final ResourceKey<ConfiguredFeature<?,?>> MAHOGANY_KEY = registerKey("mahogany");
    public static final ResourceKey<ConfiguredFeature<?,?>> GIANT_MAHOGANY_KEY = registerKey("giant_mahogany");
    public static final ResourceKey<ConfiguredFeature<?,?>> MARSHWOOD_KEY = registerKey("marshwood");
    public static final ResourceKey<ConfiguredFeature<?,?>> MOSSBARK_KEY = registerKey("mossbark");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> context) {
        register(context, ASPER_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_ASPER.get()),
                new AsperTrunkPlacer(4, 2, 0, 1),
                BlockStateProvider.simple(ModBlocks.LEAVES_ASPER.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        ).build());

        register(context, BALSAM_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_BALSAM.get()),
                new BalsamTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(ModBlocks.LEAVES_BALSAM.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build());

        register(context, BAOBAB_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_BAOBAB.get()),
                new BaobabTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(ModBlocks.LEAVES_BAOBAB.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build());

        register(context, CYPRESS_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_CYPRESS.get()),
                new CypressTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(ModBlocks.LEAVES_CYPRESS.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build());

        register(context, EUCALYPTUS_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_EUCALYPTUS.get()),
                new EucalyptusTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(ModBlocks.LEAVES_EUCALYPTUS.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build());

        register(context, GIANT_EUCALYPTUS_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_EUCALYPTUS.get()),
                new GiantEucalyptusTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(ModBlocks.LEAVES_EUCALYPTUS.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build());

        register(context, MAHOGANY_KEY, Feature.TREE, createStraightBlobTree(ModBlocks.LOG_MAHOGANY, ModBlocks.LEAVES_MAHOGANY, 4, 8, 0, 2));

        register(context, GIANT_MAHOGANY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.LOG_MAHOGANY.get()),
                        new MegaJungleTrunkPlacer(10, 2, 19),
                        BlockStateProvider.simple(ModBlocks.LEAVES_MAHOGANY.get()),
                        new MegaJungleFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                        new TwoLayersFeatureSize(1, 1, 2)
                )
                .decorators(ImmutableList.of(TrunkThornDecorator.INSTANCE, new LeaveThornDecorator(0.25F)))
                        .build()
        );

        register(context, MARSHWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_MARSHWOOD.get()),
                new ForkingTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(ModBlocks.LEAVES_MARSHWOOD.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build());

        register(context, MOSSBARK_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_MOSSBARK.get()),
                new ForkingTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(ModBlocks.LEAVES_MOSSBARK.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build());
    }

    private static TreeConfiguration createStraightBlobTree(Supplier<? extends Block> log, Supplier<? extends Block> leaves, int baseHeight, int heightRandA, int heightRandB, int radius) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(log.get()),
                new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB),
                BlockStateProvider.simple(leaves.get()),
                new BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        ).build();
    }

    public static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Erebus.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?,?>> context, ResourceKey<ConfiguredFeature<?,?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
