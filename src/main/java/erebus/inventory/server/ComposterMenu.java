package erebus.inventory.server;

import erebus.block.entity.ComposterBlockEntity;
import erebus.registries.client.ModMenuTypes;
import erebus.registries.data.tags.ModItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class ComposterMenu extends AbstractContainerMenu {
    public static final int DATA_MOULD_PROGRESS = 0;
    public static final int DATA_COMPOSTING_PROGRESS = 1;
    public static final int DATA_MOULD_MAX_TIME = 2;
    private final Container container;
    private final ContainerData data;
    public int numRows = 3;

    public ComposterMenu(int id, Inventory inv) {
        this(id, inv, new SimpleContainer(3), new SimpleContainerData(3));
    }

    public ComposterMenu(int windowId, Inventory playerInventory, Container container, ContainerData data) {
        super(ModMenuTypes.COMPOSTER.get(), windowId);
        checkContainerSize(container, 3);
        checkContainerDataCount(data, 3);
        container.startOpen(playerInventory.player);
        this.container = container;
        this.data = data;

        addSlot(new Slot(container, 0, 56, 17) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItemTags.COMPOSTABLE);
            }
        });
        addSlot(new Slot(container, 1, 56, 53) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return ComposterBlockEntity.getMouldUseTime(stack) > 0;
            }
        });
        addSlot(new Slot(container, 2, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        addStandardInventorySlots(playerInventory, 8, 84);
        addDataSlots(data);
    }

    @Override
    public boolean stillValid(@Nonnull Player player) {
        return container.stillValid(player);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        container.stopOpen(player);
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull Player player, int index) {
        if (index < 0 || index >= slots.size() || !stillValid(player)) return ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (!slot.hasItem()) return ItemStack.EMPTY;
        ItemStack stack = slot.getItem(), original = stack.copy();
        if (index < 3) {
            if (!moveItemStackTo(stack, 3, 39, true)) return ItemStack.EMPTY;
        } else if (ComposterBlockEntity.getMouldUseTime(stack) > 0) {
            if (!moveItemStackTo(stack, 1, 2, false)) return ItemStack.EMPTY;
        } else if (stack.is(ModItemTags.COMPOSTABLE)) {
            if (!moveItemStackTo(stack, 0, 1, false)) return ItemStack.EMPTY;
        } else if (index < 30) {
            if (!moveItemStackTo(stack, 30, 39, false)) return ItemStack.EMPTY;
        } else if (!moveItemStackTo(stack, 3, 30, false)) return ItemStack.EMPTY;
        if (stack.getCount() == original.getCount()) return ItemStack.EMPTY;
        if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();
        slot.onTake(player, stack);
        return original;
    }

    public int getMouldProgress() {
        return Math.clamp((int) ((long) data.get(DATA_MOULD_PROGRESS) * 13
                / Math.max(1, data.get(DATA_MOULD_MAX_TIME))), 0, 13);
    }

    public int getCompostingProgress() {
        return Math.clamp(data.get(DATA_COMPOSTING_PROGRESS) * 32L / 200, 0, 32);
    }

    public boolean isComposting() {
        return data.get(DATA_MOULD_PROGRESS) > 0;
    }
}
