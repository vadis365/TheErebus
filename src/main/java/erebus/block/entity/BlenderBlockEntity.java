package erebus.block.entity;

import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlenderBlockEntity extends BlockEntityInventoryHelper {
    public BlenderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLENDER.get(), 3, pos, state);
    }

    @Override
    public int @NotNull [] getSlotsForFace(@NotNull Direction direction) {
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack itemStack, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack itemStack, Direction direction) {
        return false;
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return null;
    }
}
