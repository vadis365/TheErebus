package erebus.world.feature.tree;

import erebus.registries.ModBlocks;
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
        return tree(3, ModBlocks.SAPLING_MAHOGANY);
    }
}
