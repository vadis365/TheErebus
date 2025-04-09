package erebus.world.tree;

import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.function.Supplier;

public class ErebusTree {
    private final ResourceKey<ConfiguredFeature<?, ?>> RESOURCE_KEY;

    public ErebusTree(ResourceKey<ConfiguredFeature<?, ?>> key) {
        RESOURCE_KEY = key;
    }

    protected static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Erebus.MODID, name));
    }

    protected static TreeConfiguration createStraightBlobTree(Supplier<? extends Block> log, Supplier<? extends Block> leaves, int baseHeight, int heightRandA, int heightRandB, int radius) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(log.get()),
                new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB),
                BlockStateProvider.simple(leaves.get()),
                new BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        ).build();
    }

    public ResourceKey<ConfiguredFeature<?, ?>> getResourceKey() {
        return RESOURCE_KEY;
    }

    public TreeConfiguration getTreeConfiguration() {
        return null;
    }
}
