package erebus.world.feature.old_structure.config;

import erebus.block.BlockOfBonesBlock;
import erebus.block.bamboo.BambooTorchBlock;
import erebus.block.entity.BlockOfBonesBlockEntity;
import erebus.block.types.EnumTorchBlockHalf;
import erebus.datagen.loot.ModChestLootTables;
import erebus.registries.item.ModItems;
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

/**
 * Antlion Dungeon Feature Configuration
 * 
 * This class is responsible for generating the Antlion Dungeon structure in the Erebus dimension.
 * The structure consists of:
 * - A maze-like underground dungeon with multiple levels
 * - A central pyramid structure with teleporters
 * - A courtyard surrounding the pyramid
 * - Various decorative and functional elements (chests, spawners, etc.)
 * 
 * The generation process uses a perfect maze algorithm to create the dungeon layout.
 */
public class AntlionDungeonFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    // Block states used throughout the structure
    private final BlockState GNEISS = ModBlocks.GNEISS.get().defaultBlockState();
    private final BlockState GNEISS_RELIEF = ModBlocks.GNEISS_RELIEF.get().defaultBlockState();
    private final BlockState GNEISS_CARVED = ModBlocks.GNEISS_CARVED.get().defaultBlockState();
    private final BlockState GNEISS_BRICKS = ModBlocks.GNEISS_BRICKS.get().defaultBlockState();
    private final BlockState GNEISS_TILES = ModBlocks.GNEISS_TILES.get().defaultBlockState();
    private final BlockState GNEISS_VENT = ModBlocks.GNEISS_VENT.get().defaultBlockState();
    private final BlockState TEMPLE_BRICK = ModBlocks.TEMPLE_BRICK.get().defaultBlockState();
    private final BlockState TEMPLE_BRICK_UNBREAKING = ModBlocks.TEMPLE_BRICK_UNBREAKING.get().defaultBlockState();
    private final BlockState TEMPLE_BRICK_UNBREAKING_JADE = ModBlocks.TEMPLE_BRICK_UNBREAKING_JADE.get().defaultBlockState();
    private final BlockState TEMPLE_BRICK_UNBREAKING_EXO = ModBlocks.TEMPLE_BRICK_UNBREAKING_EXO.get().defaultBlockState();
    private final BlockState TEMPLE_BRICK_UNBREAKING_CREAM = ModBlocks.TEMPLE_BRICK_UNBREAKING_CREAM.get().defaultBlockState();
    private final BlockState TEMPLE_BRICK_UNBREAKING_EYE = ModBlocks.TEMPLE_BRICK_UNBREAKING_EYE.get().defaultBlockState();
    private final BlockState TEMPLE_BRICK_UNBREAKING_STRING = ModBlocks.TEMPLE_BRICK_UNBREAKING_STRING.get().defaultBlockState();
    private final BlockState TEMPLE_PILLAR = ModBlocks.TEMPLE_PILLAR.get().defaultBlockState();
    private final BlockState CAPSTONE = ModBlocks.CAPSTONE.get().defaultBlockState();
    private final BlockState BAMBOO_TORCH_LOWER = ModBlocks.BAMBOO_TORCH.get().defaultBlockState().setValue(BambooTorchBlock.HALF, EnumTorchBlockHalf.LOWER);
    private final BlockState BAMBOO_TORCH_UPPER = ModBlocks.BAMBOO_TORCH.get().defaultBlockState().setValue(BambooTorchBlock.HALF, EnumTorchBlockHalf.UPPER);
    private final BlockState FORCE_FIELD = ModBlocks.FORCE_FIELD.get().defaultBlockState();
    private final BlockState ANTLION_SPAWNER = ModBlocks.ANTLION_SPAWNER.get().defaultBlockState();
    private final BlockState MAGMA_CRAWLER_SPAWNER = ModBlocks.MAGMA_CRAWLER_SPAWNER.get().defaultBlockState();
    private final BlockState TORCH = Blocks.TORCH.defaultBlockState();
    private final BlockState LAVA = Blocks.LAVA.defaultBlockState();
    private final BlockState SAND = Blocks.SAND.defaultBlockState();
    private final BlockState AIR = Blocks.AIR.defaultBlockState();

    // Map to track which blocks are considered part of the structure
    private final Map<BlockState, Boolean> STRUCTURE_BLOCKS = new HashMap<>();

    /**
     * Constructor for the Antlion Dungeon feature.
     * Initializes the structure blocks map with all blocks that are considered part of the structure.
     * This is used to determine which blocks should not be replaced during generation.
     */
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

    /**
     * Checks if a block is part of the structure and should not be replaced during generation.
     * 
     * @param block The block state to check
     * @return true if the block is part of the structure, false otherwise
     */
    public boolean isSolidStructureBlock(BlockState block) {
        return STRUCTURE_BLOCKS.getOrDefault(block, false);
    }

    /**
     * Main method called to place the structure in the world.
     * Checks if the location is valid and then generates the structure.
     * 
     * @param context The context containing level, position, and random source
     * @return true if the structure was successfully placed, false otherwise
     */
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

    /**
     * Checks if the location is valid for placing the structure.
     * Currently always returns true, but could be extended to check biome or other conditions.
     * 
     * @param level The world generation level
     * @param pos The position to check
     * @return true if the location is valid, false otherwise
     */
    public boolean checkLocation(WorldGenLevel level, BlockPos pos) {
        //if(!checkBiome(level, pos)) return false;

        //TODO: Add in NBT Data to prevent duplicate structures

        return true;
    }

    /**
     * Checks if the structure is near a Volcanic Desert biome.
     * Looks in four cardinal directions at a distance of 64 blocks.
     * 
     * @param level The world generation level
     * @param pos The position to check from
     * @return true if any of the checked positions are in a Volcanic Desert biome
     */
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

    /**
     * Removes force-loaded chunks after structure generation.
     * 
     * @param level The world generation level
     * @param pos The central position of the structure
     */
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

    /**
     * Main method for generating the entire Antlion Dungeon structure.
     * Coordinates the generation of all components:
     * - Maze levels
     * - Floor and roof
     * - Courtyard
     * - Pyramid
     * - Decorations and features
     * 
     * @param level The world generation level
     * @param pos The central position for the structure
     * @param random Random source for variation
     */
    public void generateStructure(WorldGenLevel level, BlockPos pos, RandomSource random) {
        // Define structure dimensions
        int sizeX = 60;
        int sizeY = 4;
        int sizeZ = 60;
        int width = sizeX / 2;
        int length = sizeZ / 2;

        // Generate the maze layout
        int[][] maze;
        MazeGenerator generator = new PerfectMazeGenerator(width, length);
        maze = generator.generateMaze();

        // Build the main structure components
        buildFloor(level, pos.below(sizeY), width, length, random);
        buildRoof(level, pos, width, length, random);

        // Build the three maze levels with different block types
        buildLevel(level, pos.offset(-sizeX, -sizeY + 1, -sizeZ), width, length, maze, GNEISS_RELIEF);
        buildLevel(level, pos.offset(-sizeX, -sizeY + 2, -sizeZ), width, length, maze, GNEISS_CARVED);
        buildLevel(level, pos.offset(-sizeX, -sizeY + 3, -sizeZ), width, length, maze, GNEISS_RELIEF);

        // Create air pockets and add features to the maze
        createAir(level, pos.below(sizeY - 1), width, length, random);
        addFeature(level, pos.offset(-sizeX, 0, -sizeZ), width, length, maze, random);

        // Build the courtyard and central pyramid
        buildCourtyard(level, TEMPLE_PILLAR, pos.below(sizeY), sizeX - 8, sizeY, sizeZ - 8);
        createPyramid(level, pos.below(sizeY), TEMPLE_BRICK_UNBREAKING, true, width - 8, length - 8);

        // Add decorations and special features
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

    /**
     * Places a teleporter block at the specified position.
     * The teleporter can be configured with a type and target position.
     * 
     * @param level The world generation level
     * @param pos The position to place the teleporter
     * @param type The type of teleporter (determines appearance or behavior)
     * @param target The target position that this teleporter links to
     */
    private void setTeleporter(WorldGenLevel level, BlockPos pos, int type, BlockPos target) {
        // TODO: Implement teleporter type and target functionality
        setBlock(level, pos, ModBlocks.TEMPLE_TELEPORTER.get().defaultBlockState());
    }

    /**
     * Builds the floor of the dungeon structure.
     * Creates a hollow pyramid in the center and places floor tiles with occasional lava or vents.
     * 
     * @param level The world generation level
     * @param pos The base position for the floor
     * @param width The width of the floor area
     * @param length The length of the floor area
     * @param random Random source for variation
     */
    private void buildFloor(WorldGenLevel level, BlockPos pos, int width, int length, RandomSource random) {
        // Create a hollow pyramid in the center of the floor
        createPyramid(level, pos.above(5), Blocks.AIR.defaultBlockState(), true, 24, 24);

        // Place floor tiles throughout the area
        for(int z = -length * 2; z <= length * 2; z++) {
            for(int x = -width * 2; x <= width * 2; x++) {
                BlockPos featurePos = pos.offset(x, 0, z);
                if(canPlaceFloorAt(pos, featurePos)) {
                    // 1/15 chance to place lava or a vent
                    if(random.nextInt(15) == 0) {
                        if(random.nextBoolean() && random.nextBoolean()) {
                            setBlock(level, featurePos, LAVA);
                        } else {
                            setBlock(level, featurePos, GNEISS_VENT);
                        }
                    } else {
                        // Otherwise place a normal floor tile
                        setBlock(level, featurePos, GNEISS_TILES);
                    }
                }
            }
        }
    }

    /**
     * Builds the roof of the dungeon structure.
     * Places brick blocks throughout the roof area.
     * 
     * @param level The world generation level
     * @param pos The base position for the roof
     * @param width The width of the roof area
     * @param length The length of the roof area
     * @param random Random source for variation
     */
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

    /**
     * Creates air pockets within the dungeon structure.
     * Replaces non-structure blocks with air to create open spaces.
     * 
     * @param level The world generation level
     * @param pos The base position for creating air
     * @param width The width of the area
     * @param length The length of the area
     * @param random Random source for variation
     */
    private void createAir(WorldGenLevel level, BlockPos pos, int width, int length, RandomSource random) {
        // Create air in a 3-block high space
        for(int z = -length * 2; z <= length * 2; z++) {
            for(int x = -width * 2; x <= width * 2; x++) {
                for(int y = 0; y <= 2; y++) {
                    if(canPlaceFeatureAt(pos, pos.offset(x, y, z))) {
                        // Only replace non-structure blocks with air
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

    /**
     * Adds features (torches, chests, bones, spawners) to the maze structure.
     * Features are placed based on the maze layout and random chance.
     * 
     * @param level The world generation level
     * @param pos The base position for feature placement
     * @param width The width of the maze
     * @param length The length of the maze
     * @param maze The 2D array representing the maze layout
     * @param random Random source for variation
     */
    private void addFeature(WorldGenLevel level, BlockPos pos, int width, int length, int[][] maze, RandomSource random) {
        BlockPos basePos = pos.offset(60, 0, 60);

        // Process each cell in the maze
        for(int z = 0; z < length; z++) {
            for(int x = 0; x < width; x++) {
                // Check for south passage
                if((maze[x][z] & 1) == 0) {
                    // Try to place torch and chest/bones
                    tryPlaceFeature(level, basePos, pos, x, z, 1, 1, Direction.SOUTH, random);

                    // Randomly place spawners
                    if(random.nextInt(10) == 0) {
                        if(random.nextBoolean()) {
                            setBlock(level, pos.offset(2 + x * 4, -2, 2 + z * 4), ANTLION_SPAWNER);
                        } else {
                            setBlock(level, pos.offset(2 + x * 4, 2, 2 + z * 4), MAGMA_CRAWLER_SPAWNER);
                        }
                    }
                }

                // Check for east passage
                if((maze[x][z] & 8) == 0) {
                    tryPlaceFeature(level, basePos, pos, x, z, 1, 2, Direction.EAST, random);
                }

                // Check for west passage
                if((maze[x][z] & 4) == 0) {
                    tryPlaceFeature(level, basePos, pos, x, z, 3, 2, Direction.WEST, random);
                }

                // Check for north passage
                if((maze[x][z] & 2) == 0) {
                    tryPlaceFeature(level, basePos, pos, x, z, 2, 3, Direction.NORTH, random);
                }
            }
        }
    }

    /**
     * Helper method to try placing a feature (torch, chest, or bones) at a specific position.
     * 
     * @param level The world generation level
     * @param basePos The base position for feature placement checks
     * @param pos The base position for the maze
     * @param x The x coordinate in the maze
     * @param z The z coordinate in the maze
     * @param xOffset The x offset within the cell
     * @param zOffset The z offset within the cell
     * @param direction The direction the feature should face
     * @param random Random source for variation
     */
    private void tryPlaceFeature(WorldGenLevel level, BlockPos basePos, BlockPos pos, 
                                int x, int z, int xOffset, int zOffset, Direction direction, RandomSource random) {
        BlockPos featurePos = pos.offset(xOffset + x * 4, -1, zOffset + z * 4);

        // Only place features with a 1/25 chance and if the position is valid
        if(random.nextInt(25) == 0 && canPlaceFeatureAt(basePos, featurePos)) {
            // Place torch
            setBlock(level, featurePos, TORCH);

            // 1/4 chance to place a chest
            if(random.nextInt(4) == 0) {
                placeChest(level, featurePos, direction);
            } 
            // 1/6 chance to place bones
            else if(random.nextInt(6) == 0) {
                placeBones(level, featurePos, direction);
            }
        }
    }

    /**
     * Builds a level of the maze structure using the provided maze layout.
     * Places blocks to form the walls of the maze.
     * 
     * @param level The world generation level
     * @param pos The base position for the level
     * @param width The width of the maze
     * @param length The length of the maze
     * @param maze The 2D array representing the maze layout
     * @param block The block state to use for the walls
     */
    private void buildLevel(WorldGenLevel level, BlockPos pos, int width, int length, int[][] maze, BlockState block) {
        BlockPos base = pos.offset(60, 0, 60);

        // Process each cell in the maze
        for(int z = 0; z < length; z++) {
            for(int x = 0; x < width; x++) {
                // Check for south passage
                if((maze[x][z] & 1) == 0) {
                    tryPlaceWallRow(level, base, pos, x, z, 0, 0, 4, 0, block);
                }

                // Check for east passage
                if((maze[x][z] & 8) == 0) {
                    tryPlaceWallRow(level, base, pos, x, z, 0, 0, 0, 4, block);
                }
            }

            // Place the eastern boundary wall
            tryPlaceWallRow(level, base, pos, width, z, 0, 0, 0, 4, block);
        }

        // Place the southern boundary wall
        for(int x = 0; x <= width; x++) {
            if(canPlaceFeatureAt(base, pos.offset(x * 4, 0, length * 4))) {
                setBlock(level, pos.offset(x * 4, 0, length * 4), block);
            }
        }
    }

    /**
     * Helper method to try placing a row of wall blocks.
     * Checks if all positions are valid before placing any blocks.
     * 
     * @param level The world generation level
     * @param base The base position for feature placement checks
     * @param pos The base position for the maze
     * @param x The x coordinate in the maze
     * @param z The z coordinate in the maze
     * @param xStart The starting x offset within the cell
     * @param zStart The starting z offset within the cell
     * @param xLength The length of the wall in the x direction
     * @param zLength The length of the wall in the z direction
     * @param block The block state to use for the wall
     */
    private void tryPlaceWallRow(WorldGenLevel level, BlockPos base, BlockPos pos, 
                               int x, int z, int xStart, int zStart, int xLength, int zLength, BlockState block) {
        // Check if all positions are valid
        boolean canPlace = true;

        for(int i = 0; i <= Math.max(xLength, zLength); i++) {
            int xOffset = xStart + (xLength > 0 ? i : 0);
            int zOffset = zStart + (zLength > 0 ? i : 0);

            if(!canPlaceFeatureAt(base, pos.offset(x * 4 + xOffset, 0, z * 4 + zOffset))) {
                canPlace = false;
                break;
            }
        }

        // If all positions are valid, place the blocks
        if(canPlace) {
            for(int i = 0; i <= Math.max(xLength, zLength); i++) {
                int xOffset = xStart + (xLength > 0 ? i : 0);
                int zOffset = zStart + (zLength > 0 ? i : 0);

                setBlock(level, pos.offset(x * 4 + xOffset, 0, z * 4 + zOffset), block);
            }
        } 
        // Try placing at a higher y-level if ground level doesn't work
        else if(xLength == 0 && zLength == 0 && canPlaceFeatureAt(base, pos.offset(x * 4, 1, z * 4))) {
            setBlock(level, pos.offset(x * 4, 1, z * 4), block);
        }
    }

    /**
     * Adds capstones to the top of the pyramid structure.
     * These are special blocks that serve as markers or decorative elements.
     * 
     * @param level The world generation level
     * @param pos The position to place the capstones
     */
    private void addCapstones(WorldGenLevel level, BlockPos pos) {
        setBlock(level, pos.north().west(), ModBlocks.CAPSTONE_MUD.get().defaultBlockState());
        setBlock(level, pos.north(), ModBlocks.CAPSTONE_IRON.get().defaultBlockState());
        setBlock(level, pos.west(), ModBlocks.CAPSTONE_GOLD.get().defaultBlockState());
        setBlock(level, pos, ModBlocks.CAPSTONE_JADE.get().defaultBlockState());
    }

    /**
     * Creates a pyramid structure with the specified dimensions.
     * The pyramid can be hollow or solid.
     * 
     * @param level The world generation level
     * @param pos The base position of the pyramid
     * @param block The block state to use for the pyramid walls
     * @param isHollow Whether the pyramid should be hollow
     * @param baseX The base width of the pyramid in the X direction
     * @param baseZ The base width of the pyramid in the Z direction
     */
    private void createPyramid(WorldGenLevel level, BlockPos pos, BlockState block, boolean isHollow, int baseX, int baseZ) {
        // Build the pyramid layer by layer, starting from the bottom
        for(int y = 0; y < 21; y++) {
            int maxX = baseX - 1;
            int maxZ = baseZ - 1;

            // For each layer, build the perimeter
            for(int x = -baseX; x <= maxX; x++) {
                for(int z = -baseZ; z <= maxZ; z++) {
                    BlockPos offset = pos.offset(x, y, z);

                    // Only place blocks on the perimeter of the current layer
                    if(x == -baseX || x == maxX || z == -baseZ || z == maxZ) {
                        if(!isSolidStructureBlock(level.getBlockState(offset))) {
                            setBlock(level, offset, block);
                        } else if(isHollow) {
                            // If the pyramid is hollow, clear non-structure blocks inside
                            if(!isSolidStructureBlock(level.getBlockState(offset))) {
                                if(!level.isEmptyBlock(offset)) {
                                    setBlock(level, offset, AIR);
                                }
                            }
                        }
                    }
                }
            }

            // Decrease the size of the next layer to create the pyramid shape
            baseX--;
            baseZ--;
        }
    }

    /**
     * Places a chest with the Antlion Dungeon loot table.
     * 
     * @param level The world generation level
     * @param pos The position to place the chest
     * @param direction The direction the chest should face
     */
    private void placeChest(WorldGenLevel level, BlockPos pos, Direction direction) {
        setBlock(level, pos, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, direction));
        ChestBlockEntity chest = (ChestBlockEntity) level.getBlockEntity(pos);
        if(chest != null) {
            chest.setLootTable(ModChestLootTables.ANTLION_DUNGEON);
        }
    }

    /**
     * Places a block of bones with the specified facing direction.
     * 
     * @param level The world generation level
     * @param pos The position to place the bones
     * @param direction The direction the bones should face
     */
    private void placeBones(WorldGenLevel level, BlockPos pos, Direction direction) {
        setBlock(level, pos, ModBlocks.BLOCK_OF_BONES.get().defaultBlockState().setValue(BlockOfBonesBlock.FACING, direction));
        BlockOfBonesBlockEntity bones = (BlockOfBonesBlockEntity) level.getBlockEntity(pos);
        // TODO: Implement loot table for bones if needed
        // if(bones != null) {
        //     bones.setLootTable(ModChestLootTables.ANTLION_DUNGEON);
        // }
    }

    /**
     * Checks if a feature can be placed at a specific position.
     * Ensures that features don't overlap by checking if the position is within a certain distance of another feature.
     * 
     * @param pos The position of the existing feature
     * @param featurePos The position to check for placement
     * @param size The minimum distance required between features
     * @return true if the feature can be placed, false otherwise
     */
    private boolean canPlaceAt(BlockPos pos, BlockPos featurePos, int size) {
        // Check if the feature position is within the exclusion zone of another feature
        for(int x = pos.getX() - size; x < pos.getX() + size; x++) {
            for(int z = pos.getZ() - size; z < pos.getZ() + size; z++) {
                if(x == featurePos.getX() && z == featurePos.getZ()) return false;
            }
        }
        return true;
    }

    /**
     * Checks if a general feature can be placed at a specific position.
     * Uses a standard exclusion zone size of 26 blocks.
     * 
     * @param pos The position of the existing feature
     * @param featurePos The position to check for placement
     * @return true if the feature can be placed, false otherwise
     */
    private boolean canPlaceFeatureAt(BlockPos pos, BlockPos featurePos) {
        return canPlaceAt(pos, featurePos, 26);
    }

    /**
     * Checks if a floor element can be placed at a specific position.
     * Uses a smaller exclusion zone size of 22 blocks.
     * 
     * @param pos The position of the existing feature
     * @param featurePos The position to check for placement
     * @return true if the floor element can be placed, false otherwise
     */
    private boolean canPlaceFloorAt(BlockPos pos, BlockPos featurePos) {
        return canPlaceAt(pos, featurePos, 22);
    }
}
