package erebus.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class ConnectedTextureBlock extends Block {

    public static final BooleanProperty CONNECTED_DOWN = BooleanProperty.create("connected_down");
    public static final BooleanProperty CONNECTED_UP = BooleanProperty.create("connected_up");
    public static final BooleanProperty CONNECTED_NORTH = BooleanProperty.create("connected_north");
    public static final BooleanProperty CONNECTED_SOUTH = BooleanProperty.create("connected_south");
    public static final BooleanProperty CONNECTED_WEST = BooleanProperty.create("connected_west");
    public static final BooleanProperty CONNECTED_EAST = BooleanProperty.create("connected_east");


    public ConnectedTextureBlock(Properties properties) {
        super(properties);

        registerDefaultState(getStateDefinition().any()
                .setValue(CONNECTED_DOWN, false)
                .setValue(CONNECTED_UP, false)
                .setValue(CONNECTED_NORTH, false)
                .setValue(CONNECTED_SOUTH, false)
                .setValue(CONNECTED_WEST, false)
                .setValue(CONNECTED_EAST, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CONNECTED_DOWN, CONNECTED_UP, CONNECTED_NORTH, CONNECTED_SOUTH, CONNECTED_WEST, CONNECTED_EAST);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();

        if (context.canPlace()) {
            return getConnectableSides(level, pos);
        }

        return null;
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        return adjacentState.is(this) || super.skipRendering(state, adjacentState, direction);
    }

    private BlockState getConnectableSides(Level level, BlockPos pos) {
        return defaultBlockState()
                .setValue(CONNECTED_DOWN, level.getBlockState(pos.below()).getBlock() == this)
                .setValue(CONNECTED_UP, level.getBlockState(pos.above()).getBlock() == this)
                .setValue(CONNECTED_NORTH, level.getBlockState(pos.north()).getBlock() == this)
                .setValue(CONNECTED_SOUTH, level.getBlockState(pos.south()).getBlock() == this)
                .setValue(CONNECTED_WEST, level.getBlockState(pos.west()).getBlock() == this)
                .setValue(CONNECTED_EAST, level.getBlockState(pos.east()).getBlock() == this);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        level.setBlock(pos, getConnectableSides(level, pos), Block.UPDATE_ALL);
    }
}
