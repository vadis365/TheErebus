package erebus.block.entity;

import erebus.inventory.UmberFurnaceMenu;
import erebus.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class UmberFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    public UmberFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.UMBERFURNACE.get(), pos, state, RecipeType.SMELTING);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.umberfurnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new UmberFurnaceMenu(id, player, this, this.dataAccess);
    }
}
