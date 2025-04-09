package erebus.world.tree;

import erebus.registries.ModBlocks;
import erebus.world.tree.trunkplacer.BaobabTrunkPlacer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class BaobabTree extends ErebusTree {

    private static final ResourceKey<ConfiguredFeature<?, ?>> KEY = registerKey("baobab");

    public BaobabTree() {
        super(KEY);
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_BAOBAB.get()),
                new BaobabTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(ModBlocks.LEAVES_BAOBAB.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build();
    }
}
