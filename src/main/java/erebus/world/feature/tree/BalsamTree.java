package erebus.world.feature.tree;

import erebus.registries.ModBlocks;
import erebus.world.feature.tree.foliage.BalsamFoliagePlacer;
import erebus.world.feature.tree.trunk.BalsamTrunkPlacer;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class BalsamTree extends ErebusTree {

    public BalsamTree() {
        super("balsam");
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_BALSAM_RESINLESS.get()),
                new BalsamTrunkPlacer(12, 4, 3),
                BlockStateProvider.simple(ModBlocks.LEAVES_BALSAM.get()),
                new BalsamFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build();
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return tree(3, ModBlocks.SAPLING_BALSAM);
    }
}
