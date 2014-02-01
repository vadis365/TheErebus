package erebus.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import erebus.block.BlockBambooCrop;
import erebus.block.BlockHollowLog;
import erebus.core.handler.ConfigurationHandler;
import erebus.entity.EntityBeetleLarva;

public class EntityAIEatWoodenItem extends EntityAIBase {

	private final int maxTicks = 240;// approx 30 tick/sec +- processing delays
	protected EntityBeetleLarva theEntity;
	public int woodX, woodY, woodZ = -1;
	private final double moveSpeed;
	private int ticksSpent = 0;
	private int coolDown;

	public EntityAIEatWoodenItem(EntityBeetleLarva entityLiving, double d) {
		theEntity = entityLiving;
		moveSpeed = d;
	}

	@Override
	public boolean shouldExecute() {
		if (theEntity.worldObj.getGameRules().getGameRuleBooleanValue(
				"mobGriefing")) {
			if (!hasTarget()) {
				theEntity.setIsEating(false);
				ticksSpent = 0;
				return false;
			}
			return true;
		} else
			return false;
	}

	@Override
	public boolean continueExecuting() {
		return isBlockEdible(woodX, woodY, woodZ)
				|| !theEntity.getNavigator().noPath();
	}

	@Override
	public void updateTask() {
		if (!continueExecuting())
			return;
		theEntity.getLookHelper().setLookPosition(woodX + 0.5D, woodY,
				woodZ + 0.5D, 50.0F, 8.0F);
		theEntity.getNavigator().tryMoveToXYZ(woodX + 0.5D, woodY,
				woodZ + 0.5D, moveSpeed);
		if (theEntity.getNavigator().noPath()
				&& !theEntity.isCollidedHorizontally)
			theEntity.getMoveHelper().setMoveTo(woodX + 0.5D, woodY,
					woodZ + 0.5D, moveSpeed);
		AxisAlignedBB blockbounds = getBlockAABB(woodX, woodY, woodZ);
		boolean flag = theEntity.boundingBox.maxY >= blockbounds.minY
				&& theEntity.boundingBox.minY <= blockbounds.maxY
				&& theEntity.boundingBox.maxX >= blockbounds.minX
				&& theEntity.boundingBox.minX <= blockbounds.maxX
				&& theEntity.boundingBox.maxZ >= blockbounds.minZ
				&& theEntity.boundingBox.minZ <= blockbounds.maxZ
				&& ticksSpent < maxTicks;
		if (flag)
			if (isBlockEdible(woodX, woodY, woodZ)) {
				ticksSpent++;
				theEntity.setIsEating(true);
				theEntity.munchBlock();
				if (ticksSpent >= maxTicks) {
					theEntity.worldObj.destroyBlock(woodX, woodY, woodZ, false);
					float size = theEntity.getLarvaSize();
					theEntity.setLarvaSize(size + 0.2F);
					theEntity.setIsEating(false);
					ticksSpent = 0;
				}
			} else {
				theEntity.setIsEating(false);
				ticksSpent = 0;
			}
		super.updateTask();
	}

	private boolean hasTarget() {
		int xCoord = (int) theEntity.posX;
		int yCoord = (int) theEntity.posY;
		int zCoord = (int) theEntity.posZ;
		for (int x = -8; x < 8; x++)
			for (int y = -8; y < 8; y++)
				for (int z = -8; z < 8; z++)
					if (isBlockEdible(xCoord + x, yCoord + y, zCoord + z)) {
						woodX = xCoord + x;
						woodY = yCoord + y;
						woodZ = zCoord + z;
						return true;
					}
		return false;
	}

	private boolean isBlockEdible(int x, int y, int z) {
		int blockID = theEntity.worldObj.getBlockId(x, y, z);
		if (blockID == 0)
			return false;

		Block block = Block.blocksList[blockID];
		if (block.blockHardness == -1)
			return false;

		if (ConfigurationHandler.beetleLarvaEating == 2)
			return true;
		else if (block.blockMaterial != Material.wood
				|| block instanceof BlockLog
				|| block instanceof BlockBambooCrop
				|| block instanceof BlockHollowLog)
			return false;
		else if (ConfigurationHandler.beetleLarvaEating == 0
				&& block.hasTileEntity(theEntity.worldObj.getBlockMetadata(x,
						y, z)))
			return false;

		return true;
	}

	protected AxisAlignedBB getBlockAABB(int par1, int par2, int par3) {
		return AxisAlignedBB.getAABBPool().getAABB(woodX, woodY, woodZ,
				(float) (woodX + 1.0D), woodY + 1.0D, (float) (woodZ + 1.0D));
	}
}
