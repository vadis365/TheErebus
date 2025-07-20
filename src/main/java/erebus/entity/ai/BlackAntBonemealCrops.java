package erebus.entity.ai;

import erebus.entity.BlackAnt;
import erebus.utils.FakePlayerHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BlackAntBonemealCrops extends BlackAntBlockHome {
	public static final int INVENTORY_SLOT = 2;
	private BlackAnt blackAnt;
	private double moveSpeed;

	public BlackAntBonemealCrops(BlackAnt blackAnt, double moveSpeed, int eatSpeed, boolean shouldDropItem) {
		super(blackAnt, null, moveSpeed, eatSpeed, shouldDropItem);
		this.blackAnt = blackAnt;
		this.moveSpeed = moveSpeed;
	}

	@Override
	public boolean canUse() {
		return blackAnt.isTamedAnt() && blackAnt.getAntRole() == blackAnt.FERTILIZER && !blackAnt.canCollectFromSilo && !blackAnt.isAntInvSlotEmpty() ? !blackAnt.getMoveControl().hasWanted() && super.canUse() : false;
	}

	@Override
	public boolean canContinueToUse() {
		return !blackAnt.canCollectFromSilo && !blackAnt.isAntInvSlotEmpty() && super.canContinueToUse();
	}

	@Override
	protected boolean canEatBlock(BlockState state) {
		Block block = state.getBlock();
		return block instanceof CropBlock && !((CropBlock) block).isMaxAge(state);
	}

	@Override
	protected boolean isEntityReady() {
		return true;
	}

	@Override
	protected void moveToLocation() {
		blackAnt.getMoveControl().setWantedPosition(targetX + 0.5D, targetY, targetZ + 0.5D, moveSpeed);
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
		BlockPos pos = new BlockPos(targetX, targetY, targetZ);
		if (!blackAnt.level().isClientSide()) {
			Player player = FakePlayerHandler.get((ServerLevel) blackAnt.level(), blackAnt.getPlayerOwner());
			if (!blackAnt.isFilterSlotEmpty() && !blackAnt.isAntInvSlotEmpty()) {
				ItemStack filterItem = blackAnt.getFilterSlotStack();
				ItemStack invItem = blackAnt.getAntInvSlotStack();

				if (ItemStack.isSameItem(filterItem, invItem)) {
					FakePlayerHandler.rightClickItemAt(blackAnt.level(), pos, InteractionHand.MAIN_HAND, Direction.UP, invItem, blackAnt.getPlayerOwner());
					BoneMealItem.applyBonemeal(blackAnt.getAntInvSlotStack(), blackAnt.level(), pos, player);
					if (blackAnt.getAntInvSlotStack().getCount() < 1)
						blackAnt.inventory.setItem(INVENTORY_SLOT, ItemStack.EMPTY);
				}
			}
		}
	}

}
