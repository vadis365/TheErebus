package erebus.inventory.server;

import erebus.entity.TitanBeetle;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class TitanBeetleMenu extends ChestMenu {
    private final TitanBeetle beetle;
    private final boolean ender;

    public TitanBeetleMenu(int id, Inventory inventory, TitanBeetle beetle, boolean ender) {
        super(MenuType.GENERIC_9x3, id, inventory, ender ? inventory.player.getEnderChestInventory() : beetle.getInventory(), 3);
        this.beetle = beetle;
        this.ender = ender;
        beetle.startViewing(inventory.player);
    }

    public boolean isFor(TitanBeetle entity) {
        return beetle == entity;
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return beetle.canUseCargo(player, ender) && super.stillValid(player);
    }

    @Override
    public @NonNull ItemStack quickMoveStack(@NonNull Player player, int slot) {
        return stillValid(player) && slot >= 0 && slot < slots.size() ? super.quickMoveStack(player, slot) : ItemStack.EMPTY;
    }

    @Override
    public void removed(@NonNull Player player) {
        super.removed(player);
        beetle.stopViewing(player);
    }
}
