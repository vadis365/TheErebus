package erebus.entity.ai;

import java.awt.Point;
import java.util.List;

import erebus.core.helper.Spiral;
import erebus.core.helper.Utils;
import erebus.entity.EntityBlackAnt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public abstract class EntityAIAntsBlock extends EntityAIBase {

	/**
	 * The bigger you make this value the faster the AI will be. But performance will also decrease so be sensible
	 */
	private static final int CHECKS_PER_TICK = 3;

	private final int EAT_SPEED;
	protected final EntityLiving entity;
	private final int maxGrowthMetadata;
	private final IBlockState blockState;
	private final ItemStack seed;

	private boolean hasTarget;
	public int cropX;
	public int cropY;
	public int cropZ;
	private int spiralIndex;
	private int eatTicks;
	private static final List<Point> spiral = new Spiral(8, 8).spiral();

	public EntityAIAntsBlock(EntityLiving entity, IBlockState state, int maxGrowthMetadata, ItemStack seed, double moveSpeed, int eatSpeed) {
		this.entity = entity;
		this.maxGrowthMetadata = maxGrowthMetadata;
		blockState = state;
		this.seed = seed;
		hasTarget = false;
		spiralIndex = 0;
		EAT_SPEED = eatSpeed * 20;
	}

	public EntityAIAntsBlock(EntityAnimal entity, IBlockState state, int maxGrowthMetadata, float moveSpeed, int eatSpeed) {
		this(entity, state, maxGrowthMetadata, null, moveSpeed, eatSpeed);
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public boolean shouldContinueExecuting() {
		return !entity.isChild();
	}

	@Override
	public void updateTask() {
		EntityBlackAnt blackAnt = (EntityBlackAnt) entity;
		if (!shouldContinueExecuting())
			return;

		int xCoord = blackAnt.getDropPointX();
		int yCoord = blackAnt.getDropPointY();
		int zCoord = blackAnt.getDropPointZ();

		for (int i = 0; i < CHECKS_PER_TICK; i++)
			if (!hasTarget) {
				increment();

				Point p = getNextPoint();
				for (int y = -2; y < 1; y++)
					if (canEatBlock(entity.getEntityWorld().getBlockState(new BlockPos(xCoord + p.x, yCoord + y, zCoord + p.y)))) {
						cropX = xCoord + p.x;
						cropY = yCoord + y;
						cropZ = zCoord + p.y;
						hasTarget = true;
					}
			} else if (isEntityReady()) {
				moveToLocation();
				entity.getLookHelper().setLookPosition(cropX + 0.5D, cropY + 0.5D, cropZ + 0.5D, 30.0F, 8.0F);
				AxisAlignedBB blockbounds = getBlockAABB(cropX, cropY, cropZ);
				boolean flag = entity.getEntityBoundingBox().maxY >= blockbounds.minY && entity.getEntityBoundingBox().minY <= blockbounds.maxY && entity.getEntityBoundingBox().maxX >= blockbounds.minX && entity.getEntityBoundingBox().minX <= blockbounds.maxX && entity.getEntityBoundingBox().maxZ >= blockbounds.minZ && entity.getEntityBoundingBox().minZ <= blockbounds.maxZ;

				if (flag && canEatBlock(getTargetBlock())) {
					prepareToEat();
					eatTicks++;
				if (!canEatBlock(getTargetBlock())) {
						eatingInterupted();
						hasTarget = false;
						eatTicks = 0;
						return;
					}
					else if (EAT_SPEED <= eatTicks) {
						if (seed != null)
							Utils.dropStack(entity.getEntityWorld(), new BlockPos(cropX, cropY, cropZ), seed.copy());
						hasTarget = false;
						eatTicks = 0;
						afterEaten();
						return;
					}
				}
				if (!flag && eatTicks >  0 || entity.getEntityWorld().isAirBlock(new BlockPos(cropX, cropY, cropZ))) {
					eatingInterupted();
					hasTarget = false;
					eatTicks = 0;
					return;
				}
			}
	}

	private void increment() {
		spiralIndex++;
		if (spiralIndex >= spiral.size())
			spiralIndex = 0;
	}

	private Point getNextPoint() {
		return spiral.get(spiralIndex);
	}

	public IBlockState getTargetBlock() {
		IBlockState state = entity.getEntityWorld().getBlockState(new BlockPos(cropX, cropY, cropZ));
		return state;
	}

	/**
	 * Override this if you wish to do a more advanced checking on which blocks should be eaten
	 *
	 * @param state
	 * @yeah - it uses meta atm :(
	 * @return true is should eat block, false is it shouldn't
	 */
	
	protected boolean canEatBlock(IBlockState state) {
		return state == blockState && state.getBlock().getMetaFromState(state) == maxGrowthMetadata && state.getBlock() != Blocks.AIR;
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
	 * Gets called just after block has been eaten.
	 */
	protected abstract void afterEaten();

	protected AxisAlignedBB getBlockAABB(int x, int y, int z) {
		return new AxisAlignedBB(cropX, cropY, cropZ, cropX + 1D, cropY + 1D, cropZ + 1D);
	}
}