package erebus.utils;

import erebus.block.portal.ErebusPortalBlock;
import erebus.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public class AdvancedBlockPos extends BlockPos {
    private final Level level;
    private final BlockPos pos;


    public AdvancedBlockPos(Level level, BlockPos pos) {
        super(pos.getX(), pos.getY(), pos.getZ());
        this.level = level;
        this.pos = pos;
    }

    public void iterateCube(BlockPos min, BlockPos max, ICoordFunc func) {
        for (int x = min.getX(); x <= max.getX(); x++) {
            for (int y = min.getY(); y <= max.getY(); y++) {
                for (int z = min.getZ(); z <= max.getZ(); z++) {
                    if (func.visit(new AdvancedBlockPos(level, new BlockPos(x, y, z)))) {
                        return;
                    }
                }
            }
        }
    }

    public boolean isValidLeafPortal() {
        return ErebusPortalBlock.obeysPortalRule(level, pos, false);
    }

    public AdvancedBlockPos[] neighbors() {
        AdvancedBlockPos[] ret = new AdvancedBlockPos[6];
        int c = 0;
        for (Direction dir : Direction.values()) {
            ret[c++] = new AdvancedBlockPos(level, pos.relative(dir));
        }
        return ret;
    }

    public void ensureFloored() {
        if (level.getBlockState(pos).canBeReplaced()) {
            level.setBlockAndUpdate(pos, ModBlocks.UMBERSTONE.get().defaultBlockState());
        }
    }
}
