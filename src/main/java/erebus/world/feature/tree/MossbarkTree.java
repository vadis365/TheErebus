package erebus.world.feature.tree;

import erebus.registries.blocks.ModBlocks;
import erebus.world.feature.tree.foliage.SingleLeafFoliagePlacer;
import erebus.world.feature.tree.trunk.MossbarkTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class MossbarkTree extends ErebusTree {

    public MossbarkTree() {
        super("mossbark");
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_MOSSBARK.get()),
                new MossbarkTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(ModBlocks.LEAVES_MOSSBARK.get()),
                new SingleLeafFoliagePlacer(),
                new TwoLayersFeatureSize(1, 0, 0)
        ).build();
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return tree(3, ModBlocks.SAPLING_MOSSBARK);
    }
}
