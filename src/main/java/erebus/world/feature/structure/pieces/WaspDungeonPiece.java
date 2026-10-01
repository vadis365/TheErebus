package erebus.world.feature.structure.pieces;

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
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class WaspDungeonPiece extends ScatteredFeaturePiece implements TerrainCheckedPiece {

    private final Set<Long> processedChunks = new HashSet<>();
    private BlockState BLOCK;
    private BlockState STAIR;
    private BlockState SPAWNER;
    private boolean validated, rejected;

    public WaspDungeonPiece(BlockPos top) {
        super(ModStructurePieces.WASP_DUNGEON.get(), top.getX() - 7, top.getY() - 14, top.getZ() - 7, 15, 15, 15, Direction.SOUTH);
    }

    public WaspDungeonPiece(StructurePieceSerializationContext context, CompoundTag tag) {
        super(ModStructurePieces.WASP_DUNGEON.get(), tag);
        validated = tag.getBooleanOr("Validated", false);
        rejected = tag.getBooleanOr("Rejected", false) || tag.getIntOr("LayoutVersion", 0) != 1;
        for (long chunk : tag.getLongArray("ProcessedChunks").orElse(new long[0])) processedChunks.add(chunk);
    }

    private static boolean ceiling(BlockState state) {
        return state == ModBlocks.UMBERSTONE.get().defaultBlockState();
    }

    public static boolean validSite(BlockPos top, Function<BlockPos, BlockState> blocks,
                                    Predicate<BlockPos> inBounds) {
        // A solid attachment above the cap prevents detached nests; retain the
        // reference umberstone anchor two layers below the top as well.
        for (int x = -1; x <= 1; x++)
            for (int z = -1; z <= 1; z++) {
                var p = top.offset(x, 1, z);
                if (!inBounds.test(p) || !ceiling(blocks.apply(p))) return false;
            }
        if (!inBounds.test(top.below(2)) || !ceiling(blocks.apply(top.below(2)))) return false;
        for (int x = -7; x <= 7; x++)
            for (int z = -7; z <= 7; z++)
                for (int depth = 0; depth <= 16; depth++) {
                    var p = top.offset(x, -depth, z);
                    if (!inBounds.test(p)) return false;
                    var state = blocks.apply(p);
                    if (!state.isAir() && !(depth < 8 && ceiling(state))) return false;
                }
        return true;
    }

    @Override
    public synchronized boolean isRejected() {
        return rejected;
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag) {
        super.addAdditionalSaveData(context, tag);
        tag.putInt("LayoutVersion", 1);
        tag.putBoolean("Validated", validated);
        tag.putBoolean("Rejected", rejected);
        tag.putLongArray("ProcessedChunks", processedChunks.stream().mapToLong(Long::longValue).toArray());
    }

    public BlockPos top() {
        return new BlockPos(boundingBox.minX() + 7, boundingBox.maxY(), boundingBox.minZ() + 7);
    }

    @Override
    public synchronized void postProcess(WorldGenLevel level, StructureManager manager, ChunkGenerator generator,
                                         RandomSource random, BoundingBox clip, ChunkPos chunk, BlockPos ignored) {
        long key = ((long) chunk.getMinBlockX() << 32) ^ (chunk.getMinBlockZ() & 0xffffffffL);
        if (rejected || processedChunks.contains(key)) return;
        if (!validated) {
            if (!validSite(top(), level::getBlockState, p -> !level.isOutsideBuildHeight(p))) {
                rejected = true;
                return;
            }
            validated = true;
        }
        for (var entry : layout().entrySet()) {
            var p = entry.getKey();
            if (!clip.isInside(p)) continue;
            var current = level.getBlockState(p);
            if (!current.isAir() && !(p.getY() > top().getY() - 8 && ceiling(current))) continue;
            if (level.setBlock(p, entry.getValue(), Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE)
                    && entry.getValue().getBlock() instanceof StairBlock) level.getChunk(p).markPosForPostprocessing(p);
        }
        processedChunks.add(key);
    }

    private void setupBlockStates() {
        BLOCK = ModBlocks.WASP_NEST.get().defaultBlockState();
        STAIR = ModBlocks.STAIRS_WASP_NEST.get().defaultBlockState();
        SPAWNER = ModBlocks.WASP_SPAWNER.get().defaultBlockState();
    }

    public synchronized Map<BlockPos, BlockState> layout() {
        setupBlockStates();
        var level = new LinkedHashMap<BlockPos, BlockState>();
        var pos = top();
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();

        // Layer 0 (starting from the top)

        rect(level, BLOCK, x - 1, z - 1, x + 1, z + 1, y);
        --y;

        // Layer 1

        for (int a = 0; a < 2; a++) {
            lineX(level, BLOCK, x - 1 - a, x + 1 + a, z - 3 + a, y);
            lineX(level, BLOCK, x - 1 - a, x + 1 + a, z + 3 - a, y);
        }
        rect(level, BLOCK, x - 3, z - 1, x - 1, z + 1, y);
        rect(level, BLOCK, x + 1, z - 1, x + 3, z + 1, y);
        for (int a = 0; a < 2; a++) {
            lineX(level, getStairRotation(STAIR, 4 + (a == 0 ? 3 : 2)), x - 1, x + 1, z - 1 + 2 * a, y);
            block(level, getStairRotation(STAIR, 4 + (a == 0 ? 1 : 0)), x - 1 + 2 * a, z, y);
        }
        block(level, Blocks.AIR.defaultBlockState(), x, z, y);
        --y;

        // Layer 2

        for (int a = 0; a < 2; a++) {
            lineX(level, BLOCK, x - 1, x + 1, z - 5 + 10 * a, y);
            lineX(level, BLOCK, x - 3, x + 3, z - 4 + 8 * a, y);
            lineX(level, BLOCK, x - 4, x - 2, z - 3 + 6 * a, y);
            lineX(level, BLOCK, x + 2, x + 4, z - 3 + 6 * a, y);
            lineX(level, BLOCK, x - 4, x - 3, z - 2 + 4 * a, y);
            lineX(level, BLOCK, x + 3, x + 4, z - 2 + 4 * a, y);
            lineZ(level, Blocks.AIR.defaultBlockState(), z - 1, z + 1, x - 3 + 6 * a, y);
            lineZ(level, Blocks.AIR.defaultBlockState(), z - 2, z + 2, x - 2 + 4 * a, y);
        }
        rect(level, BLOCK, x - 5, z - 1, x - 4, z + 1, y);
        rect(level, BLOCK, x + 4, z - 1, x + 5, z + 1, y);
        rect(level, Blocks.AIR.defaultBlockState(), x - 1, z - 3, x + 1, z + 3, y);
        --y;

        // Layer 3

        for (int a = 0; a < 2; a++) {
            lineX(level, BLOCK, x - 3, x + 3, z - 5 + 10 * a, y);
            lineZ(level, BLOCK, z - 3, z + 3, x - 5 + 10 * a, y);
            for (int b = 0; b < 2; b++)
                block(level, BLOCK, x - 4 + 8 * a, z - 4 + 8 * b, y);
            lineZ(level, Blocks.AIR.defaultBlockState(), z - 3, z + 3, x - 4 + 8 * a, y);
        }
        rect(level, Blocks.AIR.defaultBlockState(), x - 3, z - 4, x + 3, z + 4, y);
        --y;

        // Layer 4

        for (int a = 0; a < 2; a++) {
            lineX(level, BLOCK, x - 1, x + 1, z - 6 + 12 * a, y);
            lineX(level, BLOCK, x - 4, x - 2, z - 5 + 10 * a, y);
            lineX(level, BLOCK, x + 2, x + 4, z - 5 + 10 * a, y);
            lineX(level, BLOCK, x - 5, x - 4, z - 4 + 8 * a, y);
            lineX(level, BLOCK, x + 4, x + 5, z - 4 + 8 * a, y);
            lineZ(level, BLOCK, z - 3, z - 1, x - 5 + 10 * a, y);
            lineZ(level, BLOCK, z + 1, z + 3, x - 5 + 10 * a, y);
            lineZ(level, BLOCK, z - 1, z + 1, x - 6 + 12 * a, y);
            lineZ(level, Blocks.AIR.defaultBlockState(), z - 1, z + 1, x - 5 + 10 * a, y);
            lineZ(level, Blocks.AIR.defaultBlockState(), z - 3, z + 3, x - 4 + 8 * a, y);
            lineX(level, Blocks.AIR.defaultBlockState(), x - 1, x + 1, z - 5 + 10 * a, y);
        }
        rect(level, Blocks.AIR.defaultBlockState(), x - 3, z - 4, x + 3, z + 4, y);
        --y;

        // Layer 5

        for (int a = 0; a < 2; a++) {
            lineX(level, BLOCK, x - 2, x + 2, z - 6 + 12 * a, y);
            lineX(level, BLOCK, x - 4, x - 3, z - 5 + 10 * a, y);
            lineX(level, BLOCK, x + 3, x + 4, z - 5 + 10 * a, y);
            lineZ(level, BLOCK, z - 4, z - 3, x - 5 + 10 * a, y);
            lineZ(level, BLOCK, z + 3, z + 4, x - 5 + 10 * a, y);
            lineZ(level, BLOCK, z - 2, z + 2, x - 6 + 12 * a, y);
            lineX(level, Blocks.AIR.defaultBlockState(), x - 2, x + 2, z - 5 + 10 * a, y);
            lineZ(level, Blocks.AIR.defaultBlockState(), z - 2, z + 2, x - 5 + 10 * a, y);
        }
        rect(level, Blocks.AIR.defaultBlockState(), x - 4, z - 4, x + 4, z + 4, y);
        --y;

        // Layer 6,7,8

        for (int layer = 0; layer < 3; layer++) {
            for (int a = 0; a < 2; a++) {
                lineX(level, Blocks.AIR.defaultBlockState(), x - 3, x + 3, z - 5 + 10 * a, y);
                lineZ(level, Blocks.AIR.defaultBlockState(), z - 3, z + 3, x - 5 + 10 * a, y);
                lineX(level, BLOCK, x - 1, x + 1, z - 7 + 14 * a, y);
                lineX(level, BLOCK, x - 3, x - 2, z - 6 + 12 * a, y);
                lineX(level, BLOCK, x - 1, x + 1, z - 6 + 12 * a, y);
                lineX(level, BLOCK, x + 2, x + 3, z - 6 + 12 * a, y);
                lineX(level, BLOCK, x - 5, x - 4, z - 5 + 10 * a, y);
                lineX(level, BLOCK, x + 4, x + 5, z - 5 + 10 * a, y);
                block(level, BLOCK, x - 5, z - 4 + 8 * a, y);
                block(level, BLOCK, x + 5, z - 4 + 8 * a, y);
                lineZ(level, BLOCK, z - 3, z - 2, x - 6 + 12 * a, y);
                lineZ(level, BLOCK, z - 1, z + 1, x - 6 + 12 * a, y);
                lineZ(level, BLOCK, z + 2, z + 3, x - 6 + 12 * a, y);
                lineZ(level, BLOCK, z - 1, z + 1, x - 7 + 14 * a, y);
            }
            rect(level, Blocks.AIR.defaultBlockState(), x - 4, z - 4, x + 4, z + 4, y);
            --y;
        }

        block(level, SPAWNER, x, z, y + 3);

        // Layer 9 (copied 5)

        for (int a = 0; a < 2; a++) {
            lineX(level, BLOCK, x - 2, x + 2, z - 6 + 12 * a, y);
            lineX(level, BLOCK, x - 4, x - 3, z - 5 + 10 * a, y);
            lineX(level, BLOCK, x + 3, x + 4, z - 5 + 10 * a, y);
            lineZ(level, BLOCK, z - 4, z - 3, x - 5 + 10 * a, y);
            lineZ(level, BLOCK, z + 3, z + 4, x - 5 + 10 * a, y);
            lineZ(level, BLOCK, z - 2, z + 2, x - 6 + 12 * a, y);
            lineX(level, Blocks.AIR.defaultBlockState(), x - 2, x + 2, z - 5 + 10 * a, y);
            lineZ(level, Blocks.AIR.defaultBlockState(), z - 2, z + 2, x - 5 + 10 * a, y);
        }
        rect(level, Blocks.AIR.defaultBlockState(), x - 4, z - 4, x + 4, z + 4, y);
        --y;

        // Layer 10 (copied 4)

        for (int a = 0; a < 2; a++) {
            lineX(level, BLOCK, x - 1, x + 1, z - 6 + 12 * a, y);
            lineX(level, BLOCK, x - 4, x - 2, z - 5 + 10 * a, y);
            lineX(level, BLOCK, x + 2, x + 4, z - 5 + 10 * a, y);
            lineX(level, BLOCK, x - 5, x - 4, z - 4 + 8 * a, y);
            lineX(level, BLOCK, x + 4, x + 5, z - 4 + 8 * a, y);
            lineZ(level, BLOCK, z - 3, z - 1, x - 5 + 10 * a, y);
            lineZ(level, BLOCK, z + 1, z + 3, x - 5 + 10 * a, y);
            lineZ(level, BLOCK, z - 1, z + 1, x - 6 + 12 * a, y);
            lineZ(level, Blocks.AIR.defaultBlockState(), z - 1, z + 1, x - 5 + 10 * a, y);
            lineZ(level, Blocks.AIR.defaultBlockState(), z - 3, z + 3, x - 4 + 8 * a, y);
            lineX(level, Blocks.AIR.defaultBlockState(), x - 1, x + 1, z - 5 + 10 * a, y);
        }
        rect(level, Blocks.AIR.defaultBlockState(), x - 3, z - 4, x + 3, z + 4, y);
        --y;

        // Layer 11 (copied 3)

        for (int a = 0; a < 2; a++) {
            lineX(level, BLOCK, x - 3, x + 3, z - 5 + 10 * a, y);
            lineZ(level, BLOCK, z - 3, z + 3, x - 5 + 10 * a, y);
            for (int b = 0; b < 2; b++)
                block(level, BLOCK, x - 4 + 8 * a, z - 4 + 8 * b, y);
            lineZ(level, Blocks.AIR.defaultBlockState(), z - 3, z + 3, x - 4 + 8 * a, y);
        }
        rect(level, Blocks.AIR.defaultBlockState(), x - 3, z - 4, x + 3, z + 4, y);
        --y;

        // Layer 12

        for (int a = 0; a < 2; a++) {
            lineX(level, BLOCK, x - 2, x + 2, z - 5 + 10 * a, y);
            lineX(level, BLOCK, x - 3, x + 3, z - 4 + 8 * a, y);
            lineZ(level, BLOCK, z - 2, z + 2, x - 5 + 10 * a, y);
            lineZ(level, BLOCK, z - 3, z + 3, x - 4 + 8 * a, y);
        }
        rect(level, Blocks.AIR.defaultBlockState(), x - 3, z - 3, x + 3, z + 3, y);
        --y;

        // Layer 13

        for (int a = 0; a < 2; a++) {
            lineX(level, getStairRotation(STAIR, 4 + (a == 0 ? 2 : 3)), x - 3, x + 3, z - 4 + 8 * a, y);
            lineZ(level, getStairRotation(STAIR, 4 + (a == 0 ? 0 : 1)), z - 3, z + 3, x - 4 + 8 * a, y);
            lineX(level, BLOCK, x - 3, x + 3, z - 3 + 6 * a, y);
            lineZ(level, BLOCK, z - 2, z + 2, x - 3 + 6 * a, y);
        }
        rect(level, Blocks.AIR.defaultBlockState(), x - 2, z - 2, x + 2, z + 2, y);
        --y;

        // Layer 14

        for (int a = 0; a < 2; a++) {
            lineX(level, getStairRotation(STAIR, 4 + (a == 0 ? 3 : 2)), x - 3, x + 3, z - 3 + 6 * a, y);
            lineZ(level, getStairRotation(STAIR, 4 + (a == 0 ? 1 : 0)), z - 2, z + 2, x - 3 + 6 * a, y);
        }
        rect(level, Blocks.AIR.defaultBlockState(), x - 2, z - 2, x + 2, z + 2, y);
        return Collections.unmodifiableMap(level);
    }

    private void rect(Map<BlockPos, BlockState> level, BlockState state, int x1, int z1, int x2, int z2, int y) {
        for (int x = x1; x <= x2; x++) {
            for (int z = z1; z <= z2; z++) {
                level.put(new BlockPos(x, y, z), state);
            }
        }
    }

    private void lineX(Map<BlockPos, BlockState> level, BlockState state, int x1, int x2, int z, int y) {
        for (int x = x1; x <= x2; x++) {
            level.put(new BlockPos(x, y, z), state);
        }
    }

    private void lineZ(Map<BlockPos, BlockState> level, BlockState state, int z1, int z2, int x, int y) {
        for (int z = z1; z <= z2; z++) {
            level.put(new BlockPos(x, y, z), state);
        }
    }

    private void block(Map<BlockPos, BlockState> level, BlockState state, int x, int z, int y) {
        level.put(new BlockPos(x, y, z), state);
    }

    private BlockState getStairRotation(BlockState state, int direction) {
        return switch (direction) {
            case 0 -> state.setValue(StairBlock.FACING, Direction.EAST);
            case 1 -> state.setValue(StairBlock.FACING, Direction.WEST);
            case 2 -> state.setValue(StairBlock.FACING, Direction.SOUTH);
            case 3 -> state.setValue(StairBlock.FACING, Direction.NORTH);
            case 4 -> state.setValue(StairBlock.FACING, Direction.EAST).setValue(StairBlock.HALF, Half.TOP);
            case 5 -> state.setValue(StairBlock.FACING, Direction.WEST).setValue(StairBlock.HALF, Half.TOP);
            case 6 -> state.setValue(StairBlock.FACING, Direction.SOUTH).setValue(StairBlock.HALF, Half.TOP);
            case 7 -> state.setValue(StairBlock.FACING, Direction.NORTH).setValue(StairBlock.HALF, Half.TOP);
            default -> state;
        };
    }
}
