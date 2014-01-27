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

	private final int diffEaten = 0;// 0-peaceful,1-easy,2-med,3-hard
	private final int maxTicks = 240;// approx 30 tick/sec +- processing delays
	private final int maxDToWood = 8;// this variable has a childish name.
	// he-he.
	protected EntityLiving theEntity;
	public int WoodX, WoodY, WoodZ = -1;
	private final double moveSpeed;
	private int ticksSpent = 0;

	public EntityAIEatWoodenItem(EntityLiving entityLiving, double d) {
		theEntity = entityLiving;
		moveSpeed = d;
		setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		if (theEntity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
			boolean hasFoundWood = findClosestWood(maxDToWood);
			if (!hasFoundWood) {
				((EntityBeetleLarva) theEntity).setIsEating(false);
				return false;
			}
			return true;
		} else
			return false;
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
	}

	@Override
	public boolean continueExecuting() {
		return isBlockEdible(WoodX, WoodY, WoodZ) || !theEntity.getNavigator().noPath();
	}

	@Override
	public void updateTask() {
		AxisAlignedBB blockbounds = getBlockAABB(WoodX, WoodY, WoodZ);
		theEntity.getLookHelper().setLookPosition(WoodX + 0.5D, WoodY, WoodZ + 0.5D, 50.0F, 8.0F);
		theEntity.getNavigator().tryMoveToXYZ(WoodX + 0.5D, WoodY, WoodZ + 0.5D, moveSpeed);
		if (theEntity.getNavigator().noPath() && !theEntity.isCollidedHorizontally)
			theEntity.getMoveHelper().setMoveTo(WoodX + 0.5D, WoodY, WoodZ + 0.5D, moveSpeed);
		ticksSpent++;
		if (theEntity.boundingBox.maxY >= blockbounds.minY && theEntity.boundingBox.minY <= blockbounds.maxY && theEntity.boundingBox.maxX >= blockbounds.minX && theEntity.boundingBox.minX <= blockbounds.maxX && theEntity.boundingBox.maxZ >= blockbounds.minZ &&
		theEntity.boundingBox.minZ <= blockbounds.maxZ && ticksSpent < maxTicks) {
			((EntityBeetleLarva) theEntity).setIsEating(true);
			((EntityBeetleLarva) theEntity).munchBlock();
		} else
			((EntityBeetleLarva) theEntity).setIsEating(false);
		if (ticksSpent >= maxTicks && theEntity.worldObj.difficultySetting >= diffEaten && theEntity.boundingBox.maxY >= blockbounds.minY && theEntity.boundingBox.minY <= blockbounds.maxY && theEntity.boundingBox.maxX >= blockbounds.minX && theEntity.boundingBox.minX <= blockbounds.maxX &&
		theEntity.boundingBox.maxZ >= blockbounds.minZ && theEntity.boundingBox.minZ <= blockbounds.maxZ) {
			theEntity.worldObj.destroyBlock(WoodX, WoodY, WoodZ, false);
			float size = ((EntityBeetleLarva) theEntity).getLarvaSize();
			((EntityBeetleLarva) theEntity).setLarvaSize(size + 0.2F);
			((EntityBeetleLarva) theEntity).setMoveTasks(true);
			ticksSpent = 0;
		}
		super.updateTask();
	}

	private boolean findClosestWood(int maxDistance) {
		for (int currentCheckDistance = 1; currentCheckDistance < maxDistance; currentCheckDistance++)
			for (int x = -currentCheckDistance; x <= currentCheckDistance; x++)
				for (int y = -currentCheckDistance; y <= currentCheckDistance; y++)
					for (int z = -currentCheckDistance; z <= currentCheckDistance; z++)
						if (isBlockEdible((int) theEntity.posX + x, (int) theEntity.posY + y, (int) theEntity.posZ + z)) {
							WoodX = (int) theEntity.posX + x;
							WoodY = (int) theEntity.posY + y;
							WoodZ = (int) theEntity.posZ + z;
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
		else if (block.blockMaterial != Material.wood || block instanceof BlockLog || block instanceof BlockBambooCrop || block instanceof BlockHollowLog)
			return false;
		else if (ConfigurationHandler.beetleLarvaEating == 0 && block.hasTileEntity(theEntity.worldObj.getBlockMetadata(x, y, z)))
			return false;

		return true;
	}

	protected AxisAlignedBB getBlockAABB(int par1, int par2, int par3) {
		return AxisAlignedBB.getAABBPool().getAABB(WoodX, WoodY, WoodZ, (float) (WoodX + 1.0D), WoodY + 1.0D, (float) (WoodZ + 1.0D));
	}
}
