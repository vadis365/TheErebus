package erebus.entity.ai;

import java.awt.Point;
import java.util.List;

import erebus.utils.Spiral;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;


public abstract class FindFlowerGoal extends Goal {

	/**
	 * The bigger you make this value the faster the AI will be. But performance will also decrease so be sensible
	 */
	private static final int CHECKS_PER_TICK = 6;

	private final int COLLECT_SPEED;
	protected final Mob entity;
	private final BlockState blockState;

	protected boolean hasTarget;
	public int flowerX;
	public int flowerY;
	public int flowerZ;
	private int spiralIndex;
	private int collectTicks;
	private static final List<Point> spiral = new Spiral(32, 32).spiral();

	public FindFlowerGoal(Mob entity, BlockState state, int pollinateSpeed) {
		this.entity = entity;
		blockState = state;
		hasTarget = false;
		spiralIndex = 0;
		COLLECT_SPEED = pollinateSpeed * 20;
	}

	@Override
	public boolean canUse() {
		return !hasTarget;
	}

	@Override
	public boolean canContinueToUse() {
		return hasTarget;
	}
	
    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
    	int xCoord = (int) entity.getX();
		int yCoord = (int) entity.getY();
		int zCoord = (int) entity.getZ();

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
				moveToLocation();
				entity.getLookControl().setLookAt(flowerX + 0.5D, flowerY + 0.5D, flowerZ + 0.5D, 30.0F, 8.0F);
				AABB blockbounds = getBlockAABB(flowerX, flowerY, flowerZ);
				boolean flag = entity.getBoundingBox().maxY >= blockbounds.minY && entity.getBoundingBox().minY <= blockbounds.maxY + 0.25D && entity.getBoundingBox().maxX >= blockbounds.minX && entity.getBoundingBox().minX <= blockbounds.maxX && entity.getBoundingBox().maxZ >= blockbounds.minZ && entity.getBoundingBox().minZ <= blockbounds.maxZ;

				if (flag) {
					prepareToPollinate();
					collectTicks++;
					//entity.level().sendBlockBreakProgress(entity.getEntityId(), new BlockPos(flowerX, flowerY, flowerZ), getScaledcollectTicks());
					if (!canPolinate(entity.level().getBlockState(new BlockPos(flowerX, flowerY, flowerZ)))) {
						hasTarget = false;
						return;
					}
					else if (COLLECT_SPEED <= collectTicks) {
						hasTarget = false;
						collectTicks = 0;
						afterPollination();
						return;
					}
				}
				if (!flag && collectTicks > 1) {
					pollinationInterupted();
					hasTarget = false;
					collectTicks = 0;
					return;
				}
			}
	}

	private int getScaledcollectTicks() {
		return (int) ((float) collectTicks / (float) COLLECT_SPEED * 10.0F);
	}

	private void increment() {
		spiralIndex++;
		if (spiralIndex >= spiral.size())
			spiralIndex = 0;
	}

	private Point getNextPoint() {
		return spiral.get(spiralIndex);
	}

	public Block getTargetBlockID() {
		return entity.level().getBlockState(new BlockPos(flowerX, flowerY, flowerZ)).getBlock();
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