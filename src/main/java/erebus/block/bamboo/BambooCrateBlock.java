package erebus.block.bamboo;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NotNull;

import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class BambooCrateBlock extends Block {

    public static final EnumProperty<EnumCrateType> CRATE_TYPE = EnumProperty.create("crate_type", EnumCrateType.class);

    public BambooCrateBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(CRATE_TYPE, EnumCrateType.DEFAULT));
    }
    
	@Override
	 public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(CRATE_TYPE, EnumCrateType.DEFAULT);
	}

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CRATE_TYPE);
    }
 
    @Override
    protected void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean movedByPiston) {
    	updateShape(state, Direction.UP, oldState, level, pos, pos); 
    }

	@Override
	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
		if (isCrate(level, pos)) {
            if (isCrate(level, pos.above()) && isCrate(level, pos.offset(1, 1, 0)) && isCrate(level, pos.offset(1, 1, 1)) && isCrate(level, pos.offset(0, 1, 1)))
                if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1)))
                	level.setBlock(pos, state.setValue(CRATE_TYPE, EnumCrateType.BTL), 3);

            if (isCrate(level, pos.above()) && isCrate(level, pos.offset(-1, 1, 0)) && isCrate(level, pos.offset(-1, 1, 1)) && isCrate(level, pos.offset(0, 1, 1)))
                if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1)))
                	level.setBlock(pos, state.setValue(CRATE_TYPE, EnumCrateType.BTR), 3);

            if (isCrate(level, pos.above()) && isCrate(level, pos.offset(1, 1, 0)) && isCrate(level, pos.offset(1, 1, -1)) && isCrate(level, pos.offset(0, 1, -1))) 
                if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1)))
                	level.setBlock(pos, state.setValue(CRATE_TYPE, EnumCrateType.BBL), 3);

            if (isCrate(level, pos.above()) && isCrate(level, pos.offset(-1, 1, 0)) && isCrate(level, pos.offset(-1, 1, -1)) && isCrate(level, pos.offset(0, 1, -1))) 
                if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1)))
                	level.setBlock(pos, state.setValue(CRATE_TYPE, EnumCrateType.BBR), 3);

            if (isCrate(level, pos.below()) && isCrate(level, pos.offset(1, -1, 0)) && isCrate(level, pos.offset(1, -1, 1)) && isCrate(level, pos.offset(0, -1, 1)))
                if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1)))
                	level.setBlock(pos, state.setValue(CRATE_TYPE, EnumCrateType.TTL), 3);

            if (isCrate(level, pos.below()) && isCrate(level, pos.offset(-1, -1, 0)) && isCrate(level, pos.offset(-1, -1, 1)) && isCrate(level, pos.offset(0, -1, 1)))
                if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1)))
                	level.setBlock(pos, state.setValue(CRATE_TYPE, EnumCrateType.TTR), 3);
 
            if (isCrate(level, pos.below()) && isCrate(level, pos.offset(1, -1, 0)) && isCrate(level, pos.offset(1, -1, -1)) && isCrate(level, pos.offset(0, -1, -1)))
                if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1)))
                	level.setBlock(pos, state.setValue(CRATE_TYPE, EnumCrateType.TBL), 3);

            if (isCrate(level, pos.below()) && isCrate(level, pos.offset(-1, -1, 0)) && isCrate(level, pos.offset(-1, -1, -1)) && isCrate(level, pos.offset(0, -1, -1)))
                if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1)))
                	level.setBlock(pos, state.setValue(CRATE_TYPE, EnumCrateType.TBR), 3);
		}
		return state;
	}

	@Override
	public void onRemove(BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock()))
			resetCrates(level, pos, state.getValue(CRATE_TYPE));
		super.onRemove(state, level, pos, newState, isMoving);
	}

	private void resetCrates(Level level, BlockPos pos, EnumCrateType type) {
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++)
				for (int k = -1; k <= 1; k++)
					if (level.getBlockState(pos.offset(i, k, j)).getBlock() == this) {
						BlockState crateState = level.getBlockState(pos.offset(i, k, j));
						if (crateState.getValue(CRATE_TYPE) != EnumCrateType.DEFAULT)
							level.setBlock(pos.offset(i, k, j), crateState.setValue(CRATE_TYPE, EnumCrateType.DEFAULT), 3);
					}
	}

    private boolean isCrate(LevelAccessor level, BlockPos pos) {
        return level.getBlockState(pos).is(OtherBlocks.BAMBOO_CRATE.get());
    }
/*
    public boolean canPlace(Level level, BlockPos pos) {  // TODO done in an itemb lock now (will crash if placed next to already formed big ones atm)
		for (Direction dir : Direction.values()) {
			BlockState state = level.getBlockState(pos.offset(dir.getStepX(), dir.getStepY(), dir.getStepZ()));
			if (state.getBlock() == this) {
				EnumCrateType type = state.getValue(CRATE_TYPE);
				if (type != EnumCrateType.DEFAULT)
					return false;
				if (level.getBlockState(pos.offset(dir.getOpposite().getStepX(), dir.getOpposite().getStepY(), dir.getOpposite().getStepZ())).getBlock() == this)
					return false;
			}
		}
		return true;
	}*/
}
