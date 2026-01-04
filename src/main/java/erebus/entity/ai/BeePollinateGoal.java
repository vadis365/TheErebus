package erebus.entity.ai;


import erebus.entity.WorkerBee;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;


public class BeePollinateGoal extends FindFlowerGoal {
	private final WorkerBee bee;

	public BeePollinateGoal(WorkerBee bee, int pollinateSpeed) {
		super(bee, null, pollinateSpeed);
		this.bee = bee;
	}

	@Override
	protected boolean canPolinate(BlockState state) {
		if (state == null)
			return false;
		else return state.is(ModTags.BEE_POLLINATION_BLOCKS);
    }

	@Override
	protected boolean isEntityReady() {
		return true;
	}
	
	@Override
	public boolean canUse() {
		return !bee.getMoveControl().hasWanted() && !bee.beeCollecting && !bee.beePollinating && super.canUse();
	}
	
	@Override
	public boolean canContinueToUse() {
		return bee.getTarget() == null && super.canContinueToUse();
	}

	@Override
	protected void moveToLocation() {
		if (bee.isTamedBee())
			bee.setBeeCollecting(false);
		bee.setBeePollinating(true);
		bee.getMoveControl().setWantedPosition(flowerX + 0.5D, flowerY + 1D, flowerZ + 0.5D, 1D);
	}

	@Override
	protected void prepareToPollinate() {
		Vec3 vec3 = bee.getDeltaMovement();
		if (flowerY >= bee.getBoundingBox().minY - 0.75D)
			bee.setDeltaMovement(vec3.multiply(1D, 1.04D, 1D));
	}

	@Override
	protected void pollinationInterupted() {
			bee.setBeePollinating(false);
		if (bee.isTamedBee())
			bee.setBeeCollecting(true);
	}

	@Override
	protected void afterPollination() {
		if (bee.getNectarPoints() < 127)
			bee.setNectarPoints(bee.getNectarPoints() + 2);
		if (!bee.isTamedBee()) {
			bee.setBeePollinating(false);
		} else if (bee.isTamedBee()) {
			bee.setBeePollinating(false);
			bee.setBeeCollecting(true);
		}
	}
}