package erebus.block;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.util.TriState;

public class PricklyPearBlock extends Block implements BonemealableBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 11);

    public PricklyPearBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return defaultBlockState().setValue(AGE, 0);
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Block.box(2, 0, 2, 14, 16, 14);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader level, @NotNull BlockPos pos, BlockState state) {
        return state.getValue(AGE) < 11 && level.isEmptyBlock(pos.above());
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
    	randomTick(state, level, pos, random);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 11;
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos))
            level.destroyBlock(pos, true);
    }

	@Override
	protected void randomTick(BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		if (level.isEmptyBlock(pos.above())) {
			int growthHeight;

			for (growthHeight = 1; level.getBlockState(pos.below(growthHeight)).is(this); ++growthHeight);

			if (growthHeight < 3) {
				int stage = state.getValue(AGE);

				if (stage == 10) {
					level.setBlockAndUpdate(pos.above(), defaultBlockState());
					if (level.getBlockState(pos).is(this) && level.getBlockState(pos.below()).is(this)) {
						level.setBlock(pos.above(), state.setValue(AGE, 11), 4);
					} else
						level.setBlock(pos, state.setValue(AGE, 0), 4);
				} else if (stage < 10) {
					level.setBlock(pos, state.setValue(AGE, stage + 1), 4);
				}
			}
		}
      /*  if (state.getValue(AGE) < 11 && level.getRawBrightness(pos.above(), 0) >= 9 && CommonHooks.canCropGrow(level, pos, state, random.nextInt(5) == 0)) {
            BlockState growthAge = state.setValue(AGE, state.getValue(AGE) + 1);
            level.setBlock(pos, growthAge, 2);
            CommonHooks.fireCropGrowPost(level, pos, state);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(growthAge));
        }*/
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (!state.canSurvive(level, currentPos))
            level.scheduleTick(currentPos, this, 1);
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockState stateBelow = level.getBlockState(pos.below());
		if (stateBelow.is(this)) {
			return true;
		} else {
			TriState soilDecision = stateBelow.canSustainPlant(level, pos.below(), Direction.UP, state);
			if (!soilDecision.isDefault())
				return soilDecision.isTrue();
			if (stateBelow.is(BlockTags.DIRT) || stateBelow.is(BlockTags.SAND))
				return true;
		}
		return false;
	}
}
