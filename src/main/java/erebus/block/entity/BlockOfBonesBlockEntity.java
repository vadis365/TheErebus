package erebus.block.entity;

import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Collection;

public class BlockOfBonesBlockEntity extends BlockEntityInventoryHelper {

    public Component displayName;
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
    public @NonNull ItemStack getItem(int slot) {
        return inventory.get(slot);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public int @NonNull [] getSlotsForFace(@NonNull Direction direction) {
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, @NonNull ItemStack itemStack, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int i, @NonNull ItemStack itemStack, @NonNull Direction direction) {
        return false;
    }

    @Override
    public @NonNull ItemStack removeItemNoUpdate(int i) {
        return null;
    }
}
