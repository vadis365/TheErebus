package erebus.entity.ai;

import erebus.utils.Spiral;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.awt.*;
import java.util.EnumSet;
import java.util.List;


public abstract class FindFlowerGoal extends Goal {

    /**
     * The bigger you make this value the faster the AI will be. But performance will also decrease so be sensible
     */
    private static final int CHECKS_PER_TICK = 6;
    private static final List<Point> SPIRAL = new Spiral(32, 32).spiral();
    protected final Mob entity;
    private final int collectSpeed;
    private final BlockState blockState;
    public int flowerX;
    public int flowerY;
    public int flowerZ;
    protected boolean hasTarget;
    private int spiralIndex;
    private int collectTicks;

    public FindFlowerGoal(Mob entity, BlockState state, int pollinateSpeed) {
        this.entity = entity;
        blockState = state;
        hasTarget = false;
        spiralIndex = 0;
        collectSpeed = pollinateSpeed * 20;
        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return !hasTarget && collectTicks == 0;
    }

    @Override
    public boolean canContinueToUse() {
        return hasTarget && isEntityReady() && canPolinate(getTargetBlock());
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void stop() {
        hasTarget = false;
        collectTicks = 0;
        entity.getNavigation().stop();
        pollinationInterupted();
    }

    public void tick() {
        if (!isEntityReady() || hasTarget && !canPolinate(getTargetBlock())) {
            stop();
            return;
        }
        int xCoord = entity.blockPosition().getX();
        int yCoord = entity.blockPosition().getY();
        int zCoord = entity.blockPosition().getZ();

        for (int i = 0; i < CHECKS_PER_TICK; i++)
            if (!hasTarget) {
                increment();

                Point p = getNextPoint();
                for (int y = -16; y < 16; y++)
                    if (canPolinate(entity.level().getBlockState(new BlockPos(xCoord + p.x, yCoord + y, zCoord + p.y)))) {
                        flowerX = xCoord + p.x;
                        flowerY = yCoord + y;
                        flowerZ = zCoord + p.y;
                        hasTarget = true;
                    }
            } else if (isEntityReady()) {
                AABB blockbounds = getBlockAABB(flowerX, flowerY, flowerZ);
                boolean flag = entity.getBoundingBox().maxY >= blockbounds.minY && entity.getBoundingBox().minY <= blockbounds.maxY + 0.25D && entity.getBoundingBox().maxX >= blockbounds.minX && entity.getBoundingBox().minX <= blockbounds.maxX && entity.getBoundingBox().maxZ >= blockbounds.minZ && entity.getBoundingBox().minZ <= blockbounds.maxZ;
                if (!flag && canPolinate(getTargetBlock()))
                    moveToLocation();
                entity.getLookControl().setLookAt(flowerX + 0.5D, flowerY + 0.5D, flowerZ + 0.5D, 30.0F, 8.0F);

                if (flag && canPolinate(getTargetBlock())) {
                    entity.getNavigation().stop();
                    prepareToPollinate();
                    collectTicks++;

                    if (collectSpeed <= collectTicks) {
                        hasTarget = false;
                        collectTicks = 0;
                        afterPollination();
                        return;
                    }
                } else if (collectTicks > 0) {
                    stop();
                    return;
                }
            }
    }


    private void increment() {
        spiralIndex++;
        if (spiralIndex >= SPIRAL.size())
            spiralIndex = 0;
    }

    private Point getNextPoint() {
        return SPIRAL.get(spiralIndex);
    }

    public BlockState getTargetBlock() {
        BlockState state = entity.level().getBlockState(new BlockPos(flowerX, flowerY, flowerZ));
        return state;
    }

    protected boolean canPolinate(BlockState state) {
        return state == blockState;
    }

    protected abstract boolean isEntityReady();

    protected abstract void moveToLocation();

    protected abstract void prepareToPollinate();

    protected abstract void pollinationInterupted();

    protected abstract void afterPollination();

    protected AABB getBlockAABB(int x, int y, int z) {
        return new AABB(flowerX, flowerY, flowerZ, flowerX + 1.0D, flowerY + 1.0D, flowerZ + 1.0D);
    }
}
