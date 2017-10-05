package erebus.inventory;

import erebus.tileentity.TileEntityExtenderThingy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerExtenderThingy extends Container {

	public ContainerExtenderThingy(InventoryPlayer inventory, TileEntityExtenderThingy tile) {

		for (int i = 0; i < tile.getSizeInventory(); i++)
			addSlotToContainer(new BetterSlot(tile, i, 35 + 18 * i, 18));

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 9; j++)
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 54 + i * 18));
		for (int i = 0; i < 9; i++)
			addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 112));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = (Slot) inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotItemStack = slot.getStack();
			itemStack = slotItemStack.copy();
			
			if (slotIndex < 6) {
				if (!mergeItemStack(itemStack, 6, inventorySlots.size(), true))
					return ItemStack.EMPTY;
			} else if (!mergeItemStack(itemStack, 0, 6, false))
				return ItemStack.EMPTY;

			if (itemStack.getCount() == 0)
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();
		}
		return itemStack;
	}
}
