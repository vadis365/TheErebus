package erebus.inventory;

import erebus.entity.EntityBlackAnt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAntInventory extends Container {

	private final IInventory inventory;

	public ContainerAntInventory(InventoryPlayer playerInventory, IInventory entityInventory) {
		inventory = entityInventory;

		int i = -54;

		for (int k = 0; k < 3; k++)
			addSlotToContainer(new BetterSlot(entityInventory, k, 26 + k * 54, 18, k == 2));

		for (int j = 0; j < 3; j++)
			for (int k = 0; k < 9; k++)
				addSlotToContainer(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
		for (int j = 0; j < 9; j++)
			addSlotToContainer(new Slot(playerInventory, j, 8 + j * 18, 161 + i));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {
		ItemStack is = ItemStack.EMPTY;
		Slot slot = (Slot) inventorySlots.get(slotNumber);

		if (slot != null && slot.getHasStack()) {
			ItemStack is1 = slot.getStack();
			is = is1.copy();

			if (slotNumber == EntityBlackAnt.CROP_ID_SLOT) {
				slot.putStack(ItemStack.EMPTY);
				return null;
			} else if (slotNumber < 3) {
				if (!mergeItemStack(is1, 3, inventorySlots.size(), true))
					return ItemStack.EMPTY;
			} else if (inventory.isItemValidForSlot(0, is1)) {
				if (!mergeItemStack(is1, 0, 1, false))
					return ItemStack.EMPTY;
			} else
				return ItemStack.EMPTY;

			if (is1.getCount() == 0)
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();
		}

		return is;
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		inventory.closeInventory(player);
	}

	@Override
	  public ItemStack slotClick(int slotId, int dragType, ClickType clickType, EntityPlayer player) {
		if (slotId == EntityBlackAnt.CROP_ID_SLOT) {
			Slot slot = (Slot) inventorySlots.get(slotId);
			ItemStack slotStack = slot.getStack();
			ItemStack heldStack = player.inventory.getItemStack();

			if (slotStack.isEmpty() && !heldStack.isEmpty()) {
				ItemStack copy = heldStack.copy();
				copy.setCount(1);
				slot.putStack(copy);
			} else if (!slotStack.isEmpty())
				slot.putStack(ItemStack.EMPTY);

			return ItemStack.EMPTY;
		} else
			return super.slotClick(slotId, dragType, clickType, player);
	}
}