package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.LiquifierBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;

public class LiquifierBlock extends HorizontalDirectionalBlock implements EntityBlock {

	public static final MapCodec<LiquifierBlock> CODEC = simpleCodec(LiquifierBlock::new);
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");

	public LiquifierBlock(Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(POWERED, false));
	}

	@Override
	protected @NonNull MapCodec<LiquifierBlock> codec() {
		return CODEC;
	}

    @Override
	public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
		return new LiquifierBlockEntity(pos, state);
	}

	/*
	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
		tooltipComponents.add(Component.translatable("tooltip.erebus.liquifier").withStyle(ChatFormatting.YELLOW));
	}*/

	@Override
	public @NonNull RenderShape getRenderShape(@NonNull BlockState state) {
		return RenderShape.INVISIBLE;
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, @NonNull BlockState pState, @NonNull BlockEntityType<T> pBlockEntityType) {
		return pLevel.isClientSide() ? LiquifierBlockEntity::clientTick : LiquifierBlockEntity::serverTick;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getHorizontalDirection();
		return this.defaultBlockState().setValue(FACING, direction).setValue(POWERED, false);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, POWERED);
	}

	@Override
	public @NonNull InteractionResult useWithoutItem(@NonNull BlockState state, Level world, @NonNull BlockPos pos, @NonNull Player player, @NonNull BlockHitResult hitResult) {
		if (!world.isClientSide()) {
			if (world.getBlockEntity(pos) instanceof LiquifierBlockEntity liquifier)
				player.openMenu(liquifier, pos);
		}
		return InteractionResult.SUCCESS;
	}

	/*@Override
	public void onRemove(BlockState state, @Nonnull Level world, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock())) {
			LiquifierBlockEntity tile = (LiquifierBlockEntity) world.getBlockEntity(pos);
			if (tile != null) {
				Containers.dropContents(world, pos, tile);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}*/

	@Override
	public void neighborChanged(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull Block block, Orientation orientation, boolean movedByPiston) {
		if (!level.isClientSide()) {
			LiquifierBlockEntity tile = (LiquifierBlockEntity) level.getBlockEntity(pos);
			boolean flag = level.hasNeighborSignal(pos);
			if (flag != state.getValue(POWERED)) {
				level.setBlock(pos, state.setValue(POWERED, flag), 3);
				if (tile != null)
					tile.setActive(flag);
			}
		}
	}

	@Override
	public void tick(@NonNull BlockState state, ServerLevel world, @NonNull BlockPos pos, @NonNull RandomSource rand) {
		if (!world.isClientSide()) {
			boolean flag = !world.hasNeighborSignal(pos);
			if (flag != state.getValue(POWERED))
				world.setBlock(pos, state.setValue(POWERED, flag), 4);
		}
	}
}
