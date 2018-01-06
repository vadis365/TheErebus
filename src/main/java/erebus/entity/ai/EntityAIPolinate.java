package erebus.entity.ai;


import erebus.blocks.BlockStigma;
import erebus.entity.EntityWorkerBee;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;


public class EntityAIPolinate extends EntityAIFindFlower {

	public EntityAIPolinate(EntityLiving entity, int pollinateSpeed) {
		super(entity, null, pollinateSpeed);
		setMutexBits(2);
	}

	@Override
	protected boolean canPolinate(IBlockState state) {
		if (state == null)
			return false;
		else if (state.getBlock() instanceof BlockStigma)
			return true;

		return false;
	}

	@Override
	protected boolean isEntityReady() {
		return true;
	}
	
	@Override
	public boolean shouldExecute() {
		EntityWorkerBee bee = (EntityWorkerBee) entity;
		return !bee.beeCollecting && !bee.beePollinating && super.shouldExecute();
		//return !entity.getNavigator().noPath() && super.shouldExecute();
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return entity.getAttackTarget() == null;
	}

	@Override
	protected void moveToLocation() {
		if(!entity.getEntityWorld().isRemote) {
		EntityWorkerBee bee = (EntityWorkerBee) entity;
		bee.getJumpHelper().doJump();
		if (bee.getTameState() == 1)
			bee.setBeeCollecting(false);
		bee.setBeePollinating(true);
		bee.getNavigator().tryMoveToXYZ(flowerX + 0.5D, flowerY + 1D, flowerZ + 0.5D, 0.25D);
		}
	}

	@Override
	protected void prepareToPollinate() {
		EntityWorkerBee bee = (EntityWorkerBee) entity;
		if (flowerY >= bee.getEntityBoundingBox().minY - 0.75D)
			bee.motionY+=0.04D;
	}

	@Override
	protected void pollinationInterupted() {
		if(!entity.getEntityWorld().isRemote) {
		EntityWorkerBee bee = (EntityWorkerBee) entity;
		bee.setBeePollinating(false);
		bee.setBeeCollecting(true);
		//bee.flyAbout();
		}
	}

	@Override
	protected void afterPollination() {
		if (!entity.getEntityWorld().isRemote) {
			EntityWorkerBee bee = (EntityWorkerBee) entity;
			if (bee.getNectarPoints() < 127)
				bee.setNectarPoints(bee.getNectarPoints() + 2);
			if (bee.getTameState() == 0) {
				bee.setBeePollinating(false);
				bee.flyAbout();
			} else if (bee.getTameState() == 1) {
				bee.setBeePollinating(false);
				bee.setBeeCollecting(true);
				bee.getNavigator().clearPath();
			}
		}
	}
}