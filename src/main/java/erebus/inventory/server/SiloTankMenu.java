package erebus.inventory.server;

import erebus.registries.client.ModMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;

public class SiloTankMenu extends AbstractContainerMenu {
    private static final int ROWS = 8;
    private static final int SIZE = ROWS * 13;
    private final Container siloTank;

    public SiloTankMenu(int id, Inventory inventory) {
        this(id, inventory, new SimpleContainer(SIZE));
    }

    public SiloTankMenu(int id, Inventory playerInventory, Container siloTank) {
        super(ModMenuTypes.SILO_TANK.get(), id);
        checkContainerSize(siloTank, SIZE);
        this.siloTank = siloTank;
        siloTank.startOpen(playerInventory.player);
        int i = (ROWS - 4) * 18;
        int j;
        int k;

        for (j = 0; j < ROWS; ++j)
            for (k = 0; k < 13; ++k)
                addSlot(new Slot(siloTank, k + j * 13, 12 + k * 18, 17 + j * 18));

        for (j = 0; j < 3; ++j)
            for (k = 0; k < 9; ++k)
                addSlot(new Slot(playerInventory, k + j * 9 + 9, 48 + k * 18, 102 + j * 18 + i));

        for (j = 0; j < 9; ++j)
            addSlot(new Slot(playerInventory, j, 48 + j * 18, 160 + i));
    }

    @Override
    public boolean stillValid(@Nonnull Player player) {
        return siloTank.stillValid(player);
    }

    @Override
    public @NonNull ItemStack quickMoveStack(@Nonnull Player player, int slotIndex) {
        ItemStack is = ItemStack.EMPTY;
        if (slotIndex < 0 || slotIndex >= slots.size() || !stillValid(player)) return ItemStack.EMPTY;
        Slot slot = slots.get(slotIndex);

        if (slot.hasItem()) {
            ItemStack is1 = slot.getItem();
            is = is1.copy();

            if (slotIndex < ROWS * 13) {
                if (!moveItemStackTo(is1, ROWS * 13, slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!moveItemStackTo(is1, 0, ROWS * 13, false))
                return ItemStack.EMPTY;

            if (is1.getCount() == 0)
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();
        }

        return is;
    }

    @Override
    public void removed(@NonNull Player player) {
        super.removed(player);
        siloTank.stopOpen(player);
    }

}
