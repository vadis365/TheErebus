package erebus.world.feature.tree;

import erebus.world.feature.tree.foliage.BaobabFoliagePlacer;
import erebus.world.feature.tree.trunk.BaobabTrunkPlacer;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class BaobabTree extends ErebusTree {

    public BaobabTree() {
        super("baobab");
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_BAOBAB.get()),
                new BaobabTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(ModBlocks.LEAVES_BAOBAB.get()),
                new BaobabFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build();
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return tree(1, ModBlocks.SAPLING_BAOBAB);
    }
}
