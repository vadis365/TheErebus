package erebus.world.feature.tree;

import com.google.common.collect.ImmutableList;
import erebus.registries.blocks.ModBlocks;
import erebus.world.feature.tree.decorator.TrunkThornDecorator;
import erebus.world.feature.tree.foliage.SingleLeafFoliagePlacer;
import erebus.world.feature.tree.trunk.GiantMahoganyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class GiantMahoganyTree extends ErebusTree {

    public GiantMahoganyTree() {
        super("giant_mahogany");
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_MAHOGANY.get()),
                new GiantMahoganyTrunkPlacer(20, 4, 2),
                BlockStateProvider.simple(ModBlocks.LEAVES_MAHOGANY.get()),
                new SingleLeafFoliagePlacer(),
                new TwoLayersFeatureSize(1, 1, 2)
        )
                .decorators(ImmutableList.of(TrunkThornDecorator.INSTANCE))
                .build();
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return tree(3, ModBlocks.SAPLING_MAHOGANY);
    }
}
