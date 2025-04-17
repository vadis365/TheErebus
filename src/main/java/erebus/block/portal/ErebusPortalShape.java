package erebus.block.portal;

import erebus.registries.ModBlocks;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;

public class ErebusPortalShape {
    public static final int MIN_WIDTH = 2;
    public static final int MIN_HEIGHT = 3;
    public static final int MAX_WIDTH = 21;
    public static final int MAX_HEIGHT = 21;

    private static final BlockBehaviour.StatePredicate FRAME = ErebusPortalShape::isPortalFrame;

    private static final float SAFE_TRAVEL_MAX_ENTITY_XY = 4.0F;
    private static final double SAFE_TRAVEL_MAX_VERTICAL_DELTA = 1.0F;

    private final LevelAccessor level;
    private final BlockPos bottomLeft;
    private final Direction.Axis axis;
    private final Direction rightDir;
    private final int width;
    private int numPortalBlocks;
    private int height;

    public ErebusPortalShape(LevelAccessor level, BlockPos bottomLeft, Direction.Axis axis) {
        this.level = level;
        this.axis = axis;
        this.rightDir = axis == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;
        this.bottomLeft = calculateBottomLeft(bottomLeft);

        if (bottomLeft == null) {
            this.width = 1;
            this.height = 1;
        } else {
            this.width = calculateWidth();
            if (width > 0) this.height = calculateHeight();
        }
    }

    public static Vec3 getRelativePosition(BlockUtil.FoundRectangle foundRectangle, Direction.Axis axis, Vec3 pos, EntityDimensions dimensions) {
        double width = foundRectangle.axis1Size - dimensions.width();
        double height = foundRectangle.axis2Size - dimensions.height();
        BlockPos blockPos = foundRectangle.minCorner;

        double adjustedWidth;
        double adjustedHeight;

        if (width > 0.0F) {
            double offset = blockPos.get(axis) + dimensions.width() / 2;
            adjustedWidth = Mth.clamp(Mth.inverseLerp(pos.get(axis) - offset, 0, width), 0, SAFE_TRAVEL_MAX_VERTICAL_DELTA);
        } else {
            adjustedWidth = 0.5D;
        }

        if (height > 0) {
            Direction.Axis y = Direction.Axis.Y;
            adjustedHeight = Mth.clamp(Mth.inverseLerp(pos.get(y) - (double) blockPos.getY(), 0, height), 0, SAFE_TRAVEL_MAX_VERTICAL_DELTA);
        } else {
            adjustedHeight = 0;
        }

        Direction.Axis opposite = axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
        return new Vec3(adjustedWidth, adjustedHeight, pos.get(opposite) - (double) blockPos.get(opposite) + 0.5);
    }

    public static Vec3 findCollisionFreePosition(Vec3 pos, ServerLevel level, Entity entity, EntityDimensions dimensions) {
        if (!(dimensions.width() > SAFE_TRAVEL_MAX_ENTITY_XY) && !(dimensions.height() > SAFE_TRAVEL_MAX_ENTITY_XY)) {
            double halfHeight = dimensions.height() / 2.0D;
            Vec3 adjustedPos = pos.add(0, halfHeight, 0);
            VoxelShape shape = Shapes.create(AABB.ofSize(adjustedPos, dimensions.width(), 0, dimensions.width()).expandTowards(0, SAFE_TRAVEL_MAX_VERTICAL_DELTA, 0).inflate(-1.0E-6));
            Optional<Vec3> optional = level.findFreePosition(entity, shape, adjustedPos, dimensions.width(), dimensions.height(), dimensions.width());
            return optional.map(vec3 -> vec3.subtract(0, halfHeight, 0)).orElse(pos);
        }

        return pos;
    }

    private static boolean isEmpty(BlockState state) {
        return state.isAir() || state.is(BlockTags.FIRE) || state.is(ModBlocks.PORTAL);
    }

    public static boolean isPortalFrame(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.STONE_BRICKS);
    }

    private BlockPos calculateBottomLeft(BlockPos pos) {
        Direction direction = rightDir.getOpposite();
        int distance = getDistanceUntilEdgeAboveFrame(pos, direction) - 1;
        return distance < 0 ? null : pos.relative(direction, distance);
    }

    private int getDistanceUntilEdgeAboveFrame(BlockPos pos, Direction direction) {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for (int c = 0; c <= MAX_WIDTH; c++) {
            mutableBlockPos.set(pos).move(direction, c);
            BlockState state = level.getBlockState(mutableBlockPos);
            if (!isEmpty(state)) {
                if (FRAME.test(state, level, mutableBlockPos)) {
                    return c;
                }
                break;
            }

            BlockState below = level.getBlockState(mutableBlockPos.below());
            if (!FRAME.test(below, level, mutableBlockPos.below())) {
                break;
            }
        }

        return 0;
    }

    private int getDistanceUntilTop(BlockPos.MutableBlockPos pos) {
        for (int c = 0; c < MAX_HEIGHT; c++) {
            pos.set(bottomLeft).move(Direction.UP, c).move(rightDir, -1);
            if (!FRAME.test(level.getBlockState(pos), level, pos)) {
                return c;
            }

            pos.set(bottomLeft).move(Direction.UP, c).move(rightDir, width);
            if (!FRAME.test(level.getBlockState(pos), level, pos)) {
                return c;
            }

            for (int d = 0; d < width; d++) {
                pos.set(bottomLeft).move(Direction.UP, c).move(rightDir, d);
                BlockState state = level.getBlockState(pos);
                if (!isEmpty(state)) {
                    return c;
                }

                if (state.is(ModBlocks.PORTAL)) {
                    ++numPortalBlocks;
                }
            }
        }

        return MAX_HEIGHT;
    }

    private boolean hasTopFrame(BlockPos.MutableBlockPos pos, int distanceToTop) {
        for (int c = 0; c < width; c++) {
            BlockPos.MutableBlockPos mutableBlockPos = pos.set(bottomLeft).move(Direction.UP, distanceToTop).move(rightDir, c);
            if (!FRAME.test(level.getBlockState(mutableBlockPos), level, mutableBlockPos)) {
                return false;
            }
        }
        return true;
    }

    private int calculateHeight() {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int distance = getDistanceUntilTop(pos);
        return distance >= MIN_HEIGHT && hasTopFrame(pos, distance) ? distance : 0;
    }

    private int calculateWidth() {
        int distance = getDistanceUntilEdgeAboveFrame(bottomLeft, rightDir);
        return distance >= MIN_WIDTH && distance <= MAX_WIDTH ? distance : 0;
    }

    public void createPortalBlocks() {
        BlockState state = ModBlocks.PORTAL.get().defaultBlockState().setValue(ErebusPortalBlock.AXIS, axis);
        BlockPos.betweenClosed(bottomLeft, bottomLeft.relative(Direction.UP, height - 1).relative(rightDir, width - 1))
                .forEach(pos -> level.setBlock(pos, state, 18));
    }

    public boolean isValid() {
        return bottomLeft != null && width >= MIN_WIDTH && width <= MAX_WIDTH && height >= MIN_HEIGHT && height <= MAX_HEIGHT;
    }

    public boolean isComplete() {
        return isValid() && numPortalBlocks == width * height;
    }
}
