package erebus.world.feature.tree;

import erebus.registries.ModBlocks;
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
                BlockStateProvider.simple(ModBlocks.LOG_MARSHWOOD.get()),
                new MarshwoodTrunkPlacer(12, 2, 3),
                BlockStateProvider.simple(ModBlocks.LEAVES_MARSHWOOD.get()),
                new MarshwoodFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build();
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return tree(3, ModBlocks.SAPLING_MARSHWOOD);
    }
}
