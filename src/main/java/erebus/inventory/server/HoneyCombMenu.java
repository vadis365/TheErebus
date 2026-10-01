package erebus.inventory.server;

import erebus.registries.client.ModMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class HoneyCombMenu extends AbstractContainerMenu {
    private static final int numRows = 3;
    private final Container honey_comb;

    public HoneyCombMenu(int id, Inventory inventory) {
        this(id, inventory, new SimpleContainer(27));
    }

    public HoneyCombMenu(int id, Inventory playerInventory, Container inventory) {
        super(ModMenuTypes.HONEY_COMB.get(), id);
        checkContainerSize(inventory, 27);
        honey_comb = inventory;
        honey_comb.startOpen(playerInventory.player);
        int i = (numRows - 4) * 18;
        int j;
        int k;

        for (j = 0; j < numRows; ++j)
            for (k = 0; k < 9; ++k)
                addSlot(new Slot(honey_comb, k + j * 9, 8 + k * 18, 18 + j * 18));

        for (j = 0; j < 3; ++j)
            for (k = 0; k < 9; ++k)
                addSlot(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 102 + j * 18 + i));

        for (j = 0; j < 9; ++j)
            addSlot(new Slot(playerInventory, j, 8 + j * 18, 160 + i));
    }

    @Override
    public boolean stillValid(@Nonnull Player player) {
        return honey_comb.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(@Nonnull Player player, int slotIndex) {
        ItemStack is = ItemStack.EMPTY;
        if (slotIndex < 0 || slotIndex >= slots.size() || !stillValid(player)) return ItemStack.EMPTY;
        Slot slot = slots.get(slotIndex);

        if (slot != null && slot.hasItem()) {
            ItemStack is1 = slot.getItem();
            is = is1.copy();

            if (slotIndex < numRows * 9) {
                if (!moveItemStackTo(is1, numRows * 9, slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!moveItemStackTo(is1, 0, numRows * 9, false))
                return ItemStack.EMPTY;

            if (is1.getCount() == 0)
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();
        }

        return is;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        honey_comb.stopOpen(player);
    }

}
