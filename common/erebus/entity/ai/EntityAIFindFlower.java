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

public abstract class EntityAIFindFlower extends EntityAIBase {

	private final double moveSpeed;
	private final int COLLECT_SPEED;
	protected final EntityLiving entity;
	private final int blockMetadata;
	private final Block block;

	private boolean hasTarget;
	public int flowerX;
	public int flowerY;
	public int flowerZ;
	private int spiralIndex;
	private int collectTicks;
	private static final List<Point> spiral = new Spiral(32, 32).spiral();

	public EntityAIFindFlower(EntityLiving entity, Block block, int blockMetadata, ItemStack seed, double moveSpeed, int pollinateSpeed) {
		this.entity = entity;
		this.blockMetadata = blockMetadata;
		this.block = block;
		hasTarget = false;
		spiralIndex = 0;
		this.moveSpeed = moveSpeed;
		COLLECT_SPEED = pollinateSpeed * 20;
	}

	public EntityAIFindFlower(EntityAnimal entity, Block block, int blockMetadata, float moveSpeed, int pollinateSpeed) {
		this(entity, block, blockMetadata, null, moveSpeed, pollinateSpeed);
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
				if (canPolinate(entity.worldObj.getBlockId(xCoord + p.x, yCoord + y, zCoord + p.y), entity.worldObj.getBlockMetadata(xCoord + p.x, yCoord + y, zCoord + p.y))) {
					flowerX = xCoord + p.x;
					flowerY = yCoord + y;
					flowerZ = zCoord + p.y;
					hasTarget = true;
				}
		} else if (isEntityReady()) {
			moveToLocation();
			entity.getLookHelper().setLookPosition(flowerX + 0.5D, flowerY + 0.5D, flowerZ + 0.5D, 30.0F, 8.0F);
			AxisAlignedBB blockbounds = getBlockAABB(flowerX, flowerY, flowerZ);
			boolean flag = entity.boundingBox.maxY >= blockbounds.minY && entity.boundingBox.minY <= blockbounds.maxY && entity.boundingBox.maxX >= blockbounds.minX && entity.boundingBox.minX <= blockbounds.maxX && entity.boundingBox.maxZ >= blockbounds.minZ && entity.boundingBox.minZ <= blockbounds.maxZ;

			if (flag) {
				prepareToPollinate();
				collectTicks++;
				entity.worldObj.destroyBlockInWorldPartially(entity.entityId, flowerX, flowerY, flowerZ, getScaledcollectTicks());
				if (!canPolinate(entity.worldObj.getBlockId(flowerX, flowerY, flowerZ), entity.worldObj.getBlockMetadata(flowerX, flowerY, flowerZ))) {
					hasTarget = false;
				} else if (COLLECT_SPEED <= collectTicks) {
					System.out.println("POLINATED");
					hasTarget = false;
					collectTicks = 0;
					afterPollination();
				}
			}
			if (!flag && collectTicks>0) {
				pollinationInterupted();
				hasTarget = false;
				collectTicks = 0;
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

	public int getTargetBlockID() {
		return entity.worldObj.getBlockId(flowerX, flowerY, flowerZ);
	}

	/**
	 * Override this if you wish to do a more advanced checking on which blocks
	 * should be eaten
	 * 
	 * @param blockID
	 * @param meta
	 * @return true is should eat block, false is it shouldn't
	 */
	protected boolean canPolinate(int blockID, int meta) {
		return blockID == block.blockID && meta == blockMetadata;
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
	protected abstract void prepareToPollinate();

	/**
	 * Allows any other tasks to be cancelled if the block cannot be eaten.
	 */
	protected abstract void pollinationInterupted();

	/**
	 * Gets called just after block has been eaten.
	 */
	protected abstract void afterPollination();

	protected AxisAlignedBB getBlockAABB(int x, int y, int z) {
		return AxisAlignedBB.getAABBPool().getAABB(flowerX, flowerY, flowerZ, flowerX + 1.0D, flowerY + 1.0D, flowerZ + 1.0D);
	}
}