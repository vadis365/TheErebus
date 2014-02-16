package erebus.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ChunkCoordinates;
import erebus.ModBlocks;
import erebus.entity.EntityWorkerBee;

public class EntityAIPolinate extends EntityAIFindFlower {

	public EntityAIPolinate(EntityLiving entity, int pollinateSpeed) {
		super(entity, null, 0, pollinateSpeed);
	}

	@Override
	protected boolean canPolinate(int blockID, int blockMeta) {
		if (blockID == 0)
			return false;

		else if (blockID == ModBlocks.erebusFlower.blockID && blockMeta == 0)
			return true;

		return false;
	}

	@Override
	protected boolean isEntityReady() {
		return true;
	}

	@Override
	protected void moveToLocation() {
		EntityWorkerBee bee = (EntityWorkerBee) entity;
		bee.setBeePollinating(true);
		bee.setBeeFlying(false);
		bee.currentFlightTarget = new ChunkCoordinates(flowerX, flowerY, flowerZ);
		bee.flyToTarget();
	}

	@Override
	protected void prepareToPollinate() {

	}

	@Override
	protected void pollinationInterupted() {
		EntityWorkerBee bee = (EntityWorkerBee) entity;
		bee.setBeePollinating(false);
		bee.setBeeFlying(true);
		bee.flyAbout();
	}

	@Override
	protected void afterPollination() {
		EntityWorkerBee bee = (EntityWorkerBee) entity;
		bee.setBeePollinating(false);
		bee.setBeeFlying(true);
		bee.setNectarPoints(bee.getNectarPoints() + 1);
		bee.flyAbout();
	}

}