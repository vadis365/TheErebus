package erebus.inventory.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capabilities;

public class FluidContainerSlot extends Slot {

    public FluidContainerSlot(Container container, int slot, int x, int y) {
        super(container, slot, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        var fluidCap = stack.getCapability(Capabilities.Fluid.ITEM, null);
        return fluidCap != null;
    }
}
