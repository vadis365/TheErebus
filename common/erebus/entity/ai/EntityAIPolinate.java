package erebus.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.ChunkCoordinates;
import erebus.entity.EntityWorkerBee;

public class EntityAIPolinate extends EntityAIFindFlower {

	private double moveSpeed;

	public EntityAIPolinate(EntityAnimal entity, double moveSpeed, int pollinateSpeed) {
		super(entity, null, 0, null, moveSpeed, pollinateSpeed);
		this.moveSpeed=moveSpeed;
	}

	@Override
	protected boolean canPolinate(int blockID, int blockMeta) {
		if (blockID == 0)
			return false;

		Block block = Block.blocksList[blockID];
		if (block.blockHardness == -1)
			return false;

		if (blockID == 170)
			return true;
		
		else if (block.hasTileEntity(blockMeta))
			return false;

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
		bee.currentFlightTarget = new ChunkCoordinates((int) flowerX, (int) flowerY, (int) flowerZ);
		bee.flyToTarget();
	}

	@Override
	protected void prepareToPollinate() {
		// TODO Auto-generated method stub
		
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
		bee.flyAbout();
	}
}