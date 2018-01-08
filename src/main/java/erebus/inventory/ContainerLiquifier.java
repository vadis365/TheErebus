package erebus.inventory;

import erebus.tileentity.TileEntityLiquifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerLiquifier extends Container {
	private final TileEntityLiquifier liquifier;
	private int lastProgress;
	private int progress;

	public int numRows = 3;

	public ContainerLiquifier(InventoryPlayer playerInventory, TileEntityLiquifier tile) {
		liquifier = tile;
		int i = (numRows - 4) * 18;
		int j;
		int k;

		addSlotToContainer(new Slot(tile, 0, 36, 36));

		for (j = 0; j < 3; ++j)
			for (k = 0; k < 9; ++k)
				addSlotToContainer(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 102 + j * 18 + i));

		for (j = 0; j < 9; ++j)
			addSlotToContainer(new Slot(playerInventory, j, 8 + j * 18, 160 + i));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack is = ItemStack.EMPTY;
		Slot slot = (Slot) inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack()) {
			ItemStack is1 = slot.getStack();
			is = is1.copy();

			if (slotIndex < numRows * 9) {
				if (!mergeItemStack(is1, numRows * 9, inventorySlots.size(), true))
					return ItemStack.EMPTY;
			} else if (!mergeItemStack(is1, 0, numRows * 9, false))
				return ItemStack.EMPTY;

			if (is1.getCount() == 0)
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();
		}

		return is;
	}

	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendWindowProperty(this, 0, liquifier.operatingTime);
	}

	@Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (IContainerListener listener : listeners) 
            if (lastProgress != liquifier.operatingTime)
            	listener.sendWindowProperty(this, 0, liquifier.operatingTime);
        lastProgress = liquifier.operatingTime;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int value) {
		switch (id) {
			case 0:
				liquifier.operatingTime = value;
				break;
		}
	}
}