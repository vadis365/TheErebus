package erebus.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.recipes.ComposterRegistry;
import erebus.tileentity.TileEntityComposter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerComposter extends Container {

	private final TileEntityComposter tileComposter;
	private int lastCookTime;
	private int lastBurnTime;
	private int lastItemBurnTime;

	public ContainerComposter(InventoryPlayer player, TileEntityComposter composter) {
		tileComposter = composter;
		addSlotToContainer(new BetterSlot(composter, 0, 56, 17));
		addSlotToContainer(new BetterSlot(composter, 1, 56, 53));
		addSlotToContainer(new BetterSlot(composter, 2, 116, 35, true));

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 9; j++)
				addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

		for (int i = 0; i < 9; i++)
			addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
	}

	@Override
	public void addCraftingToCrafters(ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, tileComposter.composterCookTime);
		crafting.sendProgressBarUpdate(this, 1, tileComposter.composterBurnTime);
		crafting.sendProgressBarUpdate(this, 2, tileComposter.currentItemBurnTime);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (Object crafter : crafters) {
			ICrafting icrafting = (ICrafting) crafter;

			if (lastCookTime != tileComposter.composterCookTime)
				icrafting.sendProgressBarUpdate(this, 0, tileComposter.composterCookTime);

			if (lastBurnTime != tileComposter.composterBurnTime)
				icrafting.sendProgressBarUpdate(this, 1, tileComposter.composterBurnTime);

			if (lastItemBurnTime != tileComposter.currentItemBurnTime)
				icrafting.sendProgressBarUpdate(this, 2, tileComposter.currentItemBurnTime);
		}

		lastCookTime = tileComposter.composterCookTime;
		lastBurnTime = tileComposter.composterBurnTime;
		lastItemBurnTime = tileComposter.currentItemBurnTime;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int value) {
		switch (id) {
			case 0:
				tileComposter.composterCookTime = value;
				break;
			case 1:
				tileComposter.composterBurnTime = value;
				break;
			case 2:
				tileComposter.currentItemBurnTime = value;
				break;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileComposter.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (slotIndex == 2) {
				if (!mergeItemStack(itemstack1, 3, 39, true))
					return null;

				slot.onSlotChange(itemstack1, itemstack);
			} else if (slotIndex != 1 && slotIndex != 0) {
				if (ComposterRegistry.isCompostable(itemstack1) != null) {
					if (!mergeItemStack(itemstack1, 0, 1, false))
						return null;
				} else if (TileEntityComposter.isItemFuel(itemstack1)) {
					if (!mergeItemStack(itemstack1, 1, 2, false))
						return null;
				} else if (slotIndex >= 3 && slotIndex < 30) {
					if (!mergeItemStack(itemstack1, 30, 39, false))
						return null;
				} else if (slotIndex >= 30 && slotIndex < 39 && !mergeItemStack(itemstack1, 3, 30, false))
					return null;
			} else if (!mergeItemStack(itemstack1, 3, 39, false))
				return null;

			if (itemstack1.stackSize == 0)
				slot.putStack(null);
			else
				slot.onSlotChanged();

			if (itemstack1.stackSize == itemstack.stackSize)
				return null;

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}
}