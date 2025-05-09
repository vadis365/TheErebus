
package erebus.world.feature.tree;

import com.google.common.collect.ImmutableList;
import erebus.registries.blocks.providers.WoodBlocks;
import erebus.world.feature.tree.decorator.LeaveThornDecorator;
import erebus.world.feature.tree.decorator.TrunkThornDecorator;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class GiantMahoganyTree extends ErebusTree {

    public GiantMahoganyTree() {
        super("giant_mahogany");
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlocks.LOG_MAHOGANY.get()),
                new MegaJungleTrunkPlacer(10, 2, 19),
                BlockStateProvider.simple(WoodBlocks.LEAVES_MAHOGANY.get()),
                new MegaJungleFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(1, 1, 2)
        )
                .decorators(ImmutableList.of(TrunkThornDecorator.INSTANCE, new LeaveThornDecorator(0.25F)))
                .build();
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return tree(3, WoodBlocks.SAPLING_MAHOGANY);
    }
}
