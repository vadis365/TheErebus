package erebus.block.entity;

import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PetrifiedChestBlockEntity extends ChestBlockEntity {
    public PetrifiedChestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PETRIFIED_CHEST.get(), pos, state);
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.petrified_chest");
    }

    @Override
    public int getContainerSize() {
        return 36;
    }

    @Override
    @NotNull
    protected AbstractContainerMenu createMenu(int id, @NotNull Inventory player) {
        return ChestMenu.fourRows(id, player);
    }
}
