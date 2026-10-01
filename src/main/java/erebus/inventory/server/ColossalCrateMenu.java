package erebus.inventory.server;

import erebus.block.bamboo.BambooCrateBlock;
import erebus.block.entity.BambooCrateBlockEntity;
import erebus.registries.client.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ColossalCrateMenu extends AbstractContainerMenu {
    private static final int[][] PLACES = {{1, 0, 0}, {1, 0, 1}, {0, 0, 1}, {1, 1, 0}, {1, 1, 1}, {0, 1, 1}, {0, 1, 0}, {0, 0, 0}};
    private final List<BambooCrateBlockEntity> crates = new ArrayList<>();
    private final Level level;
    private final BlockPos anchor;
    public int page = 1;

    public ColossalCrateMenu(int id, Inventory inventory) {
        this(id, inventory, BlockPos.ZERO);
    }

    public ColossalCrateMenu(int id, Inventory inventory, BlockPos anchor) {
        super(ModMenuTypes.COLOSSAL_CRATE.get(), id);
        this.level = inventory.player.level();
        this.anchor = anchor.immutable();
        if (!level.isClientSide()) {
            for (var offset : PLACES) {
                if (level.getBlockEntity(anchor.offset(offset[0], offset[1], offset[2])) instanceof BambooCrateBlockEntity crate) crates.add(crate);
            }
        }
        // Client slots hold only the visible page; incoming item packets must not depend on page-data packet order.
        Container visible = crates.size() == 8 ? new PageContainer() : new SimpleContainer(72);
        for (int slot = 0; slot < 72; slot++) addSlot(new Slot(visible, slot, 8 + slot % 12 * 18, 18 + slot / 12 * 18));
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 9; column++)
                addSlot(new Slot(inventory, column + row * 9 + 9, 35 + column * 18, 138 + row * 18));
        for (int slot = 0; slot < 9; slot++) addSlot(new Slot(inventory, slot, 35 + slot * 18, 196));
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return page;
            }

            @Override
            public void set(int value) {
                changePage(value);
            }
        });
    }

    public void changePage(int requested) {
        if (requested >= 1 && requested <= 3) page = requested;
    }

    @Override
    public boolean stillValid(Player player) {
        if (level.isClientSide()) return true;
        if (player.level() != level || player.distanceToSqr(anchor.getCenter()) > 64 || crates.size() != 8 || !BambooCrateBlock.isFormed(level, anchor)) return false;
        for (var crate : crates) if (crate.isRemoved() || level.getBlockEntity(crate.getBlockPos()) != crate) return false;
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        if (!stillValid(player) || index < 0 || index >= slots.size()) return ItemStack.EMPTY;
        var slot = slots.get(index);
        if (!slot.hasItem()) return ItemStack.EMPTY;
        var stack = slot.getItem();
        var original = stack.copy();
        if (index < 72 ? !moveItemStackTo(stack, 72, slots.size(), true) : !moveItemStackTo(stack, 0, 72, false)) return ItemStack.EMPTY;
        if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();
        return original;
    }

    private final class PageContainer implements Container {
        private int globalSlot(int slot) {
            return (page - 1) * 72 + slot;
        }

        private BambooCrateBlockEntity crate(int slot) {
            return crates.get(globalSlot(slot) / 27);
        }

        private int localSlot(int slot) {
            return globalSlot(slot) % 27;
        }

        @Override
        public int getContainerSize() {
            return 72;
        }

        @Override
        public ItemStack getItem(int slot) {
            return crate(slot).getItem(localSlot(slot));
        }

        @Override
        public ItemStack removeItem(int slot, int count) {
            return crate(slot).removeItem(localSlot(slot), count);
        }

        @Override
        public ItemStack removeItemNoUpdate(int slot) {
            return crate(slot).removeItemNoUpdate(localSlot(slot));
        }

        @Override
        public void setItem(int slot, ItemStack stack) {
            crate(slot).setItem(localSlot(slot), stack);
        }

        @Override
        public void setChanged() {
            crates.forEach(BambooCrateBlockEntity::setChanged);
        }

        @Override
        public boolean stillValid(Player player) {
            return ColossalCrateMenu.this.stillValid(player);
        }

        @Override
        public boolean isEmpty() {
            for (int slot = 0; slot < 72; slot++) if (!getItem(slot).isEmpty()) return false;
            return true;
        }

        @Override
        public void clearContent() {
            for (int slot = 0; slot < 72; slot++) setItem(slot, ItemStack.EMPTY);
        }
    }
}
