package erebus.entity.ai;

import erebus.entity.AnimatedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.EnumSet;

public class AnimatedBlockFollowOwnerGoal extends Goal {
    private final AnimatedBlock block;
    private @Nullable Player owner;
    private int pathUpdateDelay;
    private float previousWaterMalus;

    public AnimatedBlockFollowOwnerGoal(AnimatedBlock block) {
        this.block = block;
        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    private boolean canFollow() {
        return !block.isPassenger();
    }

    @Override
    public boolean canUse() {
        Player candidate = block.getOwner();
        if (!canFollow() || candidate == null || !candidate.isAlive() || candidate.isSpectator()
                || block.distanceToSqr(candidate) < 100.0) return false;
        owner = candidate;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return canFollow() && owner != null && owner == block.getOwner() && owner.isAlive()
                && !owner.isSpectator() && !block.getNavigation().isDone() && block.distanceToSqr(owner) > 4.0;
    }

    @Override
    public void start() {
        pathUpdateDelay = 0;
        previousWaterMalus = block.getPathfindingMalus(PathType.WATER);
        block.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    @Override
    public void stop() {
        owner = null;
        block.getNavigation().stop();
        block.setPathfindingMalus(PathType.WATER, previousWaterMalus);
    }

    @Override
    public void tick() {
        if (owner == null) return;
        block.getLookControl().setLookAt(owner, 10.0F, block.getMaxHeadXRot());
        if (--pathUpdateDelay > 0) return;
        pathUpdateDelay = adjustedTickDelay(10);
        if (!block.getNavigation().moveTo(owner, 1.0) && !block.isLeashed() && block.distanceToSqr(owner) >= 144.0) {
            teleportNearOwner();
        }
    }

    private void teleportNearOwner() {
        if (owner == null) return;
        BlockPos center = owner.blockPosition();
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (Math.abs(x) != 2 && Math.abs(z) != 2) continue;
                BlockPos pos = center.offset(x, 0, z);
                var level = block.level();
                if (!level.hasChunkAt(pos) || !level.getWorldBorder().isWithinBounds(pos)
                        || !level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)
                        || !level.isEmptyBlock(pos) || !level.isEmptyBlock(pos.above())) continue;
                Vec3 destination = Vec3.atBottomCenterOf(pos);
                var bounds = block.getBoundingBox().move(destination.subtract(block.position()));
                if (!level.getWorldBorder().isWithinBounds(bounds) || !level.noCollision(block, bounds)
                        || level.containsAnyLiquid(bounds)) continue;
                block.setPos(destination);
                block.getNavigation().stop();
                return;
            }
        }
    }
}
