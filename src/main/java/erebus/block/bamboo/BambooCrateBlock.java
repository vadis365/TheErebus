package erebus.block.bamboo;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NotNull;

import erebus.block.entity.BambooCrateBlockEntity;
import erebus.registries.ModItems;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

public class BambooCrateBlock extends Block implements EntityBlock {

    public static final EnumProperty<EnumCrateType> CRATE_TYPE = EnumProperty.create("crate_type", EnumCrateType.class);

    public BambooCrateBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(CRATE_TYPE, EnumCrateType.DEFAULT));
    }

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new BambooCrateBlockEntity(pos, state);
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
	public @NotNull ItemInteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (level.isClientSide()) {
			return ItemInteractionResult.SUCCESS;
		} else if (blockEntity instanceof BambooCrateBlockEntity crate) {
			if (state.getValue(CRATE_TYPE) == EnumCrateType.DEFAULT) {
				if (!stack.isEmpty() && stack.is(ModItems.BAMBOO_CRATE.get()))
					return ItemInteractionResult.FAIL;
				player.openMenu(crate, pos);
			} else
				for (int i = -1; i <= 1; i++)
					for (int j = -1; j <= 1; j++)
						for (int k = -1; k <= 1; k++)
							if (level.getBlockState(pos.offset(i, k, j)).is(OtherBlocks.BAMBOO_CRATE.get())) {
								BlockState crateState = level.getBlockState(pos.offset(i, k, j));
								if (crateState.getValue(CRATE_TYPE) == EnumCrateType.BTL) {
									System.out.println("WILL OPEN BIG CRATE MENU HERE");
									//player.openGui(Erebus.INSTANCE, CommonProxy.GuiID.COLOSSAL_CRATE.ordinal(), world, pos.getX() + i, pos.getY() + k, pos.getZ() + j);
									return ItemInteractionResult.SUCCESS;
								}
							}
		}
		return ItemInteractionResult.SUCCESS;
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
}
