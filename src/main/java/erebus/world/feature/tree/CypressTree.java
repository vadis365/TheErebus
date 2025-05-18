package erebus.world.feature.tree;

import erebus.registries.blocks.providers.WoodBlocks;
import erebus.world.feature.tree.foliage.SingleLeafFoliagePlacer;
import erebus.world.feature.tree.trunk.CypressTrunkPlacer;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class CypressTree extends ErebusTree {

    public CypressTree() {
        super("cypress");
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlocks.LOG_CYPRESS.get()),
                new CypressTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(WoodBlocks.LEAVES_CYPRESS.get()),
                new SingleLeafFoliagePlacer(ConstantInt.ZERO, ConstantInt.ZERO),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build();
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return tree(1, WoodBlocks.SAPLING_CYPRESS);
    }
}
