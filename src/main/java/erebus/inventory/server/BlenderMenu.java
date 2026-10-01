package erebus.inventory.server;

import erebus.block.entity.BlenderBlockEntity;
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
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BlenderMenu extends AbstractContainerMenu {

    public final FluidStacksResourceHandler tanks;
    private final Container blender;
    private final ContainerData data;
    private final Player viewer;
    private List<FluidStack> lastFluids;

    public BlenderMenu(int id, Inventory inventory) {
        this(id, inventory, new SimpleContainer(5), new SimpleContainerData(1), new FluidStacksResourceHandler(4, BlenderBlockEntity.TANK_CAPACITY));
    }

    public BlenderMenu(int id, Inventory inventory, Container blender, ContainerData data, FluidStacksResourceHandler tanks) {
        super(ModMenuTypes.BLENDER.get(), id);
        checkContainerSize(blender, 5);
        checkContainerDataCount(data, 1);
        this.blender = blender;
        this.data = data;
        this.tanks = tanks;
        viewer = inventory.player;
        blender.startOpen(viewer);
        addDataSlots(data);

        addSlot(new Slot(blender, 0, 47, 9));
        addSlot(new Slot(blender, 1, 113, 9));
        addSlot(new Slot(blender, 2, 68, 30));
        addSlot(new Slot(blender, 3, 92, 30));
        addSlot(new Slot(blender, 4, 80, 63) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public boolean mayPlace(ItemStack stack) {
                return isContainer(stack);
            }
        });

        for (int c = 0; c < 3; ++c) {
            for (int d = 0; d < 9; ++d) {
                addSlot(new Slot(inventory, d + c * 9 + 9, 8 + d * 18, 84 + c * 18));
            }
        }
        for (int c = 0; c < 9; ++c) {
            addSlot(new Slot(inventory, c, 8 + c * 18, 142));
        }
    }

    public static boolean isContainer(ItemStack stack) {
        return stack.is(ModItems.SMOOTHIE_GLASS) || stack.is(ModItems.BAMBUCKET) || stack.is(Items.BUCKET) || stack.is(Items.GLASS_BOTTLE);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int slotIndex) {
        ItemStack resultStack = ItemStack.EMPTY;
        if (slotIndex < 0 || slotIndex >= slots.size() || !stillValid(player)) return ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);

        if (slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            resultStack = stackInSlot.copy();

            // Moving from player inventory to blender
            if (slotIndex > 4) {
                if (isContainer(stackInSlot)) {
                    // Smoothie glass goes to output slot (slot 4)
                    if (!moveItemStackTo(stackInSlot, 4, 5, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    // Other items go to input slots (slots 0-3)
                    if (!moveItemStackTo(stackInSlot, 0, 4, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }
            // Moving from blender to player inventory
            else if (!moveItemStackTo(stackInSlot, 5, slots.size(), false)) {
                return ItemStack.EMPTY;
            }

            // Update slot after transfer
            if (stackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            // Handle partial transfers
            if (stackInSlot.getCount() != resultStack.getCount()) {
                slot.onTake(player, stackInSlot);
            } else {
                return ItemStack.EMPTY;
            }
        }

        return resultStack;
    }

    public int getProgress() {
        return data.get(0);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        blender.stopOpen(player);
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();
        if (!(viewer instanceof ServerPlayer player)) return;
        lastFluids = MachineFluidsPacket.sendChanges(player, containerId, tanks, lastFluids);
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return blender.stillValid(player);
    }
}
