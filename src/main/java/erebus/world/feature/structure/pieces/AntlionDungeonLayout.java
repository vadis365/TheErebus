package erebus.world.feature.structure.pieces;

import erebus.block.BlockOfBonesBlock;
import erebus.block.TempleTeleporterBlock;
import erebus.block.bamboo.BambooTorchBlock;
import erebus.block.types.EnumTorchBlockHalf;
import erebus.registries.blocks.ModBlocks;
import erebus.world.util.MazeGenerator;
import erebus.world.util.PerfectMazeGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public final class AntlionDungeonLayout {
    private final Map<BlockPos, BlockState> blocks = new LinkedHashMap<>();
    private final Map<BlockPos, BlockPos> teleporters = new LinkedHashMap<>();
    private final Set<BlockPos> lootChests = new HashSet<>();
    private final Set<BlockPos> lootBones = new HashSet<>();
    private final List<Guardian> guardians = new ArrayList<>();
    private final Set<BlockState> structureBlocks = new HashSet<>();
    private BlockPos jadeChest = BlockPos.ZERO;
    private BlockState GNEISS;
    private BlockState GNEISS_RELIEF;
    private BlockState GNEISS_CARVED;
    private BlockState GNEISS_BRICKS;
    private BlockState GNEISS_TILES;
    private BlockState GNEISS_VENT;
    private BlockState TEMPLE_BRICK;
    private BlockState TEMPLE_BRICK_UNBREAKING;
    private BlockState TEMPLE_BRICK_UNBREAKING_JADE;
    private BlockState TEMPLE_BRICK_UNBREAKING_EXO;
    private BlockState TEMPLE_BRICK_UNBREAKING_CREAM;
    private BlockState TEMPLE_BRICK_UNBREAKING_EYE;
    private BlockState TEMPLE_BRICK_UNBREAKING_STRING;
    private BlockState TEMPLE_PILLAR;
    private BlockState CAPSTONE;
    private BlockState BAMBOO_TORCH_LOWER;
    private BlockState BAMBOO_TORCH_UPPER;
    private BlockState FORCE_FIELD;
    private BlockState ANTLION_SPAWNER;
    private BlockState MAGMA_CRAWLER_SPAWNER;
    private BlockState LAVA;
    private BlockState SAND;
    private BlockState AIR;
    public AntlionDungeonLayout(BlockPos center, long seed) {
        setupBlockStates();
        generateStructure(center, RandomSource.create(seed));
    }

    public Map<BlockPos, BlockState> blocks() {
        return Collections.unmodifiableMap(blocks);
    }

    public Map<BlockPos, BlockPos> teleporters() {
        return Collections.unmodifiableMap(teleporters);
    }

    public Set<BlockPos> lootChests() {
        return Collections.unmodifiableSet(lootChests);
    }

    public List<Guardian> guardians() {
        return Collections.unmodifiableList(guardians);
    }

    public Set<BlockPos> lootBones() {
        return Collections.unmodifiableSet(lootBones);
    }

    public BlockPos jadeChest() {
        return jadeChest;
    }

    private void put(BlockPos pos, BlockState state) {
        blocks.put(pos.immutable(), state);
    }

    private BlockState stateAt(BlockPos pos) {
        return blocks.getOrDefault(pos, Blocks.AIR.defaultBlockState());
    }

    private void setupBlockStates() {
        GNEISS = ModBlocks.GNEISS.get().defaultBlockState();
        GNEISS_RELIEF = ModBlocks.GNEISS_RELIEF.get().defaultBlockState();
        GNEISS_CARVED = ModBlocks.GNEISS_CARVED.get().defaultBlockState();
        GNEISS_BRICKS = ModBlocks.GNEISS_BRICKS.get().defaultBlockState();
        GNEISS_TILES = ModBlocks.GNEISS_TILES.get().defaultBlockState();
        GNEISS_VENT = ModBlocks.GNEISS_VENT.get().defaultBlockState();
        TEMPLE_BRICK = ModBlocks.TEMPLE_BRICK.get().defaultBlockState();
        TEMPLE_BRICK_UNBREAKING = ModBlocks.TEMPLE_BRICK_UNBREAKING.get().defaultBlockState();
        TEMPLE_BRICK_UNBREAKING_JADE = ModBlocks.TEMPLE_BRICK_UNBREAKING_JADE.get().defaultBlockState();
        TEMPLE_BRICK_UNBREAKING_EXO = ModBlocks.TEMPLE_BRICK_UNBREAKING_EXO.get().defaultBlockState();
        TEMPLE_BRICK_UNBREAKING_CREAM = ModBlocks.TEMPLE_BRICK_UNBREAKING_CREAM.get().defaultBlockState();
        TEMPLE_BRICK_UNBREAKING_EYE = ModBlocks.TEMPLE_BRICK_UNBREAKING_EYE.get().defaultBlockState();
        TEMPLE_BRICK_UNBREAKING_STRING = ModBlocks.TEMPLE_BRICK_UNBREAKING_STRING.get().defaultBlockState();
        TEMPLE_PILLAR = ModBlocks.TEMPLE_PILLAR.get().defaultBlockState();
        CAPSTONE = ModBlocks.CAPSTONE.get().defaultBlockState();
        BAMBOO_TORCH_LOWER = ModBlocks.BAMBOO_TORCH.get().defaultBlockState().setValue(BambooTorchBlock.HALF, EnumTorchBlockHalf.LOWER);
        BAMBOO_TORCH_UPPER = ModBlocks.BAMBOO_TORCH.get().defaultBlockState().setValue(BambooTorchBlock.HALF, EnumTorchBlockHalf.UPPER);
        FORCE_FIELD = ModBlocks.FORCE_FIELD.get().defaultBlockState();
        ANTLION_SPAWNER = ModBlocks.ANTLION_SPAWNER.get().defaultBlockState();
        MAGMA_CRAWLER_SPAWNER = ModBlocks.MAGMA_CRAWLER_SPAWNER.get().defaultBlockState();
        LAVA = Blocks.LAVA.defaultBlockState();
        SAND = Blocks.SAND.defaultBlockState();
        AIR = Blocks.AIR.defaultBlockState();

        if (structureBlocks.isEmpty()) {
            structureBlocks.add(GNEISS);
            structureBlocks.add(GNEISS_RELIEF);
            structureBlocks.add(GNEISS_CARVED);
            structureBlocks.add(GNEISS_BRICKS);
            structureBlocks.add(GNEISS_TILES);
            structureBlocks.add(GNEISS_VENT);
            structureBlocks.add(TEMPLE_BRICK);
            structureBlocks.add(TEMPLE_BRICK_UNBREAKING);
            structureBlocks.add(TEMPLE_PILLAR);
            structureBlocks.add(CAPSTONE);
            structureBlocks.add(BAMBOO_TORCH_LOWER);
            structureBlocks.add(BAMBOO_TORCH_UPPER);
            structureBlocks.add(FORCE_FIELD);
            structureBlocks.add(ANTLION_SPAWNER);
            structureBlocks.add(MAGMA_CRAWLER_SPAWNER);
            structureBlocks.add(LAVA);
        }
    }

    private boolean isSolidStructureBlock(BlockState block) {
        return structureBlocks.contains(block);
    }

    private void generateStructure(BlockPos pos, RandomSource random) {
        int sizeX = 60;
        int sizeY = 4;
        int sizeZ = 60;
        int width = sizeX / 2;
        int length = sizeZ / 2;

        int[][] maze;
        MazeGenerator generator = new PerfectMazeGenerator(width, length, random.nextLong());
        maze = generator.generateMaze();

        buildFloor(pos.below(sizeY), width, length, random);
        buildRoof(pos, width, length, random);

        buildLevel(pos.offset(-sizeX, -sizeY + 1, -sizeZ), width, length, maze, GNEISS_RELIEF);
        buildLevel(pos.offset(-sizeX, -sizeY + 2, -sizeZ), width, length, maze, GNEISS_CARVED);
        buildLevel(pos.offset(-sizeX, -sizeY + 3, -sizeZ), width, length, maze, GNEISS_RELIEF);

        createAir(pos.below(sizeY - 1), width, length, random);
        addFeature(pos.offset(-sizeX, -2, -sizeZ), width, length, maze, random);

        buildCourtyard(TEMPLE_PILLAR, pos.below(sizeY), sizeX - 8, sizeY, sizeZ - 8);
        createPyramid(pos.below(sizeY), TEMPLE_BRICK_UNBREAKING, true, width - 8, length - 8);

        decoratePyramid(pos.offset(-width + 8, -sizeY, -length + 8));
        addTeleporters(pos.offset(-width + 8, -sizeY, -length + 8));
        addCapstones(pos.above(17));
        spawnIdolGuardians(pos.offset(-sizeX, -sizeY + 1, -sizeZ));
    }

    private void addTeleporters(BlockPos pos) {
        put(pos.offset(13, 9, 13), CAPSTONE);
        setFloorDecoStone(pos.offset(14, 9, 14));
        setLockStone(pos.offset(15, 9, 15), TEMPLE_BRICK_UNBREAKING_EXO);
        setTeleporter(pos.offset(16, 9, 16), 0, pos.offset(30, 9, 13));
        setTeleporter(pos.offset(19, 9, 19), 5, pos.offset(13, 9, 13));

        put(pos.offset(30, 9, 13), CAPSTONE);
        setFloorDecoStone(pos.offset(25, 9, 14));
        setLockStone(pos.offset(26, 9, 15), TEMPLE_BRICK_UNBREAKING_CREAM);
        setTeleporter(pos.offset(27, 9, 16), 0, pos.offset(30, 9, 30));
        setTeleporter(pos.offset(24, 9, 19), 5, pos.offset(13, 9, 13));

        put(pos.offset(30, 9, 30), CAPSTONE);
        setFloorDecoStone(pos.offset(25, 9, 25));
        setLockStone(pos.offset(26, 9, 26), TEMPLE_BRICK_UNBREAKING_EYE);
        setTeleporter(pos.offset(27, 9, 27), 0, pos.offset(13, 9, 30));
        setTeleporter(pos.offset(24, 9, 24), 5, pos.offset(13, 9, 13));

        put(pos.offset(13, 9, 30), CAPSTONE);
        setFloorDecoStone(pos.offset(14, 9, 25));
        setLockStone(pos.offset(15, 9, 26), TEMPLE_BRICK_UNBREAKING_STRING);
        setTeleporter(pos.offset(16, 9, 27), 0, pos.offset(13, 9, 13));
        setTeleporter(pos.offset(19, 9, 24), 5, pos.offset(30, 9, 30));

        setFloorMidDecoStone(pos.offset(20, 9, 20));
        setTeleporter(pos.offset(22, 9, 21), 6, pos.offset(5, 1, 5));
        setTeleporter(pos.offset(21, 9, 21), 7, pos.offset(38, 1, 5));
        setTeleporter(pos.offset(22, 9, 22), 8, pos.offset(38, 1, 38));
        setTeleporter(pos.offset(21, 9, 22), 9, pos.offset(5, 1, 38));

        put(pos.offset(19, 14, 19), CAPSTONE);
        put(pos.offset(19, 15, 25), BAMBOO_TORCH_LOWER);
        put(pos.offset(19, 16, 25), BAMBOO_TORCH_UPPER);
        put(pos.offset(25, 15, 19), BAMBOO_TORCH_LOWER);
        put(pos.offset(25, 16, 19), BAMBOO_TORCH_UPPER);
        setFloorDecoStone(pos.offset(20, 14, 20));
        setLockStone(pos.offset(21, 14, 21), TEMPLE_BRICK_UNBREAKING_JADE);
        setTeleporter(pos.offset(22, 14, 22), 0, pos.offset(13, 9, 13));
        setTeleporter(pos.offset(25, 14, 25), 5, pos.offset(19, 14, 19));
    }

    private void setFloorDecoStone(BlockPos pos) {
        setDecoStone(pos, 5);
    }

    private void setFloorMidDecoStone(BlockPos pos) {
        setDecoStone(pos, 4);
    }

    private void setDecoStone(BlockPos pos, int size) {
        for (int x = 0; x < size; x++) {
            for (int z = 0; z < size; z++) {
                put(pos.offset(x, 0, z), CAPSTONE);
            }
        }
    }

    private void setLockStone(BlockPos pos, BlockState lockStone) {
        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                put(pos.offset(x, 0, z), lockStone);
            }
        }
    }

    private void buildFloor(BlockPos pos, int width, int length, RandomSource random) {

        createPyramid(pos.above(5), Blocks.AIR.defaultBlockState(), true, 24, 24);

        for (int z = -length * 2; z <= length * 2; z++) {
            for (int x = -width * 2; x <= width * 2; x++) {
                BlockPos featurePos = pos.offset(x, 0, z);
                if (canPlaceFloorAt(pos, featurePos)) {

                    if (random.nextInt(15) == 0) {
                        if (random.nextBoolean() && random.nextBoolean()) {
                            put(featurePos, LAVA);
                        } else {
                            put(featurePos, GNEISS_VENT);
                        }
                    } else {

                        put(featurePos, GNEISS_TILES);
                    }
                }
            }
        }
    }

    private void buildRoof(BlockPos pos, int width, int length, RandomSource random) {
        for (int z = -length * 2; z <= length * 2; z++) {
            for (int x = -width * 2; x <= width * 2; x++) {
                BlockPos featurePos = pos.offset(x, 0, z);
                if (canPlaceFeatureAt(pos, featurePos)) {
                    put(featurePos, GNEISS_BRICKS);
                }
            }
        }
    }

    private void createAir(BlockPos pos, int width, int length, RandomSource random) {

        for (int z = -length * 2; z <= length * 2; z++) {
            for (int x = -width * 2; x <= width * 2; x++) {
                for (int y = 0; y <= 2; y++) {
                    if (canPlaceFeatureAt(pos, pos.offset(x, y, z))) {

                        if (!isSolidStructureBlock(stateAt(pos.offset(x, y, z)))) {
                            put(pos.offset(x, y, z), AIR);
                        }
                    }
                }
            }
        }
    }

    private void decoratePyramid(BlockPos pos) {
        boolean forceFieldSet = false;
        boolean topChestSet = false;

        for (int y = 0; y < 30; y++) {
            for (int x = 0; x < 44; x++) {
                for (int z = 0; z < 44; z++) {
                    BlockPos offset = pos.offset(x, y, z);
                    if (y == 0) put(offset, TEMPLE_BRICK_UNBREAKING);
                    if (y == 1) {
                        if (x > 1 && x < 42 && z > 1 && z < 42) {
                            put(offset, SAND);
                        }

                        if (x > 4 && x < 39 && z > 4 && z < 39) {
                            if (x % 11 == 5 || z % 11 == 5) {
                                put(offset, GNEISS_VENT);
                            } else {
                                if (x >= 21 && x <= 22 && z >= 21 && z <= 22) {
                                    put(offset, GNEISS_VENT);
                                } else {
                                    put(offset, SAND);
                                }
                            }
                        }
                    }

                    if (y == 9) {
                        if (x > 9 && x < 34 && z > 9 && z < 34) {
                            put(offset, TEMPLE_BRICK_UNBREAKING);
                        }
                    }

                    if (y == 10 && !forceFieldSet) {
                        for (int c = 0; c < 4; c++) {
                            for (int d = c; d < 9; d++) {
                                put(pos.offset(11 + d, y + c, 21), FORCE_FIELD);
                                put(pos.offset(11 + d, y + c, 22), FORCE_FIELD);
                                put(pos.offset(21, y + c, 11 + d), FORCE_FIELD);
                                put(pos.offset(22, y + c, 11 + d), FORCE_FIELD);
                                put(pos.offset(21, y + c, 32 - d), FORCE_FIELD);
                                put(pos.offset(22, y + c, 32 - d), FORCE_FIELD);
                                put(pos.offset(32 - d, y + c, 21), FORCE_FIELD);
                                put(pos.offset(32 - d, y + c, 22), FORCE_FIELD);
                            }

                            for (int dx = 20; dx < 24; dx++) {
                                for (int dz = 20; dz < 24; dz++) {
                                    put(pos.offset(dx, y + c, dz), FORCE_FIELD);
                                }
                            }

                            for (int dx = 21; dx < 23; dx++) {
                                for (int dz = 21; dz < 23; dz++) {
                                    put(pos.offset(dx, y + c, dz), AIR);
                                }
                            }
                        }

                        forceFieldSet = true;
                    }

                    if (y == 14) {
                        if (x > 14 && x < 29 && z > 14 && z < 29) {
                            put(offset, TEMPLE_BRICK_UNBREAKING);
                        }
                    }

                    if (y == 15 && !topChestSet) {
                        put(pos.offset(19, y, 19), Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.NORTH));
                        jadeChest = pos.offset(19, y, 19);
                        topChestSet = true;
                    }
                }
            }
        }
    }

    private void buildCourtyard(BlockState block, BlockPos pos, int lengthX, int height, int lengthZ) {
        for (int y = 0; y <= height; y++) {
            for (int x = -lengthX / 2; x < lengthX / 2; x++) {
                for (int z = -lengthZ / 2; z < lengthZ / 2; z++) {
                    if (y > 0) {
                        put(pos.offset(x, y, z), AIR);

                        if (x == -lengthX / 2 || x == lengthX / 2 - 1) {
                            if (z > -lengthZ / 2 && z < lengthZ / 2) {
                                if (y <= 3) {
                                    for (int c = 3; c < 49; c++) {
                                        put(pos.offset(x, y, -lengthZ / 2 + c), block);
                                    }
                                }

                                if (y == 4) {
                                    for (int c = 0; c < 52; c++) {
                                        put(pos.offset(x, y, -lengthZ / 2 + c), TEMPLE_BRICK);
                                    }
                                }
                            }
                        }

                        if (z == -lengthZ / 2 || z == lengthZ / 2 - 1) {
                            if (x > -lengthX / 2 && x < lengthX / 2) {
                                if (y <= 3) {
                                    for (int c = 3; c < 49; c++) {
                                        put(pos.offset(-lengthZ / 2 + c, y, z), block);
                                    }
                                }

                                if (y == 4) {
                                    for (int c = 0; c < 52; c++) {
                                        put(pos.offset(-lengthZ / 2 + c, y, z), TEMPLE_BRICK);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void addCapstones(BlockPos pos) {
        put(pos.north().west(), ModBlocks.CAPSTONE_MUD.get().defaultBlockState());
        put(pos.north(), ModBlocks.CAPSTONE_IRON.get().defaultBlockState());
        put(pos.west(), ModBlocks.CAPSTONE_GOLD.get().defaultBlockState());
        put(pos, ModBlocks.CAPSTONE_JADE.get().defaultBlockState());
    }

    private boolean canPlaceAt(BlockPos pos, BlockPos featurePos, int size) {

        for (int x = pos.getX() - size; x < pos.getX() + size; x++) {
            for (int z = pos.getZ() - size; z < pos.getZ() + size; z++) {
                if (x == featurePos.getX() && z == featurePos.getZ()) return false;
            }
        }
        return true;
    }

    private boolean canPlaceFeatureAt(BlockPos pos, BlockPos featurePos) {
        return canPlaceAt(pos, featurePos, 26);
    }

    private boolean canPlaceFloorAt(BlockPos pos, BlockPos featurePos) {
        return canPlaceAt(pos, featurePos, 22);
    }

    private void setTeleporter(BlockPos pos, int type, BlockPos target) {
        put(pos, ModBlocks.TEMPLE_TELEPORTER.get().defaultBlockState().setValue(TempleTeleporterBlock.PHASE, type));
        teleporters.put(pos.immutable(), target.immutable());
    }

    private void spawnIdolGuardians(BlockPos pos) {
        for (int variant = 0; variant < 4; variant++)
            guardians.add(new Guardian(
                    new Vec3(pos.getX() + 2.5 + (variant % 2) * 116, pos.getY(), pos.getZ() + 2.5 + (variant / 2) * 116), variant));
    }

    private void placeChest(BlockPos pos, Direction direction) {
        put(pos, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, direction));
        lootChests.add(pos.immutable());
    }

    private void placeBones(BlockPos pos, Direction direction) {
        put(pos, ModBlocks.BLOCK_OF_BONES.get().defaultBlockState().setValue(BlockOfBonesBlock.FACING, direction));
        lootBones.add(pos.immutable());
    }

    private void createPyramid(BlockPos pos, BlockState block, boolean hollow, int baseX, int baseZ) {
        for (int y = 0; y < 21; y++, baseX--, baseZ--) {
            for (int x = -baseX; x < baseX; x++)
                for (int z = -baseZ; z < baseZ; z++) {
                    var at = pos.offset(x, y, z);
                    if (isSolidStructureBlock(stateAt(at))) continue;
                    if (x == -baseX || x == baseX - 1 || z == -baseZ || z == baseZ - 1) put(at, block);
                    else if (hollow) put(at, AIR);
                }
        }
    }

    private void buildLevel(BlockPos pos, int width, int length, int[][] maze, BlockState block) {
        var center = pos.offset(60, 0, 60);
        for (int z = 0; z < length; z++)
            for (int x = 0; x < width; x++) {
                putWall(center, pos.offset(x * 4, 0, z * 4), block);
                if ((maze[x][z] & 1) == 0) for (int step = 1; step < 4; step++) putWall(center, pos.offset(x * 4 + step, 0, z * 4), block);
                if ((maze[x][z] & 8) == 0) for (int step = 1; step < 4; step++) putWall(center, pos.offset(x * 4, 0, z * 4 + step), block);
            }
        for (int z = 0; z <= length * 4; z++) putWall(center, pos.offset(width * 4, 0, z), block);
        for (int x = 0; x <= width * 4; x++) putWall(center, pos.offset(x, 0, length * 4), block);
    }

    private void putWall(BlockPos center, BlockPos pos, BlockState state) {
        if (canPlaceFeatureAt(center, pos)) put(pos, state);
    }

    private void addFeature(BlockPos pos, int width, int length, int[][] maze, RandomSource random) {
        var center = pos.offset(60, 0, 60);
        for (int z = 0; z < length; z++) {
            for (int x = 0; x < width; x++)
                if ((maze[x][z] & 1) == 0) {
                    if (!decorateCell(center, pos.offset(1 + x * 4, 0, 1 + z * 4), Direction.SOUTH, random) && random.nextInt(10) == 0) {
                        if (random.nextBoolean()) put(pos.offset(2 + x * 4, -2, 2 + z * 4), ANTLION_SPAWNER);
                        else put(pos.offset(2 + x * 4, 2, 2 + z * 4), MAGMA_CRAWLER_SPAWNER);
                    }
                }
            for (int x = 0; x < width; x++) if ((maze[x][z] & 8) == 0) decorateCell(center, pos.offset(1 + x * 4, 0, 2 + z * 4), Direction.EAST, random);
            for (int x = 0; x < width; x++) if ((maze[x][z] & 4) == 0) decorateCell(center, pos.offset(3 + x * 4, 0, 2 + z * 4), Direction.WEST, random);
            for (int x = 0; x < width; x++) if ((maze[x][z] & 2) == 0) decorateCell(center, pos.offset(2 + x * 4, 0, 3 + z * 4), Direction.NORTH, random);
        }
    }

    private boolean decorateCell(BlockPos center, BlockPos torch, Direction direction, RandomSource random) {
        if (random.nextInt(25) != 0 || !canPlaceFeatureAt(center, torch.below())) return false;
        put(torch, Blocks.WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, direction));
        if (random.nextInt(4) == 0) placeChest(torch.below(), direction);
        else if (random.nextInt(6) == 0) placeBones(torch.below(), direction);
        return true;
    }

    public record Guardian(Vec3 position, int variant) {
    }
}
