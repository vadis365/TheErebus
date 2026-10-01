package erebus.block.plants;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class FireBloomBlock extends SmallGroundPlantBlock {
    public static final MapCodec<FireBloomBlock> CODEC = simpleCodec(FireBloomBlock::new);

    public FireBloomBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull MapCodec<? extends SmallGroundPlantBlock> codec() {
        return CODEC;
    }

    @Override
    public void animateTick(@NonNull BlockState state, Level level, BlockPos pos, @NonNull RandomSource random) {
        level.addParticle(ParticleTypes.FLAME, pos.getX() + 0.5D, pos.getY() + 1D, pos.getZ() + 0.5D, 0, 0, 0);
    }
}
