package erebus.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class SlotFluidContainer extends Slot {

	public SlotFluidContainer(IInventory tile, int slotIndex, int x, int y) {
		super(tile, slotIndex, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack is) {
		return FluidContainerRegistry.isContainer(is);
	}
}
