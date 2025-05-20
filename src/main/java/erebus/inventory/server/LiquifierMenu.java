package erebus.inventory.server;

import erebus.block.entity.LiquifierBlockEntity;
import erebus.registries.client.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import javax.annotation.Nonnull;

public class LiquifierMenu extends AbstractContainerMenu {
	public LiquifierBlockEntity liquifier;
	public int numRows = 3;

	public LiquifierMenu(final int windowId, final Inventory playerInventory, FriendlyByteBuf extra) {
		super(ModMenuTypes.LIQUIFIER.get(), windowId);
		BlockPos tilePos = extra.readBlockPos();
		BlockEntity tile = playerInventory.player.getCommandSenderWorld().getBlockEntity(tilePos);
		if (!(tile instanceof LiquifierBlockEntity))
			return;
		liquifier = (LiquifierBlockEntity) tile;

		int i = (numRows - 4) * 18;
		int j;
		int k;

		addSlot(new Slot((Container)tile, 0, 36, 36));

		for (j = 0; j < 3; ++j)
			for (k = 0; k < 9; ++k)
				addSlot(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 102 + j * 18 + i));

		for (j = 0; j < 9; ++j)
			addSlot(new Slot(playerInventory, j, 8 + j * 18, 160 + i));
	}
	
	@Override
	public boolean stillValid(@Nonnull Player player) {
		return true;
	}

	@Nonnull
	@Override
	public ItemStack quickMoveStack(@Nonnull Player player, int slotIndex) {
		ItemStack is = ItemStack.EMPTY;
		Slot slot = slots.get(slotIndex);

		if (slot != null && slot.hasItem()) {
			ItemStack is1 = slot.getItem();
			is = is1.copy();

			if (slotIndex < numRows * 9) {
				if (!moveItemStackTo(is1, numRows * 9, slots.size(), true))
					return ItemStack.EMPTY;
			} else if (!moveItemStackTo(is1, 0, numRows * 9, false))
				return ItemStack.EMPTY;

			if (is1.getCount() == 0)
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();
		}

		return is;
	}

	@Override
	protected boolean moveItemStackTo(@Nonnull ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
		boolean merged = false;
		int slotIndex = startIndex;

		if (reverseDirection)
			slotIndex = endIndex - 1;

		Slot slot;
		ItemStack slotstack;

		if (stack.isStackable()) {
			while (stack.getCount() > 0 && (!reverseDirection && slotIndex < endIndex || reverseDirection && slotIndex >= startIndex)) {
				slot = this.slots.get(slotIndex);
				slotstack = slot.getItem();

				if (!slotstack.isEmpty() && slotstack.getItem() == stack.getItem() && stack.getDamageValue() == slotstack.getDamageValue() && ItemStack.isSameItemSameComponents(stack, slotstack) && slotstack.getCount() < slot.getMaxStackSize()) {
					int mergedStackSize = stack.getCount() + Math.min(slotstack.getCount(), slot.getMaxStackSize());

					if (mergedStackSize <= stack.getMaxStackSize() && mergedStackSize <= slot.getMaxStackSize()) {
						stack.setCount(0);
						slotstack.setCount(mergedStackSize);
						slot.setChanged();
						merged = true;
					} else if (slotstack.getCount() < stack.getMaxStackSize() && slotstack.getCount() < slot.getMaxStackSize()) {
						if (slot.getMaxStackSize() >= stack.getMaxStackSize()) {
							stack.shrink(stack.getMaxStackSize() - slotstack.getCount());
							slotstack.setCount(stack.getMaxStackSize());
							slot.setChanged();
							merged = true;
						}
						else if (slot.getMaxStackSize() < stack.getMaxStackSize()) {
							stack.shrink(slot.getMaxStackSize() - slotstack.getCount());
							slotstack.setCount(slot.getMaxStackSize());
							slot.setChanged();
							merged = true;
						}
					}
				}

				if (reverseDirection)
					--slotIndex;
				else
					++slotIndex;
			}
		}

		if (stack.getCount() > 0) {
			if (reverseDirection)
				slotIndex = endIndex - 1;
			else
				slotIndex = startIndex;

			while (!reverseDirection && slotIndex < endIndex || reverseDirection && slotIndex >= startIndex) {
				slot = this.slots.get(slotIndex);
				slotstack = slot.getItem();
				if (slotstack.isEmpty() && slot.mayPlace(stack) && slot.getMaxStackSize() < stack.getCount()) {
					ItemStack copy = stack.copy();
					copy.setCount(slot.getMaxStackSize());
					stack.shrink(slot.getMaxStackSize());
					slot.set(copy);
					slot.setChanged();
					merged = true;
					break;
				} else if (slotstack.isEmpty() && slot.mayPlace(stack)) {
					slot.set(stack.copy());
					slot.setChanged();
					stack.setCount(0);
					merged = true;
					break;
				}

				if (reverseDirection)
					--slotIndex;
				else
					++slotIndex;
			}
		}

		return merged;
	}
}