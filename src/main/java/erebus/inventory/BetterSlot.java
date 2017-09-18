package erebus.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class BetterSlot extends Slot {

	private final boolean isInvalid;

	public BetterSlot(IInventory inventory, int slot, int posX, int posY, boolean isInvalid) {
		super(inventory, slot, posX, posY);
		this.isInvalid = isInvalid;
	}

	public BetterSlot(IInventory inventory, int slot, int posX, int posY) {
		this(inventory, slot, posX, posY, false);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return !isInvalid && inventory.isItemValidForSlot(getSlotIndex(), stack);
	}
}