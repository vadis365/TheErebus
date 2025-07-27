package erebus.entity.ai;

import erebus.utils.Spiral;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.awt.*;
import java.util.List;


public abstract class FindFlowerGoal extends Goal {

	/**
	 * The bigger you make this value the faster the AI will be. But performance will also decrease so be sensible
	 */
	private static final int CHECKS_PER_TICK = 6;

	private final int collectSpeed;
	protected final Mob entity;
	private final BlockState blockState;

	protected boolean hasTarget;
	public int flowerX;
	public int flowerY;
	public int flowerZ;
	private int spiralIndex;
	private int collectTicks;
	private static final List<Point> SPIRAL = new Spiral(32, 32).spiral();

	public FindFlowerGoal(Mob entity, BlockState state, int pollinateSpeed) {
		this.entity = entity;
		blockState = state;
		hasTarget = false;
		spiralIndex = 0;
		collectSpeed = pollinateSpeed * 20;
	}

	@Override
	public boolean canUse() {
		return !hasTarget && collectTicks == 0;
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
				AABB blockbounds = getBlockAABB(flowerX, flowerY, flowerZ);
				boolean flag = entity.getBoundingBox().maxY >= blockbounds.minY && entity.getBoundingBox().minY <= blockbounds.maxY + 0.25D && entity.getBoundingBox().maxX >= blockbounds.minX && entity.getBoundingBox().minX <= blockbounds.maxX && entity.getBoundingBox().maxZ >= blockbounds.minZ && entity.getBoundingBox().minZ <= blockbounds.maxZ;
				if(!flag && canPolinate(getTargetBlock()))
					moveToLocation();
				entity.getLookControl().setLookAt(flowerX + 0.5D, flowerY + 0.5D, flowerZ + 0.5D, 30.0F, 8.0F);

				if (flag && canPolinate(getTargetBlock())) {
					entity.getNavigation().stop();
					prepareToPollinate();
					collectTicks++;
					//entity.level().sendBlockBreakProgress(entity.getEntityId(), new BlockPos(flowerX, flowerY, flowerZ), getScaledcollectTicks());

					if (collectSpeed <= collectTicks) {
						hasTarget = false;
						collectTicks = 0;
						afterPollination();
						return;
					}
				}
				else if (collectTicks > 0) {
					pollinationInterupted();
					hasTarget = false;
					collectTicks = 0;
					return;
				}
			}
	}

	private int getScaledcollectTicks() {
		return (int) ((float) collectTicks / (float) collectSpeed * 10.0F);
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