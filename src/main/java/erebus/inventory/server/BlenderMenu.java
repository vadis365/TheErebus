package erebus.inventory.server;

import erebus.block.entity.BlenderBlockEntity;
import erebus.registries.client.ModMenuTypes;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class BlenderMenu extends AbstractContainerMenu {

    public BlenderBlockEntity blender;

    public BlenderMenu(int containerId, Inventory inventory, FriendlyByteBuf extra) {
        super(ModMenuTypes.BLENDER.get(), containerId);
        BlockPos tilePos = extra.readBlockPos();
        BlockEntity tile = inventory.player.getCommandSenderWorld().getBlockEntity(tilePos);
        if (!(tile instanceof BlenderBlockEntity)) {
            return;
        }
        blender = (BlenderBlockEntity) tile;

        addSlot(new Slot(blender, 0, 47, 9));
        addSlot(new Slot(blender, 1, 113, 9));
        addSlot(new Slot(blender, 2, 68, 30));
        addSlot(new Slot(blender, 3, 92, 30));
        addSlot(new Slot(blender, 4, 80, 63));

        for (int c = 0; c < 3; ++c) {
            for (int d = 0; d < 9; ++d) {
                addSlot(new Slot(inventory, d + c * 9 + 9, 8 + d * 18, 84 + c * 18));
            }
        }
        for (int c = 0; c < 9; ++c) {
            addSlot(new Slot(inventory, c, 8 + c * 18, 142));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int slotIndex) {
        ItemStack resultStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);

        if (slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            resultStack = stackInSlot.copy();

            // Moving from player inventory to blender
            if (slotIndex > 4) {
                if (stackInSlot.is(ModItems.SMOOTHIE_GLASS)) {
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



    @Override
    public boolean stillValid(@NotNull Player player) {
        return blender.stillValid(player);
    }
}
