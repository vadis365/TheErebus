package erebus.entity.ai;

import erebus.entity.BlackAnt;
import erebus.utils.FakePlayerHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class BlackAntPlantCrops extends EatBlockGoal {

	BlackAnt blackAnt;
	public static final int CROP_ID_SLOT = 1;
	public static final int INVENTORY_SLOT = 2;
	private final double moveSpeed;

	public BlackAntPlantCrops(BlackAnt blackAnt, double moveSpeed, int eatSpeed, boolean doDropItem) {
		super(blackAnt, null, moveSpeed, eatSpeed, doDropItem, 1);
		this.moveSpeed = moveSpeed;
		this.dropItem = doDropItem;
		this.blackAnt = blackAnt;
	}

	@Override
	public boolean canUse() {
		return true;//!blackAnt.canCollectFromSilo;
	}

	@Override
	public boolean canContinueToUse() {
		return true;// !blackAnt.canCollectFromSilo && !isAntInvSlotEmpty();
	}

	@Override
	protected boolean canEatBlock(BlockState state) {
		Block block = state.getBlock();
		BlockPos above = new BlockPos(targetX, targetY + 1, targetZ);
		if (state.isAir() || block == null)
			return false;

		if (state.is(Blocks.DIRT) || state.is(Blocks.GRASS_BLOCK))
			return true;
		if (state.is(Blocks.FARMLAND) && blackAnt.level().getBlockState(above).isAir())
			return true;
		else if (state.hasBlockEntity())
			return false;

		return false;
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
	}

	@Override
	protected void afterEaten() {
		BlockPos pos = new BlockPos(targetX, targetY, targetZ);
		if (!blackAnt.level().isClientSide()) {
			if (!getTargetBlock().is(Blocks.FARMLAND)) {
				FakePlayerHandler.rightClickItemAt(blackAnt.level(), pos, InteractionHand.MAIN_HAND, Direction.UP, new ItemStack(Items.WOODEN_HOE), blackAnt.getPlayerOwner());
				blackAnt.level().playSound(null, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
			}

			if (!isFilterSlotEmpty() && !isAntInvSlotEmpty()) {
				ItemStack filterItem = getFilterSlotStack();
				ItemStack invItem = getAntInvSlotStack();

				if (ItemStack.isSameItem(filterItem, invItem)) {
					FakePlayerHandler.rightClickItemAt(blackAnt.level(), pos, InteractionHand.MAIN_HAND, Direction.UP, invItem, blackAnt.getPlayerOwner());
					blackAnt.inventory.setItem(INVENTORY_SLOT, new ItemStack(invItem.getItem(), getAntInvSlotStack().getCount() - 1));
					if (getAntInvSlotStack().getCount() < 1)
						blackAnt.inventory.setItem(INVENTORY_SLOT, ItemStack.EMPTY);
				}
			}
		}
	}

	public boolean isFilterSlotEmpty() {
		return getFilterSlotStack().isEmpty();
	}

	public ItemStack getFilterSlotStack() {
		return blackAnt.inventory.getItem(CROP_ID_SLOT);
	}

	public boolean isAntInvSlotEmpty() {
		return getAntInvSlotStack().isEmpty();
	}

	public ItemStack getAntInvSlotStack() {
		return blackAnt.inventory.getItem(INVENTORY_SLOT);
	}

	@Override
	protected void dropItem() {
		// TODO Auto-generated method stub
	}

	@Override
	protected AABB getBlockAABB(int x, int y, int z) {
		return new AABB(targetX, targetY, targetZ, targetX + 1D, targetY + 1D, targetZ + 1D);
	}

}
