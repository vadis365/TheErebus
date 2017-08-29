package erebus.entity.ai;

import erebus.ModBlocks;
import erebus.entity.EntityWorkerBee;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ChunkCoordinates;

public class EntityAIPolinate extends EntityAIFindFlower {

	public EntityAIPolinate(EntityLiving entity, int pollinateSpeed) {
		super(entity, null, 0, pollinateSpeed);
	}

	@Override
	protected boolean canPolinate(Block blockID, int blockMeta) {
		if (blockID == null)
			return false;
		else if (blockID == ModBlocks.stiga)
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
		if (flowerY > bee.boundingBox.minY - 1)
			bee.posY++;
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
		if (bee.getNectarPoints() < 127)
			bee.setNectarPoints(bee.getNectarPoints() + 2);
		if (bee.getTameState() == 0) {
			bee.setBeePollinating(false);
			bee.setBeeFlying(true);
			bee.flyAbout();
		} else if (bee.getTameState() == 1) {
			bee.setBeePollinating(false);
			bee.setBeeCollecting(true);
		}
	}
}