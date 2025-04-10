package erebus.world.feature.tree;

import erebus.registries.ModBlocks;
import erebus.world.feature.tree.trunkplacer.CypressTrunkPlacer;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class CypressTree extends ErebusTree {

    public CypressTree() {
        super("cypress");
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_CYPRESS.get()),
                new CypressTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(ModBlocks.LEAVES_CYPRESS.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build();
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 2), ModBlocks.SAPLING_CYPRESS.get());
    }
}
