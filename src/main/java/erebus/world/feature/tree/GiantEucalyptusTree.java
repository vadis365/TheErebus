package erebus.world.feature.tree;

import erebus.registries.blocks.ModBlocks;
import erebus.world.feature.tree.trunk.GiantEucalyptusTrunkPlacer;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class GiantEucalyptusTree extends ErebusTree {

    public GiantEucalyptusTree() {
        super("giant_eucalyptus");
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_EUCALYPTUS.get()),
                new GiantEucalyptusTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(ModBlocks.LEAVES_EUCALYPTUS.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build();
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return tree(3, ModBlocks.SAPLING_EUCALYPTUS);
    }
}
