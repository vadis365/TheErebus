package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import org.jspecify.annotations.NonNull;

public class SiloSupportsBlock extends Block {

	public static final MapCodec<SiloSupportsBlock> CODEC = simpleCodec(SiloSupportsBlock::new);

	public SiloSupportsBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected @NonNull MapCodec<SiloSupportsBlock> codec() {
		return CODEC;
	}

    @Override
    protected boolean canSurvive(@NonNull BlockState state, LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.below()).is(ModBlocks.RED_GEM_BLOCK.get());
	}

    @Override
    protected boolean isPathfindable(@NonNull BlockState state, @NonNull PathComputationType pathComputationType) {
		return true;
    }

	@Override
	protected @NonNull BlockState updateShape(@NonNull BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess ticks, @NonNull BlockPos pos, @NonNull Direction directionToNeighbour, @NonNull BlockPos neighbourPos, @NonNull BlockState neighbourState, @NonNull RandomSource random) {
		boolean canSurvive = canSurvive(state, level, pos);
        return !canSurvive ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
	}

	@NonNull
	@Override
	public RenderShape getRenderShape(@NonNull BlockState state) {
		return RenderShape.MODEL;
	}
}
