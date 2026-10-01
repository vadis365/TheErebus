package erebus.entity.ai;

import erebus.entity.BlackAnt;
import erebus.utils.FakePlayerHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BlackAntHarvestCrops extends BlackAntBlockHome {
    private final BlackAnt blackAnt;
    private final double moveSpeed;

    public BlackAntHarvestCrops(BlackAnt blackAnt, double moveSpeed, int eatSpeed, boolean shouldDropItem) {
        super(blackAnt, null, moveSpeed, eatSpeed, shouldDropItem);
        this.blackAnt = blackAnt;
        this.moveSpeed = moveSpeed;
    }

    @Override
    public boolean canUse() {
        return blackAnt.isTamedAnt() && blackAnt.getAntRole() == blackAnt.HARVESTER && !blackAnt.getMoveControl().hasWanted() && super.canUse();
    }

    @Override
    protected boolean canEatBlock(BlockPos pos, BlockState state) {
        Block block = state.getBlock();
        return block instanceof CropBlock && ((CropBlock) block).isMaxAge(state);
    }

    @Override
    protected boolean isEntityReady() {
        return blackAnt.isTamedAnt() && blackAnt.getAntRole() == blackAnt.HARVESTER;
    }

    @Override
    protected void moveToLocation() {
        blackAnt.getNavigation().moveTo(targetX + 0.5D, targetY, targetZ + 0.5D, moveSpeed);
    }

    @Override
    protected void prepareToEat() {
    }

    @Override
    protected void eatingInterupted() {
        blackAnt.getNavigation().stop();
    }

    @Override
    protected void dropItem() {
    }

    @Override
    protected void afterEaten() {
        FakePlayerHandler.breakBlockAt(blackAnt.level(), new BlockPos(targetX, targetY, targetZ), blackAnt.getPlayerOwner());
    }
}
