package erebus.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ChunkCoordinates;
import erebus.ModBlocks;
import erebus.entity.EntityWasp;

public class EntityAISeekHoneyTreat extends EntityAIFindFlower {

	public EntityAISeekHoneyTreat(EntityLiving entity, int pollinateSpeed) {
		super(entity, null, 0, pollinateSpeed);
	}

	@Override
	protected boolean canPolinate(int blockID, int blockMeta) {
		if (blockID == 0)
			return false;

		else if (blockID == ModBlocks.honeyTreat.blockID && blockMeta <6)
			return true;

		return false;
	}

	@Override
	protected boolean isEntityReady() {
		return true;
	}

	@Override
	protected void moveToLocation() {
		EntityWasp wasp = (EntityWasp) entity;
		wasp.tasks.removeTask(wasp.aiAttackOnCollide);
		wasp.targetTasks.removeTask(wasp.aiNearestAttackableTarget);
		wasp.setWaspEating(true);
		wasp.setWaspFlying(false);
		wasp.currentFlightTarget = new ChunkCoordinates(flowerX, flowerY-1, flowerZ);
		wasp.flyToTarget();
	}

	@Override
	protected void prepareToPollinate() {

	}

	@Override
	protected void pollinationInterupted() {
		EntityWasp wasp = (EntityWasp) entity;
		wasp.setWaspEating(false);
		wasp.setWaspFlying(true);
		wasp.tasks.addTask(2, wasp.aiAttackOnCollide);
		wasp.targetTasks.addTask(1, wasp.aiNearestAttackableTarget);
		wasp.flyAbout();
	}

	@Override
	protected void afterPollination() {
		EntityWasp wasp = (EntityWasp) entity;
		if(wasp.worldObj.getBlockId(flowerX, flowerY, flowerZ)==ModBlocks.honeyTreat.blockID) {
			int meta = entity.worldObj.getBlockMetadata(flowerX, flowerY, flowerZ) + 1;
			if (meta >= 6) {
				wasp.worldObj.setBlockToAir(flowerX, flowerY, flowerZ);
			} else {
				wasp.worldObj.setBlockMetadataWithNotify(flowerX, flowerY, flowerZ, meta, 2);
			}
		}
		wasp.setWaspEating(false);
		wasp.setWaspFlying(true);
		wasp.tasks.addTask(2, wasp.aiAttackOnCollide);
		wasp.targetTasks.addTask(1, wasp.aiNearestAttackableTarget);
		wasp.flyAbout();
	}
}