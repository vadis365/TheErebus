package erebus.entity.ai;


import erebus.entity.WorkerBee;
import erebus.registries.data.tags.ModBlockTags;
import net.minecraft.core.BlockPos;
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
        else return state.is(ModBlockTags.BEE_POLLINATION_BLOCKS);
    }

    @Override
    protected boolean isEntityReady() {
        return bee.getTarget() == null && !bee.beeCollecting;
    }

    @Override
    public boolean canUse() {
        return isEntityReady() && !bee.getMoveControl().hasWanted() && !bee.beePollinating && super.canUse();
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
        bee.getNavigation().moveTo(bee.getNavigation().createPath(new BlockPos(flowerX, flowerY + 1, flowerZ), 0), 1D);
    }

    @Override
    protected void prepareToPollinate() {
        bee.setBeePollinating(true);
        Vec3 vec3 = bee.getDeltaMovement();
        if (flowerY >= bee.getBoundingBox().minY - 0.75D)
            bee.setDeltaMovement(vec3.add(0, 0.04D, 0));
    }

    @Override
    protected void pollinationInterupted() {
        bee.setBeePollinating(false);
        bee.setBeeCollecting(bee.isTamedBee() && bee.getNectarPoints() > 0);
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
