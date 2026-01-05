package erebus.utils;

import erebus.block.portal.ErebusPortalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class AdvancedBlockPos extends BlockPos {
    private final Level level;
    private final BlockPos pos;


    public AdvancedBlockPos(Level level, BlockPos pos) {
        super(pos.getX(), pos.getY(), pos.getZ());
        this.level = level;
        this.pos = pos;
    }

    public AdvancedBlockPos add(int x, int y, int z) {
        return new AdvancedBlockPos(level, pos.offset(x, y, z));
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

    public boolean isLeaf() {
        return level.getBlockState(pos).is(BlockTags.LEAVES);
    }

    public boolean isValidLeafPortal() {
        return ErebusPortalBlock.obeysPortalRule(level, pos, false);
    }

    @Override
    public int hashCode() {
        int result = pos.getX();
        result = 31 * result + pos.getY();
        result = 31 * result + pos.getZ();
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof AdvancedBlockPos otherPos)) return false;
        if (pos.getX() != otherPos.pos.getX()) return false;
        if (pos.getY() != otherPos.pos.getY()) return false;
        if (pos.getZ() != otherPos.pos.getZ()) return false;
        return Objects.equals(level, otherPos.level);
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
