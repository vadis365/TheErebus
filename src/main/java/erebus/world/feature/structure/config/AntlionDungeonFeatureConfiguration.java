package erebus.world.feature.structure.config;

import erebus.block.BlockOfBonesBlock;
import erebus.block.bamboo.BambooTorchBlock;
import erebus.block.bamboo.EnumTorchBlockHalf;
import erebus.block.entity.BlockOfBonesBlockEntity;
import erebus.datagen.loot.ModChestLootTables;
import erebus.registries.ModItems;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.world.ModBiomes;
import erebus.world.util.MazeGenerator;
import erebus.world.util.PerfectMazeGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AntlionDungeonFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    private final BlockState GNEISS = OtherBlocks.GNEISS.get().defaultBlockState();
    private final BlockState GNEISS_RELIEF = OtherBlocks.GNEISS_RELIEF.get().defaultBlockState();
    private final BlockState GNEISS_CARVED = OtherBlocks.GNEISS_CARVED.get().defaultBlockState();
    private final BlockState GNEISS_BRICKS = OtherBlocks.GNEISS_BRICKS.get().defaultBlockState();
    private final BlockState GNEISS_TILES = OtherBlocks.GNEISS_TILES.get().defaultBlockState();
    private final BlockState GNEISS_VENT = OtherBlocks.GNEISS_VENT.get().defaultBlockState();
    private final BlockState TEMPLE_BRICK = OtherBlocks.TEMPLE_BRICK.get().defaultBlockState();
    private final BlockState TEMPLE_BRICK_UNBREAKING = OtherBlocks.TEMPLE_BRICK_UNBREAKING.get().defaultBlockState();
    private final BlockState TEMPLE_BRICK_UNBREAKING_JADE = OtherBlocks.TEMPLE_BRICK_UNBREAKING_JADE.get().defaultBlockState();
    private final BlockState TEMPLE_BRICK_UNBREAKING_EXO = OtherBlocks.TEMPLE_BRICK_UNBREAKING_EXO.get().defaultBlockState();
    private final BlockState TEMPLE_BRICK_UNBREAKING_CREAM = OtherBlocks.TEMPLE_BRICK_UNBREAKING_CREAM.get().defaultBlockState();
    private final BlockState TEMPLE_BRICK_UNBREAKING_EYE = OtherBlocks.TEMPLE_BRICK_UNBREAKING_EYE.get().defaultBlockState();
    private final BlockState TEMPLE_BRICK_UNBREAKING_STRING = OtherBlocks.TEMPLE_BRICK_UNBREAKING_STRING.get().defaultBlockState();
    private final BlockState TEMPLE_PILLAR = OtherBlocks.TEMPLE_PILLAR.get().defaultBlockState();
    private final BlockState CAPSTONE = OtherBlocks.CAPSTONE.get().defaultBlockState();
    private final BlockState BAMBOO_TORCH_LOWER = OtherBlocks.BAMBOO_TORCH.get().defaultBlockState().setValue(BambooTorchBlock.HALF, EnumTorchBlockHalf.LOWER);
    private final BlockState BAMBOO_TORCH_UPPER = OtherBlocks.BAMBOO_TORCH.get().defaultBlockState().setValue(BambooTorchBlock.HALF, EnumTorchBlockHalf.UPPER);
    private final BlockState FORCE_FIELD = OtherBlocks.FORCE_FIELD.get().defaultBlockState();
    private final BlockState ANTLION_SPAWNER = OtherBlocks.ANTLION_SPAWNER.get().defaultBlockState();
    private final BlockState MAGMA_CRAWLER_SPAWNER = OtherBlocks.MAGMA_CRAWLER_SPAWNER.get().defaultBlockState();
    private final BlockState TORCH = Blocks.TORCH.defaultBlockState();
    private final BlockState LAVA = Blocks.LAVA.defaultBlockState();
    private final BlockState SAND = Blocks.SAND.defaultBlockState();
    private final BlockState AIR = Blocks.AIR.defaultBlockState();

    private final Map<BlockState, Boolean> STRUCTURE_BLOCKS = new HashMap<>();

    public AntlionDungeonFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
        if(STRUCTURE_BLOCKS.isEmpty()) {
            STRUCTURE_BLOCKS.put(GNEISS, true);
            STRUCTURE_BLOCKS.put(GNEISS_RELIEF, true);
            STRUCTURE_BLOCKS.put(GNEISS_CARVED, true);
            STRUCTURE_BLOCKS.put(GNEISS_BRICKS, true);
            STRUCTURE_BLOCKS.put(GNEISS_TILES, true);
            STRUCTURE_BLOCKS.put(GNEISS_VENT, true);
            STRUCTURE_BLOCKS.put(TEMPLE_BRICK, true);
            STRUCTURE_BLOCKS.put(TEMPLE_BRICK_UNBREAKING, true);
            STRUCTURE_BLOCKS.put(TEMPLE_PILLAR, true);
            STRUCTURE_BLOCKS.put(CAPSTONE, true);
            STRUCTURE_BLOCKS.put(BAMBOO_TORCH_LOWER, true);
            STRUCTURE_BLOCKS.put(BAMBOO_TORCH_UPPER, true);
            STRUCTURE_BLOCKS.put(FORCE_FIELD, true);
            STRUCTURE_BLOCKS.put(ANTLION_SPAWNER, true);
            STRUCTURE_BLOCKS.put(MAGMA_CRAWLER_SPAWNER, true);
            STRUCTURE_BLOCKS.put(TORCH, true);
            STRUCTURE_BLOCKS.put(LAVA, true);
        }
    }
    // MARK: isSolidStructureBlock
    public boolean isSolidStructureBlock(BlockState block) {
        return STRUCTURE_BLOCKS.getOrDefault(block, false);
    }

    // MARK: place
    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        if(checkLocation(level, pos)) {
            generateStructure(level, pos, random);
            return true;
        }

        return false;
    }

    // MARK: checkLocation
    public boolean checkLocation(WorldGenLevel level, BlockPos pos) {
        //if(!checkBiome(level, pos)) return false;

        //TODO: Add in NBT Data

        return true;
    }

    // MARK: checkBiome
    public boolean checkBiome(WorldGenLevel level, BlockPos pos) {
        ResourceKey<Biome> volcanicDesert = ModBiomes.VOLCANIC_DESERT.getResourceKey();

        BlockPos north = pos.north(64);
        BlockPos south = pos.south(64);
        BlockPos east = pos.east(64);
        BlockPos west = pos.west(64);

        if(level.getBiome(north).is(volcanicDesert)) return true;
        if(level.getBiome(south).is(volcanicDesert)) return true;
        if(level.getBiome(east).is(volcanicDesert)) return true;
        return level.getBiome(west).is(volcanicDesert);
    }

    private void removeForceLoads(WorldGenLevel level, BlockPos pos) {
        BlockPos north = pos.north(64);
        BlockPos south = pos.south(64);
        BlockPos east = pos.east(64);
        BlockPos west = pos.west(64);

        level.getLevel().setChunkForced(north.getX(), north.getZ(), false);
        level.getLevel().setChunkForced(south.getX(), south.getZ(), false);
        level.getLevel().setChunkForced(east.getX(), east.getZ(), false);
        level.getLevel().setChunkForced(west.getX(), west.getZ(), false);
    }

    // MARK: generateStructure
    public void generateStructure(WorldGenLevel level, BlockPos pos, RandomSource random) {
        int sizeX = 60;
        int sizeY = 4;
        int sizeZ = 60;
        int width = sizeX / 2;
        int length = sizeZ / 2;
        int[][] maze;
        MazeGenerator generator = new PerfectMazeGenerator(width, length);
        maze = generator.generateMaze();

        buildFloor(level, pos.below(sizeY), width, length, random);
        buildRoof(level, pos, width, length, random);

        buildLevel(level, pos.offset(-sizeX, -sizeY + 1, -sizeZ), width, length, maze, GNEISS_RELIEF);
        buildLevel(level, pos.offset(-sizeX, -sizeY + 2, -sizeZ), width, length, maze, GNEISS_CARVED);
        buildLevel(level, pos.offset(-sizeX, -sizeY + 3, -sizeZ), width, length, maze, GNEISS_RELIEF);
        createAir(level, pos.below(sizeY - 1), width, length, random);
        addFeature(level, pos.offset(-sizeX, 0, -sizeZ), width, length, maze, random);
        buildCourtyard(level, TEMPLE_PILLAR, pos.below(sizeY), sizeX - 8, sizeY, sizeZ - 8);
        createPyramid(level, pos.below(sizeY), TEMPLE_BRICK_UNBREAKING, true, width - 8, length - 8);
        decoratePyramid(level, pos.offset(-width + 8, -sizeY, -length + 8));
        addTeleporters(level, pos.offset(-width + 8, -sizeY, -length + 8));
        addCapstones(level, pos.above(17));
        spawnIdolGuardians(level, pos.offset(-sizeX, -sizeY + 1, -sizeZ));
    }

    // MARK: addTeleporters
    private void addTeleporters(WorldGenLevel level, BlockPos pos) {
        // Room 1
        setBlock(level, pos.offset(13, 9, 13), CAPSTONE);
        setFloorDecoStone(level, pos.offset(14, 9, 14));
        setLockStone(level, pos.offset(15, 9, 15), TEMPLE_BRICK_UNBREAKING_EXO);
        setTeleporter(level, pos.offset(16, 9, 16), 0, pos.offset(30, 9, 13));
        setTeleporter(level, pos.offset(19, 9, 19), 5, pos.offset(13, 9, 13));

        // Room 2
        setBlock(level, pos.offset(30, 9, 13), CAPSTONE);
        setFloorDecoStone(level, pos.offset(25, 9, 14));
        setLockStone(level, pos.offset(26, 9, 15), TEMPLE_BRICK_UNBREAKING_CREAM);
        setTeleporter(level, pos.offset(27, 9, 16), 0, pos.offset(30, 9, 30));
        setTeleporter(level, pos.offset(24, 9, 19), 5, pos.offset(13, 9, 13));

        // Room 3
        setBlock(level, pos.offset(30, 9, 30), CAPSTONE);
        setFloorDecoStone(level, pos.offset(25, 9, 25));
        setLockStone(level, pos.offset(26, 9, 26), TEMPLE_BRICK_UNBREAKING_EYE);
        setTeleporter(level, pos.offset(27, 9, 27), 0, pos.offset(13, 9, 30));
        setTeleporter(level, pos.offset(24, 9, 24), 5, pos.offset(13, 9, 13));

        // Room 4
        setBlock(level, pos.offset(13, 9, 30), CAPSTONE);
        setFloorDecoStone(level, pos.offset(14, 9, 25));
        setLockStone(level, pos.offset(15, 9, 26), TEMPLE_BRICK_UNBREAKING_STRING);
        setTeleporter(level, pos.offset(16, 9, 27), 0, pos.offset(13, 9, 13));
        setTeleporter(level, pos.offset(19, 9, 24), 5, pos.offset(30, 9, 30));

        // Center
        setFloorMidDecoStone(level, pos.offset(20, 9, 20));
        setTeleporter(level, pos.offset(22, 9, 21), 6, pos.offset(5, 1, 5));
        setTeleporter(level, pos.offset(21, 9, 21), 7, pos.offset(38, 1, 5));
        setTeleporter(level, pos.offset(22, 9, 22), 8, pos.offset(38, 1, 38));
        setTeleporter(level, pos.offset(21, 9, 22), 9, pos.offset(5, 1, 38));

        // Top Level
        setBlock(level, pos.offset(19, 14, 19), CAPSTONE);
        setBlock(level, pos.offset(19, 15, 25), BAMBOO_TORCH_LOWER);
        setBlock(level, pos.offset(19, 16, 25), BAMBOO_TORCH_UPPER);
        setBlock(level, pos.offset(25, 15, 19), BAMBOO_TORCH_LOWER);
        setBlock(level, pos.offset(25, 16, 19), BAMBOO_TORCH_UPPER);
        setFloorDecoStone(level, pos.offset(20, 14, 20));
        setLockStone(level, pos.offset(21, 14, 21), TEMPLE_BRICK_UNBREAKING_JADE);
        setTeleporter(level, pos.offset(22, 14, 22), 0, pos.offset(13, 9, 13));
        setTeleporter(level, pos.offset(25, 14, 25), 5, pos.offset(19, 14, 19));
    }

    // MARK: setFloorDecoStone
    private void setFloorDecoStone(WorldGenLevel level, BlockPos pos) {
        setDecoStone(level, pos, 5);
    }

    // MARK: setFloorMidDecoStone
    private void setFloorMidDecoStone(WorldGenLevel level, BlockPos pos) {
        setDecoStone(level, pos, 4);
    }

    // MARK: setDecoStone
    private void setDecoStone(WorldGenLevel level, BlockPos pos, int size) {
        for(int x = 0; x < size; x++) {
            for(int z = 0; z < size; z++) {
                setBlock(level, pos.offset(x, 0, z), CAPSTONE);
            }
        }
    }

    // MARK: setLockStone
    private void setLockStone(WorldGenLevel level, BlockPos pos, BlockState lockStone) {
        for(int x = 0; x < 3; x++) {
            for(int z = 0; z < 3; z++) {
                setBlock(level, pos.offset(x, 0, z), lockStone);
            }
        }
    }

    // MARK: setTeleporter
    private void setTeleporter(WorldGenLevel level, BlockPos pos, int type, BlockPos target) {
        setBlock(level, pos, OtherBlocks.TEMPLE_TELEPORTER.get().defaultBlockState());
    }

    // MARK: buildFloor
    private void buildFloor(WorldGenLevel level, BlockPos pos, int width, int length, RandomSource random) {
        createPyramid(level, pos.above(5), Blocks.AIR.defaultBlockState(), true, 24, 24);

        for(int z = -length * 2; z <= length * 2; z++) {
            for(int x = -width * 2; x <= width * 2; x++) {
                BlockPos featurePos = pos.offset(x, 0, z);
                if(canPlaceFloorAt(pos, featurePos)) {
                    if(random.nextInt(15) == 0) {
                        if(random.nextBoolean() && random.nextBoolean()) {
                            setBlock(level, featurePos, LAVA);
                        } else {
                            setBlock(level, featurePos, GNEISS_VENT);
                        }
                    } else {
                        setBlock(level, featurePos, GNEISS_TILES);
                    }
                }
            }
        }
    }

    // MARK: buildRoof
    private void buildRoof(WorldGenLevel level, BlockPos pos, int width, int length, RandomSource random) {
        for(int z = -length * 2; z <= length * 2; z++) {
            for(int x = -width * 2; x <= width * 2; x++) {
                BlockPos featurePos = pos.offset(x, 0, z);
                if(canPlaceFeatureAt(pos, featurePos)) {
                    setBlock(level, featurePos, GNEISS_BRICKS);
                }
            }
        }
    }

    // MARK: createAir
    private void createAir(WorldGenLevel level, BlockPos pos, int width, int length, RandomSource random) {
        for(int z = -length * 2; z <= length * 2; z++) {
            for(int x = -width * 2; x <= width * 2; x++) {
                for(int y = 0; y <= 2; y++) {
                    if(canPlaceFeatureAt(pos, pos.offset(x, y, z))) {
                        if(!isSolidStructureBlock(level.getBlockState(pos.offset(x, y, z)))) {
                            setBlock(level, pos.offset(x, y, z), AIR);
                        }
                    }
                }
            }
        }
    }

    // MARK: decoratePyramid
    private void decoratePyramid(WorldGenLevel level, BlockPos pos) {
        boolean forceFieldSet = false;
        boolean topChestSet = false;

        for(int y = 0; y < 30; y++) {
            for(int x = 0; x < 44; x++) {
                for(int z = 0; z < 44; z++) {
                    BlockPos offset = pos.offset(x, y, z);
                    if(y == 0) setBlock(level, offset, TEMPLE_BRICK_UNBREAKING);
                    if(y == 1) {
                        if(x > 1 && x < 42 && z > 1 && z < 42) {
                            setBlock(level, offset, SAND);
                        }

                        if(x > 4 && x < 39 && z > 4 && z < 39) {
                            if(x % 11 == 5 || z % 11 == 5) {
                                setBlock(level, offset, GNEISS_VENT);
                            } else {
                                if(x >= 21 && x <= 22 && z >= 21 && z <= 22) {
                                    setBlock(level, pos.offset(offset), GNEISS_VENT);
                                } else {
                                    setBlock(level, offset, SAND);
                                }
                            }
                        }
                    }

                    if(y == 9) {
                        if(x > 9 && x < 34 && z > 9 && z < 34) {
                            setBlock(level, offset, TEMPLE_BRICK_UNBREAKING);
                        }
                    }

                    if(y == 10 && !forceFieldSet) {
                        for(int c = 0; c < 4; c++) {
                            for(int d = c; d < 9; d++) {
                                setBlock(level, pos.offset(11 + d, y + c, 21), FORCE_FIELD);
                                setBlock(level, pos.offset(11 + d, y + c, 22), FORCE_FIELD);
                                setBlock(level, pos.offset(21, y + c, 11 + d), FORCE_FIELD);
                                setBlock(level, pos.offset(22, y + c, 11 + d), FORCE_FIELD);
                                setBlock(level, pos.offset(21, y + c, 32 - d), FORCE_FIELD);
                                setBlock(level, pos.offset(22, y + c, 32 - d), FORCE_FIELD);
                                setBlock(level, pos.offset(32 - d, y + c, 21), FORCE_FIELD);
                                setBlock(level, pos.offset(32 - d, y + c, 22), FORCE_FIELD);
                            }

                            for(int dx = 20; dx < 24; dx++) {
                                for(int dz = 20; dz < 24; dz++) {
                                    setBlock(level, pos.offset(dx, y + c, dz), FORCE_FIELD);
                                }
                            }

                            for(int dx = 21; dx < 23; dx++) {
                                for(int dz = 21; dz < 23; dz++) {
                                    setBlock(level, pos.offset(dx, y + c, dz), AIR);
                                }
                            }
                        }

                        forceFieldSet = true;
                    }

                    if(y == 14) {
                        if(x > 14 && x < 29 && z > 14 && z < 29) {
                            setBlock(level, offset, TEMPLE_BRICK_UNBREAKING);
                        }
                    }

                    if(y == 15 && !topChestSet) {
                        setBlock(level, pos.offset(19, y, 19), Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.NORTH));
                        ChestBlockEntity chest = (ChestBlockEntity)level.getBlockEntity(pos.offset(19, y, 19));
                        if(chest != null) {
                            chest.setItem(0, new ItemStack(ModItems.JADE.get(), 8));
                            chest.setChanged();
                        }
                    }
                }
            }
        }
    }

    // MARK: spawnIdolGuardians
    private void spawnIdolGuardians(WorldGenLevel level, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        for(byte spawn = 0; spawn < 4; spawn++) {
            Cow cow = new Cow(EntityType.COW, level.getLevel());
            switch(spawn) {
                case 0:
                    cow.setPos(x + 2.5D, y, z + 2.5D);
                    break;
                case 1:
                    cow.setPos(x + 118.5D, y, z + 2.5D);
                    break;
                case 2:
                    cow.setPos(x + 2.5D, y, z + 118.5D);
                    break;
                case 3:
                    cow.setPos(x + 118.5D, y, z + 118.5D);
                    break;
            }
            level.getLevel().addFreshEntity(cow);
        }
    }

    // MARK: buildCourtyard
    private void buildCourtyard(WorldGenLevel level, BlockState block, BlockPos pos, int lengthX, int height, int lengthZ) {
        for(int y = 0; y <= height; y++) {
            for(int x = -lengthX / 2; x < lengthX / 2; x++) {
                for(int z = -lengthZ / 2; z < lengthZ / 2; z++) {
                    if(y > 0) {
                        if(!level.isEmptyBlock(pos.offset(x, y, z))) setBlock(level, pos.offset(x, y, z), AIR);

                        if(x == -lengthX / 2 || x == lengthX / 2 - 1) {
                            if(z > -lengthZ / 2 && z < lengthZ / 2) {
                                if(y <= 3) {
                                    for(int c = 3; c < 49; c++) {
                                        setBlock(level, pos.offset(x, y, -lengthZ / 2 + c), block);
                                    }
                                }

                                if(y == 4) {
                                    for(int c = 0; c < 52; c++) {
                                        setBlock(level, pos.offset(x, y, -lengthZ / 2 + c), TEMPLE_BRICK);
                                    }
                                }
                            }
                        }

                        if(z == -lengthZ / 2 || z == lengthZ / 2 - 1) {
                            if(x > -lengthX / 2 && x < lengthX / 2) {
                                if(y <= 3) {
                                    for(int c = 3; c < 49; c++) {
                                        setBlock(level, pos.offset(-lengthZ / 2 + c, y, z), block);
                                    }
                                }

                                if(y == 4) {
                                    for(int c = 0; c < 52; c++) {
                                        setBlock(level, pos.offset(-lengthZ / 2 + c, y, z), TEMPLE_BRICK);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // MARK: addFeature
    private void addFeature(WorldGenLevel level, BlockPos pos, int width, int length, int[][] maze, RandomSource random) {
        for(int z = 0; z < length; z++) {
            for(int x = 0; x < width; x++) {
                if((maze[x][z] & 1) == 0) {
                    if(random.nextInt(25) == 0 && canPlaceFeatureAt(pos.offset(60, 0, 60), pos.offset(1 + x * 4, -1, 1 + z * 4))) {
                        setBlock(level, pos.offset(1 + x * 4, -1, 1 + z * 4), TORCH);
                        if(random.nextInt(4) == 0) {
                            placeChest(level, pos.offset(1 + x * 4, -1, 1 + z * 4), Direction.SOUTH);
                        } else if(random.nextInt(6) == 0) {
                            placeBones(level, pos.offset(1 + x * 4, -1, 1 + z * 4), Direction.SOUTH);
                        }
                    } else if(random.nextInt(10) == 0) {
                        if(random.nextBoolean()) {
                            setBlock(level, pos.offset(2 + x * 4, -2, 2 + z * 4), ANTLION_SPAWNER);
                        } else {
                            setBlock(level, pos.offset(2 + x * 4, 2, 2 + z * 4), MAGMA_CRAWLER_SPAWNER);
                        }
                    }
                }
            }

            for(int x = 0; x < width; x++) {
                if((maze[x][z] & 8) == 0) {
                    if(random.nextInt(25) == 0 && canPlaceFeatureAt(pos.offset(60, 0, 60), pos.offset(1 + x * 4, -1, 2 + z * 4))) {
                        setBlock(level, pos.offset(1 + x * 4, 0, 2 + z * 4), TORCH);
                        if(random.nextInt(4) == 0) {
                            placeChest(level, pos.offset(1 + x * 4, -1, 2 + z * 4), Direction.EAST);
                        } else if(random.nextInt(6) == 0) {
                            placeBones(level, pos.offset(1 + x * 4, -1, 2 + z * 4), Direction.EAST);
                        }
                    }
                }
            }

            for(int x = 0; x < width; x++) {
                if((maze[x][z] & 4) == 0) {
                    if(random.nextInt(25) == 0 && canPlaceFeatureAt(pos.offset(60, 0, 60), pos.offset(3 + x * 4, -1, 2 + z * 4))) {
                        setBlock(level, pos.offset(3 + x * 4, 0, 2 + z * 4), TORCH);
                        if(random.nextInt(4) == 0) {
                            placeChest(level, pos.offset(3 + x * 4, -1, 2 + z * 4), Direction.WEST);
                        } else if(random.nextInt(6) == 0) {
                            placeBones(level, pos.offset(3 + x * 4, -1, 2 + z * 4), Direction.WEST);
                        }
                    }
                }
            }

            for(int x = 0; x < width; x++) {
                if((maze[x][z] & 2) == 0) {
                    if(random.nextInt(25) == 0 && canPlaceFeatureAt(pos.offset(60, 0, 60), pos.offset(2 + x * 4, -1, 3 + z * 4))) {
                        setBlock(level, pos.offset(2 + x * 4, 0, 3 + z * 4), TORCH);
                        if(random.nextInt(4) == 0) {
                            placeChest(level, pos.offset(2 + x * 4, -1, 3 + z * 4), Direction.NORTH);
                        } else if(random.nextInt(6) == 0) {
                            placeBones(level, pos.offset(2 + x * 4, -1, 3 + z * 4), Direction.NORTH);
                        }
                    }
                }
            }
        }
    }

    // MARK: buildLevel
    private void buildLevel(WorldGenLevel level, BlockPos pos, int width, int length, int[][] maze, BlockState block) {
        BlockPos base = pos.offset(60, 0, 60);
        for(int z = 0; z < length; z++) {
            for(int x = 0; x < width; x++) {
                if((maze[x][z] & 1) == 0) {
                    if(canPlaceFeatureAt(base, pos.offset(x * 4, 0, z * 4))) {
                        if(canPlaceFeatureAt(base, pos.offset(x * 4 + 1, 0, z * 4))) {
                            if(canPlaceFeatureAt(base, pos.offset(x * 4 + 2, 0, z * 4))) {
                                if(canPlaceFeatureAt(base, pos.offset(x * 4 + 3, 0, z * 4))) {
                                    setBlock(level, pos.offset(x * 4, 0, z * 4), block);
                                    setBlock(level, pos.offset(x * 4 + 1, 0, z * 4), block);
                                    setBlock(level, pos.offset(x * 4 + 2, 0, z * 4), block);
                                    setBlock(level, pos.offset(x * 4 + 3, 0, z * 4), block);
                                }
                            }
                        }
                    } else if (canPlaceFeatureAt(base, pos.offset(x * 4, 1, z * 4))) {
                        setBlock(level, pos.offset(x * 4, 1, z * 4), block);
                    }
                }
            }

            for(int x = 0; x < width; x++) {
                if((maze[x][z] & 8) == 0) {
                    if(canPlaceFeatureAt(base, pos.offset(x * 4, 0, z * 4 + 1))) {
                        if(canPlaceFeatureAt(base, pos.offset(x * 4, 0, z * 4 + 2))) {
                            if(canPlaceFeatureAt(base, pos.offset(x * 4, 0, z * 4 + 3))) {
                                setBlock(level, pos.offset(x * 4, 0, z * 4), block);
                                setBlock(level, pos.offset(x * 4, 0, z * 4 + 1), block);
                                setBlock(level, pos.offset(x * 4, 0, z * 4 + 2), block);
                                setBlock(level, pos.offset(x * 4, 0, z * 4 + 3), block);
                            }
                        }
                    }
                }
            }

            if(canPlaceFeatureAt(base, pos.offset(width * 4, 0, z * 4))) {
                if(canPlaceFeatureAt(base, pos.offset(width * 4, 0, z * 4 + 1))) {
                    if(canPlaceFeatureAt(base, pos.offset(width * 4, 0, z * 4 + 2))) {
                        if(canPlaceFeatureAt(base, pos.offset(width * 4, 0, z * 4 + 3))) {
                            setBlock(level, pos.offset(width * 4, 0, z * 4), block);
                            setBlock(level, pos.offset(width * 4, 0, z * 4 + 1), block);
                            setBlock(level, pos.offset(width * 4, 0, z * 4 + 2), block);
                            setBlock(level, pos.offset(width * 4, 0, z * 4 + 3), block);
                        }
                    }
                }
            }
        }

        for(int x = 0; x <= width * 4; x++) {
            if(canPlaceFeatureAt(base, pos.offset(x * 4, 0, length * 4))) {
                setBlock(level, pos.offset(x * 4, 0, length * 4), block);
            }
        }
    }

    // MARK: addCapstones
    private void addCapstones(WorldGenLevel level, BlockPos pos) {
        setBlock(level, pos.north().west(), OtherBlocks.CAPSTONE_MUD.get().defaultBlockState());
        setBlock(level, pos.north(), OtherBlocks.CAPSTONE_IRON.get().defaultBlockState());
        setBlock(level, pos.west(), OtherBlocks.CAPSTONE_GOLD.get().defaultBlockState());
        setBlock(level, pos, OtherBlocks.CAPSTONE_JADE.get().defaultBlockState());
    }

    private void createPyramid(WorldGenLevel level, BlockPos pos, BlockState block, boolean isHollow, int baseX, int baseZ) {
        for(int y = 0; y < 21; y++) {
            int maxX = baseX - 1;
            int maxZ = baseZ - 1;

            for(int x = -baseX; x <= maxX; x++) {
                for(int z = -baseZ; z <= maxZ; z++) {
                    BlockPos offset = pos.offset(x, y, z);
                    if(x == -baseX || x == maxX || z == -baseZ || z == maxZ) {
                        if(!isSolidStructureBlock(level.getBlockState(offset))) {
                            setBlock(level, offset, block);
                        } else if(isHollow) {
                            if(!isSolidStructureBlock(level.getBlockState(offset))) {
                                if(!level.isEmptyBlock(offset)) {
                                    setBlock(level, offset, AIR);
                                }
                            }
                        }
                    }
                }
            }

            baseX--;
            baseZ--;
        }
    }

    // MARK: placeChest
    private void placeChest(WorldGenLevel level, BlockPos pos, Direction direction) {
        setBlock(level, pos, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, direction));
        ChestBlockEntity chest = (ChestBlockEntity) level.getBlockEntity(pos);
        if(chest != null) {
            chest.setLootTable(ModChestLootTables.ANTLION_DUNGEON);
        }
    }

    // MARK: placeBones
    private void placeBones(WorldGenLevel level, BlockPos pos, Direction direction) {
        setBlock(level, pos, OtherBlocks.BLOCK_OF_BONES.get().defaultBlockState().setValue(BlockOfBonesBlock.FACING, direction));
        BlockOfBonesBlockEntity bones = (BlockOfBonesBlockEntity) level.getBlockEntity(pos);
        if(bones != null) {
            //bones.setLootTable(ModChestLootTables.ANTLION_DUNGEON);
        }
    }

    // MARK: canPlaceAt
    private boolean canPlaceAt(BlockPos pos, BlockPos featurePos, int size) {
        for(int x = pos.getX() - size; x < pos.getX() + size; x++) {
            for(int z = pos.getZ() - size; z < pos.getZ() + size; z++) {
                if(x == featurePos.getX() && z == featurePos.getZ()) return false;
            }
        }
        return true;
    }

    // MARK: canPlaceFeatureAt
    private boolean canPlaceFeatureAt(BlockPos pos, BlockPos featurePos) {
        return canPlaceAt(pos, featurePos, 26);
    }

    // MARK: canPlaceFloorAt
    private boolean canPlaceFloorAt(BlockPos pos, BlockPos featurePos) {
        return canPlaceAt(pos, featurePos, 22);
    }
}
