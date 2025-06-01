package erebus.world.feature.tree;

import erebus.registries.blocks.providers.PlantBlocks;
import erebus.registries.blocks.providers.WoodBlocks;
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
                BlockStateProvider.simple(PlantBlocks.COLOSSAL_BAMBOO.get()),
                new StraightTrunkPlacer(4, 6, 6),
                BlockStateProvider.simple(PlantBlocks.COLOSSAL_BAMBOO.get()),
                new SingleLeafFoliagePlacer(),
                new TwoLayersFeatureSize(1, 0, 1)).build();
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return tree(3, WoodBlocks.SAPLING_BAMBOO);
    }
}
