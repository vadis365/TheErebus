package erebus.entity.ai;

import erebus.entity.BlackAnt;
import erebus.utils.FakePlayerHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;

public class BlackAntPlantCrops extends BlackAntBlockHome {

    public static final int CROP_ID_SLOT = 1;
    public static final int INVENTORY_SLOT = 2;
    private final BlackAnt blackAnt;
    private final double moveSpeed;

    public BlackAntPlantCrops(BlackAnt blackAnt, double moveSpeed, int eatSpeed, boolean doDropItem) {
        super(blackAnt, null, moveSpeed, eatSpeed, doDropItem);
        this.moveSpeed = moveSpeed;
        this.dropItem = doDropItem;
        this.blackAnt = blackAnt;
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
        return !state.hasBlockEntity() && blackAnt.level().isEmptyBlock(pos.above())
                && (state.is(BlockTags.DIRT) || state.is(Blocks.GRASS_BLOCK) || state.is(Tags.Blocks.VILLAGER_FARMLANDS));
    }

    @Override
    protected boolean isEntityReady() {
        return blackAnt.isTamedAnt() && blackAnt.getAntRole() == blackAnt.PLANTER && !blackAnt.canCollectFromSilo
                && !blackAnt.isFilterSlotEmpty() && !blackAnt.isAntInvSlotEmpty()
                && ItemStack.isSameItem(blackAnt.getFilterSlotStack(), blackAnt.getAntInvSlotStack());
    }

    @Override
    protected void moveToLocation() {
        blackAnt.getNavigation().moveTo(targetX + 0.5D, targetY + 1, targetZ + 0.5D, moveSpeed);
    }

    @Override
    protected void prepareToEat() {
    }

    @Override
    protected void eatingInterupted() {
        blackAnt.getNavigation().stop();
    }

    @Override
    protected void afterEaten() {
        BlockPos pos = new BlockPos(targetX, targetY, targetZ);
        if (!blackAnt.level().isClientSide()) {
            if (!getTargetBlock().is(Tags.Blocks.VILLAGER_FARMLANDS)) {
                FakePlayerHandler.rightClickItemAt(blackAnt.level(), pos, InteractionHand.MAIN_HAND, Direction.UP, new ItemStack(Items.WOODEN_HOE), blackAnt.getPlayerOwner());
            }

            if (getTargetBlock().is(Tags.Blocks.VILLAGER_FARMLANDS) && !blackAnt.isFilterSlotEmpty() && !blackAnt.isAntInvSlotEmpty()) {
                ItemStack filterItem = blackAnt.getFilterSlotStack();
                ItemStack invItem = blackAnt.getAntInvSlotStack();

                if (ItemStack.isSameItem(filterItem, invItem)) {
                    FakePlayerHandler.rightClickItemAt(blackAnt.level(), pos, InteractionHand.MAIN_HAND, Direction.UP, invItem, blackAnt.getPlayerOwner());
                    blackAnt.inventory.setChanged();
                    if (blackAnt.getAntInvSlotStack().getCount() < 1)
                        blackAnt.inventory.setItem(INVENTORY_SLOT, ItemStack.EMPTY);
                }
            }
        }
    }

    @Override
    protected void dropItem() {
    }
}
