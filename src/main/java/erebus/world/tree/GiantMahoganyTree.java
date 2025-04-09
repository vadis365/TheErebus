
package erebus.world.tree;

import com.google.common.collect.ImmutableList;
import erebus.registries.ModBlocks;
import erebus.world.tree.decorator.LeaveThornDecorator;
import erebus.world.tree.decorator.TrunkThornDecorator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;

public class GiantMahoganyTree extends ErebusTree {

    private static final ResourceKey<ConfiguredFeature<?, ?>> KEY = registerKey("giant_mahogany");

    public GiantMahoganyTree() {
        super(KEY);
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_MAHOGANY.get()),
                new MegaJungleTrunkPlacer(10, 2, 19),
                BlockStateProvider.simple(ModBlocks.LEAVES_MAHOGANY.get()),
                new MegaJungleFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(1, 1, 2)
        )
                .decorators(ImmutableList.of(TrunkThornDecorator.INSTANCE, new LeaveThornDecorator(0.25F)))
                .build();
    }
}
