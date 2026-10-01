package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class ForceFieldBlock extends Block {
    public static final MapCodec<ForceFieldBlock> CODEC = simpleCodec(ForceFieldBlock::new);
    private static final VoxelShape COLLISION = Block.box(2, 0, 2, 14, 16, 14);

    public ForceFieldBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull MapCodec<? extends ForceFieldBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NonNull VoxelShape getCollisionShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return COLLISION;
    }

    @Override
    protected boolean skipRendering(@NonNull BlockState state, BlockState adjacent, @NonNull Direction side) {
        return adjacent.is(ModBlocks.FORCE_FIELD) || adjacent.is(ModBlocks.FORCE_LOCK) || super.skipRendering(state, adjacent, side);
    }

    @Override
    public void animateTick(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, RandomSource random) {
        if (random.nextInt(5) != 0) return;
        for (var side : new Direction[]{Direction.UP, Direction.DOWN, Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST}) {
            double x = pos.getX() + random.nextFloat();
            double y = pos.getY() + random.nextFloat();
            double z = pos.getZ() + random.nextFloat();
            if (level.getBlockState(pos.relative(side)).isSolidRender()) continue;
            switch (side) {
                case UP -> y = pos.getY() + 1.0625D;
                case DOWN -> y = pos.getY() - 0.0625D;
                case SOUTH -> z = pos.getZ() + 1.0625D;
                case NORTH -> z = pos.getZ() - 0.0625D;
                case EAST -> x = pos.getX() + 1.0625D;
                case WEST -> x = pos.getX() - 0.0625D;
            }
            level.addParticle(ParticleTypes.FIREWORK, x, y, z, 0, 0, 0);
        }
    }

    @Override
    protected void entityInside(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Entity entity, @NonNull InsideBlockEffectApplier effects, boolean precise) {
        if (!(level instanceof ServerLevel server) || !(entity instanceof LivingEntity living)) return;
        living.hurtServer(server, server.damageSources().cactus(), 1.0F);
        float angle = entity.getYRot() * 3.141593F / 180.0F;
        entity.push(Mth.sin(angle) * 0.1F, 0.08D, -Mth.cos(angle) * 0.1F);
        level.playSound(null, pos, ModSounds.GLOW_WORM_HURT.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
    }
}
