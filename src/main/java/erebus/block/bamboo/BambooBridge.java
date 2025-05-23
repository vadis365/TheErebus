package erebus.block.bamboo;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.MapCodec;

import erebus.block.entity.BambooBridgeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BambooBridge extends HorizontalDirectionalBlock implements EntityBlock {
	public static final MapCodec<BambooBridge> CODEC = simpleCodec(BambooBridge::new);
	public BambooBridge(Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	//	setCreativeTab(ModTabs.BLOCKS);
	//	setHardness(0.4F);
	//	setSoundType(SoundType.LADDER);
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
/*
	@Override
    public void breakBlock(World level, BlockPos pos, IBlockState state) {
		level.playEvent(2001, pos, Block.getStateId(EnumWood.BAMBOO.getLog().getDefaultState()));
		super.breakBlock(level, pos, state);
	}
*/
	 @Override
	    protected void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean movedByPiston) {
		BlockEntity te = level.getBlockEntity(pos);
		if (te instanceof BambooBridgeBlockEntity bridge) {
			boolean front = canConnectBridgeTo(level, pos.offset(0, 0, - 1));
			boolean back = canConnectBridgeTo(level, pos.offset(0, 0, 1));
			boolean left = canConnectBridgeTo(level, pos.offset(- 1, 0, 0));
			boolean right = canConnectBridgeTo(level, pos.offset(1, 0, 0));
	
			switch (state.getValue(FACING)) {
				case NORTH: //North
					if (!right)
						bridge.setRenderSide1(true);
					if (!left)
						bridge.setRenderSide2(true);
					if (right)
						bridge.setRenderSide1(false);
					if (left)
						bridge.setRenderSide2(false);
					break;
				case SOUTH: //SOUTH
					if (!right)
						bridge.setRenderSide2(true);
					if (!left)
						bridge.setRenderSide1(true);
					if (right)
						bridge.setRenderSide2(false);
					if (left)
						bridge.setRenderSide1(false);
					break;
				case EAST: // WEST
					if (!back)
						bridge.setRenderSide1(true);
					if (!front)
						bridge.setRenderSide2(true);
					if (back)
						bridge.setRenderSide1(false);
					if (front)
						bridge.setRenderSide2(false);
					break;
				case WEST: //EAST
					if (!back)
						bridge.setRenderSide2(true);
					if (!front)
						bridge.setRenderSide1(true);
					if (back)
						bridge.setRenderSide2(false);
					if (front)
						bridge.setRenderSide1(false);
					break;
			default:
				break;
			}
		level.sendBlockUpdated(pos, state, oldState, UPDATE_ALL);
		}
	}

	 @Override
	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
		 return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
	}

    public static final VoxelShape  RIGHT_AABB = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 14D, 16.0D);
    public static final VoxelShape LEFT_AABB = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 14D, 16.0D);
    public static final VoxelShape BACK_AABB = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 14D, 16.0D);
    public static final VoxelShape FRONT_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14D, 2.0D);
    public static final VoxelShape BASE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2D, 16.0D);

	@Nonnull
	@Override
	public VoxelShape getShape(BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		BlockEntity te = level.getBlockEntity(pos);
		if (te instanceof BambooBridgeBlockEntity bridge) {
			boolean front = canConnectBridgeTo(bridge.getLevel(), pos.offset(0, 0, -1));
			boolean back = canConnectBridgeTo(bridge.getLevel(), pos.offset(0, 0, 1));
			boolean left = canConnectBridgeTo(bridge.getLevel(), pos.offset(-1, 0, 0));
			boolean right = canConnectBridgeTo(bridge.getLevel(), pos.offset(1, 0, 0));
			VoxelShape side_1 = Shapes.empty();
			VoxelShape side_2 = Shapes.empty();
			VoxelShape s_combined = Shapes.empty();

			if (state.getValue(FACING).equals(Direction.NORTH) || state.getValue(FACING).equals(Direction.SOUTH)) {
				if (!right)
					side_1 = RIGHT_AABB;
				if (!left)
					side_2 = LEFT_AABB;
				s_combined = Shapes.join(side_1, side_2, BooleanOp.OR);
			}

			if (state.getValue(FACING).equals(Direction.EAST) || state.getValue(FACING).equals(Direction.WEST)) {
				if (!back)
					side_1 = BACK_AABB;
				if (!front)
					side_2 = FRONT_AABB;
				s_combined = Shapes.join(side_1, side_2, BooleanOp.OR);
			}
			return Shapes.join(BASE, s_combined, BooleanOp.OR);
		}
		return BASE;
	}

	public boolean canConnectBridgeTo(LevelAccessor level, BlockPos pos) {
		BlockState state = level.getBlockState(pos);
		Block block = state.getBlock();
		if (block != this)
			return !level.isEmptyBlock(pos) && state.isCollisionShapeFullBlock(level, pos) ? !state.is(BlockTags.CROPS) : false;
		else
			return true;
	}
}