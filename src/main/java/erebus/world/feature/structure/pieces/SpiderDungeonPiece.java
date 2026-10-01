package erebus.world.feature.structure.pieces;

import erebus.datagen.loot.ModChestLootTables;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.structure.ModStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import org.jspecify.annotations.NonNull;

import java.util.*;
import java.util.function.Function;

public class SpiderDungeonPiece extends ScatteredFeaturePiece implements TerrainCheckedPiece {
    private final int halfX, halfZ;
    private final long layoutSeed;
    private final Set<Long> processedChunks = new HashSet<>();
    private boolean rejected;
    private byte[] plan;

    public SpiderDungeonPiece(BlockPos center, int halfX, int halfZ, long seed) {
        super(ModStructurePieces.SPIDER_DUNGEON.get(), center.getX() - halfX - 1, center.getY() - 1, center.getZ() - halfZ - 1, halfX * 2 + 3, 7, halfZ * 2 + 3, Direction.SOUTH);
        this.halfX = halfX;
        this.halfZ = halfZ;
        this.layoutSeed = seed;
    }

    public SpiderDungeonPiece(StructurePieceSerializationContext context, CompoundTag tag) {
        super(ModStructurePieces.SPIDER_DUNGEON.get(), tag);
        halfX = tag.getIntOr("HalfX", 4);
        halfZ = tag.getIntOr("HalfZ", 4);
        layoutSeed = tag.getLongOr("LayoutSeed", 0);
        rejected = tag.getBooleanOr("Rejected", false) || tag.getIntOr("LayoutVersion", 0) != 1
                || halfX < 4 || halfX > 7 || halfZ < 4 || halfZ > 7;
        plan = tag.getByteArray("Plan").orElse(null);
        if (plan != null && plan.length != (halfX * 2 + 3) * 7 * (halfZ * 2 + 3)) rejected = true;
        if (plan != null) for (byte cell : plan)
            if (cell < 0 || cell > 12) {
                rejected = true;
                break;
            }
        for (long key : tag.getLongArray("ProcessedChunks").orElse(new long[0])) processedChunks.add(key);
    }

    public static boolean validSite(BlockPos center, int halfX, int halfZ, int minY, int maxY, Function<BlockPos, BlockState> blocks) {
        if (halfX < 4 || halfX > 7 || halfZ < 4 || halfZ > 7 || center.getY() - 1 < minY
                || center.getY() + 5 >= Math.min(maxY, minY + 127)) return false;
        int openings = 0;
        // Noise columns are expensive during structure-start selection. Check the boundary first:
        // solid rock with no cave entrance never needs its interior floor/ceiling sampled.
        for (int x = -halfX - 1; x <= halfX + 1; x++)
            for (int z = -halfZ - 1; z <= halfZ + 1; z++) {
                if (Math.abs(x) != halfX + 1 && Math.abs(z) != halfZ + 1) continue;
                if (!blocks.apply(center.offset(x, -1, z)).isSolid() || !blocks.apply(center.offset(x, 5, z)).isSolid()) return false;
                if (blocks.apply(center.offset(x, 0, z)).isAir() && blocks.apply(center.offset(x, 1, z)).isAir()
                        && ++openings > 5) return false;
            }
        if (openings == 0) return false;
        for (int x = -halfX; x <= halfX; x++)
            for (int z = -halfZ; z <= halfZ; z++)
                if (!blocks.apply(center.offset(x, -1, z)).isSolid() || !blocks.apply(center.offset(x, 5, z)).isSolid()) return false;
        return true;
    }

    private static BlockState state(int code) {
        return switch (code) {
            case 1 -> Blocks.AIR.defaultBlockState();
            case 2 -> ModBlocks.UMBERCOBBLE.get().defaultBlockState();
            case 3 -> ModBlocks.UMBERCOBBLE_WEBBED.get().defaultBlockState();
            case 4 -> ModBlocks.UMBERCOBBLE_MOSSY.get().defaultBlockState();
            case 5, 6, 7, 8 -> Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING,
                    new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST}[code - 5]);
            case 9 -> Blocks.COBWEB.defaultBlockState();
            case 10 -> ModBlocks.SPIDER_SPAWNER.get().defaultBlockState();
            case 11 -> ModBlocks.JUMPING_SPIDER_SPAWNER.get().defaultBlockState();
            case 12 -> ModBlocks.TARANTULA_SPAWNER.get().defaultBlockState();
            default -> throw new IllegalArgumentException("Invalid spider room cell " + code);
        };
    }

    private static int chestCode(Direction facing) {
        return switch (facing) {
            case NORTH -> 5;
            case SOUTH -> 6;
            case WEST -> 7;
            case EAST -> 8;
            default -> throw new IllegalArgumentException();
        };
    }

    private static Direction chestFacing(Function<BlockPos, BlockState> blocks, BlockPos pos) {
        if (!blocks.apply(pos).isAir()) return null;
        Direction facing = null;
        for (var direction : Direction.Plane.HORIZONTAL)
            if (blocks.apply(pos.relative(direction)).isSolid()) {
                if (facing != null) return null;
                facing = direction.getOpposite();
            }
        return facing;
    }

    public static List<BlockPos> chestCandidates(WorldGenLevel level, BlockPos center, int halfX, int halfZ) {
        return chestCandidates(level::getBlockState, center, halfX, halfZ).stream().filter(p -> level.getBlockEntity(p) == null).toList();
    }

    private static List<BlockPos> chestCandidates(Function<BlockPos, BlockState> blocks, BlockPos center, int halfX, int halfZ) {
        var result = new ArrayList<BlockPos>();
        for (int x = -halfX; x <= halfX; x++)
            for (int z = -halfZ; z <= halfZ; z++) {
                var p = center.offset(x, 0, z);
                if (chestFacing(blocks, p) != null) result.add(p);
            }
        return result;
    }

    @Override
    public synchronized boolean isRejected() {
        return rejected;
    }

    @Override
    protected void addAdditionalSaveData(@NonNull StructurePieceSerializationContext context, @NonNull CompoundTag tag) {
        super.addAdditionalSaveData(context, tag);
        tag.putInt("LayoutVersion", 1);
        tag.putInt("HalfX", halfX);
        tag.putInt("HalfZ", halfZ);
        tag.putLong("LayoutSeed", layoutSeed);
        tag.putBoolean("Rejected", rejected);
        if (plan != null) tag.putByteArray("Plan", plan);
        tag.putLongArray("ProcessedChunks", processedChunks.stream().mapToLong(Long::longValue).toArray());
    }

    public BlockPos center() {
        return new BlockPos(boundingBox.minX() + halfX + 1, boundingBox.minY() + 1, boundingBox.minZ() + halfZ + 1);
    }

    private int index(int x, int y, int z) {
        return ((x + halfX + 1) * 7 + (y + 1)) * (halfZ * 2 + 3) + z + halfZ + 1;
    }

    private boolean prepare(WorldGenLevel level) {
        var center = center();
        if (!validSite(center, halfX, halfZ, level.getMinY(), level.getMaxY(), level::getBlockState)) return false;
        var random = RandomSource.create(layoutSeed);
        var changes = new HashMap<BlockPos, Integer>();

        Function<BlockPos, BlockState> view = p -> changes.containsKey(p) ? state(changes.get(p)) : level.getBlockState(p);
        for (int x = -halfX - 1; x <= halfX + 1; x++)
            for (int y = 5; y >= -1; y--)
                for (int z = -halfZ - 1; z <= halfZ + 1; z++) {
                    var p = center.offset(x, y, z);
                    if (Math.abs(x) != halfX + 1 && y != -1 && Math.abs(z) != halfZ + 1 && y != 5)
                        changes.put(p, 1);
                    else if (y >= 0 && !view.apply(p.below()).isSolid()) changes.put(p, 1);
                    else if (view.apply(p).isSolid()) {
                        int cell = (y == -1 || y == 5) && random.nextInt(4) == 0 ? 3
                                : (y == -1 || y == 5) && random.nextInt(4) == 0 ? 4 : 2;
                        changes.put(p, cell);
                    }
                }
        var candidates = chestCandidates(view, center, halfX, halfZ);
        if (candidates.isEmpty()) return false;
        var first = candidates.get(random.nextInt(candidates.size()));
        changes.put(first, chestCode(chestFacing(view, first)));
        for (int attempt = 0; attempt < 3; attempt++) {
            var p = center.offset(random.nextInt(halfX * 2 + 1) - halfX, 0, random.nextInt(halfZ * 2 + 1) - halfZ);
            var facing = chestFacing(view, p);
            if (facing != null) {
                changes.put(p, chestCode(facing));
                break;
            }
        }
        for (var direction : Direction.values()) changes.put(center.relative(direction), 9);
        changes.put(center, 10 + random.nextInt(3));
        plan = new byte[(halfX * 2 + 3) * 7 * (halfZ * 2 + 3)];
        changes.forEach((p, code) -> plan[index(p.getX() - center.getX(), p.getY() - center.getY(), p.getZ() - center.getZ())] = code.byteValue());
        return true;
    }

    @Override
    public synchronized void postProcess(@NonNull WorldGenLevel level, @NonNull StructureManager manager, @NonNull ChunkGenerator generator, @NonNull RandomSource random, @NonNull BoundingBox clip, ChunkPos chunk, @NonNull BlockPos ignored) {
        long key = ((long) chunk.getMinBlockX() << 32) ^ (chunk.getMinBlockZ() & 0xffffffffL);
        if (rejected || processedChunks.contains(key)) return;
        if (plan == null && !prepare(level)) {
            rejected = true;
            return;
        }
        var center = center();
        for (int x = -halfX - 1; x <= halfX + 1; x++)
            for (int y = 5; y >= -1; y--)
                for (int z = -halfZ - 1; z <= halfZ + 1; z++) {
                    int code = plan[index(x, y, z)];
                    if (code == 0) continue;
                    var p = center.offset(x, y, z);
                    if (!clip.isInside(p)) continue;
                    var target = state(code);
                    if (level.setBlock(p, target, Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE)
                            && code >= 5 && code <= 8 && level.getBlockEntity(p) instanceof ChestBlockEntity chest)
                        chest.setLootTable(ModChestLootTables.SPIDER_DUNGEON, layoutSeed ^ p.asLong());
                }
        processedChunks.add(key);
    }
}
