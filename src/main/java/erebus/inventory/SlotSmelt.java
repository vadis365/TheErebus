package erebus.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class SlotSmelt extends Slot {

	public SlotSmelt(IInventory tile, int slotIndex, int x, int y) {
		super(tile, slotIndex, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack is) {
		return FurnaceRecipes.smelting().getSmeltingResult(is) != null;
	}
}
