package erebus.block.bamboo;

import erebus.block.entity.BambooCrateBlockEntity;
import erebus.block.types.EnumCrateType;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;

public class BambooCrateBlock extends Block implements EntityBlock {

    public static final EnumProperty<EnumCrateType> CRATE_TYPE = EnumProperty.create("crate_type", EnumCrateType.class);

    public BambooCrateBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(CRATE_TYPE, EnumCrateType.DEFAULT));
    }

	@Override
	public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
		return new BambooCrateBlockEntity(pos, state);
	}

	@Override
	 public BlockState getStateForPlacement(@NonNull BlockPlaceContext context) {
		return this.defaultBlockState().setValue(CRATE_TYPE, EnumCrateType.DEFAULT);
	}

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CRATE_TYPE);
    }

	@Override
	protected @NonNull BlockState updateShape(@NonNull BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess ticks, @NonNull BlockPos pos, @NonNull Direction directionToNeighbour, @NonNull BlockPos neighbourPos, @NonNull BlockState neighbourState, @NonNull RandomSource random) {
		if (isCrate(level, pos)) {
			if (isCrate(level, pos.above()) && isCrate(level, pos.offset(1, 1, 0)) && isCrate(level, pos.offset(1, 1, 1)) && isCrate(level, pos.offset(0, 1, 1)))
				if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1)))
					state.setValue(CRATE_TYPE, EnumCrateType.BTL);

			if (isCrate(level, pos.above()) && isCrate(level, pos.offset(-1, 1, 0)) && isCrate(level, pos.offset(-1, 1, 1)) && isCrate(level, pos.offset(0, 1, 1)))
				if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1)))
					state.setValue(CRATE_TYPE, EnumCrateType.BTR);

			if (isCrate(level, pos.above()) && isCrate(level, pos.offset(1, 1, 0)) && isCrate(level, pos.offset(1, 1, -1)) && isCrate(level, pos.offset(0, 1, -1)))
				if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1)))
					state.setValue(CRATE_TYPE, EnumCrateType.BBL);

			if (isCrate(level, pos.above()) && isCrate(level, pos.offset(-1, 1, 0)) && isCrate(level, pos.offset(-1, 1, -1)) && isCrate(level, pos.offset(0, 1, -1)))
				if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1)))
					state.setValue(CRATE_TYPE, EnumCrateType.BBR);

			if (isCrate(level, pos.below()) && isCrate(level, pos.offset(1, -1, 0)) && isCrate(level, pos.offset(1, -1, 1)) && isCrate(level, pos.offset(0, -1, 1)))
				if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1)))
					state.setValue(CRATE_TYPE, EnumCrateType.TTL);

			if (isCrate(level, pos.below()) && isCrate(level, pos.offset(-1, -1, 0)) && isCrate(level, pos.offset(-1, -1, 1)) && isCrate(level, pos.offset(0, -1, 1)))
				if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, 1)) && isCrate(level, pos.offset(0, 0, 1)))
					state.setValue(CRATE_TYPE, EnumCrateType.TTR);

			if (isCrate(level, pos.below()) && isCrate(level, pos.offset(1, -1, 0)) && isCrate(level, pos.offset(1, -1, -1)) && isCrate(level, pos.offset(0, -1, -1)))
				if (isCrate(level, pos.offset(1, 0, 0)) && isCrate(level, pos.offset(1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1)))
					state.setValue(CRATE_TYPE, EnumCrateType.TBL);

			if (isCrate(level, pos.below()) && isCrate(level, pos.offset(-1, -1, 0)) && isCrate(level, pos.offset(-1, -1, -1)) && isCrate(level, pos.offset(0, -1, -1)))
				if (isCrate(level, pos.offset(-1, 0, 0)) && isCrate(level, pos.offset(-1, 0, -1)) && isCrate(level, pos.offset(0, 0, -1)))
					state.setValue(CRATE_TYPE, EnumCrateType.TBR);
		}
		return state;
	}

	@Override
	public @NotNull InteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		} else if (blockEntity instanceof BambooCrateBlockEntity crate) {
			if (state.getValue(CRATE_TYPE) == EnumCrateType.DEFAULT) {
				if (!stack.isEmpty() && stack.is(ModItems.BAMBOO_CRATE.get()))
					return InteractionResult.FAIL;
				player.openMenu(crate, pos);
			} else
				for (int i = -1; i <= 1; i++)
					for (int j = -1; j <= 1; j++)
						for (int k = -1; k <= 1; k++)
							if (level.getBlockState(pos.offset(i, k, j)).is(ModBlocks.BAMBOO_CRATE.get())) {
								BlockState crateState = level.getBlockState(pos.offset(i, k, j));
								if (crateState.getValue(CRATE_TYPE) == EnumCrateType.BTL) {
									BlockEntity blockEntityOther = level.getBlockEntity(pos.offset(i, k, j));
									if (blockEntityOther instanceof BambooCrateBlockEntity crateOther)
										player.openMenu(crateOther, pos.offset(i, k, j));
									return InteractionResult.SUCCESS;
								}
							}
		}
		return InteractionResult.SUCCESS;
	}

/*
	@Override
	public void onRemove(BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock()))
			resetCrates(level, pos, state.getValue(CRATE_TYPE));
		super.onRemove(state, level, pos, newState, isMoving);
	}
*/

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

    private boolean isCrate(LevelReader level, BlockPos pos) {
        return level.getBlockState(pos).is(ModBlocks.BAMBOO_CRATE.get());
    }
}
