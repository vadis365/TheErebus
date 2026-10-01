package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.TempleTeleporterBlockEntity;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.client.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jspecify.annotations.NonNull;

public class TempleTeleporterBlock extends BaseEntityBlock {
    public static final MapCodec<TempleTeleporterBlock> CODEC = simpleCodec(TempleTeleporterBlock::new);
    public static final IntegerProperty PHASE = IntegerProperty.create("phase", 0, 9);

    public TempleTeleporterBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(PHASE, 0));
    }

    @Override
    public void animateTick(BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull RandomSource random) {
        int phase = state.getValue(PHASE);
        if (phase == 0) return;
        var particle = phase == 5 ? ModParticles.REPELLENT.get() : ParticleTypes.PORTAL;
        for (var side : Direction.values()) {
            if (level.getBlockState(pos.relative(side)).isSolidRender()) continue;
            double x = pos.getX() + random.nextFloat(), y = pos.getY() + random.nextFloat(), z = pos.getZ() + random.nextFloat();
            switch (side) {
                case UP -> y = pos.getY() + 1.0625;
                case DOWN -> y = pos.getY() - 0.0625;
                case SOUTH -> z = pos.getZ() + 1.0625;
                case NORTH -> z = pos.getZ() - 0.0625;
                case EAST -> x = pos.getX() + 1.0625;
                case WEST -> x = pos.getX() - 0.0625;
            }
            level.addParticle(particle, x, y, z, 0, 0, 0);
        }
    }

    @Override
    protected @NonNull MapCodec<TempleTeleporterBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PHASE);
    }

    @Override
    protected @NonNull RenderShape getRenderShape(@NonNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new TempleTeleporterBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NonNull BlockState state, @NonNull BlockEntityType<T> type) {
        return level.isClientSide() ? null : createTickerHelper(type, ModBlockEntities.TEMPLE_TELEPORTER.get(), TempleTeleporterBlockEntity::serverTick);
    }
}
