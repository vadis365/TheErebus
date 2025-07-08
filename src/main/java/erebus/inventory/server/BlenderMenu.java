package erebus.inventory.server;

import erebus.block.entity.BlenderBlockEntity;
import erebus.registries.client.ModMenuTypes;
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
        if (!(tile instanceof BlenderBlockEntity))
            return;
        blender = (BlenderBlockEntity) tile;

        addSlot(new Slot(blender, 0, 47, 9));
        addSlot(new Slot(blender, 1, 113, 9));
        addSlot(new Slot(blender, 2, 68, 30));
        addSlot(new Slot(blender, 3, 92, 30));
        addSlot(new Slot(blender, 4, 80, 63));

        for (int c = 0; c < 3; ++c)
            for (int d = 0; d < 9; ++d)
                addSlot(new Slot(inventory, d + c * 9 + 9, 8 + d * 18, 84 + c * 18));
        for (int c = 0; c < 9; ++c)
            addSlot(new Slot(inventory, c, 8 + c * 18, 142));
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return blender.stillValid(player);
    }
}
