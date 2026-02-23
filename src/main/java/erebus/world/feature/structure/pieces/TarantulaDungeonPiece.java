package erebus.world.feature.structure.pieces;

import erebus.block.bamboo.BambooTorchBlock;
import erebus.block.entity.ErebusSpawnerBlockEntity;
import erebus.block.types.EnumTorchBlockHalf;
import erebus.datagen.loot.ModChestLootTables;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.entity.ModEntities;
import erebus.registries.world.structure.ModStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import org.jspecify.annotations.NonNull;

public class TarantulaDungeonPiece extends ScatteredFeaturePiece {
    private final BlockState STAIRS = ModBlocks.STAIRS_EUCALYPTUS.get().defaultBlockState();
    private final BlockState LOG = ModBlocks.LOG_EUCALYPTUS.get().defaultBlockState();
    private final BlockState LEAVES = ModBlocks.LEAVES_EUCALYPTUS.get().defaultBlockState();
    private final BlockState FENCE = ModBlocks.FENCE_EUCALYPTUS.get().defaultBlockState();
    private final BlockState DOOR = ModBlocks.DOOR_EUCALYPTUS.get().defaultBlockState();
    private final BlockState BAMBOO_TORCH_LOWER = ModBlocks.BAMBOO_TORCH.get().defaultBlockState().setValue(BambooTorchBlock.HALF, EnumTorchBlockHalf.LOWER);
    private final BlockState BAMBOO_TORCH_UPPER = ModBlocks.BAMBOO_TORCH.get().defaultBlockState().setValue(BambooTorchBlock.HALF, EnumTorchBlockHalf.UPPER);
    private final BlockState VINE = Blocks.VINE.defaultBlockState();
    private final BlockState AIR = Blocks.AIR.defaultBlockState();
    private final BlockState COBWEB = Blocks.COBWEB.defaultBlockState();

    public TarantulaDungeonPiece(RandomSource random, int west, int north) {
        super(ModStructurePieces.TARANTULA_DUNGEON.get(), west, 31, north, 31, 31, 31, getRandomHorizontalDirection(random));
    }

    public TarantulaDungeonPiece(StructurePieceSerializationContext ignoredContext, CompoundTag tag) {
        super(ModStructurePieces.TARANTULA_DUNGEON.get(), tag);
    }

    @Override
    public void postProcess(@NonNull WorldGenLevel level, @NonNull StructureManager structureManager, @NonNull ChunkGenerator generator, @NonNull RandomSource random, @NonNull BoundingBox chunkBB, @NonNull ChunkPos chunkPos, @NonNull BlockPos pos) {
        generateTrunk(level, chunkBB);
        generateLeaves(level, random, chunkBB);
        generateRoots(level, random, chunkBB);
        generateGroundFloorVines(level, chunkBB);
        generate2ndFloorHoles(level, chunkBB);
        generateFirstFloorVines(level, chunkBB);
        generateSpawners(level, random, pos, chunkBB);
        addEntranceDecoration(level, chunkBB);
    }

    private void generateTrunk(WorldGenLevel level, BoundingBox bb) {
        int BASE_RADIUS = 14;
        int radius = BASE_RADIUS - 1;

        for(int y = 0; y <= height; y++) {
            for(int x = -radius; x <= radius; x++) {
                for(int z = -radius; z <= radius; z++) {
                    double dSq = x * x + z * z;
                    double rounded = Math.round(Math.sqrt(dSq));

                    int LAYER_1 = 4;
                    if(y <= LAYER_1) {
                        if(rounded == radius || rounded <= radius - 1 && y < 2)
                            placeBlock(level, LOG, 15 + x, y, 15 + z, bb);

                        placeBlock(level, LOG, 15, y, 15, bb);
                    }

                    int LAYER_2 = 7;
                    if(y <= LAYER_2) {
                        if(rounded == radius - 1)
                            placeBlock(level, LOG, 15 + x, y, 15 + z, bb);
                        placeBlock(level, LOG, 15, y, 15, bb);
                    }

                    int LAYER_3 = 9;
                    if(y <= LAYER_3) {
                        if(rounded == radius - 2)
                            placeBlock(level, LOG, 15 + x, y, 15 + z, bb);
                        placeBlock(level, LOG, 15, y, 15, bb);
                    }

                    int LAYER_4 = 19;
                    if(y <= LAYER_4) {
                        placeBlock(level, LOG, 15, 10, 15, bb);
                        placeBlock(level, LOG, 15, 11, 15, bb);

                        if(rounded <= radius - 12 && rounded > 0)
                            placeBlock(level, COBWEB, 15 + x, 12, 15 + z, bb);

                        if(rounded == radius - 3 || rounded <= radius - 3 && rounded > radius - 12 && y >= 9 && y <= 12)
                            placeBlock(level, LOG, 15 + x, y, 15 + z, bb);
                    }

                    int LAYER_5 = 27;
                    if(y <= LAYER_5) {
                        if(rounded == radius - 12)
                            placeBlock(level, LOG, 15 + x, y, 15 + z, bb);

                        if(rounded <= radius - 3 && y == 20)
                            placeBlock(level, LOG, 15 + x, y, 15 + z, bb);

                        if(rounded <= radius - 3 && y == 21)
                            placeBlock(level, ModBlocks.SILK.get().defaultBlockState(), 15 + x, y, 15 + z, bb);
                    }

                    if(rounded < radius - 3 && rounded % 2 == 0 && y == 21)
                        if(x != 0 && z != 0)
                            placeBlock(level, COBWEB, 15 + x, y, 15 + z, bb);
                }
            }
        }
    }

    private void generateLeaves(WorldGenLevel level, RandomSource random, BoundingBox boundingBox) {
        createLeaves(level, random, 10, 29, 0, boundingBox);
        createLeaves(level, random, -10, 29, 0, boundingBox);
        createLeaves(level, random, 0, 29, 10, boundingBox);
        createLeaves(level, random, 0, 29, -10, boundingBox);
        createLeaves(level, random, 7, 27, 7, boundingBox);
        createLeaves(level, random, -7, 27, 7, boundingBox);
        createLeaves(level, random, 7, 27, -7, boundingBox);
        createLeaves(level, random, -7, 27, -7, boundingBox);
    }

    private void generateRoots(WorldGenLevel level, RandomSource random, BoundingBox boundingBox) {
        createRoots(level, random, 0, 0, boundingBox);
        createRoots(level, random, 9, 0, boundingBox);
        createRoots(level, random, -9, 0, boundingBox);
        createRoots(level, random, 0, 9, boundingBox);
        createRoots(level, random, 0, -9, boundingBox);
        createRoots(level, random, 8, 8, boundingBox);
        createRoots(level, random, -8, 8, boundingBox);
        createRoots(level, random, 8, -8, boundingBox);
        createRoots(level, random, -8, -8, boundingBox);
    }

    private void generateGroundFloorVines(WorldGenLevel level, BoundingBox boundingBox) {
        for(int y = 3; y <= 11; y++) {
            placeBlock(level, VINE, 16, y, 15, boundingBox);
            placeBlock(level, VINE, 14, y, 15, boundingBox);
            placeBlock(level, VINE, 15, y, 16, boundingBox);
            placeBlock(level, VINE, 15, y, 14, boundingBox);
        }
    }

    private void generate2ndFloorHoles(WorldGenLevel level, BoundingBox boundingBox) {
        placeBlock(level, AIR, 24, 20, 15, boundingBox);
        placeBlock(level, AIR, 6, 20, 15, boundingBox);
        placeBlock(level, AIR, 15, 20, 24, boundingBox);
        placeBlock(level, AIR, 15, 20, 6, boundingBox);
        placeBlock(level, AIR, 24, 21, 15, boundingBox);
        placeBlock(level, AIR, 6, 21, 15, boundingBox);
        placeBlock(level, AIR, 15, 21, 24, boundingBox);
        placeBlock(level, AIR, 15, 21, 6, boundingBox);
    }

    private void generateFirstFloorVines(WorldGenLevel level, BoundingBox boundingBox) {
        for(int y = 13; y <= 21; y++) {
            placeBlock(level, VINE, 24, y, 15, boundingBox);
            placeBlock(level, VINE, 6, y, 15, boundingBox);
            placeBlock(level, VINE, 15, y, 24, boundingBox);
            placeBlock(level, VINE, 15, y, 6, boundingBox);
        }
    }

    private void generateSpawners(WorldGenLevel level, RandomSource random, BlockPos pos, BoundingBox boundingBox) {
        placeSpawner(level, random, pos, -7, 3, 0, boundingBox);
        placeSpawner(level, random, pos, 7, 3, 0, boundingBox);
        placeSpawner(level, random, pos, 0, 3, -7, boundingBox);
        placeSpawner(level, random, pos, 0, 3, 7, boundingBox);

        if(random.nextBoolean()) {
            placeSpawner(level, random, pos, -5, 13, 0, boundingBox);
            placeSpawner(level, random, pos, 5, 13, 0, boundingBox);
        } else {
            placeSpawner(level, random, pos, 0, 13, -5, boundingBox);
            placeSpawner(level, random, pos, 0, 13, 5, boundingBox);
        }
    }

    private void addEntranceDecoration(WorldGenLevel level, BoundingBox bb) {
        for(int d = 0; d < 4; d++) {
            for(int c = 0; c < 3; c++) {
                rotatedCubeVolume(level, 0, c - 2, c - 15, LOG, 1, 2, 2, d, bb);
                rotatedCubeVolume(level, 0, c, c - 15, getStairRotation(d == 0 ? 2 : d == 1 ? 0 : d == 2 ? 3 : 1), 1, 1, 1, d, bb);
                rotatedCubeVolume(level, 0, c + 1, c - 15, AIR, 1, 2, 1, d, bb);

                if(c < 2) {
                    rotatedCubeVolume(level, -1, c - 2, c - 15, LOG, 1, 3, 2, d, bb);
                    rotatedCubeVolume(level, 1, c - 2, c - 15, LOG, 1, 3, 2, d, bb);
                    rotatedCubeVolume(level, -1, c + 1, c - 15, FENCE, 1, 1, 2, d, bb);
                    rotatedCubeVolume(level, 1, c + 1, c - 15, FENCE, 1, 1, 2, d, bb);

                    if(c < 1) {
                        rotatedCubeVolume(level, -1, c + 2, c - 15, FENCE, 1, 1, 1, d, bb);
                        rotatedCubeVolume(level, 1, c + 2, c - 15, FENCE, 1, 1, 1, d, bb);
                        rotatedCubeVolume(level, -1, c + 3, c - 15, BAMBOO_TORCH_LOWER, 1, 1, 1, d, bb);
                        rotatedCubeVolume(level, 1, c + 3, c - 15, BAMBOO_TORCH_LOWER, 1, 1, 1, d, bb);
                        rotatedCubeVolume(level, -1, c + 4, c - 15, BAMBOO_TORCH_UPPER, 1, 1, 1, d, bb);
                        rotatedCubeVolume(level, 1, c + 4, c - 15, BAMBOO_TORCH_UPPER, 1, 1, 1, d, bb);
                    }
                }
            }

            rotatedCubeVolume(level, 0, 3, -12, getDoorRotations(d), 1, 1, 1, d, bb);
            rotatedCubeVolume(level, 0, 4, -12, getDoorRotations(d + 4), 1, 1, 1, d, bb);

            rotatedCubeVolume(level, -2, 5, -13, LOG, 5, 1, 1, d, bb);
            rotatedCubeVolume(level, -1, 6, -13, LOG, 3, 1, 1, d, bb);
            rotatedCubeVolume(level, 0, 7, -13, LOG, 1, 1, 1, d, bb);

            rotatedCubeVolume(level, -1, 8, -12, LOG, 3, 1, 1, d, bb);
            rotatedCubeVolume(level, 0, 9, -12, LOG, 1, 1, 1, d, bb);

            rotatedCubeVolume(level, 0, 10, -11, LOG, 1, 1, 1, d, bb);
            rotatedCubeVolume(level, -1, 10, -11, LOG, 1, 2, 1, d, bb);
            rotatedCubeVolume(level, 1, 10, -11, LOG, 1, 2, 1, d, bb);

            rotatedCubeVolume(level, -2, 11, -11, LOG, 1, 2, 1, d, bb);
            rotatedCubeVolume(level, 2, 11, -11, LOG, 1, 2, 1, d, bb);

            rotatedCubeVolume(level, -3, 12, -11, LOG, 1, 5, 1, d, bb);
            rotatedCubeVolume(level, 3, 12, -11, LOG, 1, 5, 1, d, bb);

            rotatedCubeVolume(level, -1, 14, -10, ModBlocks.AMBER_GLASS.get().defaultBlockState(), 1, 2, 1, d, bb);
            rotatedCubeVolume(level, 1, 14, -10, ModBlocks.AMBER_GLASS.get().defaultBlockState(), 1, 2, 1, d, bb);

            rotatedCubeVolume(level, -2, 16, -11, LOG, 1, 2, 1, d, bb);
            rotatedCubeVolume(level, 2, 16, -11, LOG, 1, 2, 1, d, bb);

            rotatedCubeVolume(level, -1, 17, -11, LOG, 1, 2, 1, d, bb);
            rotatedCubeVolume(level, 1, 17, -11, LOG, 1, 2, 1, d, bb);

            rotatedCubeVolume(level, 0, 18, -11, LOG, 1, 2, 1, d, bb);

            rotatedCubeVolume(level, 0, 20, -12, LOG, 1, 6, 1, d, bb);

            rotatedCubeVolume(level, -1, 22, -12, LOG, 1, 2, 1, d, bb);
            rotatedCubeVolume(level, 1, 22, -12, LOG, 1, 2, 1, d, bb);

            rotatedCubeVolume(level, -2, 23, -12, LOG, 1, 3, 1, d, bb);
            rotatedCubeVolume(level, 2, 23, -12, LOG, 1, 3, 1, d, bb);
        }
    }

    private void createLeaves(WorldGenLevel level, RandomSource rand, int x, int y, int z, BoundingBox boundingBox) {
        int radius = 5;
        int height = 3;

        for (int xx = x - radius; xx <= x + radius; xx++)
            for (int zz = z - radius; zz <= z + radius; zz++)
                for (int yy = y - height; yy < y + height; yy++) {
                    double dSq = Math.pow(xx - x, 2.0D) + Math.pow(zz - z, 2.0D) + Math.pow(yy - y, 2.0D);
                    if (Math.round(Math.sqrt(dSq)) < radius)
                        if (dSq >= Math.pow(radius - 2, 2.0D))
                            if (rand.nextInt(10) == 0)
                                placeBlock(level, LOG, 15 + xx, yy, 15 + zz, boundingBox);
                            else
                                placeBlock(level, LEAVES, 15 + xx, yy, 15 + zz, boundingBox);
                }
    }

    private void createRoots(WorldGenLevel level, RandomSource rand, int x, int z, BoundingBox boundingBox) {
        float radius = 4;
        int height = rand.nextInt(6) + 10;
        for (int yy = -1; yy > -height; --yy)
            for (int i = (int) (radius * -1); i <= radius; ++i)
                for (int j = (int) (radius * -1); j <= radius; ++j) {
                    double dSq = i * i + j * j;
                    if (Math.round(Math.sqrt(dSq)) <= radius)
                        if (rand.nextInt(5) != 0)
                            placeBlock(level, LOG.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y), 15 + x + i, yy, 15 + z + j, boundingBox);
                    if (yy % 4 == 0)
                        radius -= 0.02F;
                }
    }

    private void placeSpawner(WorldGenLevel level, RandomSource rand, BlockPos startingPos, int x, int y, int z, BoundingBox boundingBox) {
        placeBlock(level, COBWEB, 15 + x + 1, y, 15 + z, boundingBox);
        placeBlock(level, COBWEB, 15 + x - 1, y, 15 + z, boundingBox);
        placeBlock(level, COBWEB, 15 + x, y, 15 + z - 1, boundingBox);
        placeBlock(level, COBWEB, 15 + x, y, 15 + z + 1, boundingBox);
        placeBlock(level, COBWEB, 15 + x, y + 1, 15 + z, boundingBox);
        placeBlock(level, ModBlocks.TARANTULA_SPAWNER.get().defaultBlockState(), 15 + x, y, 15 + z, boundingBox);
        ErebusSpawnerBlockEntity spawner = (ErebusSpawnerBlockEntity) level.getBlockEntity(this.getWorldPos(15 + x, y, 15 + z));
        if(spawner != null) {
            spawner.setEntityId(ModEntities.TARANTULA.get(), rand);
        }

        createChest(level, boundingBox, rand, 15 + x, y - 1, 15 + z, ModChestLootTables.TARANTULA_DUNGEON);
    }

    private void rotatedCubeVolume(WorldGenLevel level, int offsetA, int offsetB, int offsetC, BlockState state, int sizeWidth, int sizeHeight, int sizeDepth, int direction, BoundingBox boundingBox) {
        switch (direction) {
            case 0:
                for (int y = offsetB; y < offsetB + sizeHeight; y++)
                    for (int x = offsetA; x < offsetA + sizeWidth; x++)
                        for (int z = offsetC; z < offsetC + sizeDepth; z++) {
                            placeBlock(level, state, 15 + x, y, 15 + z, boundingBox);
                        }
                break;
            case 1:
                for (int y = offsetB; y < offsetB + sizeHeight; y++)
                    for (int z = - offsetA; z > - offsetA - sizeWidth; z--)
                        for (int x = offsetC; x < offsetC + sizeDepth; x++) {
                            placeBlock(level, state, 15 + x, y, 15 + z, boundingBox);
                        }
                break;
            case 2:
                for (int y = offsetB; y < offsetB + sizeHeight; y++)
                    for (int x = - offsetA; x > - offsetA - sizeWidth; x--)
                        for (int z = - offsetC; z > - offsetC - sizeDepth; z--) {
                            placeBlock(level, state, 15 + x, y, 15 + z, boundingBox);
                        }
                break;
            case 3:
                for (int y = offsetB; y < offsetB + sizeHeight; y++)
                    for (int z = offsetA; z < offsetA + sizeWidth; z++)
                        for (int x = - offsetC; x > - offsetC - sizeDepth; x--) {
                            placeBlock(level, state, 15 + x, y, 15 + z, boundingBox);
                        }
                break;
        }
    }

    private BlockState getStairRotation(int direction) {
        return switch (direction) {
            case 0 -> STAIRS.setValue(StairBlock.FACING, Direction.EAST);
            case 1 -> STAIRS.setValue(StairBlock.FACING, Direction.WEST);
            case 2 -> STAIRS.setValue(StairBlock.FACING, Direction.SOUTH);
            case 3 -> STAIRS.setValue(StairBlock.FACING, Direction.NORTH);
            case 4 -> STAIRS.setValue(StairBlock.FACING, Direction.EAST).setValue(StairBlock.HALF, Half.TOP);
            case 5 -> STAIRS.setValue(StairBlock.FACING, Direction.WEST).setValue(StairBlock.HALF, Half.TOP);
            case 6 -> STAIRS.setValue(StairBlock.FACING, Direction.SOUTH).setValue(StairBlock.HALF, Half.TOP);
            case 7 -> STAIRS.setValue(StairBlock.FACING, Direction.NORTH).setValue(StairBlock.HALF, Half.TOP);
            default -> STAIRS;
        };
    }

    public BlockState getDoorRotations(int direction) {
        return switch (direction) {
            case 0 -> DOOR.setValue(DoorBlock.FACING, Direction.SOUTH).setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER);
            case 1 -> DOOR.setValue(DoorBlock.FACING, Direction.EAST).setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER);
            case 2 -> DOOR.setValue(DoorBlock.FACING, Direction.NORTH).setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER);
            case 3 -> DOOR.setValue(DoorBlock.FACING, Direction.WEST).setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER);
            case 4 -> DOOR.setValue(DoorBlock.FACING, Direction.SOUTH).setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER);
            case 5 -> DOOR.setValue(DoorBlock.FACING, Direction.EAST).setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER);
            case 6 -> DOOR.setValue(DoorBlock.FACING, Direction.NORTH).setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER);
            case 7 -> DOOR.setValue(DoorBlock.FACING, Direction.WEST).setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER);
            default -> DOOR;
        };
    }
}
