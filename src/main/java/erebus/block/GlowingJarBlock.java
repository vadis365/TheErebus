package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.GlowingJarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

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

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.INVISIBLE;
    }

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, @Nonnull BlockState pState, @Nonnull BlockEntityType<T> pBlockEntityType) {
		return pLevel.isClientSide() ? GlowingJarBlockEntity::clientTick : null;
	}

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
     /*   for (int i = 0; i < 2; i++) {
            double dx = pos.getX() + ((random.nextFloat() - random.nextFloat()) * 0.2F + 0.5F);
            double dy = pos.getY() + 0.4F + ((random.nextFloat() - random.nextFloat()) * 0.3F);
            double dz = pos.getZ() + ((random.nextFloat() - random.nextFloat()) * 0.2F + 0.5F);
            level.addParticle(ModParticles.WISP.get(), dx, dy, dz, 0, 0, 0);
        }*/
    }
}
