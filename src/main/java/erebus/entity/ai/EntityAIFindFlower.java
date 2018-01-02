package erebus.entity.ai;

import java.awt.Point;
import java.util.List;

import erebus.core.helper.Spiral;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;


public abstract class EntityAIFindFlower extends EntityAIBase {

	/**
	 * The bigger you make this value the faster the AI will be. But performance will also decrease so be sensible
	 */
	private static final int CHECKS_PER_TICK = 6;

	private final int COLLECT_SPEED;
	protected final EntityLiving entity;
	private final IBlockState blockState;

	protected boolean hasTarget;
	public int flowerX;
	public int flowerY;
	public int flowerZ;
	private int spiralIndex;
	private int collectTicks;
	private static final List<Point> spiral = new Spiral(32, 32).spiral();

	public EntityAIFindFlower(EntityLiving entity, IBlockState state, int pollinateSpeed) {
		this.entity = entity;
		blockState = state;
		hasTarget = false;
		spiralIndex = 0;
		COLLECT_SPEED = pollinateSpeed * 20;
	}

	@Override
	public boolean shouldExecute() {
		return !hasTarget;
	}

	@Override
	public boolean shouldContinueExecuting() {
		return true;//entity.getEntityWorld().getEntitiesWithinAABBExcludingEntity(entity, new AxisAlignedBB(flowerX, flowerY + 1, flowerZ, flowerX + 1, flowerY + 2, flowerZ + 1)).isEmpty();
	}

	@Override
	public void updateTask() {
		if (!shouldContinueExecuting())
			return;

		int xCoord = (int) entity.posX;
		int yCoord = (int) entity.posY;
		int zCoord = (int) entity.posZ;

		for (int i = 0; i < CHECKS_PER_TICK; i++)
			if (!hasTarget) {
				increment();

				Point p = getNextPoint();
				for (int y = -16; y < 16; y++)
					if (canPolinate(entity.getEntityWorld().getBlockState(new BlockPos(xCoord + p.x, yCoord + y, zCoord + p.y)))) {
						flowerX = xCoord + p.x;
						flowerY = yCoord + y;
						flowerZ = zCoord + p.y;
						hasTarget = true;
					}
			} else if (isEntityReady()) {
				moveToLocation();
				entity.getLookHelper().setLookPosition(flowerX + 0.5D, flowerY + 0.5D, flowerZ + 0.5D, 30.0F, 8.0F);
				AxisAlignedBB blockbounds = getBlockAABB(flowerX, flowerY, flowerZ);
				boolean flag = entity.getEntityBoundingBox().maxY >= blockbounds.minY && entity.getEntityBoundingBox().minY <= blockbounds.maxY + 0.25D && entity.getEntityBoundingBox().maxX >= blockbounds.minX && entity.getEntityBoundingBox().minX <= blockbounds.maxX && entity.getEntityBoundingBox().maxZ >= blockbounds.minZ && entity.getEntityBoundingBox().minZ <= blockbounds.maxZ;

				if (flag) {
					prepareToPollinate();
					collectTicks++;
					//entity.getEntityWorld().sendBlockBreakProgress(entity.getEntityId(), new BlockPos(flowerX, flowerY, flowerZ), getScaledcollectTicks());
					if (!canPolinate(entity.getEntityWorld().getBlockState(new BlockPos(flowerX, flowerY, flowerZ)))) {
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
		return entity.getEntityWorld().getBlockState(new BlockPos(flowerX, flowerY, flowerZ)).getBlock();
	}

	protected boolean canPolinate(IBlockState state) {
		return state == blockState;
	}

	protected abstract boolean isEntityReady();

	protected abstract void moveToLocation();

	protected abstract void prepareToPollinate();

	protected abstract void pollinationInterupted();

	protected abstract void afterPollination();

	protected AxisAlignedBB getBlockAABB(int x, int y, int z) {
		return new AxisAlignedBB(flowerX, flowerY, flowerZ, flowerX + 1.0D, flowerY + 1.0D, flowerZ + 1.0D);
	}
}