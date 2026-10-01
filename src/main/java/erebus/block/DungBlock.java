package erebus.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SpellParticleOption;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TriState;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class DungBlock extends FallingBlock {
    public static final MapCodec<DungBlock> CODEC = simpleCodec(DungBlock::new);
    private static final SpellParticleOption ODOR = SpellParticleOption.create(ParticleTypes.EFFECT, 0.306F, 0.576F, 0.192F, 1);
    private static final Direction[] ODOR_FACES = {Direction.UP, Direction.DOWN, Direction.SOUTH, Direction.NORTH, Direction.EAST};

    public DungBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull MapCodec<DungBlock> codec() {
        return CODEC;
    }

    @Override
    public int getDustColor(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos) {
        return -8356741;
    }

    @Override
    public @NonNull TriState canSustainPlant(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull Direction face, @NonNull BlockState plant) {
        return TriState.TRUE;
    }

    @Override
    public boolean isFireSource(@NonNull BlockState state, @NonNull LevelReader level, @NonNull BlockPos pos, @NonNull Direction face) {
        return face == Direction.UP;
    }

    @Override
    public int getFlammability(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull Direction face) {
        return 0;
    }

    @Override
    public void animateTick(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, RandomSource random) {
        if (random.nextInt(3) != 0) return;
        for (var face : ODOR_FACES) {
            double x = pos.getX() + random.nextFloat();
            double y = pos.getY() + random.nextFloat();
            double z = pos.getZ() + random.nextFloat();
            if (level.getBlockState(pos.relative(face)).isSolidRender()) continue;
            switch (face) {
                case UP -> y = pos.getY() + 1.0625;
                case DOWN -> y = pos.getY() - 0.0625;
                case SOUTH -> z = pos.getZ() + 1.0625;
                case NORTH -> z = pos.getZ() - 0.0625;
                case EAST -> x = pos.getX() + 1.0625;
                case WEST -> x = pos.getX() - 0.0625;
            }
            level.addParticle(ODOR, x, y, z, 0, 0, 0);
        }
    }
}
