package erebus.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class SiloSupportsBlock extends Block {

	public static final MapCodec<SiloSupportsBlock> CODEC = simpleCodec(SiloSupportsBlock::new);

	public SiloSupportsBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected @NotNull MapCodec<SiloSupportsBlock> codec() {
		return CODEC;
	}

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.below()).is(ModBlocks.RED_GEM_BLOCK.get());
	}

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
		return true;
    }

	@Override
	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
		boolean canSurvive = canSurvive(state, level, pos);
        return !canSurvive ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, pos, facingPos);
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}
}
