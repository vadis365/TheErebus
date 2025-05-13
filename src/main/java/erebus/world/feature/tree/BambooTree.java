package erebus.world.feature.tree;

import erebus.registries.blocks.providers.WoodBlocks;
import erebus.world.feature.tree.trunk.AsperTrunkPlacer;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class BambooTree extends ErebusTree {

    public BambooTree() {
        super("bamboo_tree");
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlocks.LOG_ASPER.get()),
                new AsperTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(WoodBlocks.LEAVES_ASPER.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 1)).build();
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return tree(3, WoodBlocks.SAPLING_BAMBOO);
    }
}
