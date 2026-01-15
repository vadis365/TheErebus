package erebus.world.feature.tree;

import erebus.registries.blocks.ModBlocks;
import erebus.world.feature.tree.foliage.SingleLeafFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class BambooTree extends ErebusTree {

    public BambooTree() {
        super("bamboo_tree");
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.COLOSSAL_BAMBOO.get()),
                new StraightTrunkPlacer(4, 6, 6),
                BlockStateProvider.simple(ModBlocks.COLOSSAL_BAMBOO.get()),
                new SingleLeafFoliagePlacer(),
                new TwoLayersFeatureSize(1, 0, 1)).build();
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return tree(3, ModBlocks.SAPLING_BAMBOO);
    }
}
