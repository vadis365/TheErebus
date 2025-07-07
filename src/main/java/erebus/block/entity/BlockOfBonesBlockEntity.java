package erebus.block.entity;

import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class BlockOfBonesBlockEntity extends BlockEntityInventoryHelper {

    private final NonNullList<ItemStack> inventory;

    public BlockOfBonesBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.BLOCK_OF_BONES.get(), 86, pos, blockState);
        inventory = NonNullList.withSize(86, ItemStack.EMPTY);
    }

    public void setDrops(Collection<ItemEntity> drops) {
        int c = 0;
        for (ItemEntity entity : drops) {
            inventory.set(c, entity.getItem());
            c++;
        }
    }

    @Override
    public @NotNull ItemStack getItem(int slot) {
        return inventory.get(slot);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
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
