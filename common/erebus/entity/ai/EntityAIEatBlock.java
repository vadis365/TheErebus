package erebus.entity.ai;

import java.awt.Point;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import erebus.core.helper.Spiral;
import erebus.core.helper.Utils;

public abstract class EntityAIEatBlock extends EntityAIBase {

	private final double moveSpeed;
	private final int EAT_SPEED;
	protected final EntityLiving entity;
	private final int maxGrowthMetadata;
	private final Block block;
	private final ItemStack seed;

	private boolean hasTarget;
	public int cropX;
	public int cropY;
	public int cropZ;
	private int spiralIndex;
	private int eatTicks;
	private static final List<Point> spiral = new Spiral(32, 32).spiral();

	public EntityAIEatBlock(EntityLiving entity, Block block, int maxGrowthMetadata, ItemStack seed, double moveSpeed, int eatSpeed) {
		this.entity = entity;
		this.maxGrowthMetadata = maxGrowthMetadata;
		this.block = block;
		this.seed = seed;
		hasTarget = false;
		spiralIndex = 0;
		this.moveSpeed = moveSpeed;
		EAT_SPEED = eatSpeed * 20;
	}

	public EntityAIEatBlock(EntityAnimal entity, Block block, int maxGrowthMetadata, float moveSpeed, int eatSpeed) {
		this(entity, block, maxGrowthMetadata, null, moveSpeed, eatSpeed);
	}

	@Override
	public boolean shouldExecute() {
		return entity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
	}

	@Override
	public boolean continueExecuting() {
		return !entity.isChild();
	}

	@Override
	public void updateTask() {
		if (!continueExecuting())
			return;

		int xCoord = (int) entity.posX;
		int yCoord = (int) entity.posY;
		int zCoord = (int) entity.posZ;

		if (!hasTarget) {
			increment();

			Point p = getNextPoint();
			for (int y = -2; y < 2; y++)
				if (canEatBlock(entity.worldObj.getBlockId(xCoord + p.x, yCoord + y, zCoord + p.y), entity.worldObj.getBlockMetadata(xCoord + p.x, yCoord + y, zCoord + p.y))) {
					cropX = xCoord + p.x;
					cropY = yCoord + y;
					cropZ = zCoord + p.y;
					hasTarget = true;
				}
		} else if (isEntityReady()) {
			entity.getMoveHelper().setMoveTo(cropX + 0.5D, cropY, cropZ + 0.5D, moveSpeed);
			entity.getLookHelper().setLookPosition(cropX + 0.5D, cropY + 0.5D, cropZ + 0.5D, 30.0F, 8.0F);
			AxisAlignedBB blockbounds = getBlockAABB(cropX, cropY, cropZ);
			boolean flag = entity.boundingBox.maxY >= blockbounds.minY && entity.boundingBox.minY <= blockbounds.maxY && entity.boundingBox.maxX >= blockbounds.minX && entity.boundingBox.minX <= blockbounds.maxX && entity.boundingBox.maxZ >= blockbounds.minZ && entity.boundingBox.minZ <= blockbounds.maxZ;

			if (flag) {
				prepareToEat();
				eatTicks++;
				entity.worldObj.destroyBlockInWorldPartially(entity.entityId, cropX, cropY, cropZ, getScaledEatTicks());
				if (!canEatBlock(entity.worldObj.getBlockId(cropX, cropY, cropZ), entity.worldObj.getBlockMetadata(cropX, cropY, cropZ))) {
					hasTarget = false;
					eatingInterupted();
				} else if (EAT_SPEED <= eatTicks) {
					entity.worldObj.playAuxSFXAtEntity(null, 2001, cropX, cropY, cropZ, entity.worldObj.getBlockId(cropX, cropY, cropZ) + (maxGrowthMetadata << 12));
					entity.worldObj.setBlockToAir(cropX, cropY, cropZ);
					if (seed != null)
						Utils.dropStack(entity.worldObj, cropX, cropY, cropZ, seed.copy());
					hasTarget = false;
					eatTicks = 0;
					afterEaten();
				}
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

	public int getTargetBlockID() {
		return entity.worldObj.getBlockId(cropX, cropY, cropZ);
	}

	/**
	 * Override this if you wish to do a more advanced checking on which blocks
	 * should be eaten
	 * 
	 * @param blockID
	 * @param meta
	 * @return true is should eat block, false is it shouldn't
	 */
	protected boolean canEatBlock(int blockID, int meta) {
		return blockID == block.blockID && meta == maxGrowthMetadata;
	}

	/**
	 * Test if entity is ready to eat block
	 * 
	 * @return true to allow block to be eaten. false to deny it.
	 */
	protected abstract boolean isEntityReady();

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
		return AxisAlignedBB.getAABBPool().getAABB(cropX, cropY, cropZ, cropX + 1.0D, cropY + 1.0D, cropZ + 1.0D);
	}
}