package erebus.block.portal;

import erebus.Erebus;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModPOIs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Comparator;
import java.util.Optional;

public class ErebusPortalForcer {

    protected final ServerLevel level;
    private static final byte F = 1, L = 2, END = -1;
    private static final byte[] portalFrame = new byte[]{
            0, F, F, F, 0, END,
            F, L, L, L, F, END,
            F, L, L, L, F, END,
            F, L, L, L, F, END,
            0, F, F, F, 0, END,
    };

    public ErebusPortalForcer(ServerLevel level) {
        this.level = level;
    }

    public static Optional<BlockPos> findClosestPortalPosition(ServerLevel level, BlockPos exitPos, boolean isErebus, WorldBorder border) {
        PoiManager poiManager = level.getPoiManager();
        int scale = isErebus ? 16 : 128;
        poiManager.ensureLoadedAndValid(level, exitPos, scale);
        return poiManager.getInSquare(type -> type.is(ModPOIs.EREBUS_PORTAL), exitPos, scale, PoiManager.Occupancy.ANY)
                .map(PoiRecord::getPos)
                .filter(border::isWithinBounds)
                .filter(pos -> level.getBlockState(pos).hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                .min(Comparator.<BlockPos>comparingDouble(pos -> pos.distSqr(exitPos)).thenComparingInt(Vec3i::getY));
    }

    public static Optional<BlockUtil.FoundRectangle> createPortal(ServerLevel level, BlockPos pos, Axis axis) {
        Direction direction = Direction.get(AxisDirection.POSITIVE, axis);
        double d0 = -1;
        double d1 = -1;
        BlockPos blockPos = null, blockPos1 = null;
        WorldBorder border = level.getWorldBorder();
        int minHeight = Math.min(level.getMaxY(), level.getLogicalHeight() - 1);
        MutableBlockPos mutable = pos.mutable();

        for(MutableBlockPos mut : BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH)) {
            int validStartHeight = Math.min(minHeight, level.getHeight(Heightmap.Types.MOTION_BLOCKING, mut.getX(), mut.getZ()));
            if(border.isWithinBounds(mut) && border.isWithinBounds(mut.move(direction, 1))) {
                mut.move(direction.getOpposite(), 1);

                for(int y = validStartHeight; y >= 0; y--) {
                    mut.setY(y);

                    if(canPortalReplaceBlock(level, mut)) {
                        int y1 = y;

                        while(y > level.getMinY() && canPortalReplaceBlock(level, mut.move(Direction.DOWN))) {
                            y--;
                        }

                        if(y + 4 <= minHeight) {
                            int yDiff = y1 - y;

                            if(yDiff <= 0 || yDiff >= 3) {
                                mut.setY(y);

                                if(canHostFrame(level, mut, mutable, direction, 0)) {
                                    double dist = pos.distSqr(mut);
                                    if(canHostFrame(level, mut, mutable, direction, -1)) {
                                        if(canHostFrame(level, mut, mutable, direction, 1)) {
                                            if(d0 == -1 || d0 > dist) {
                                                d0 = dist;
                                                blockPos = mut.immutable();
                                            }

                                            if (d0 == -1 && (d1 == -1 || d1 > dist)) {
                                                d1 = dist;
                                                blockPos1 = mut.immutable();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if(d0 == -1 && d1 != -1) {
            blockPos = blockPos1;
            d0 = d1;
        }

        if(d0 == -1) {
            int clampHeight = Mth.clamp(pos.getY(), 32, 70);
            int height = minHeight - 9;
            if(height < clampHeight) return Optional.empty();

            blockPos = new BlockPos(pos.getX() - direction.getStepX(), Mth.clamp(pos.getY(), clampHeight, height), pos.getZ() - direction.getStepZ()).immutable();
            blockPos = border.clampToBounds(blockPos);
            Direction dir = direction.getClockWise();

            for(int x = -5; x < 5; x++) {
                for (int z = -5; z < 5; z++) {
                    for (int y = 0; y < ErebusPortalShape.HEIGHT; y++) {
                        Erebus.LOGGER.info("BASE: %s".formatted(blockPos));
                        mutable.setWithOffset(blockPos, z * direction.getStepX() + x * dir.getStepX(), y, z * direction.getStepZ() + x * dir.getStepZ());
                        Erebus.LOGGER.info("Updated: %s".formatted(mutable));
                        level.setBlockAndUpdate(mutable, Blocks.AIR.defaultBlockState());
                    }
                }
            }
        }

        int dx = 0, dy = 0, dz = 0;

        for (byte b : portalFrame) {
            if (b == END) {
                dy++;
                dx = 0;
                continue;
            } else if (b == F || b == L) {
                BlockState state;
                if (b == L) {
                    if (level.getDifficulty() != Difficulty.HARD) {
                        state = Blocks.OAK_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true);
                    } else {
                        state = Blocks.AIR.defaultBlockState();
                    }
                } else {
                    state = level.getRandom().nextBoolean() ? ModBlocks.UMBERTILE_SMOOTH.get().defaultBlockState() : ModBlocks.UMBERTILE_SMOOTH_SMALL.get().defaultBlockState();
                }
                mutable.setWithOffset(blockPos, dx, dy, dz);
                level.setBlock(mutable, state, 3);
            }
            dx++;
        }

        mutable.setWithOffset(blockPos, 0, -1, 0);

        for(int x = 0; x < ErebusPortalShape.WIDTH; x++) {
            for(int z = -1; z < 2; z++) {
                if(axis == Axis.X) {
                    mutable.setWithOffset(blockPos, x, -1, z);
                } else {
                    mutable.setWithOffset(blockPos, z, -1, x);
                }

                if(level.getBlockState(mutable).isAir()) {
                    level.setBlockAndUpdate(mutable, ModBlocks.UMBERSTONE.get().defaultBlockState());
                }
            }
        }

        if(axis == Axis.X) {
            mutable.setWithOffset(blockPos, 2, 0, 3);
        } else {
            mutable.setWithOffset(blockPos, 3, 0, 2);
        }

        level.setBlockAndUpdate(mutable, ModBlocks.GAEAN_KEYSTONE.get().defaultBlockState());
        return Optional.of(new BlockUtil.FoundRectangle(mutable.immutable(), ErebusPortalShape.WIDTH, ErebusPortalShape.HEIGHT));
    }

    private static boolean canPortalReplaceBlock(ServerLevel level, MutableBlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.canBeReplaced() && state.getFluidState().isEmpty();
    }

    private static boolean canHostFrame(ServerLevel level, BlockPos originalPos, MutableBlockPos offsetPos, Direction direction, int offsetScale) {
        Direction clockwise = direction.getClockWise();

        for (int c = -1; c <= 3; c++) {
            for (int d = -1; d <= 3; d++) {
                offsetPos.setWithOffset(originalPos, clockwise.getStepX() * c + clockwise.getStepX() * offsetScale, d, clockwise.getStepZ() * c + clockwise.getStepZ() * offsetScale);

                if (d < 0 && !level.getBlockState(offsetPos).isSolid()) {
                    return false;
                }

                if (d >= 0 && !canPortalReplaceBlock(level, offsetPos)) {
                    return false;
                }
            }
        }

        return true;
    }
}
