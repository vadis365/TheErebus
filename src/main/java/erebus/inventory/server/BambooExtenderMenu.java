package erebus.inventory.server;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.client.ModMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class BambooExtenderMenu extends AbstractContainerMenu {
    private final Container extender;

    public BambooExtenderMenu(int id, Inventory inventory) {
        this(id, inventory, new SimpleContainer(6));
    }

    public BambooExtenderMenu(int id, Inventory playerInventory, Container extender) {
        super(ModMenuTypes.BAMBOO_EXTENDER.get(), id);
        checkContainerSize(extender, 6);
        this.extender = extender;
        extender.startOpen(playerInventory.player);
        for (int i = 0; i < 6; i++) {
            addSlot(new Slot(extender, i, 35 + 18 * i, 18) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.is(ModBlocks.BAMBOO_BRIDGE.asItem()) || stack.is(ModBlocks.BAMBOO_NERD_POLE.asItem());
                }
            });
        }

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++)
                addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 54 + i * 18));
        for (int i = 0; i < 9; i++)
            addSlot(new Slot(playerInventory, i, 8 + i * 18, 112));
    }

    @Override
    public boolean stillValid(@Nonnull Player player) {
        return extender.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(@Nonnull Player player, int slotIndex) {
        ItemStack itemStack = ItemStack.EMPTY;
        if (slotIndex < 0 || slotIndex >= slots.size() || !stillValid(player)) return ItemStack.EMPTY;
        Slot slot = slots.get(slotIndex);

        if (slot != null && slot.hasItem()) {
            ItemStack slotItemStack = slot.getItem();
            itemStack = slotItemStack.copy();

            if (slotIndex < 6) {
                if (!moveItemStackTo(slotItemStack, 6, slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!moveItemStackTo(slotItemStack, 0, 6, false))
                return ItemStack.EMPTY;

            if (slotItemStack.isEmpty())
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();
            if (slotItemStack.getCount() == itemStack.getCount()) return ItemStack.EMPTY;
            slot.onTake(player, slotItemStack);
        }
        return itemStack;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        extender.stopOpen(player);
    }
}
