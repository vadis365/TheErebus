package erebus.entity.ai;

import java.awt.Point;
import java.util.List;

import erebus.core.helper.Spiral;
import erebus.core.helper.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public abstract class EntityAIEatBlock extends EntityAIBase {

	/**
	 * The bigger you make this value the faster the AI will be. But performance will also decrease so be sensible
	 */
	private static final int CHECKS_PER_TICK = 3;

	private final int EAT_SPEED;
	protected final EntityLiving entity;
	private final int maxGrowthMetadata;
	private final IBlockState blockState;

	private boolean hasTarget;
	public int cropX;
	public int cropY;
	public int cropZ;
	private int spiralIndex;
	private int eatTicks;
	private static final List<Point> spiral = new Spiral(16, 16).spiral();

	public EntityAIEatBlock(EntityLiving entity, IBlockState state, int maxGrowthMetadata, double moveSpeed, int eatSpeed) {
		this.entity = entity;
		this.maxGrowthMetadata = maxGrowthMetadata;
		blockState = state;
		hasTarget = false;
		spiralIndex = 0;
		EAT_SPEED = eatSpeed * 20;
	}

	@Override
	public boolean shouldExecute() {
		return entity.getEntityWorld().getGameRules().hasRule("mobGriefing");
	}

	@Override
	public boolean shouldContinueExecuting() {
		return !entity.isChild();
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
				for (int y = -2; y < 2; y++)
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

				if (flag) {
					prepareToEat();
					eatTicks++;
					entity.getEntityWorld().sendBlockBreakProgress(entity.getEntityId(), new BlockPos(cropX, cropY, cropZ), getScaledEatTicks());
					if (!canEatBlock(entity.getEntityWorld().getBlockState(new BlockPos(cropX, cropY, cropZ))))
						hasTarget = false;
					else if (EAT_SPEED <= eatTicks) {
						entity.getEntityWorld().playEvent(null, 2001, new BlockPos(cropX, cropY, cropZ), Block.getIdFromBlock(entity.getEntityWorld().getBlockState(new BlockPos(cropX, cropY, cropZ)).getBlock()) + (maxGrowthMetadata << 12));
						if (getTargetBlock() != Blocks.TALLGRASS)
							Utils.dropStack(entity.getEntityWorld(), new BlockPos(cropX, cropY, cropZ), new ItemStack(getTargetBlock().getItemDropped(entity.getEntityWorld().getBlockState(new BlockPos (cropX, cropY, cropZ)), entity.getEntityWorld().rand, 0), 1));
						hasTarget = false;
						eatTicks = 0;
						afterEaten();
					}
				}
				if (!flag && eatTicks > 0) {
					eatingInterupted();
					hasTarget = false;
					eatTicks = 0;
				}
			}
	}

	private int getScaledEatTicks() {
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

	public Block getTargetBlock() {
		return entity.getEntityWorld().getBlockState(new BlockPos(cropX, cropY, cropZ)).getBlock();
	}

	/**
	 * Override this if you wish to do a more advanced checking on which blocks should be eaten
	 *
	 * @param block
	 * @return true is should eat block, false is it shouldn't
	 */
	protected boolean canEatBlock(IBlockState state) {
		return state == blockState;
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
		return new AxisAlignedBB(cropX, cropY, cropZ, cropX + 1.0D, cropY + 1.0D, cropZ + 1.0D);
	}
}