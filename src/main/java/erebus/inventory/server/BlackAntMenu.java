package erebus.inventory.server;

import erebus.entity.BlackAnt;
import erebus.inventory.container.BlackAntSimpleContainer;
import erebus.inventory.slot.BlackAntSlot;
import erebus.registries.client.ModMenuTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.Nullable;

public class BlackAntMenu extends AbstractContainerMenu {
    private final BlackAntSimpleContainer container;
    private final @Nullable BlackAnt entity;

    public BlackAntMenu(int id, Inventory playerInventory) {
        this(id, playerInventory, new BlackAntSimpleContainer(3), null);
    }

    public BlackAntMenu(int id, Inventory playerInventory, BlackAnt entity) {
        this(id, playerInventory, entity.getInventory(), entity);
    }

    private BlackAntMenu(int id, Inventory playerInventory, BlackAntSimpleContainer inventory, @Nullable BlackAnt entity) {
        super(ModMenuTypes.BLACK_ANT.get(), id);
        checkContainerSize(inventory, 3);
        this.container = inventory;
        this.entity = entity;
        container.startOpen(playerInventory.player);
        addSlot(new BlackAntSlot(inventory, 0, 26, 18));
        addSlot(new BlackAntSlot(inventory, 1, 80, 18, true) {
            @Override
            public boolean mayPickup(Player player) {
                return false;
            }
        });
        addSlot(new BlackAntSlot(inventory, 2, 134, 18));
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 9; column++)
                addSlot(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 49 + row * 18));
        for (int column = 0; column < 9; column++) addSlot(new Slot(playerInventory, column, 8 + column * 18, 107));
    }

    public boolean isFor(BlackAnt ant) {
        return entity == ant;
    }

    @Override
    public boolean stillValid(Player player) {
        return entity == null || entity.isAlive() && entity.isTamedAnt() && entity.getInventory() == container
                && player.level() == entity.level() && entity.distanceToSqr(player) <= 64;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        if (index < 0 || index >= slots.size() || !stillValid(player)) return ItemStack.EMPTY;
        if (index == BlackAnt.CROP_ID_SLOT) {
            container.setItem(index, ItemStack.EMPTY);
            return ItemStack.EMPTY;
        }
        var slot = slots.get(index);
        if (!slot.hasItem() || !slot.mayPickup(player)) return ItemStack.EMPTY;
        var stack = slot.getItem();
        var original = stack.copy();
        if (index < 3) {
            if (!moveItemStackTo(stack, 3, slots.size(), true)) return ItemStack.EMPTY;
        } else if (container.canPlaceItem(0, stack)) {
            if (!moveItemStackTo(stack, 0, 1, false)) return ItemStack.EMPTY;
        } else return ItemStack.EMPTY;
        if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();
        slot.onTake(player, stack);
        return original;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        container.stopOpen(player);
    }

    @Override
    public void clicked(int index, int button, ContainerInput input, Player player) {
        if (!stillValid(player)) return;
        if (index == BlackAnt.CROP_ID_SLOT) {
            if (input == ContainerInput.QUICK_MOVE) {
                container.setItem(index, ItemStack.EMPTY);
                return;
            }
            if (input != ContainerInput.PICKUP) return;
            var slot = slots.get(index);
            var carried = getCarried();
            if (!slot.getItem().isEmpty()) slot.set(ItemStack.EMPTY);
            else if (!carried.isEmpty() && slots.get(BlackAnt.TOOL_SLOT).hasItem() && !slots.get(BlackAnt.TOOL_SLOT).getItem().is(Items.SHEARS))
                slot.set(carried.copyWithCount(1));
            broadcastChanges();
        } else super.clicked(index, button, input, player);
    }
}
