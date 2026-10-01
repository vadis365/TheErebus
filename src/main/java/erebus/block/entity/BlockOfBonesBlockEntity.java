package erebus.block.entity;

import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Collection;

public class BlockOfBonesBlockEntity extends BlockEntityInventoryHelper {

    public Component displayName;

    public BlockOfBonesBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.BLOCK_OF_BONES.get(), 86, pos, blockState);
    }

    // Transfer only accepted stacks out of the event collection. Overflow remains
    // available for the normal death-drop path instead of being lost or throwing.
    public void setDrops(Collection<ItemEntity> drops) {
        var iterator = drops.iterator();
        for (int slot = 0; slot < getContainerSize() && iterator.hasNext(); slot++) {
            if (!getItem(slot).isEmpty()) continue;
            var entity = iterator.next();
            if (entity.getItem().getCount() > getMaxStackSize()) continue;
            setItem(slot, entity.getItem().copy());
            iterator.remove();
        }
    }

    public void setDisplayName(Component name) {
        displayName = name;
        setChanged();
        if (level != null) level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 2);
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        if (displayName != null) output.store("OwnerName", ComponentSerialization.CODEC, displayName);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        displayName = input.read("OwnerName", ComponentSerialization.CODEC).orElse(null);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        var tag = saveCustomOnly(registries);
        tag.remove("Items");
        return tag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
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
        return ContainerHelper.takeItem(getItems(), i);
    }
}
