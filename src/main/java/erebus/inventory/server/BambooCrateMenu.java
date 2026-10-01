package erebus.inventory.server;

import erebus.block.entity.BambooCrateBlockEntity;
import erebus.registries.client.ModMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;

public class BambooCrateMenu extends AbstractContainerMenu {
    public final Container crate;
    public int numRows = 3;

    public BambooCrateMenu(int windowId, Inventory inventory) {
        this(ModMenuTypes.BAMBOO_CRATE.get(), windowId, inventory, new SimpleContainer(27));
    }

    public BambooCrateMenu(MenuType<?> type, int windowId, Inventory playerInventory, Container crate) {
        super(type, windowId);
        checkContainerSize(crate, 27);
        this.crate = crate;
        crate.startOpen(playerInventory.player);

        int i = (numRows - 4) * 18;
        int j;
        int k;

        for (j = 0; j < numRows; ++j)
            for (k = 0; k < 9; ++k)
                addSlot(new Slot(crate, k + j * 9, 8 + k * 18, 18 + j * 18));

        for (j = 0; j < 3; ++j)
            for (k = 0; k < 9; ++k)
                addSlot(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 104 + j * 18 + i));

        for (j = 0; j < 9; ++j)
            addSlot(new Slot(playerInventory, j, 8 + j * 18, 162 + i));
    }

    public static BambooCrateMenu animatedClient(int windowId, Inventory inventory) {
        return new BambooCrateMenu(ModMenuTypes.ANIMATED_BAMBOO_CRATE.get(), windowId, inventory, new SimpleContainer(27));
    }

    @Override
    public boolean stillValid(@Nonnull Player player) {
        if (crate instanceof BambooCrateBlockEntity block) {
            return !block.isRemoved() && player.level().getBlockEntity(block.getBlockPos()) == block
                    && player.distanceToSqr(block.getBlockPos().getCenter()) <= 64;
        }
        return crate.stillValid(player);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        crate.stopOpen(player);
    }

    @Override
    public @NonNull ItemStack quickMoveStack(@Nonnull Player player, int slotIndex) {
        ItemStack is = ItemStack.EMPTY;
        if (slotIndex < 0 || slotIndex >= slots.size()) return ItemStack.EMPTY;
        Slot slot = slots.get(slotIndex);

        if (slot.hasItem()) {
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

}
