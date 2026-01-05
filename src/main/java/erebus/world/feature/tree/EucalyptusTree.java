package erebus.world.feature.tree;

import erebus.world.feature.tree.foliage.SingleLeafFoliagePlacer;
import erebus.world.feature.tree.trunk.EucalyptusTrunkPlacer;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class EucalyptusTree extends ErebusTree {

    public EucalyptusTree() {
        super("eucalyptus");
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_EUCALYPTUS.get()),
                new EucalyptusTrunkPlacer(8, 4, 0),
                BlockStateProvider.simple(ModBlocks.LEAVES_EUCALYPTUS.get()),
                new SingleLeafFoliagePlacer(ConstantInt.ZERO, ConstantInt.ZERO),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build();
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return tree(3, ModBlocks.SAPLING_EUCALYPTUS);
    }
}
