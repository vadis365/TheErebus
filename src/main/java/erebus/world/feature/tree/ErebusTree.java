package erebus.world.feature.tree;

import erebus.world.feature.ErebusFeature;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;
import java.util.function.Supplier;

public class ErebusTree extends ErebusFeature {

    public ErebusTree(String key) {
        super(key);
    }

    protected static TreeConfiguration createStraightBlobTree(Supplier<? extends Block> log, Supplier<? extends Block> leaves, int baseHeight, int heightRandA, int heightRandB, int radius) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(log.get()),
                new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB),
                BlockStateProvider.simple(leaves.get()),
                new BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        ).build();
    }

    public TreeConfiguration getTreeConfiguration() {
        return null;
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return tree(8);
    }
}
