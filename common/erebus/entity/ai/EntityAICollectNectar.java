package erebus.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.entity.EntityWorkerBee;
import erebus.item.ItemErebusMaterial;

public class EntityAICollectNectar extends EntityAIFindFlower {

	public EntityAICollectNectar(EntityLiving entity, int pollinateSpeed) {
		super(entity, null, 0, pollinateSpeed);
	}

	@Override
	protected boolean canPolinate(int blockID, int blockMeta) {
		EntityWorkerBee bee = (EntityWorkerBee) entity;
		if (blockID == 0)
			return false;

		else if (bee.getTameState()==1 && bee.getNectarPoints() > 0 && blockID == ModBlocks.honeyCombBlock.blockID)
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
		bee.setBeeCollecting(true);
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
		bee.setBeeCollecting(false);
		bee.setBeeFlying(true);
		bee.flyAbout();
	}

	@Override
	protected void afterPollination() {
		EntityWorkerBee bee = (EntityWorkerBee) entity;
		bee.setBeeCollecting(false);
		bee.setBeeFlying(true);
		if(bee.getNectarPoints() >0){
			bee.entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataNectar), 0.0F);
			bee.setNectarPoints(bee.getNectarPoints() - 1);
		}
		bee.flyAbout();
	}

}