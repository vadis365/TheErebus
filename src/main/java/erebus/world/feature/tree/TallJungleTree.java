package erebus.world.feature.tree;

import erebus.world.feature.tree.foliage.SingleLeafFoliagePlacer;
import erebus.world.feature.tree.trunk.TallJungleTrunkPlacer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class TallJungleTree extends ErebusTree {
    public TallJungleTree() {
        super("tall_jungle");
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new TallJungleTrunkPlacer(), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
                new SingleLeafFoliagePlacer(), new TwoLayersFeatureSize(1, 0, 0)).build();
    }
}
