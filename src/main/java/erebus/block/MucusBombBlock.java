package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.entity.MucusBombPrimed;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public final class MucusBombBlock extends TntBlock {
    public static final MapCodec<TntBlock> CODEC = simpleCodec(MucusBombBlock::new);

    public MucusBombBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull MapCodec<TntBlock> codec() {
        return CODEC;
    }

    private MucusBombPrimed create(Level level, BlockPos pos) {
        var bomb = new MucusBombPrimed(ModEntities.MUCUS_BOMB_PRIMED.get(), level);
        bomb.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        double angle = level.getRandom().nextDouble() * Math.PI * 2;
        bomb.setDeltaMovement(-Math.sin(angle) * 0.02, 0.2, -Math.cos(angle) * 0.02);
        return bomb;
    }

    @Override
    public boolean onCaughtFire(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        if (level.isClientSide()) return false;
        var bomb = create(level, pos);
        if (!level.addFreshEntity(bomb)) return false;
        level.playSound(null, pos, SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1, 1);
        level.gameEvent(igniter, GameEvent.PRIME_FUSE, pos);
        return true;
    }

    @Override
    public void wasExploded(@NonNull ServerLevel level, @NonNull BlockPos pos, @NonNull Explosion explosion) {
        var bomb = create(level, pos);
        bomb.setFuse(level.getRandom().nextInt(20) + 10);
        level.addFreshEntity(bomb);
    }
}
