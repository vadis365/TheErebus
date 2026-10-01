package erebus.block;

import erebus.block.entity.ErebusSpawnerBlockEntity;
import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.Supplier;

public class ErebusSpawnerBlock extends SpawnerBlock {

    private final Supplier<? extends EntityType<?>> entityType;

    public ErebusSpawnerBlock(Properties properties, Supplier<? extends EntityType<?>> entityType) {
        super(properties);
        this.entityType = entityType;
    }

    @Override
    public @NonNull BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        var entity = new ErebusSpawnerBlockEntity(pos, state);
        entity.setEntityId(entityType.get(), RandomSource.create());
        return entity;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NonNull Level level, @NonNull BlockState blockState, @NonNull BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.SPAWNER.get(), level.isClientSide() ? (level1, pos, _, entity) -> ErebusSpawnerBlockEntity.clientTick(level1, pos, entity) : (level2, pos1, state1, entity1) -> ErebusSpawnerBlockEntity.serverTick(level2, pos1, entity1));
    }
}
