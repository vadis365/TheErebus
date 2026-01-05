package erebus.inventory.slot;

import erebus.entity.BlackAnt;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BlackAntSlot extends Slot {

	private final boolean isInvalid;

	public BlackAntSlot(Container container, int slot, int posX, int posY, boolean isInvalid) {
		super(container, slot, posX, posY);
		this.isInvalid = isInvalid;
	}

	public BlackAntSlot(Container container, int slot, int posX, int posY) {
		this(container, slot, posX, posY, false);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return !isInvalid && container.canPlaceItem(getSlotIndex(), stack);
	}
	
	@Override
	public int getMaxStackSize() {
       return this.index == BlackAnt.INVENTORY_SLOT ? super.getMaxStackSize() : 1;
    }
}
