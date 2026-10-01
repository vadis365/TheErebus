package erebus.block.entity;

import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class PreservedBlockEntity extends BlockEntity {

    public byte rotation = 0;
    private CompoundTag entityData;
    private Entity trappedEntity;

    public PreservedBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PRESERVED_BLOCK.get(), pos, state);
    }

    public boolean setTrappedEntity(Entity entity) {
        TagValueOutput output = TagValueOutput.createWithContext(ProblemReporter.DISCARDING, entity.registryAccess());
        if (!entity.save(output)) return false;
        entityData = output.buildResult();
        trappedEntity = null;
        setChanged();
        if (level != null && !level.isClientSide()) level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
        return true;
    }

    public @Nullable Entity getTrappedEntity() {
        if (trappedEntity == null && entityData != null && level != null) {
            ValueInput input = TagValueInput.create(ProblemReporter.DISCARDING, level.registryAccess(), entityData);
            trappedEntity = EntityType.create(input, level, EntitySpawnReason.LOAD).orElse(null);
        }
        return trappedEntity;
    }

    public ItemStack preservedItem() {
        ItemStack stack = new ItemStack(getBlockState().getBlock());
        if (level != null) {
            stack.set(DataComponents.BLOCK_ENTITY_DATA, TypedEntityData.of(getType(), saveWithoutMetadata(level.registryAccess())));
        }
        return stack;
    }

    public boolean releaseEntity(ServerLevel level, BlockPos pos) {
        if (entityData == null) return true;
        Entity entity = EntityType.create(TagValueInput.create(ProblemReporter.DISCARDING,
                level.registryAccess(), entityData.copy()), level, EntitySpawnReason.LOAD).orElse(null);
        if (entity == null) return false;
        entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        if (!level.addFreshEntity(entity)) return false;
        entityData = null;
        trappedEntity = null;
        setChanged();
        return true;
    }

    public void setGeneratedContents(CompoundTag data, byte rotation) {
        entityData = data.copy();
        trappedEntity = null;
        this.rotation = rotation;
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        if (entityData != null) output.store("EntityNBT", CompoundTag.CODEC, entityData);
        output.putByte("rotation", rotation);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        entityData = input.read("EntityNBT", CompoundTag.CODEC).orElse(null);
        trappedEntity = null;
        rotation = input.getByteOr("rotation", (byte) 0);
    }

    @Override
    public @NonNull CompoundTag getUpdateTag(HolderLookup.@NonNull Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public @NonNull ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
