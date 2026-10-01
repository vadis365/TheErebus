package erebus.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.*;
import org.jspecify.annotations.NonNull;

public class AboveWaterNavigation extends WaterBoundPathNavigation {
    public AboveWaterNavigation(Mob mob, Level level) {
        super(mob, level);
    }

    @Override
    protected @NonNull PathFinder createPathFinder(int maxVisitedNodes) {
        nodeEvaluator = new AirNodes();
        return new PathFinder(nodeEvaluator, maxVisitedNodes);
    }

    @Override
    protected boolean canUpdatePath() {
        return level.getFluidState(mob.blockPosition().below()).is(FluidTags.WATER);
    }

    @Override
    public boolean isStableDestination(BlockPos pos) {
        return level.getFluidState(pos.below()).is(FluidTags.WATER);
    }

    private static class AirNodes extends NodeEvaluator {
        @Override
        public @NonNull Node getStart() {
            var box = mob.getBoundingBox();
            return getNode(Mth.floor(box.minX), Mth.floor(box.minY), Mth.floor(box.minZ));
        }

        @Override
        public @NonNull Target getTarget(double x, double y, double z) {
            return getTargetNodeAt(x - mob.getBbWidth() / 2, y - mob.getBbHeight() / 2, z - mob.getBbWidth() / 2);
        }

        @Override
        public int getNeighbors(Node @NonNull [] neighbors, @NonNull Node node) {
            int count = 0;
            for (var direction : Direction.values()) {
                int x = node.x + direction.getStepX(), y = node.y + direction.getStepY(), z = node.z + direction.getStepZ();
                if (getPathTypeOfMob(currentContext, x, y, z, mob) != PathType.OPEN) continue;
                var next = getNode(x, y, z);
                if (!next.closed) neighbors[count++] = next;
            }
            return count;
        }

        @Override
        public @NonNull PathType getPathTypeOfMob(@NonNull PathfindingContext context, int x, int y, int z, @NonNull Mob mob) {
            for (var pos : BlockPos.betweenClosed(x, y, z, x + entityWidth - 1, y + entityHeight - 1, z + entityDepth - 1))
                if (!context.getBlockState(pos).isAir()) return PathType.BLOCKED;
            return PathType.OPEN;
        }

        @Override
        public @NonNull PathType getPathType(PathfindingContext context, int x, int y, int z) {
            return context.getBlockState(new BlockPos(x, y, z)).isAir() ? PathType.OPEN : PathType.BLOCKED;
        }
    }
}
