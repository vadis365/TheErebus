package erebus.block.entity;

import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class PreservedBlockEntity extends BlockEntity {

    private Entity trappedEntity;
    public byte rotation = 0;

    public PreservedBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PRESERVED_BLOCK.get(), pos, state);
    }

    public void setTrappedEntity(Entity entity) {
        trappedEntity = entity;
    }

    public Entity getTrappedEntity() {
        return trappedEntity;
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        if(trappedEntity != null)
            trappedEntity.save(output);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        if (trappedEntity != null) {
            trappedEntity.load(input);
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
    public void onDataPacket(@NonNull Connection net, @NonNull ValueInput valueInput) {
        super.onDataPacket(net, valueInput);
    }
}
