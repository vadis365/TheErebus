package erebus.block.entity;

import com.mojang.datafixers.util.Either;
import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ErebusSpawnerBlockEntity extends BlockEntity implements Spawner {

    private final BaseSpawner spawner = new BaseSpawner() {
        @Override
        public void broadcastEvent(Level level, @NonNull BlockPos pos, int id) {
            level.blockEvent(pos, getBlockState().getBlock(), id, 0);
        }

        @Override
        protected void setNextSpawnData(@Nullable Level level, @NonNull BlockPos pos, @NonNull SpawnData nextSpawnData) {
            super.setNextSpawnData(level, pos, nextSpawnData);
            if(level != null) {
                BlockState state = level.getBlockState(pos);
                level.sendBlockUpdated(pos, state, state, Block.UPDATE_NONE);
            }
        }

        @Override
        public @Nullable Either<BlockEntity, Entity> getOwner() {
            return Either.left(ErebusSpawnerBlockEntity.this);
        }
    };

    public ErebusSpawnerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SPAWNER.get(), pos, state);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        spawner.load(level, worldPosition, input);
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        spawner.save(output);
    }

    public static void clientTick(Level level, BlockPos pos, ErebusSpawnerBlockEntity entity) {
        entity.spawner.clientTick(level, pos);
    }

    public static void serverTick(Level level, BlockPos pos, ErebusSpawnerBlockEntity entity) {
        entity.spawner.serverTick((ServerLevel) level, pos);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.saveCustomOnly(registries);
        tag.remove("SpawnPotentials");
        return tag;
    }

    @Override
    public boolean triggerEvent(int b0, int b1) {
        return spawner.onEventTriggered(level, b0) || super.triggerEvent(b0, b1);
    }

    @Override
    public void setEntityId(@NonNull EntityType<?> type, @NonNull RandomSource random) {
        spawner.setEntityId(type, level, random, worldPosition);
        setChanged();
    }

    public BaseSpawner getSpawner() {
        return spawner;
    }
}
