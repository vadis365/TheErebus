package erebus.inventory.slot;

import erebus.inventory.server.UmberFurnaceMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

public class UmberFurnaceFuelSlot extends Slot {
    private final UmberFurnaceMenu menu;

    public UmberFurnaceFuelSlot(UmberFurnaceMenu menu, Container container, int slot, int x, int y) {
        super(container, slot, x, y);
        this.menu = menu;
    }

    @Override
    public boolean mayPlace(@NonNull ItemStack stack) {
        return menu.isFuel(stack) || isBucket(stack);
    }

    @Override
    public int getMaxStackSize(@NonNull ItemStack stack) {
        return isBucket(stack) ? 1 : super.getMaxStackSize(stack);
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.is(Items.BUCKET);
    }
}
