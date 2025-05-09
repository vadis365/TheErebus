package erebus.world.feature.tree;

import erebus.registries.blocks.providers.WoodBlocks;
import erebus.world.feature.tree.decorator.LeaveDarkFruitVineDecorator;
import erebus.world.feature.tree.foliage.MarshwoodFoliagePlacer;
import erebus.world.feature.tree.trunk.MarshwoodTrunkPlacer;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class MarshwoodTree extends ErebusTree {

    public MarshwoodTree() {
        super("marshwood");
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlocks.LOG_MARSHWOOD.get()),
                new MarshwoodTrunkPlacer(12, 2, 3),
                BlockStateProvider.simple(WoodBlocks.LEAVES_MARSHWOOD.get()),
                new MarshwoodFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 2)
        )
                .decorators(
                        List.of(
                                new LeaveDarkFruitVineDecorator(0.125F)
                        )
                )
                .build();
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return tree(3, WoodBlocks.SAPLING_MARSHWOOD);
    }
}
