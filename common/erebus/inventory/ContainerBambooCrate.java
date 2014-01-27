package erebus.inventory;

import invtweaks.api.container.ChestContainer;
import invtweaks.api.container.InventoryContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import erebus.tileentity.TileEntityBambooCrate;

@ChestContainer(rowSize = 9, isLargeChest = false)
@InventoryContainer(showOptions = false)
public class ContainerBambooCrate extends Container {

	private final TileEntityBambooCrate bambooCrateInventory;
	public int numRows = 3;

	public ContainerBambooCrate(InventoryPlayer playerInventory, TileEntityBambooCrate tile) {
		bambooCrateInventory = tile;
		tile.openChest();
		int i = (numRows - 4) * 18;
		int j;
		int k;

		for (j = 0; j < numRows; ++j)
			for (k = 0; k < 9; ++k)
				addSlotToContainer(new Slot(tile, k + j * 9, 8 + k * 18, 18 + j * 18));

		for (j = 0; j < 3; ++j)
			for (k = 0; k < 9; ++k)
				addSlotToContainer(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 104 + j * 18 + i));

		for (j = 0; j < 9; ++j)
			addSlotToContainer(new Slot(playerInventory, j, 8 + j * 18, 162 + i));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return bambooCrateInventory.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int par2) {
		ItemStack is = null;
		Slot slot = (Slot) inventorySlots.get(par2);

		if (slot != null && slot.getHasStack()) {
			ItemStack is1 = slot.getStack();
			is = is1.copy();

			if (par2 < numRows * 9) {
				if (!mergeItemStack(is1, numRows * 9, inventorySlots.size(), true))
					return null;
			} else if (!mergeItemStack(is1, 0, numRows * 9, false))
				return null;

			if (is1.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();
		}

		return is;
	}
}