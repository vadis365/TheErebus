package erebus.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class GlowshroomBlock extends Block {
	public static final MapCodec<GlowshroomBlock> CODEC = simpleCodec(GlowshroomBlock::new);
	protected static final VoxelShape GLOWSHROOM = Block.box(1D, 0D, 1D, 15D, 16D, 15D);

	public GlowshroomBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected @NonNull MapCodec<GlowshroomBlock> codec() {
		return CODEC;
	}

	@Override
	public @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter worldIn, @NonNull BlockPos pos, @NonNull CollisionContext context) {
		return GLOWSHROOM;
	}

	@Override
	public @NonNull RenderShape getRenderShape(@NonNull BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	protected @NonNull BlockState updateShape(@NonNull BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess ticks, @NonNull BlockPos pos, @NonNull Direction directionToNeighbour, @NonNull BlockPos neighbourPos, @NonNull BlockState neighbourState, @NonNull RandomSource random) {
		return directionToNeighbour == Direction.DOWN && !this.canSurvive(state, level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
	}

	@Override
	protected boolean canSurvive(@NonNull BlockState state, @NonNull LevelReader level, BlockPos pos) {
		return canSupportCenter(level, pos.below(), Direction.UP);
	}

}
