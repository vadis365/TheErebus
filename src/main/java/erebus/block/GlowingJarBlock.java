package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.GlowingJarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GlowingJarBlock extends BaseEntityBlock {

    public static final MapCodec<GlowingJarBlock> CODEC = simpleCodec(GlowingJarBlock::new);

    public GlowingJarBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new GlowingJarBlockEntity(pos, state);
    }
}
