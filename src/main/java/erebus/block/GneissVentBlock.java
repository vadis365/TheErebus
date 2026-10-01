package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public final class GneissVentBlock extends GneissBlock {
    public static final MapCodec<GneissVentBlock> CODEC = simpleCodec(GneissVentBlock::new);

    public GneissVentBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull MapCodec<GneissVentBlock> codec() {
        return CODEC;
    }

    @Override
    protected void randomTick(@NonNull BlockState state, @NonNull ServerLevel level, @NonNull BlockPos pos, @NonNull RandomSource random) {
        tick(state, level, pos, random);
    }

    @Override
    protected void tick(@NonNull BlockState state, ServerLevel level, BlockPos pos, @NonNull RandomSource random) {
        if (!level.isEmptyBlock(pos.above())) return;
        var gas = ModEntities.SWAMP_VENT_GAS.get().create(level, EntitySpawnReason.TRIGGERED);
        if (gas == null) return;
        gas.setVolcanic(true);
        gas.setPos(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
        if (level.addFreshEntity(gas)) level.playSound(null, pos, SoundEvents.GHAST_SHOOT, SoundSource.BLOCKS,
                0.5F, 0.1F + (random.nextFloat() - random.nextFloat()) * 0.8F);
    }
}
