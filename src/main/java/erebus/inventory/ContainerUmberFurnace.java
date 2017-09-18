package erebus.inventory;

import erebus.tileentity.TileEntityUmberFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class ContainerUmberFurnace extends Container {

	TileEntityUmberFurnace furnace;

	public ContainerUmberFurnace(InventoryPlayer inventory, TileEntityUmberFurnace tile) {
		furnace = tile;

		addSlotToContainer(new SlotFluidContainer(tile, 0, 31, 35));
		addSlotToContainer(new SlotSmelt(tile, 1, 56, 17));
		addSlotToContainer(new SlotFuel(tile, 2, 56, 53));
		addSlotToContainer(new SlotFurnace(inventory.player, tile, 3, 116, 35));

		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 9; ++j)
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
		for (int i = 0; i < 9; ++i)
			addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
	}

	@Override
	public void addCraftingToCrafters(ICrafting crafter) {
		super.addCraftingToCrafters(crafter);
		furnace.sendGUIData(this, crafter);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (Object crafter : crafters)
			furnace.sendGUIData(this, (ICrafting) crafter);
	}

	@Override
	public void updateProgressBar(int id, int value) {
		furnace.getGUIData(id, value);
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
