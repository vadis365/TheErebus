package erebus.block.bamboo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.MapCodec;

import erebus.block.types.EnumTorchBlockHalf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BambooTorchBlock extends Block {
	public static final MapCodec<BambooTorchBlock> CODEC = simpleCodec(BambooTorchBlock::new);
    public static final EnumProperty<EnumTorchBlockHalf> HALF = EnumProperty.create("half", EnumTorchBlockHalf.class);
    protected static final VoxelShape TORCH = Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);

    public BambooTorchBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(HALF, EnumTorchBlockHalf.LOWER));
    }

	@Nonnull
	@Override
	protected MapCodec<BambooTorchBlock> codec() {
		return CODEC;
	}

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return TORCH;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF);
    }

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();
        return blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(context) ? super.getStateForPlacement(context) : null;
    }

	@Override
	protected void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean movedByPiston) {
		EnumTorchBlockHalf doubleblockhalf = state.getValue(HALF);
		if(doubleblockhalf == EnumTorchBlockHalf.LOWER)
			level.setBlock(pos.above(), defaultBlockState().setValue(HALF, EnumTorchBlockHalf.UPPER), 2);
    }

	@Override
	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
		EnumTorchBlockHalf doubleblockhalf = state.getValue(HALF);
        if (facing.getAxis() != Direction.Axis.Y
            || doubleblockhalf == EnumTorchBlockHalf.LOWER != (facing == Direction.UP)
            || facingState.is(this) && facingState.getValue(HALF) != doubleblockhalf) {
            return doubleblockhalf == EnumTorchBlockHalf.LOWER && facing == Direction.DOWN && !canSurvive(state, level, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, facing, facingState, level, pos, facingPos);
        } else {
            return Blocks.AIR.defaultBlockState();
        }
	}

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (state.getValue(HALF) != EnumTorchBlockHalf.UPPER) {
            return canSupportCenter(level, pos.below(), Direction.UP);
        } else {
            BlockState blockstate = level.getBlockState(pos.below());
            return blockstate.is(this) && blockstate.getValue(HALF) == EnumTorchBlockHalf.LOWER;
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (state.getValue(HALF) == EnumTorchBlockHalf.UPPER) {
			double d0 = pos.getX() + 0.4375F;
			double d1 = pos.getY() + 1.0625F;
			double d2 = pos.getZ() + 0.4375F;
			double d3 = pos.getX() + 0.5625F;
			double d4 = pos.getZ() + 0.5625F;
			double d5 = pos.getX() + 0.5F;
			double d6 = pos.getY() + 1.25F;
			double d7 = pos.getZ() + 0.5F;
			level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			level.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			level.addParticle(ParticleTypes.SMOKE, d0, d1, d4, 0.0D, 0.0D, 0.0D);
			level.addParticle(ParticleTypes.FLAME, d0, d1, d4, 0.0D, 0.0D, 0.0D);
			level.addParticle(ParticleTypes.SMOKE, d3, d1, d2, 0.0D, 0.0D, 0.0D);
			level.addParticle(ParticleTypes.FLAME, d3, d1, d2, 0.0D, 0.0D, 0.0D);
			level.addParticle(ParticleTypes.SMOKE, d3, d1, d4, 0.0D, 0.0D, 0.0D);
			level.addParticle(ParticleTypes.FLAME, d3, d1, d4, 0.0D, 0.0D, 0.0D);
			level.addParticle(ParticleTypes.SMOKE, d5, d6, d7, 0.0D, 0.0D, 0.0D);
			level.addParticle(ParticleTypes.FLAME, d5, d6, d7, 0.0D, 0.0D, 0.0D);
		}
	}

}
