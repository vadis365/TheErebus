package erebus.block.bamboo;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.BambooPipeBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

public class BambooPipe extends DirectionalBlock implements EntityBlock, TooltipProvider {
	public static final MapCodec<BambooPipe> CODEC = simpleCodec(BambooPipe::new);
    public static final BooleanProperty CONNECTED_DOWN = BooleanProperty.create("connected_down");
    public static final BooleanProperty CONNECTED_UP = BooleanProperty.create("connected_up");
    public static final BooleanProperty CONNECTED_NORTH = BooleanProperty.create("connected_north");
    public static final BooleanProperty CONNECTED_SOUTH = BooleanProperty.create("connected_south");
    public static final BooleanProperty CONNECTED_WEST = BooleanProperty.create("connected_west");
    public static final BooleanProperty CONNECTED_EAST = BooleanProperty.create("connected_east");

	public BambooPipe(Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(CONNECTED_DOWN, Boolean.FALSE).setValue(CONNECTED_EAST, Boolean.FALSE).setValue(CONNECTED_NORTH, Boolean.FALSE).setValue(CONNECTED_SOUTH, Boolean.FALSE).setValue(CONNECTED_UP, Boolean.FALSE).setValue(CONNECTED_WEST, Boolean.FALSE));
	}

	@Override
	protected @NonNull MapCodec<BambooPipe> codec() {
		return CODEC;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, @Nonnull BlockState pState, @Nonnull BlockEntityType<T> pBlockEntityType) {
		return pLevel.isClientSide() ? null : BambooPipeBlockEntity::serverTick;
	}

	@Override
	public void addToTooltip(Item.@NonNull TooltipContext context, Consumer<Component> consumer, @NonNull TooltipFlag tooltipFlag, @NonNull DataComponentGetter dataComponentGetter) {
		consumer.accept(Component.translatable("tooltip.erebus.bamboo_pipe").withColor(ChatFormatting.YELLOW.getColor()));
	}

	// TODO: This will have to be part of the Item
	/*@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
		tooltipComponents.add(Component.translatable("tooltip.erebus.bamboo_pipe").withStyle(ChatFormatting.YELLOW));
	}*/

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}

    @Override
    protected boolean skipRendering(@NonNull BlockState state, BlockState adjacentState, @NonNull Direction direction) {
        return adjacentState.is(this) || super.skipRendering(state, adjacentState, direction);
    }

	@Nonnull
	@Override
	public VoxelShape getShape(BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		float minX = 5F, minY = 5F, minZ = 5F;
		float maxX = 11F, maxY = 11F, maxZ = 11F;
		if (state.getValue(FACING) == Direction.UP) maxY = 16.0F;
		if (state.getValue(FACING) == Direction.DOWN) minY = 0.0F;
		if (state.getValue(FACING) == Direction.SOUTH) maxZ = 16.0F;
		if (state.getValue(FACING) == Direction.NORTH) minZ = 0.0F;
		if (state.getValue(FACING) == Direction.WEST) minX = 0.0F;
		if (state.getValue(FACING) == Direction.EAST) maxX = 16.0F;

		if (state.getValue(CONNECTED_UP)) maxY = 16.0F;
		if (state.getValue(CONNECTED_DOWN)) minY = 0.0F;
		if (state.getValue(CONNECTED_SOUTH)) maxZ = 16.0F;
		if (state.getValue(CONNECTED_NORTH)) minZ = 0.0F;
		if (state.getValue(CONNECTED_WEST)) minX = 0.0F;
		if (state.getValue(CONNECTED_EAST)) maxX = 16.0F;

		VoxelShape voxelshape = Block.box(minX, minY, minZ, maxX, maxY, maxZ);
		return voxelshape;
	}

	@Nonnull
	@Override
	public VoxelShape getInteractionShape(BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos) {
		float minX = 5F, minY = 5F, minZ = 5F;
		float maxX = 11F, maxY = 11F, maxZ = 11F;
		if (state.getValue(FACING) == Direction.UP) maxY = 16.0F;
		if (state.getValue(FACING) == Direction.DOWN) minY = 0.0F;
		if (state.getValue(FACING) == Direction.SOUTH) maxZ = 16.0F;
		if (state.getValue(FACING) == Direction.NORTH) minZ = 0.0F;
		if (state.getValue(FACING) == Direction.WEST) minX = 0.0F;
		if (state.getValue(FACING) == Direction.EAST) maxX = 16.0F;

		if (state.getValue(CONNECTED_UP)) maxY = 16.0F;
		if (state.getValue(CONNECTED_DOWN)) minY = 0.0F;
		if (state.getValue(CONNECTED_SOUTH)) maxZ = 16.0F;
		if (state.getValue(CONNECTED_NORTH)) minZ = 0.0F;
		if (state.getValue(CONNECTED_WEST)) minX = 0.0F;
		if (state.getValue(CONNECTED_EAST)) maxX = 16.0F;

		VoxelShape voxelshape = Block.box(minX, minY, minZ, maxX, maxY, maxZ);
		return voxelshape;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getClickedFace().getOpposite();
		return this.defaultBlockState().setValue(FACING, direction);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, CONNECTED_DOWN, CONNECTED_UP, CONNECTED_NORTH, CONNECTED_SOUTH, CONNECTED_WEST, CONNECTED_EAST);
	}

	@Override
    protected @NonNull BlockState updateShape(BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess ticks, @NonNull BlockPos pos, @NonNull Direction directionToNeighbour, @NonNull BlockPos neighbourPos, @NonNull BlockState neighbourState, @NonNull RandomSource random) {
    	return state.setValue(CONNECTED_DOWN, this.isSideConnectable(level, pos, Direction.DOWN)).setValue(CONNECTED_EAST, this.isSideConnectable(level, pos, Direction.EAST)).setValue(CONNECTED_NORTH, this.isSideConnectable(level, pos, Direction.NORTH)).setValue(CONNECTED_SOUTH, this.isSideConnectable(level, pos, Direction.SOUTH)).setValue(CONNECTED_UP, this.isSideConnectable(level, pos, Direction.UP)).setValue(CONNECTED_WEST, this.isSideConnectable(level, pos, Direction.WEST));
    }

    private boolean isSideConnectable (LevelReader level, BlockPos pos, Direction side) {
		BlockPos offsetPos = pos.relative(side);
    	BlockEntity blockEntity = level.getBlockEntity(pos.relative(side));
        return blockEntity != null && blockEntity.getLevel() != null;
    }

    @Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new BambooPipeBlockEntity(pos, state);
	}
}
