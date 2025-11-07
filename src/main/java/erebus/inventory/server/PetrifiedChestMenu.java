package erebus.inventory.server;

import erebus.registries.client.ModMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;

public class PetrifiedChestMenu extends ChestMenu {

    public PetrifiedChestMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, new SimpleContainer(9 * 4), 4);
    }

    public PetrifiedChestMenu(int containerId, Inventory inv, int rows) {
        this(containerId, inv, new SimpleContainer(9 * rows), rows);
    }

    public PetrifiedChestMenu(int containerId, Inventory inv, Container container, int rows) {
        super(ModMenuTypes.PETRIFIED_CHEST.get(), containerId, inv, container, rows);
        checkContainerSize(container, rows * 9);
        container.startOpen(inv.player);
    }

    public static PetrifiedChestMenu fourRows(int containerId, Inventory playerInventory, Container container) {
        return new PetrifiedChestMenu(containerId, playerInventory, container, 4);
    }

    public static PetrifiedChestMenu eightRows(int containerId, Inventory playerInventory, Container container) {
        return new PetrifiedChestMenu(containerId, playerInventory, container, 8);
    }
}
