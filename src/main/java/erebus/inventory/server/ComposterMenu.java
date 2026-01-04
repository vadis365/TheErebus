package erebus.inventory.server;

import erebus.registries.client.ModMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class ComposterMenu extends AbstractContainerMenu {
	public static final int DATA_MOULD_PROGRESS = 0;
	public static final int DATA_COMPOSTING_PROGRESS = 1;
	public static final int DATA_MOULD_MAX_TIME = 2;
    private final Container container;
    private final ContainerData data;
	public int numRows = 3;
	
    public ComposterMenu(int id, Inventory inv) {
        this(id, inv, new SimpleContainer(3), new SimpleContainerData(3));
    }

	public ComposterMenu(int windowId, Inventory playerInventory, Container container, ContainerData data) {
		super(ModMenuTypes.COMPOSTER.get(), windowId);
        checkContainerDataCount(data, 3);
        this.container = container;
        this.data = data;
		
		addSlot(new Slot(container, 0, 56, 17));
		addSlot(new Slot(container, 1, 56, 53));
		addSlot(new Slot(container, 2, 116, 35));

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 9; j++)
				addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

		for (int i = 0; i < 9; i++)
			addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
		addDataSlots(data);
	}
	
	@Override
	public boolean stillValid(@Nonnull Player player) {
		return container.stillValid(player);
	}

	@Nonnull
	@Override
	public ItemStack quickMoveStack(@Nonnull Player player, int slotIndex) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = slots.get(slotIndex);

		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();

			if (slotIndex == 2) {
                if (!moveItemStackTo(itemstack1, 3, 39, true))
                    return ItemStack.EMPTY;
                slot.setByPlayer(itemstack1, itemstack);
			} else if (slotIndex != 1 && slotIndex != 0) {
				if (!itemstack1.isEmpty() && itemstack1.is(ModTags.COMPOSTABLE)) {
					if (!moveItemStackTo(itemstack1, 0, 1, false))
						return ItemStack.EMPTY;
				} else if (itemstack1.is(ModTags.COMPOSTABLE)) {
					if (!moveItemStackTo(itemstack1, 1, 2, false))
						return ItemStack.EMPTY;
				} else if (slotIndex >= 3 && slotIndex < 30) {
					if (!moveItemStackTo(itemstack1, 30, 39, false))
						return ItemStack.EMPTY;
				} else if (slotIndex >= 30 && slotIndex < 39 && !moveItemStackTo(itemstack1, 3, 30, false))
					return ItemStack.EMPTY;
			} else if (!moveItemStackTo(itemstack1, 3, 39, false))
				return ItemStack.EMPTY;
            
            if (itemstack1.getCount() == 0)
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();
            if (itemstack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;

            slot.onTake(player, itemstack1);
		}
		return itemstack;
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
	
    public int getMouldProgress() {
        return data.get(DATA_MOULD_PROGRESS);
    }

    public int getCompostingProgress() {
    	return data.get(DATA_COMPOSTING_PROGRESS);
    }
}