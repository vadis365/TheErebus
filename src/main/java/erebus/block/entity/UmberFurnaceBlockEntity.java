package erebus.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class UmberFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    protected UmberFurnaceBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState, RecipeType<? extends AbstractCookingRecipe> recipeType) {
        super(type, pos, blockState, recipeType);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.umberfurnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new FurnaceMenu(id, player, this, this.dataAccess);
    }
}
