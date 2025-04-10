package erebus.world.feature.tree;

import erebus.registries.ModBlocks;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class MahoganyTree extends ErebusTree {

    public MahoganyTree() {
        super("mahogany");
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return createStraightBlobTree(ModBlocks.LOG_MAHOGANY, ModBlocks.LEAVES_MAHOGANY, 4, 8, 0, 2);
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 2), ModBlocks.SAPLING_MAHOGANY.get());
    }
}
