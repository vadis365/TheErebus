package erebus.block;

import erebus.block.entity.ErebusSpawnerBlockEntity;
import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ErebusSpawnerBlock extends SpawnerBlock {

    public ErebusSpawnerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new ErebusSpawnerBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NonNull Level level, @NonNull BlockState blockState, @NonNull BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.SPAWNER.get(), level.isClientSide() ? (level1, pos, _, entity) -> ErebusSpawnerBlockEntity.clientTick(level1, pos, entity) : (level2, pos1, state1, entity1) -> ErebusSpawnerBlockEntity.serverTick(level2, pos1, entity1));
    }
}
