package erebus.world.feature.tree;

import erebus.registries.ModBlocks;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class MahoganyTree extends ErebusTree {

    public MahoganyTree() {
        super("mahogany");
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return createStraightBlobTree(ModBlocks.LOG_MAHOGANY, ModBlocks.LEAVES_MAHOGANY, 4, 8, 0, 2);
    }
}
