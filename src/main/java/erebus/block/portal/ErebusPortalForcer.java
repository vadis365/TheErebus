package erebus.block.portal;

import erebus.registries.ModBlocks;
import erebus.registries.world.ModPOIs;
import erebus.utils.AdvancedBlockPos;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;

import java.util.Comparator;
import java.util.Optional;

public class ErebusPortalForcer {

    protected final ServerLevel level;
    private static final byte F = 1, L = 2, END = -1;
    private static final byte[] portalFrame = new byte[]{0, F, F, F, 0, END, F, L, L, L, F, END, F, L, L, L, F, END, F, L, L, L, F, END, 0, F, F, F, 0, END,};

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

    public static Optional<BlockUtil.FoundRectangle> createPortal(ServerLevel level, BlockPos pos) {
        AdvancedBlockPos keystone;
        BlockPos.MutableBlockPos check = pos.mutable();

        while (level.getBlockState(check).is(Blocks.AIR) && check.getY() > 0) {
            check.move(Direction.DOWN);
        }

        check.move(Direction.UP);
        final int C = 5;
        int r = 5 / 2;

        BlockPos.MutableBlockPos min = check.move(-r, -1, -r);
        BlockPos.MutableBlockPos max = check.move(r, -1, r);
        keystone = new AdvancedBlockPos(level, check);

        keystone.iterateCube(min, max, at -> {
            at.ensureFloored();
            int yMin = at.getY() + 1;
            int yMax = at.getY() + C;

            for (int y = yMin; y <= yMax; y++) {
                level.setBlockAndUpdate(new BlockPos(at.getX(), y, at.getZ()), Blocks.AIR.defaultBlockState());
            }

            return false;
        });

        BlockPos start = min.move(Direction.UP, 2);
        int dx = 0;
        int dy = 0;
        int dz = 0;

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
                    state = level.random.nextBoolean() ? ModBlocks.UMBERTILE_SMOOTH.get().defaultBlockState() : ModBlocks.UMBERTILE_SMOOTH_SMALL.get().defaultBlockState();
                }

                level.setBlock(new BlockPos(start.getX() + dx, start.getY() + dy, start.getZ() + dz), state, 3);
            }
            dx++;
        }

        keystone.add(-2, 3, -3);

        level.setBlockAndUpdate(keystone, ModBlocks.GAEAN_KEYSTONE.get().defaultBlockState());
        return Optional.of(new BlockUtil.FoundRectangle(keystone.immutable(), 5, 5));
    }

    private static boolean canPortalReplaceBlock(ServerLevel level, BlockPos.MutableBlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.canBeReplaced() && state.getFluidState().isEmpty();
    }

    private static boolean canHostFrame(ServerLevel level, BlockPos originalPos, BlockPos.MutableBlockPos offsetPos, Direction direction, int offsetScale) {
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
