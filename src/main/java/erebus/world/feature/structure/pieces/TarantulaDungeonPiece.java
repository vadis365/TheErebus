package erebus.world.feature.structure.pieces;

import erebus.block.BarkLogBlock;
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
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import org.jspecify.annotations.NonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class TarantulaDungeonPiece extends ScatteredFeaturePiece implements TerrainCheckedPiece {
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
    private final long layoutSeed;
    private final Set<Long> processedChunks = new HashSet<>();
    private boolean guardianSpawned;
    private boolean validated, rejected;

    public TarantulaDungeonPiece(RandomSource random, BlockPos base) {
        super(ModStructurePieces.TARANTULA_DUNGEON.get(), base.getX() - 15, base.getY(), base.getZ() - 15, 31, 31, 31, getRandomHorizontalDirection(random));
        layoutSeed = random.nextLong();
        boundingBox = new BoundingBox(boundingBox.minX(), boundingBox.minY() - 14, boundingBox.minZ(),
                boundingBox.maxX(), boundingBox.minY() + 31, boundingBox.maxZ());
    }

    public TarantulaDungeonPiece(StructurePieceSerializationContext ignoredContext, CompoundTag tag) {
        super(ModStructurePieces.TARANTULA_DUNGEON.get(), tag);
        guardianSpawned = tag.getBooleanOr("GuardianSpawned", false);
        validated = tag.getBooleanOr("Validated", false);
        rejected = tag.getBooleanOr("Rejected", false);
        layoutSeed = tag.getLongOr("LayoutSeed", 0);
        for (long chunk : tag.getLongArray("ProcessedChunks").orElse(new long[0])) processedChunks.add(chunk);
    }

    @Override
    public synchronized boolean isRejected() {
        return rejected;
    }

    public static boolean soil(BlockState state) {
        return state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.SAND) || state.is(Blocks.RED_SAND);
    }

    public static boolean validSite(BlockPos base, Function<BlockPos, BlockState> blocks,
                                    Predicate<BlockPos> inBounds) {
        if (!inBounds.test(base.below(14)) || !inBounds.test(base.above(31))) return false;
        // Match the actual rounded radius-13 trunk floor, not its square bounds.
        for (int x = -13; x <= 13; x++)
            for (int z = -13; z <= 13; z++) {
                if (Math.round(Math.sqrt(x * x + z * z)) > 13) continue;
                var pos = base.offset(x, 0, z);
                if (!inBounds.test(pos) || !soil(blocks.apply(pos))) return false;
            }
        // Reference above-ground clearance, including fluids as obstructions.
        for (int x = -14; x <= 14; x++)
            for (int z = -14; z <= 14; z++)
                for (int y = 1; y < 28; y++) {
                    var pos = base.offset(x, y, z);
                    if (!inBounds.test(pos)) return false;
                    var state = blocks.apply(pos);
                    if (!state.canBeReplaced() || !state.getFluidState().isEmpty()) return false;
                }
        // The reference clearance box stops below the highest canopy layers.
        int[][] crowns = {{10, 29, 0}, {-10, 29, 0}, {0, 29, 10}, {0, 29, -10}, {7, 27, 7}, {-7, 27, 7}, {7, 27, -7}, {-7, 27, -7}};
        for (var crown : crowns)
            for (int x = -5; x <= 5; x++)
                for (int z = -5; z <= 5; z++)
                    for (int y = -3; y < 3; y++) {
                        int relativeY = crown[1] + y;
                        if (relativeY < 28) continue;
                        int distance = x * x + y * y + z * z;
                        if (distance >= 9 && Math.round(Math.sqrt(distance)) < 5
                                && !clear(base.offset(crown[0] + x, relativeY, crown[2] + z), blocks, inBounds)) return false;
                    }
        // Outer entrances extend one block past that box on each side.
        for (int side : new int[]{-15, 15})
            for (int offset = -1; offset <= 1; offset++)
                for (int y = 0; y <= 4; y++) {
                    for (var pos : new BlockPos[]{base.offset(side, y, offset), base.offset(offset, y, side)}) {
                        if (y == 0 && inBounds.test(pos) && soil(blocks.apply(pos))) continue;
                        if (!clear(pos, blocks, inBounds)) return false;
                    }
                }
        return true;
    }

    private static boolean clear(BlockPos pos, Function<BlockPos, BlockState> blocks,
                                 Predicate<BlockPos> inBounds) {
        if (!inBounds.test(pos)) return false;
        var state = blocks.apply(pos);
        return state.canBeReplaced() && state.getFluidState().isEmpty();
    }

    public static boolean canReplaceRoot(BlockState state) {
        if (state.hasBlockEntity() || !state.getFluidState().isEmpty() || state.is(BlockTags.LOGS)) return false;
        return state.isAir() || state.is(Blocks.RED_SANDSTONE)
                || state.is(BlockTags.DIRT) || state.is(BlockTags.GRASS_BLOCKS)
                || state.is(BlockTags.MUD) || state.is(BlockTags.MOSS_BLOCKS)
                || state.is(BlockTags.SAND) || state.is(Blocks.FARMLAND) || state.is(Blocks.DIRT_PATH)
                || state.is(ModBlocks.MUD) || state.is(BlockTags.REPLACEABLE_BY_TREES);
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag) {
        super.addAdditionalSaveData(context, tag);
        tag.putBoolean("GuardianSpawned", guardianSpawned);
        tag.putBoolean("Validated", validated);
        tag.putBoolean("Rejected", rejected);
        tag.putLong("LayoutSeed", layoutSeed);
        tag.putLongArray("ProcessedChunks", processedChunks.stream().mapToLong(Long::longValue).toArray());
    }

    @Override
    protected int getWorldY(int y) {
        return getOrientation() == null ? y : boundingBox.minY() + 14 + y;
    }

    public BlockPos base() {
        return getWorldPos(15, 0, 15).immutable();
    }

    public BlockPos guardianPos() {
        return getWorldPos(15, 22, 15).immutable();
    }

    public synchronized void placeGuardian(WorldGenLevel level, BoundingBox clip, RandomSource random) {
        var pos = guardianPos();
        if (guardianSpawned || !clip.isInside(pos)) return;
        var guardian = ModEntities.TARANTULA_MINI_BOSS.get().create(level.getLevel(), EntitySpawnReason.STRUCTURE);
        if (guardian == null) return;
        guardian.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        if (!level.noCollision(guardian) || level.containsAnyLiquid(guardian.getBoundingBox())) return;
        guardian.setYRot(random.nextFloat() * 360);
        guardian.setPersistenceRequired();
        guardianSpawned = level.addFreshEntity(guardian);
    }

    @Override
    public synchronized void postProcess(@NonNull WorldGenLevel level, @NonNull StructureManager structureManager, @NonNull ChunkGenerator generator, @NonNull RandomSource random, @NonNull BoundingBox chunkBB, @NonNull ChunkPos chunkPos, @NonNull BlockPos pos) {
        if (rejected) return;
        if (!validated) {
            if (!validSite(base(), level::getBlockState, p -> !level.isOutsideBuildHeight(p))) {
                rejected = true;
                return;
            }
            validated = true;
        }
        long chunkKey = ((long) chunkPos.x() << 32) ^ (chunkPos.z() & 0xffffffffL);
        if (processedChunks.contains(chunkKey)) {
            placeGuardian(level, chunkBB, RandomSource.create(layoutSeed ^ 4));
            return;
        }
        generateTrunk(level, chunkBB);
        generateLeaves(level, RandomSource.create(layoutSeed ^ 1), chunkBB);
        generateRoots(level, RandomSource.create(layoutSeed ^ 2), chunkBB);
        generateGroundFloorVines(level, chunkBB);
        generate2ndFloorHoles(level, chunkBB);
        generateFirstFloorVines(level, chunkBB);
        generateSpawners(level, RandomSource.create(layoutSeed ^ 3), chunkBB);
        addEntranceDecoration(level, chunkBB);
        placeGuardian(level, chunkBB, RandomSource.create(layoutSeed ^ 4));
        processedChunks.add(chunkKey);
    }

    @Override
    protected void placeBlock(WorldGenLevel level, BlockState state, int x, int y, int z, BoundingBox clip) {
        super.placeBlock(level, state, x, y, z, clip);
        // StructurePiece's shape-check list names vanilla fences explicitly.
        // Queue our fence too, so connections resolve after adjacent chunks exist.
        var pos = getWorldPos(x, y, z);
        if (state.is(ModBlocks.FENCE_EUCALYPTUS) && clip.isInside(pos))
            level.getChunk(pos).markPosForPostprocessing(pos);
    }

    private void generateTrunk(WorldGenLevel level, BoundingBox bb) {
        int BASE_RADIUS = 14;
        int radius = BASE_RADIUS - 1;

        for (int y = 0; y <= height; y++) {
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    double dSq = x * x + z * z;
                    double rounded = Math.round(Math.sqrt(dSq));

                    int LAYER_1 = 4;
                    if (y <= LAYER_1) {
                        if (rounded == radius || rounded <= radius - 1 && y <= 2)
                            placeBlock(level, LOG, 15 + x, y, 15 + z, bb);

                        placeBlock(level, LOG, 15, y, 15, bb);
                    }

                    int LAYER_2 = 7;
                    if (y <= LAYER_2 && y > LAYER_1) {
                        if (rounded == radius - 1)
                            placeBlock(level, LOG, 15 + x, y, 15 + z, bb);
                        placeBlock(level, LOG, 15, y, 15, bb);
                    }

                    int LAYER_3 = 9;
                    if (y <= LAYER_3 && y > LAYER_2) {
                        if (rounded == radius - 2)
                            placeBlock(level, LOG, 15 + x, y, 15 + z, bb);
                        placeBlock(level, LOG, 15, y, 15, bb);
                    }

                    int LAYER_4 = 19;
                    if (y <= LAYER_4 && y > LAYER_3) {
                        placeBlock(level, LOG, 15, 10, 15, bb);
                        placeBlock(level, LOG, 15, 11, 15, bb);

                        if (rounded <= radius - 12 && rounded > 0)
                            placeBlock(level, COBWEB, 15 + x, 12, 15 + z, bb);

                        if (rounded == radius - 3 || rounded <= radius - 3 && rounded > radius - 12 && y >= 9 && y <= 12)
                            placeBlock(level, LOG, 15 + x, y, 15 + z, bb);
                    }

                    int LAYER_5 = 27;
                    if (y <= LAYER_5 && y > LAYER_4) {
                        if (rounded == radius - 2)
                            placeBlock(level, LOG, 15 + x, y, 15 + z, bb);

                        if (rounded <= radius - 3 && y == 20)
                            placeBlock(level, LOG, 15 + x, y, 15 + z, bb);

                        if (rounded <= radius - 3 && y == 21)
                            placeBlock(level, ModBlocks.SILK.get().defaultBlockState(), 15 + x, y, 15 + z, bb);
                    }

                    if (rounded < radius - 3 && rounded % 2 == 0 && y == 21)
                        if (x != 0 && z != 0)
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
        for (int y = 3; y <= 11; y++) {
            placeBlock(level, VINE.setValue(VineBlock.WEST, true), 16, y, 15, boundingBox);
            placeBlock(level, VINE.setValue(VineBlock.EAST, true), 14, y, 15, boundingBox);
            placeBlock(level, VINE.setValue(VineBlock.NORTH, true), 15, y, 16, boundingBox);
            placeBlock(level, VINE.setValue(VineBlock.SOUTH, true), 15, y, 14, boundingBox);
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
        for (int y = 13; y <= 21; y++) {
            placeBlock(level, VINE.setValue(VineBlock.EAST, true), 24, y, 15, boundingBox);
            placeBlock(level, VINE.setValue(VineBlock.WEST, true), 6, y, 15, boundingBox);
            placeBlock(level, VINE.setValue(VineBlock.SOUTH, true), 15, y, 24, boundingBox);
            placeBlock(level, VINE.setValue(VineBlock.NORTH, true), 15, y, 6, boundingBox);
        }
    }

    private void generateSpawners(WorldGenLevel level, RandomSource random, BoundingBox boundingBox) {
        placeSpawner(level, -7, 3, 0, boundingBox);
        placeSpawner(level, 7, 3, 0, boundingBox);
        placeSpawner(level, 0, 3, -7, boundingBox);
        placeSpawner(level, 0, 3, 7, boundingBox);

        if (random.nextBoolean()) {
            placeSpawner(level, -5, 13, 0, boundingBox);
            placeSpawner(level, 5, 13, 0, boundingBox);
        } else {
            placeSpawner(level, 0, 13, -5, boundingBox);
            placeSpawner(level, 0, 13, 5, boundingBox);
        }
    }

    private void addEntranceDecoration(WorldGenLevel level, BoundingBox bb) {
        for (int d = 0; d < 4; d++) {
            for (int c = 0; c < 3; c++) {
                rotatedCubeVolume(level, 0, c - 2, c - 15, LOG, 1, 2, 2, d, bb);
                rotatedCubeVolume(level, 0, c, c - 15, getStairRotation(d == 0 ? 2 : d == 1 ? 0 : d == 2 ? 3 : 1), 1, 1, 1, d, bb);
                rotatedCubeVolume(level, 0, c + 1, c - 15, AIR, 1, 2, 1, d, bb);

                if (c < 2) {
                    rotatedCubeVolume(level, -1, c - 2, c - 15, LOG, 1, 3, 2, d, bb);
                    rotatedCubeVolume(level, 1, c - 2, c - 15, LOG, 1, 3, 2, d, bb);
                    rotatedCubeVolume(level, -1, c + 1, c - 15, FENCE, 1, 1, 1, d, bb);
                    rotatedCubeVolume(level, 1, c + 1, c - 15, FENCE, 1, 1, 1, d, bb);

                    if (c < 1) {
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
        int height = rand.nextInt(6) + 10;
        for (int depth = 1; depth < height; depth++) {
            // Four layers per radius, measured from the root base. Never change
            // the outline while drawing a layer; retain the legacy random gaps.
            int radius = 4 - (depth - 1) / 4;
            for (int i = -radius; i <= radius; i++)
                for (int j = -radius; j <= radius; j++) {
                    if (Math.round(Math.sqrt(i * i + j * j)) <= radius && rand.nextInt(5) != 0) {
                        var target = getWorldPos(15 + x + i, -depth, 15 + z + j);
                        if (boundingBox.isInside(target) && canReplaceRoot(level.getBlockState(target)) && level.getBlockEntity(target) == null)
                            placeBlock(level, LOG.setValue(BarkLogBlock.ALL_BARK, true), 15 + x + i, -depth, 15 + z + j, boundingBox);
                    }
                }
        }
    }

    private void placeSpawner(WorldGenLevel level, int x, int y, int z, BoundingBox boundingBox) {
        var rand = RandomSource.create(layoutSeed ^ new BlockPos(x, y, z).asLong());
        placeBlock(level, COBWEB, 15 + x + 1, y, 15 + z, boundingBox);
        placeBlock(level, COBWEB, 15 + x - 1, y, 15 + z, boundingBox);
        placeBlock(level, COBWEB, 15 + x, y, 15 + z - 1, boundingBox);
        placeBlock(level, COBWEB, 15 + x, y, 15 + z + 1, boundingBox);
        placeBlock(level, COBWEB, 15 + x, y + 1, 15 + z, boundingBox);
        placeBlock(level, ModBlocks.TARANTULA_SPAWNER.get().defaultBlockState(), 15 + x, y, 15 + z, boundingBox);
        var spawnerPos = getWorldPos(15 + x, y, 15 + z);
        if (boundingBox.isInside(spawnerPos) && level.getBlockEntity(spawnerPos) instanceof ErebusSpawnerBlockEntity spawner) {
            spawner.setEntityId(ModEntities.TARANTULA.get(), rand);
        }

        // Legacy chests start facing north. Avoid terrain-dependent reorientation:
        // neighboring chunks may not have generated their floor yet.
        createChest(level, boundingBox, rand, getWorldPos(15 + x, y - 1, 15 + z),
                ModChestLootTables.TARANTULA_DUNGEON, Blocks.CHEST.defaultBlockState());
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
                    for (int z = -offsetA; z > -offsetA - sizeWidth; z--)
                        for (int x = offsetC; x < offsetC + sizeDepth; x++) {
                            placeBlock(level, state, 15 + x, y, 15 + z, boundingBox);
                        }
                break;
            case 2:
                for (int y = offsetB; y < offsetB + sizeHeight; y++)
                    for (int x = -offsetA; x > -offsetA - sizeWidth; x--)
                        for (int z = -offsetC; z > -offsetC - sizeDepth; z--) {
                            placeBlock(level, state, 15 + x, y, 15 + z, boundingBox);
                        }
                break;
            case 3:
                for (int y = offsetB; y < offsetB + sizeHeight; y++)
                    for (int z = offsetA; z < offsetA + sizeWidth; z++)
                        for (int x = -offsetC; x > -offsetC - sizeDepth; x--) {
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
