package erebus.block.portal;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModPOIs;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

public class ErebusPortalForcer {

    private static final Map<ServerLevel, LongSet> indexedLegacyChunks = new WeakHashMap<>();
    private static final byte F = 1, L = 2, END = -1;
    private static final byte[] portalFrame = new byte[]{
            0, F, F, F, 0, END,
            F, L, L, L, F, END,
            F, L, L, L, F, END,
            F, L, L, L, F, END,
            0, F, F, F, 0, END,
    };
    protected final ServerLevel level;

    public ErebusPortalForcer(ServerLevel level) {
        this.level = level;
    }

    public static Optional<BlockPos> findClosestKeystone(ServerLevel level, BlockPos exitPos, WorldBorder border) {
        PoiManager poiManager = level.getPoiManager();
        int scale = 128;
        poiManager.ensureLoadedAndValid(level, exitPos, scale);
        restoreLegacyKeystones(level, exitPos);
        return poiManager.getInSquare(type -> type.is(ModPOIs.GAEAN_KEYSTONE), exitPos, scale, PoiManager.Occupancy.ANY)
                .map(PoiRecord::getPos)
                .filter(border::isWithinBounds)
                .filter(pos -> pos.distSqr(exitPos) < 128 * 128)
                .filter(pos -> level.getBlockState(pos).is(ModBlocks.GAEAN_KEYSTONE))
                .min(Comparator.<BlockPos>comparingDouble(pos -> pos.distSqr(exitPos)).thenComparingInt(Vec3i::getY));
    }

    private static void restoreLegacyKeystones(ServerLevel level, BlockPos center) {
        LongSet inspected = indexedLegacyChunks.computeIfAbsent(level, ignored -> new LongOpenHashSet());
        PoiManager pois = level.getPoiManager();
        ChunkPos chunkCenter = ChunkPos.containing(center);
        for (int x = chunkCenter.x() - 8; x <= chunkCenter.x() + 8; x++) {
            for (int z = chunkCenter.z() - 8; z <= chunkCenter.z() + 8; z++) {
                ChunkAccess chunk = level.getChunkSource().getChunkNow(x, z);
                if (chunk == null) {
                    if (!inspected.add(ChunkPos.pack(x, z))) continue;
                    chunk = level.getChunk(x, z, ChunkStatus.EMPTY);
                }
                for (BlockPos pos : chunk.getBlockEntitiesPos()) {
                    if (chunk.getBlockState(pos).is(ModBlocks.GAEAN_KEYSTONE)
                            && !pois.exists(pos, type -> type.is(ModPOIs.GAEAN_KEYSTONE))) {
                        pois.add(pos, ModPOIs.GAEAN_KEYSTONE);
                    }
                }
            }
        }
    }

    public static @Nullable TeleportTransition getDestination(ServerLevel level, Entity entity, BlockPos exitPos) {
        Optional<BlockPos> anchor = findClosestKeystone(level, exitPos, level.getWorldBorder());
        if (anchor.isEmpty()) anchor = createPortal(level, exitPos, Axis.X);
        if (anchor.isEmpty()) return null;

        Vec3 arrival = anchor.get().above().getBottomCenter();
        var dimensions = entity.getDimensions(entity.getPose());

        while (arrival.y + dimensions.height() <= level.getMaxY() + 1) {
            var bounds = dimensions.makeBoundingBox(arrival);
            if (level.getWorldBorder().isWithinBounds(bounds) && level.noCollision(entity, bounds)
                    && !level.containsAnyLiquid(bounds)) {
                return new TeleportTransition(level, arrival, Vec3.ZERO, entity.getYRot(), entity.getXRot(),
                        TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET));
            }
            arrival = arrival.add(0, 1, 0);
        }
        return null;
    }

    public static Optional<BlockPos> createPortal(ServerLevel level, BlockPos pos, Axis axis) {
        if (axis == Axis.Y) return Optional.empty();
        WorldBorder border = level.getWorldBorder();
        int top = Math.min(level.getMaxY(), level.getMinY() + level.getLogicalHeight() - 1);
        BlockPos base = null;
        double closest = Double.POSITIVE_INFINITY;
        MutableBlockPos cursor = new MutableBlockPos();

        for (MutableBlockPos column : BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH)) {
            int startY = Math.min(top - 4, level.getHeight(Heightmap.Types.MOTION_BLOCKING, column.getX(), column.getZ()));
            for (int y = startY; y > level.getMinY(); y--) {
                cursor.set(column.getX(), y, column.getZ());
                if (!canPortalReplaceBlock(level, cursor)) continue;
                while (y > level.getMinY() && canPortalReplaceBlock(level, cursor.below())) {
                    cursor.setY(--y);
                }
                if (!fitsWorld(level, border, cursor, top) || !canHostFrame(level, cursor)) continue;
                double distance = pos.distSqr(cursor);
                if (distance < closest) {
                    closest = distance;
                    base = cursor.immutable();
                }
            }
        }

        if (base == null) {
            int bottom = Math.clamp(top - 4, level.getMinY() + 1, 32);
            if (bottom > top - 4) return Optional.empty();
            int minX = Mth.ceil(border.getMinX());
            int minZ = Mth.ceil(border.getMinZ());
            int maxX = Mth.floor(border.getMaxX()) - 5;
            int maxZ = Mth.floor(border.getMaxZ()) - 5;
            if (maxX < minX || maxZ < minZ) return Optional.empty();
            base = new BlockPos(Mth.clamp(pos.getX(), minX, maxX),
                    Mth.clamp(pos.getY(), bottom, Math.clamp(top - 4, bottom, 70)), Mth.clamp(pos.getZ(), minZ, maxZ));
            if (!fitsWorld(level, border, base, top)) return Optional.empty();

            for (BlockPos check : BlockPos.betweenClosed(base.below(), base.offset(4, 4, 4))) {
                BlockState state = level.getBlockState(check);
                if (state.hasBlockEntity() || state.getDestroySpeed(level, check) < 0) return Optional.empty();
            }
            for (BlockPos check : BlockPos.betweenClosed(base, base.offset(4, 4, 4))) {
                level.setBlockAndUpdate(check, Blocks.AIR.defaultBlockState());
            }
            for (int x = 0; x < 5; x++)
                for (int z = 0; z < 5; z++) {
                    level.setBlockAndUpdate(base.offset(x, -1, z), ModBlocks.UMBERSTONE.get().defaultBlockState());
                }
        }

        int width = 0, height = 0;
        for (byte part : portalFrame) {
            if (part == END) {
                height++;
                width = 0;
                continue;
            }
            if (part == F || part == L) {
                BlockState state = part == F
                        ? (level.getRandom().nextBoolean() ? ModBlocks.UMBERTILE_SMOOTH : ModBlocks.UMBERTILE_SMOOTH_SMALL).get().defaultBlockState()
                        : level.getDifficulty() == Difficulty.HARD ? Blocks.AIR.defaultBlockState()
                        : Blocks.OAK_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true);
                level.setBlockAndUpdate(offset(base, axis, width, height, 0), state);
            }
            width++;
        }
        BlockPos anchor = offset(base, axis, 2, 0, 3);
        level.setBlockAndUpdate(anchor, ModBlocks.GAEAN_KEYSTONE.get().defaultBlockState());
        return Optional.of(anchor);
    }

    private static BlockPos offset(BlockPos base, Axis axis, int width, int height, int depth) {
        return axis == Axis.X ? base.offset(width, height, depth) : base.offset(depth, height, width);
    }

    private static boolean fitsWorld(ServerLevel level, WorldBorder border, BlockPos base, int top) {
        return base.getY() > level.getMinY() && base.getY() + 4 <= top
                && border.isWithinBounds(base) && border.isWithinBounds(base.offset(4, 0, 4));
    }

    private static boolean canPortalReplaceBlock(ServerLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.canBeReplaced() && state.getFluidState().isEmpty() && !state.hasBlockEntity();
    }

    private static boolean canHostFrame(ServerLevel level, BlockPos base) {
        for (int x = 0; x < 5; x++)
            for (int z = 0; z < 5; z++) {
                BlockPos floor = base.offset(x, -1, z);
                if (!level.getBlockState(floor).isCollisionShapeFullBlock(level, floor)) return false;
                for (int y = 0; y < 5; y++) {
                    if (!canPortalReplaceBlock(level, base.offset(x, y, z))) return false;
                }
            }
        return true;
    }
}
