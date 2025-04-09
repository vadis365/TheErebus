package erebus.world.tree;

import erebus.registries.ModBlocks;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class MahoganyTree extends ErebusTree {

    private static final ResourceKey<ConfiguredFeature<?, ?>> KEY = registerKey("mahogany");

    public MahoganyTree() {
        super(KEY);
    }

    @Override
    public TreeConfiguration getTreeConfiguration() {
        return createStraightBlobTree(ModBlocks.LOG_MAHOGANY, ModBlocks.LEAVES_MAHOGANY, 4, 8, 0, 2);
    }
}
