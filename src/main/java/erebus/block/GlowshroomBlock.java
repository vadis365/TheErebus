package erebus.block;

import javax.annotation.Nonnull;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GlowshroomBlock extends Block {
	public static final MapCodec<GlowshroomBlock> CODEC = simpleCodec(GlowshroomBlock::new);
	protected static final VoxelShape GLOWSHROOM = Block.box(1D, 0D, 1D, 15D, 16D, 15D);

	public GlowshroomBlock(Properties properties) {
		super(properties);
	}

	@Nonnull
	@Override
	protected MapCodec<GlowshroomBlock> codec() {
		return CODEC;
	}

	@Nonnull
	@Override
	public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		return GLOWSHROOM;
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
		return facing == Direction.DOWN && !this.canSurvive(state, level, pos)
	            ? Blocks.AIR.defaultBlockState()
	            : super.updateShape(state, facing, facingState, level, pos, facingPos);
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return canSupportCenter(level, pos.below(), Direction.UP);
	}

}
