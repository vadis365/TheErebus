package erebus.world.feature.structure.pieces;

import erebus.registries.blocks.ModBlocks;
import erebus.block.BlockOfBonesBlock;
import erebus.block.entity.BlockOfBonesBlockEntity;
import erebus.registries.world.structure.ModStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import org.jetbrains.annotations.NotNull;

public class LocustShrinePiece extends ScatteredFeaturePiece {

    private final BlockState COARSE_DIRT = Blocks.COARSE_DIRT.defaultBlockState();
    private final BlockState UMBERGRAVEL = ModBlocks.UMBERGRAVEL.get().defaultBlockState();
    private final BlockState DEAD_BUSH = Blocks.DEAD_BUSH.defaultBlockState();
    private final BlockState MOSSY_UMBERCOBBLE = ModBlocks.UMBERCOBBLE_MOSSY.get().defaultBlockState();
    private final BlockState MOSSY_UMBERCOBBLE_WALL = ModBlocks.WALL_UMBERCOBBLE_MOSSY.get().defaultBlockState();
    private final BlockState BONES = ModBlocks.BLOCK_OF_BONES.get().defaultBlockState();
    private final BlockState SPAWNER = ModBlocks.LOCUST_SPAWNER.get().defaultBlockState();

    public LocustShrinePiece(RandomSource random, int x, int z) {
        super(ModStructurePieces.LOCUST_SHRINE.get(), x, 64, z, 10, 8, 10, getRandomHorizontalDirection(random));
    }

    public LocustShrinePiece(StructurePieceSerializationContext ignoredContext, CompoundTag tag) {
        super(ModStructurePieces.LOCUST_SHRINE.get(), tag);
    }

    @Override
    public void postProcess(@NotNull WorldGenLevel level, @NotNull StructureManager manager, @NotNull ChunkGenerator generator, @NotNull RandomSource random, @NotNull BoundingBox boundingBox, @NotNull ChunkPos chunkPos, @NotNull BlockPos pos) {
        for (int x = -4; x <= 4; x++) {
            for (int z = -4; z <= 4; z++) {
                double circle = Math.pow(x, 2.0D) + Math.pow(z, 2.0D);
                if (Math.round(Math.sqrt(circle)) < 5)
                    setBlock(level, pos.offset(x, -1, z), random.nextInt(5) != 0 ? COARSE_DIRT : UMBERGRAVEL);
                if(level.getBlockState(pos.offset(x, -1, z)).is(Blocks.DIRT) && random.nextInt(3) == 0)
                    setBlock(level, pos.offset(x, 0, z), DEAD_BUSH);
            }
        }
        placeSpawnerPillar(level, pos);
        placePillar(level, pos.offset(3, 0, 3), Direction.EAST);
        placePillar(level, pos.offset(3, 0, -3), Direction.NORTH);
        placePillar(level, pos.offset(-3, 0, 3), Direction.SOUTH);
        placePillar(level, pos.offset(-3, 0, -3), Direction.WEST);
    }

    private void placePillar(WorldGenLevel level, BlockPos pos, Direction facing) {
        setBlock(level, pos, MOSSY_UMBERCOBBLE);
        setBlock(level, pos.above(1), MOSSY_UMBERCOBBLE_WALL);
        setBlock(level, pos.above(2), MOSSY_UMBERCOBBLE);
        setBlock(level, pos.above(3), MOSSY_UMBERCOBBLE);
        setBlock(level, pos.above(4), MOSSY_UMBERCOBBLE_WALL);
        setBlock(level, pos.above(5), MOSSY_UMBERCOBBLE);
        setBlock(level, pos.above(6), BONES.setValue(BlockOfBonesBlock.FACING, facing));
        BlockOfBonesBlockEntity bones = (BlockOfBonesBlockEntity) level.getBlockEntity(pos.above(6));
        if (bones != null) {
            // Set Loot
        }
    }

    private void placeSpawnerPillar(WorldGenLevel level, BlockPos pos) {
        setBlock(level, pos, MOSSY_UMBERCOBBLE);
        setBlock(level, pos.above(1), MOSSY_UMBERCOBBLE);
        setBlock(level, pos.above(2), MOSSY_UMBERCOBBLE_WALL);
        setBlock(level, pos.above(3), MOSSY_UMBERCOBBLE);
        setBlock(level, pos.above(4), SPAWNER);
    }

    private void setBlock(WorldGenLevel level, BlockPos pos, BlockState state) {
        level.setBlock(pos, state, 3);
    }
}
