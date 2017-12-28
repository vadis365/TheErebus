package erebus.inventory;

import erebus.recipes.ComposterRegistry;
import erebus.tileentity.TileEntityComposter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendWindowProperty(this, 0, tileComposter.composterCookTime);
		listener.sendWindowProperty(this, 1, tileComposter.composterBurnTime);
		listener.sendWindowProperty(this, 2, tileComposter.currentItemBurnTime);
	}
	
	@Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener listener : listeners) {

            if (lastCookTime != tileComposter.composterCookTime)
            	listener.sendWindowProperty(this, 0, tileComposter.composterCookTime);

            if (lastBurnTime != tileComposter.composterBurnTime)
            	listener.sendWindowProperty(this, 1, tileComposter.composterBurnTime);

            if (lastItemBurnTime != tileComposter.currentItemBurnTime)
            	listener.sendWindowProperty(this, 2, tileComposter.currentItemBurnTime);
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
		return tileComposter.isUsableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotIndex == 2) {
                if (!mergeItemStack(itemstack1, 3, 39, true))
                    return ItemStack.EMPTY;
                slot.onSlotChange(itemstack1, itemstack);
			} else if (slotIndex != 1 && slotIndex != 0) {
				if (!ComposterRegistry.isCompostable(itemstack1).isEmpty()) {
					if (!mergeItemStack(itemstack1, 0, 1, false))
						return ItemStack.EMPTY;
				} else if (TileEntityComposter.isItemFuel(itemstack1)) {
					if (!mergeItemStack(itemstack1, 1, 2, false))
						return ItemStack.EMPTY;
				} else if (slotIndex >= 3 && slotIndex < 30) {
					if (!mergeItemStack(itemstack1, 30, 39, false))
						return ItemStack.EMPTY;
				} else if (slotIndex >= 30 && slotIndex < 39 && !mergeItemStack(itemstack1, 3, 30, false))
					return ItemStack.EMPTY;
			} else if (!mergeItemStack(itemstack1, 3, 39, false))
				return ItemStack.EMPTY;
            
            if (itemstack1.getCount() == 0)
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();
            if (itemstack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;

            slot.onTake(player, itemstack1);
		}
		return itemstack;
	}
}