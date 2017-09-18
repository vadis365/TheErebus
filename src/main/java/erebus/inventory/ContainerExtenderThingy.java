package erebus.inventory;

import erebus.tileentity.TileEntityExtenderThingy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fluids.FluidContainerRegistry;

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
		ItemStack itemStack = null;
		Slot slot = (Slot) inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotItemStack = slot.getStack();
			itemStack = slotItemStack.copy();

			if (slotIndex < 4) {
				if (!mergeItemStack(slotItemStack, 4, inventorySlots.size(), true))
					return null;
			} else if (FluidContainerRegistry.isContainer(slotItemStack)) {
				if (!mergeItemStack(slotItemStack, 0, 1, false))
					return null;
			} else if (FurnaceRecipes.smelting().getSmeltingResult(slotItemStack) != null) {
				if (!mergeItemStack(slotItemStack, 1, 2, false))
					return null;
			} else if (TileEntityFurnace.isItemFuel(slotItemStack))
				if (!mergeItemStack(slotItemStack, 2, 3, false))
					return null;

			if (slotItemStack.stackSize == 0)
				slot.putStack(null);
			else
				slot.onSlotChanged();

			if (slotItemStack.stackSize == itemStack.stackSize)
				return null;
			slot.onPickupFromSlot(player, slotItemStack);
		}

		return itemStack;
	}
}
