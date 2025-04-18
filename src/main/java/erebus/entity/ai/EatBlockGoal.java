package erebus.entity.ai;

import java.awt.Point;
import java.util.EnumSet;
import java.util.List;

import erebus.utils.Spiral;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public abstract class EatBlockGoal extends Goal {

	/**
	 * The bigger you make this value the faster the AI will be. But performance will also decrease so be sensible
	 */
	private static final int CHECKS_PER_TICK = 3;
	private final int EAT_SPEED;
	protected final Mob entity;
	private final BlockState blockState;

	private boolean hasTarget;
	public int targetX;
	public int targetY;
	public int targetZ;
	private int spiralIndex;
	public int eatTicks;
	public boolean dropItem;
	private static final List<Point> spiral = new Spiral(16, 16).spiral();

	public EatBlockGoal(Mob entity, BlockState state, double moveSpeed, int eatSpeed, boolean shouldDropItem) {
		setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		this.entity = entity;
		blockState = state;
		hasTarget = false;
		spiralIndex = 0;
		EAT_SPEED = eatSpeed * 20;
		dropItem = shouldDropItem;
	}

	@Override
	public boolean canUse() {
		return entity.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
	}

	@Override
	public boolean canContinueToUse() {
		return !entity.isBaby();
	}

    public void start() {
		if (canContinueToUse())
			return;

		int xCoord = (int) entity.getX();
		int yCoord = (int) entity.getY();
		int zCoord = (int) entity.getZ();

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
				moveToLocation();
				entity.getLookControl().setLookAt(targetX + 0.5D, targetY + 0.5D, targetZ + 0.5D, 30.0F, 8.0F);
				AABB blockbounds = getBlockAABB(targetX, targetY, targetZ);
				boolean flag = entity.getBoundingBox().maxY >= blockbounds.minY && entity.getBoundingBox().minY <= blockbounds.maxY && entity.getBoundingBox().maxX >= blockbounds.minX && entity.getBoundingBox().minX <= blockbounds.maxX && entity.getBoundingBox().maxZ >= blockbounds.minZ && entity.getBoundingBox().minZ <= blockbounds.maxZ;

				if (flag && canEatBlock(getTargetBlock())) {
					prepareToEat();
					eatTicks++;
					entity.level().destroyBlockProgress(entity.getId(), new BlockPos(targetX, targetY, targetZ), getScaledEatTicks());
					if (!canEatBlock(getTargetBlock())) {
						eatingInterupted();
						hasTarget = false;
						eatTicks = 0;
						return;
					}
					else if (EAT_SPEED <= eatTicks) {
						entity.level().levelEvent(2001, new BlockPos(targetX, targetY, targetZ), Block.getId(getTargetBlock()));
						if (dropItem)
							dropItem();
						hasTarget = false;
						eatTicks = 0;
						afterEaten();
						return;
					}
				}
				if (!flag && eatTicks > 0 || eatTicks > 0 && getTargetBlock().isAir()) {
					eatingInterupted();
					hasTarget = false;
					eatTicks = 0;
					return;
				}
			}
	}

	protected int getScaledEatTicks() {
		return (int) ((float) eatTicks / (float) EAT_SPEED * 10.0F);
	}

	private void increment() {
		spiralIndex++;
		if (spiralIndex >= spiral.size())
			spiralIndex = 0;
	}

	private Point getNextPoint() {
		return spiral.get(spiralIndex);
	}

	public BlockState getTargetBlock() {
		BlockState state = entity.level().getBlockState(new BlockPos(targetX, targetY, targetZ));
		return state;
	}

	/**
	 * Override this if you wish to do a more advanced checking on which blocks should be eaten
	 *
	 * @param block
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