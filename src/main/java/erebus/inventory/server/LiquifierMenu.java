package erebus.inventory.server;

import erebus.block.entity.LiquifierBlockEntity;
import erebus.network.client.MachineFluidsPacket;
import erebus.registries.client.ModMenuTypes;
import erebus.registries.item.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;

import java.util.List;

public class LiquifierMenu extends AbstractContainerMenu {
    public final FluidStacksResourceHandler tank;
    private final Container inventory;
    private final ContainerData data;
    private final Player viewer;
    private List<FluidStack> lastFluids;

    public LiquifierMenu(int id, Inventory playerInventory) {
        this(id, playerInventory, new SimpleContainer(1), new SimpleContainerData(1), new FluidStacksResourceHandler(1, LiquifierBlockEntity.TANK_CAPACITY));
    }

    public LiquifierMenu(int id, Inventory playerInventory, Container inventory, ContainerData data, FluidStacksResourceHandler tank) {
        super(ModMenuTypes.LIQUIFIER.get(), id);
        checkContainerSize(inventory, 1);
        checkContainerDataCount(data, 1);
        this.inventory = inventory;
        this.data = data;
        this.tank = tank;
        viewer = playerInventory.player;
        inventory.startOpen(viewer);
        addDataSlots(data);
        addSlot(new Slot(inventory, 0, 36, 36) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.HONEY_DRIP);
            }
        });
        addStandardInventorySlots(playerInventory, 8, 84);
    }

    public int getOperationProgressScaled(int scale) {
        return data.get(0) * scale / 180;
    }

    @Override
    public boolean stillValid(Player player) {
        return inventory.stillValid(player);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        inventory.stopOpen(player);
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();
        if (viewer instanceof ServerPlayer player) lastFluids = MachineFluidsPacket.sendChanges(player, containerId, tank, lastFluids);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        if (index < 0 || index >= slots.size() || !stillValid(player)) return ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (!slot.hasItem()) return ItemStack.EMPTY;
        ItemStack stack = slot.getItem(), original = stack.copy();
        if (index == 0) {
            if (!moveItemStackTo(stack, 1, slots.size(), true)) return ItemStack.EMPTY;
        } else if (stack.is(ModItems.HONEY_DRIP)) {
            if (!moveItemStackTo(stack, 0, 1, false)) return ItemStack.EMPTY;
        } else if (index < 28) {
            if (!moveItemStackTo(stack, 28, 37, false)) return ItemStack.EMPTY;
        } else if (!moveItemStackTo(stack, 1, 28, false)) return ItemStack.EMPTY;
        if (stack.getCount() == original.getCount()) return ItemStack.EMPTY;
        if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();
        slot.onTake(player, stack);
        return original;
    }
}
