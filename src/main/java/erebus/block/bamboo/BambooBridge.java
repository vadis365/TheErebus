package erebus.block.bamboo;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.BambooBridgeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;

public class BambooBridge extends HorizontalDirectionalBlock implements EntityBlock {

	public static final MapCodec<BambooBridge> CODEC = simpleCodec(BambooBridge::new);

	public BambooBridge(Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

    @Override
	protected @NotNull MapCodec<BambooBridge> codec() {
        return CODEC;
    }

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.INVISIBLE;
	}

    @Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new BambooBridgeBlockEntity(pos, state);
	}

	@Override
	 public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getHorizontalDirection();
		return this.defaultBlockState().setValue(FACING, direction);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	protected @NonNull BlockState updateShape(@NonNull BlockState state, LevelReader level, @NonNull ScheduledTickAccess ticks, @NonNull BlockPos pos, @NonNull Direction directionToNeighbour, @NonNull BlockPos neighbourPos, @NonNull BlockState neighbourState, @NonNull RandomSource random) {
		BlockEntity te = level.getBlockEntity(pos);
		if (te instanceof BambooBridgeBlockEntity bridge) {
			boolean front = canConnectBridgeTo(level, pos.offset(0, 0, -1));
			boolean back = canConnectBridgeTo(level, pos.offset(0, 0, 1));
			boolean left = canConnectBridgeTo(level, pos.offset(-1, 0, 0));
			boolean right = canConnectBridgeTo(level, pos.offset(1, 0, 0));

			switch (state.getValue(FACING)) {
			case NORTH:
				bridge.setRenderSide1(!right);
				bridge.setRenderSide2(!left);
				break;
			case SOUTH:
				bridge.setRenderSide2(!right);
				bridge.setRenderSide1(!left);
				break;
			case EAST:
				bridge.setRenderSide1(!back);
				bridge.setRenderSide2(!front);
				break;
			case WEST:
				bridge.setRenderSide2(!back);
				bridge.setRenderSide1(!front);
				break;
			default:
				break;
			}
		}
		return state;
	}

    public static final VoxelShape RIGHT_SIDE = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 14D, 16.0D);
    public static final VoxelShape LEFT_SIDE = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 14D, 16.0D);
    public static final VoxelShape BACK_SIDE = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 14D, 16.0D); //hue
    public static final VoxelShape FRONT_SIDE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14D, 2.0D);
    public static final VoxelShape BASE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2D, 16.0D);

	@Nonnull
	@Override
	public VoxelShape getShape(@NonNull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		BlockEntity te = level.getBlockEntity(pos);
		VoxelShape side_1 = Shapes.empty();
		VoxelShape side_2 = Shapes.empty();

		if (te instanceof BambooBridgeBlockEntity bridge) {
			boolean front = canConnectBridgeTo(bridge.getLevel(), pos.offset(0, 0, -1));
			boolean back = canConnectBridgeTo(bridge.getLevel(), pos.offset(0, 0, 1));
			boolean left = canConnectBridgeTo(bridge.getLevel(), pos.offset(-1, 0, 0));
			boolean right = canConnectBridgeTo(bridge.getLevel(), pos.offset(1, 0, 0));

			if (state.getValue(FACING).equals(Direction.NORTH) || state.getValue(FACING).equals(Direction.SOUTH)) {
				if (!right)
					side_1 = RIGHT_SIDE;
				if (!left)
					side_2 = LEFT_SIDE;
			}

			if (state.getValue(FACING).equals(Direction.EAST) || state.getValue(FACING).equals(Direction.WEST)) {
				if (!back)
					side_1 = BACK_SIDE;
				if (!front)
					side_2 = FRONT_SIDE;
			}
		}

		return Shapes.or(BASE, side_1, side_2);
	}

	public boolean canConnectBridgeTo(LevelReader level, BlockPos pos) {
		BlockState state = level.getBlockState(pos);
		Block block = state.getBlock();
		if (block != this)
			return !level.isEmptyBlock(pos) && state.isCollisionShapeFullBlock(level, pos) && !state.is(BlockTags.CROPS);
		else
			return true;
	}
}
