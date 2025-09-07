package erebus.inventory.server;

import erebus.registries.client.ModMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PetrifiedChestMenu extends ChestMenu {
    private final Container container;
    private final int rows;

    public PetrifiedChestMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, new SimpleContainer(9 * 4), 4);
    }

    public PetrifiedChestMenu(int containerId, Inventory inv, int rows) {
        this(containerId, inv, new SimpleContainer(9 * rows), rows);
    }

    public PetrifiedChestMenu(int containerId, Inventory inv, Container container, int rows) {
        super(ModMenuTypes.PETRIFIED_CHEST.get(), containerId, inv, container, rows);
        this.container = container;
        this.rows = rows;
        checkContainerSize(container, rows * 9);
        container.startOpen(inv.player);
    }

    public static PetrifiedChestMenu fourRows(int containerId, Inventory playerInventory) {
        return new PetrifiedChestMenu(containerId, playerInventory, 4);
    }

    public static PetrifiedChestMenu eightRows(int containerId, Inventory playerInventory, Container container) {
        return new PetrifiedChestMenu(containerId, playerInventory, container, 8);
    }

    public @NotNull Container getContainer() {
        return this.container;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return container.stillValid(player);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack empty = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            ItemStack copy = stack.copy();
            int chestEnd = rows * 9;
            int hotbarEnd = chestEnd + 27 + 9;

            if (index < chestEnd) {
                // from chest to player
                if (!this.moveItemStackTo(stack, chestEnd, hotbarEnd, true)) return ItemStack.EMPTY;
            } else {
                // from player to chest
                if (!this.moveItemStackTo(stack, 0, chestEnd, false)) return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) slot.set(ItemStack.EMPTY); else slot.setChanged();
            if (stack.getCount() == copy.getCount()) return ItemStack.EMPTY;
            slot.onTake(player, stack);
            return copy;
        }
        return empty;
    }
}
