package erebus.entity.ai;

import erebus.entity.BlackAnt;
import erebus.utils.FakePlayerHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;

public class BlackAntPlantCrops extends BlackAntBlockHome {

	private final BlackAnt blackAnt;
	public static final int CROP_ID_SLOT = 1;
	public static final int INVENTORY_SLOT = 2;
	private final double moveSpeed;

	public BlackAntPlantCrops(BlackAnt blackAnt, double moveSpeed, int eatSpeed, boolean doDropItem) {
		super(blackAnt, null, moveSpeed, eatSpeed, doDropItem);
		this.moveSpeed = moveSpeed;
		this.dropItem = doDropItem;
		this.blackAnt = blackAnt;
	}

	@Override
	public boolean canUse() {
		return blackAnt.isTamedAnt() && blackAnt.getAntRole() == blackAnt.PLANTER && !blackAnt.canCollectFromSilo ? !blackAnt.getMoveControl().hasWanted() && super.canUse() : false;
	}

	@Override
	public boolean canContinueToUse() {
		return !blackAnt.canCollectFromSilo && !blackAnt.isAntInvSlotEmpty() && super.canContinueToUse();
	}

	@Override
	protected boolean canEatBlock(BlockState state) {
		BlockPos pos = new BlockPos(targetX, targetY, targetZ);
		// TODO no idea why the empty block above is failing so harvester will make dirt for now.
		return (state.is(BlockTags.DIRT) || state.is(Blocks.GRASS_BLOCK) || (state.is(Tags.Blocks.VILLAGER_FARMLANDS) && blackAnt.level().isEmptyBlock(pos.above())));
	}

	@Override
	protected boolean isEntityReady() {
		return true;
	}

	@Override
	protected void moveToLocation() {
		blackAnt.getMoveControl().setWantedPosition(targetX + 0.5D, targetY + 1, targetZ + 0.5D, moveSpeed);
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
				blackAnt.level().playSound(null, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
			}

			if (!blackAnt.isFilterSlotEmpty() && !blackAnt.isAntInvSlotEmpty()) {
				ItemStack filterItem = blackAnt.getFilterSlotStack();
				ItemStack invItem = blackAnt.getAntInvSlotStack();

				if (ItemStack.isSameItem(filterItem, invItem)) {
					FakePlayerHandler.rightClickItemAt(blackAnt.level(), pos, InteractionHand.MAIN_HAND, Direction.UP, invItem, blackAnt.getPlayerOwner());
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
