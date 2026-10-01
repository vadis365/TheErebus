package erebus.entity.ai;

import erebus.utils.Spiral;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.event.EventHooks;

import java.awt.*;
import java.util.EnumSet;
import java.util.List;

public abstract class EatBlockGoal extends Goal {

    /**
     * The bigger you make this value the faster the AI will be. But performance will also decrease so be sensible
     */
    private static final int CHECKS_PER_TICK = 3;
    private static final List<Point> SPIRAL = new Spiral(16, 16).spiral();
    protected final Mob entity;
    private final int eatSpeed;
    private final BlockState blockState;
    public boolean hasTarget;
    public int targetX;
    public int targetY;
    public int targetZ;
    public int eatTicks;
    public boolean dropItem;
    private int spiralIndex;

    public EatBlockGoal(Mob entity, BlockState state, double moveSpeed, int eatSpeed, boolean shouldDropItem) {
        this.entity = entity;
        this.blockState = state;
        this.eatSpeed = eatSpeed * 20;
        this.dropItem = shouldDropItem;
        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        hasTarget = false;
        spiralIndex = 0;
    }

    @Override
    public boolean canUse() {
        return EventHooks.canEntityGrief(getServerLevel(entity.level()), entity) && eatTicks == 0;
    }

    @Override
    public boolean canContinueToUse() {
        return hasTarget && canEatBlock(getTargetBlock()) && EventHooks.canEntityGrief(getServerLevel(entity.level()), entity);
    }

    @Override
    public void stop() {
        if (hasTarget) entity.level().destroyBlockProgress(entity.getId(), new BlockPos(targetX, targetY, targetZ), -1);
        entity.getNavigation().stop();
        eatingInterupted();
        hasTarget = false;
        eatTicks = 0;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        if (!EventHooks.canEntityGrief(getServerLevel(entity.level()), entity)
                || hasTarget && (!canEatBlock(getTargetBlock()) || !isEntityReady())) {
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
                for (int y = -4; y < 4; y++)
                    if (canEatBlock(entity.level().getBlockState(new BlockPos(xCoord + p.x, yCoord + y, zCoord + p.y)))) {
                        targetX = xCoord + p.x;
                        targetY = yCoord + y;
                        targetZ = zCoord + p.y;
                        hasTarget = true;
                    }
            } else if (isEntityReady()) {
                AABB blockbounds = getBlockAABB(targetX, targetY, targetZ).inflate(0.0625D);
                boolean flag = entity.getBoundingBox().maxY >= blockbounds.minY && entity.getBoundingBox().minY <= blockbounds.maxY && entity.getBoundingBox().maxX >= blockbounds.minX && entity.getBoundingBox().minX <= blockbounds.maxX && entity.getBoundingBox().maxZ >= blockbounds.minZ && entity.getBoundingBox().minZ <= blockbounds.maxZ;
                if (!flag && canEatBlock(getTargetBlock()))
                    moveToLocation();
                entity.getLookControl().setLookAt(targetX + 0.5D, targetY + 0.5D, targetZ + 0.5D, 30.0F, 8.0F);

                if (flag && canEatBlock(getTargetBlock())) {
                    entity.getNavigation().stop();
                    prepareToEat();
                    eatTicks++;
                    entity.level().destroyBlockProgress(entity.getId(), new BlockPos(targetX, targetY, targetZ), getScaledEatTicks());
                    if (eatSpeed <= eatTicks) {
                        if (dropItem)
                            dropItem();
                        afterEaten();
                        stop();
                        return;
                    }
                } else if (eatTicks > 0) {
                    stop();
                    return;
                }
            }
    }

    protected int getScaledEatTicks() {
        return Math.min(9, (int) ((float) eatTicks / (float) eatSpeed * 10.0F));
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
        return entity.level().getBlockState(new BlockPos(targetX, targetY, targetZ));
    }

    /**
     * Override this if you wish to do a more advanced checking on which blocks should be eaten
     *
     * @param state
     * @return true is should eat block, false is it shouldn't
     */
    protected boolean canEatBlock(BlockState state) {
        return state == blockState && state.getBlock() != Blocks.AIR;
    }

    /**
     * Test if entity is ready to eat block
     *
     * @return true to allow block to be eaten. false to deny it.
     */
    protected abstract boolean isEntityReady();

    /**
     * Allows you to set mob specific move tasks.
     */
    protected abstract void moveToLocation();

    /**
     * Allows any other tasks to be cancelled just before block has been eaten.
     */
    protected abstract void prepareToEat();

    /**
     * Allows any other tasks to be cancelled if the block cannot be eaten.
     */
    protected abstract void eatingInterupted();

    /**
     * Gets called just after block has been eaten, if dropItem is true.
     */
    protected abstract void dropItem();

    /**
     * Gets called just after block has been eaten.
     */
    protected abstract void afterEaten();

    protected AABB getBlockAABB(int x, int y, int z) {
        return new AABB(targetX - 0.3D, targetY - 0.3D, targetZ - 0.3D, targetX + 1.3D, targetY + 1.3D, targetZ + 1.3D);
    }
}
