package erebus.entity.ai;

import java.awt.Point;
import java.util.List;

import erebus.core.helper.Spiral;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;

public abstract class EntityAIFindFlower extends EntityAIBase {

	/**
	 * The bigger you make this value the faster the AI will be. But performance will also decrease so be sensible
	 */
	private static final int CHECKS_PER_TICK = 1;

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

	public EntityAIFindFlower(EntityLiving entity, Block block, int blockMetadata, int pollinateSpeed) {
		this.entity = entity;
		this.blockMetadata = blockMetadata;
		this.block = block;
		hasTarget = false;
		spiralIndex = 0;
		COLLECT_SPEED = pollinateSpeed * 20;
	}

	@Override
	public boolean shouldExecute() {
		return entity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
	}

	@Override
	public boolean continueExecuting() {
		return entity.worldObj.getEntitiesWithinAABBExcludingEntity(entity, AxisAlignedBB.getBoundingBox(flowerX, flowerY + 1, flowerZ, flowerX + 1, flowerY + 2, flowerZ + 1)).isEmpty();
	}

	@Override
	public void updateTask() {
		if (!continueExecuting())
			return;

		int xCoord = (int) entity.posX;
		int yCoord = (int) entity.posY;
		int zCoord = (int) entity.posZ;

		for (int i = 0; i < CHECKS_PER_TICK; i++)
			if (!hasTarget) {
				increment();

				Point p = getNextPoint();
				for (int y = -8; y < 8; y++)
					if (canPolinate(entity.worldObj.getBlock(xCoord + p.x, yCoord + y, zCoord + p.y), entity.worldObj.getBlockMetadata(xCoord + p.x, yCoord + y, zCoord + p.y))) {
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
					entity.worldObj.destroyBlockInWorldPartially(entity.getEntityId(), flowerX, flowerY, flowerZ, getScaledcollectTicks());
					if (!canPolinate(entity.worldObj.getBlock(flowerX, flowerY, flowerZ), entity.worldObj.getBlockMetadata(flowerX, flowerY, flowerZ)))
						hasTarget = false;
					else if (COLLECT_SPEED <= collectTicks) {
						hasTarget = false;
						collectTicks = 0;
						afterPollination();
					}
				}
				if (!flag && collectTicks > 1) {
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

	public Block getTargetBlockID() {
		return entity.worldObj.getBlock(flowerX, flowerY, flowerZ);
	}

	protected boolean canPolinate(Block blockID, int meta) {
		return blockID == block && meta == blockMetadata;
	}

	protected abstract boolean isEntityReady();

	protected abstract void moveToLocation();

	protected abstract void prepareToPollinate();

	protected abstract void pollinationInterupted();

	protected abstract void afterPollination();

	protected AxisAlignedBB getBlockAABB(int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox(flowerX, flowerY, flowerZ, flowerX + 1.0D, flowerY + 1.0D, flowerZ + 1.0D);
	}
}