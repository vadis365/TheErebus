package erebus.block.bamboo;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.BambooExtenderBlockEntity;
import erebus.registries.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BambooExtender extends DirectionalBlock  implements EntityBlock {

	public static final MapCodec<BambooExtender> CODEC = simpleCodec(BambooExtender::new);
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");

	public BambooExtender(Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
	//	setCreativeTab(ModTabs.BLOCKS);
	//	setHardness(0.4F);
	//	setHarvestLevel("axe", 0);
	//	setSoundType(SoundType.LADDER);
	}

    @Override
	protected @NotNull MapCodec<BambooExtender> codec() {
        return CODEC;
    }

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.INVISIBLE;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, @Nonnull BlockState pState, @Nonnull BlockEntityType<T> pBlockEntityType) {
		return pLevel.isClientSide ? null : BambooExtenderBlockEntity::serverTick;
	}

    @Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new BambooExtenderBlockEntity(pos, state);
	}

    @Override
 	public ItemInteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
     	BlockEntity blockEntity = level.getBlockEntity(pos);
     	if (level.isClientSide()) {
 			return ItemInteractionResult.SUCCESS;
     	} else if (blockEntity instanceof BambooExtenderBlockEntity extender) {
			if (!stack.isEmpty() && stack.getItem() == ModItems.BAMBOO_PIPE_WRENCH.get()) {
				return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
			}
			else {
				player.openMenu(extender, pos);
				return ItemInteractionResult.SUCCESS;
			}
		}
    return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getNearestLookingDirection();
		if(direction == Direction.UP || direction == Direction.DOWN)
			return this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(POWERED, false);
		return this.defaultBlockState().setValue(FACING, direction).setValue(POWERED, false);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, POWERED);
	}

	@Override
	public void neighborChanged(BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Block block, @Nonnull BlockPos fromPos, boolean isMoving) {
		if (!level.isClientSide()) {
			BambooExtenderBlockEntity tile = (BambooExtenderBlockEntity) level.getBlockEntity(pos);
			boolean flag = level.hasNeighborSignal(pos);
			if (flag != state.getValue(POWERED)) {
				level.setBlock(pos, state.setValue(POWERED, flag), 3);
				if (tile != null)
					tile.setExtending(flag);
			}
		}
	}

	@Override
	public void onRemove(BlockState state, @Nonnull Level world, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock())) {
			BambooExtenderBlockEntity tile = (BambooExtenderBlockEntity) world.getBlockEntity(pos);
			if (tile != null) {
				Containers.dropContents(world, pos, tile);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}
}