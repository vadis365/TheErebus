package erebus.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class SlotFuel extends Slot {

	public SlotFuel(IInventory tile, int slotIndex, int x, int y) {
		super(tile, slotIndex, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack is) {
		return TileEntityFurnace.isItemFuel(is);
	}
}
