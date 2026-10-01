package erebus.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public final class ExplodingStigmaBlock extends Block {
    public static final MapCodec<ExplodingStigmaBlock> CODEC = simpleCodec(ExplodingStigmaBlock::new);

    public ExplodingStigmaBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull MapCodec<ExplodingStigmaBlock> codec() {
        return CODEC;
    }

    @Override
    public @NonNull BlockState playerWillDestroy(Level level, @NonNull BlockPos pos, @NonNull BlockState state, @NonNull Player player) {
        if (!level.isClientSide()) level.explode(player, pos.getX(), pos.getY(), pos.getZ(), 3, Level.ExplosionInteraction.NONE);
        return super.playerWillDestroy(level, pos, state, player);
    }
}
