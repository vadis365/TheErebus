package erebus.block.entity;

import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PreservedBlockEntity extends BlockEntity {

    private CompoundTag entityTag;
    private Entity trappedEntity;
    public byte rotation = 0;

    public PreservedBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PRESERVED_BLOCK.get(), pos, state);
    }

    public void setTrappedEntity(CompoundTag tag) {
        this.entityTag = tag;
        if (getLevel() != null) {
            Optional<Entity> optional = EntityType.create(entityTag, getLevel());
            optional.ifPresent(entity -> trappedEntity = entity);
        }
    }

    public Entity getTrappedEntity() {
        return trappedEntity;
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        if(entityTag != null) {
            tag.put("TrappedEntity", entityTag);
        }
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        entityTag = tag.getCompound("TrappedEntity");
        if (getLevel() != null) {
            Optional<Entity> optional = EntityType.create(entityTag, getLevel());
            optional.ifPresent(entity -> trappedEntity = entity);
        }
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
        return super.getUpdateTag(registries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return super.getUpdatePacket();
    }

    @Override
    public void onDataPacket(@NotNull Connection net, @NotNull ClientboundBlockEntityDataPacket pkt, HolderLookup.@NotNull Provider lookupProvider) {
        super.onDataPacket(net, pkt, lookupProvider);
    }
}
