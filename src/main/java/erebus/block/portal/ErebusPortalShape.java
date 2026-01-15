package erebus.block.portal;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ErebusPortalShape {
    public static final int WIDTH = 5;
    public static final int HEIGHT = 5;

    private static final BlockBehaviour.StatePredicate FRAME = ErebusPortalShape::isPortalFrame;

    private final LevelAccessor level;
    private final Direction.Axis axis;
    private final Direction rightDir;
    private int numPortalBlocks;
    private final BlockPos bottomLeft;

    public ErebusPortalShape(LevelAccessor level, BlockPos bottomLeft, Direction.Axis axis) {
        this.level = level;
        this.axis = axis;
        this.rightDir = axis == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;
        this.bottomLeft = calculateBottomLeft(bottomLeft);
    }

    public static boolean isPortalFrame(BlockState state, BlockGetter level, BlockPos pos) {
        return state.isCollisionShapeFullBlock(level, pos);
    }

    private static boolean isEmpty(BlockState state) {
        return state.isAir() || state.is(ModBlocks.PORTAL);
    }

    private BlockPos calculateBottomLeft(BlockPos pos) {
        int i = Math.max(level.getMinBuildHeight(), pos.getY() - HEIGHT);

        while (pos.getY() > i && isEmpty(level.getBlockState(pos.below()))) {
            pos = pos.below();
        }

        Direction direction = rightDir.getOpposite();
        int distance = getDistanceUntilEdgeAboveFrame(pos, direction) - 1;
        return distance < 0 ? null : pos.relative(direction, distance);
    }

    private int getDistanceUntilEdgeAboveFrame(BlockPos pos, Direction direction) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int c = 0; c < WIDTH; ++c) {
            mutable.set(pos).move(direction, c);
            BlockState state = level.getBlockState(mutable);
            if (!isEmpty(state)) {
                if (FRAME.test(state, level, mutable)) return c;
                break;
            }

            BlockState below = level.getBlockState(mutable.move(Direction.DOWN));
            if (!FRAME.test(below, level, mutable)) break;
        }

        return 0;
    }
}
