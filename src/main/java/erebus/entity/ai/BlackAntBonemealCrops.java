package erebus.entity.ai;

import erebus.entity.BlackAnt;
import erebus.utils.FakePlayerHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BlackAntBonemealCrops extends BlackAntBlockHome {
    public static final int INVENTORY_SLOT = 2;
    private final BlackAnt blackAnt;
    private final double moveSpeed;

    public BlackAntBonemealCrops(BlackAnt blackAnt, double moveSpeed, int eatSpeed, boolean shouldDropItem) {
        super(blackAnt, null, moveSpeed, eatSpeed, shouldDropItem);
        this.blackAnt = blackAnt;
        this.moveSpeed = moveSpeed;
    }

    @Override
    public boolean canUse() {
        return isEntityReady() && !blackAnt.getMoveControl().hasWanted() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return !blackAnt.canCollectFromSilo && !blackAnt.isAntInvSlotEmpty() && super.canContinueToUse();
    }

    @Override
    protected boolean canEatBlock(BlockPos pos, BlockState state) {
        Block block = state.getBlock();
        return block instanceof CropBlock && !((CropBlock) block).isMaxAge(state);
    }

    @Override
    protected boolean isEntityReady() {
        return blackAnt.isTamedAnt() && blackAnt.getAntRole() == blackAnt.FERTILIZER && !blackAnt.canCollectFromSilo
                && blackAnt.getAntInvSlotStack().getItem() instanceof BoneMealItem
                && ItemStack.isSameItem(blackAnt.getFilterSlotStack(), blackAnt.getAntInvSlotStack());
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
        if (!isEntityReady()) return;
        FakePlayerHandler.rightClickItemAt(blackAnt.level(), new BlockPos(targetX, targetY, targetZ), InteractionHand.MAIN_HAND,
                Direction.UP, blackAnt.getAntInvSlotStack(), blackAnt.getPlayerOwner());
        if (blackAnt.getAntInvSlotStack().isEmpty()) blackAnt.inventory.setItem(INVENTORY_SLOT, ItemStack.EMPTY);
        else blackAnt.inventory.setChanged();
    }
}
