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
    public static final VoxelShape RIGHT_SIDE = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 14D, 16.0D);
    public static final VoxelShape LEFT_SIDE = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 14D, 16.0D);
    public static final VoxelShape BACK_SIDE = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 14D, 16.0D); //hue
    public static final VoxelShape FRONT_SIDE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14D, 2.0D);
    public static final VoxelShape BASE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2D, 16.0D);

    public BambooBridge(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    private static VoxelShape railShape(Direction side) {
        return switch (side) {
            case EAST -> RIGHT_SIDE;
            case WEST -> LEFT_SIDE;
            case NORTH -> FRONT_SIDE;
            case SOUTH -> BACK_SIDE;
            default -> Shapes.empty();
        };
    }

    public static boolean shouldRenderRail(BlockState state, BlockGetter level, BlockPos pos, boolean right) {
        Direction side = right ? state.getValue(FACING).getClockWise() : state.getValue(FACING).getCounterClockWise();
        return !canConnectBridgeTo(level, pos.relative(side), state.getBlock());
    }

    private static boolean canConnectBridgeTo(BlockGetter level, BlockPos pos, Block bridge) {
        BlockState state = level.getBlockState(pos);
        return state.is(bridge) || !state.isAir() && state.isCollisionShapeFullBlock(level, pos) && !state.is(BlockTags.CROPS);
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

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction right = state.getValue(FACING).getClockWise();
        Direction left = state.getValue(FACING).getCounterClockWise();
        VoxelShape result = BASE;
        if (shouldRenderRail(state, level, pos, true)) result = Shapes.or(result, railShape(right));
        if (shouldRenderRail(state, level, pos, false)) result = Shapes.or(result, railShape(left));
        return result;
    }

    public boolean canConnectBridgeTo(BlockGetter level, BlockPos pos) {
        return canConnectBridgeTo(level, pos, this);
    }
}
