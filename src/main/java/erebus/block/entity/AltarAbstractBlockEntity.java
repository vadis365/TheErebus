package erebus.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

public abstract class AltarAbstractBlockEntity extends BlockEntity {

    public int animationTicks, prevAnimationTicks;
    public boolean active;
    protected int spawnTicks;

    public AltarAbstractBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    protected abstract void writeTileToNBT(ValueOutput output);

    protected abstract void readTileFromNBT(ValueInput input);

    public void setActive(boolean isActive) {
        if (active == isActive) return;
        active = isActive;
        syncState();
    }

    public void setSpawnTicks(int i) {
        int duration = Math.max(0, i);
        if (spawnTicks == duration) return;
        spawnTicks = duration;
        syncState();
    }

    protected void syncState() {
        setChanged();
        if (level != null && !level.isClientSide())
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    @Override
    public net.minecraft.nbt.@NonNull CompoundTag getUpdateTag(net.minecraft.core.HolderLookup.@NonNull Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        writeTileToNBT(output);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        readTileFromNBT(input);
        prevAnimationTicks = animationTicks;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(@NonNull Connection net, @NonNull ValueInput valueInput) {
        super.onDataPacket(net, valueInput);
        loadAdditional(valueInput);
    }

}
