package erebus.world.feature.structure.pieces;

import erebus.registries.blocks.providers.OtherBlocks;
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
import org.jetbrains.annotations.NotNull;

public class WaspDungeonPiece extends ScatteredFeaturePiece {

    private final BlockState BLOCK = OtherBlocks.WASP_NEST.get().defaultBlockState();
    private final BlockState STAIR = OtherBlocks.STAIRS_WASP_NEST.get().defaultBlockState();
    private final BlockState SPAWNER = OtherBlocks.WASP_SPAWNER.get().defaultBlockState();

    public WaspDungeonPiece(RandomSource random, int x, int z) {
        super(ModStructurePieces.WASP_DUNGEON.get(), x, 64, z, 10, 8, 10, getRandomHorizontalDirection(random));
    }

    public WaspDungeonPiece(StructurePieceSerializationContext ignoredContext, CompoundTag tag) {
        super(ModStructurePieces.WASP_DUNGEON.get(), tag);
    }

    @Override
    public void postProcess(@NotNull WorldGenLevel level, @NotNull StructureManager manager, @NotNull ChunkGenerator generator, @NotNull RandomSource random, @NotNull BoundingBox boundingBox, @NotNull ChunkPos chunkPos, @NotNull BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        y -= 12 + random.nextInt(14);

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
    }

    private void rect(WorldGenLevel level, BlockState state, int x1, int z1, int x2, int z2, int y) {
        for(int x = x1; x <= x2; x++) {
            for(int z = z1; z <= z2; z++) {
                level.setBlock(new BlockPos(x, y, z), state, Block.UPDATE_ALL);
            }
        }
    }

    private void lineX(WorldGenLevel level, BlockState state, int x1, int x2, int z, int y) {
        for(int x = x1; x <= x2; x++) {
            level.setBlock(new BlockPos(x, y, z), state, Block.UPDATE_ALL);
        }
    }

    private void lineZ(WorldGenLevel level, BlockState state, int z1, int z2, int x, int y) {
        for(int z = z1; z <= z2; z++) {
            level.setBlock(new BlockPos(x, y, z), state, Block.UPDATE_ALL);
        }
    }

    private void block(WorldGenLevel level, BlockState state, int x, int z, int y) {
        level.setBlock(new BlockPos(x, y, z), state, Block.UPDATE_ALL);
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