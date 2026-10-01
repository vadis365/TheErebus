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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class DragonflyDungeonPiece extends ScatteredFeaturePiece implements TerrainCheckedPiece {

    private final Direction opening;
    private boolean validated;
    private boolean rejected;

    public DragonflyDungeonPiece(RandomSource random, BlockPos center) {
        super(ModStructurePieces.DRAGONFLY_DUNGEON.get(), center.getX() - 6, center.getY(), center.getZ() - 6,
                13, 5, 13, Direction.SOUTH);
        opening = Direction.Plane.HORIZONTAL.getRandomDirection(random);
    }

    public DragonflyDungeonPiece(StructurePieceSerializationContext ignoredContext, CompoundTag tag) {
        super(ModStructurePieces.DRAGONFLY_DUNGEON.get(), tag);
        opening = Direction.from2DDataValue(tag.getIntOr("Opening", 0));
        validated = tag.getBooleanOr("Validated", false);
        rejected = tag.getBooleanOr("Rejected", false);
        if (!tag.contains("Opening")) rejected = true;
    }

    public static boolean validSite(BlockPos center, Function<BlockPos, BlockState> blocks,
                                    Predicate<BlockPos> inBounds) {
        if (!inBounds.test(center) || !blocks.apply(center).is(Blocks.WATER)) return false;
        for (var pos : BlockPos.betweenClosed(center.offset(-6, 1, -6), center.offset(6, 4, 6)))
            if (!inBounds.test(pos) || !blocks.apply(pos).isAir()) return false;
        for (int x = -5; x <= 5; x++)
            for (int z = -5; z <= 5; z++) {
                if (Math.round(Math.sqrt(x * x + z * z)) >= 6) continue;
                var pos = center.offset(x, 0, z);
                if (!inBounds.test(pos)) return false;
                var state = blocks.apply(pos);
                if (!state.isAir() && !state.is(Blocks.WATER)) return false;
            }
        return true;
    }

    private static void generateMainLilyPad(Map<BlockPos, BlockState> plan, BlockPos pos) {
        for (int x = -6; x <= 6; x++) {
            for (int z = -6; z <= 6; z++) {
                double dSqCylinder = Math.pow(x, 2) + Math.pow(z, 2);
                long rounded = Math.round(Math.sqrt(dSqCylinder));

                if (rounded < 6) {
                    if (dSqCylinder <= Math.pow(6, 2)) {
                        setBlock(plan, pos.offset(x, 0, z), ModBlocks.GIANT_LILY_PAD.get().defaultBlockState());
                    }
                }

                if (rounded == 5) {
                    if (dSqCylinder <= Math.pow(6, 2)) {
                        setBlock(plan, pos.offset(x, 1, z), ModBlocks.GIANT_LILY_PAD.get().defaultBlockState());
                    }
                }
            }
        }
    }

    private static void northAirGap(Map<BlockPos, BlockState> plan, BlockPos pos) {
        for (int z = -5; z < -1; z++) {
            zAirGap(plan, pos, z);
        }
    }

    private static void southAirGap(Map<BlockPos, BlockState> plan, BlockPos pos) {
        for (int z = 5; z > 1; z--) {
            zAirGap(plan, pos, z);
        }
    }

    private static void eastAirGap(Map<BlockPos, BlockState> plan, BlockPos pos) {
        for (int x = 5; x > 1; x--) {
            xAirGap(plan, pos, x);
        }
    }

    private static void westAirGap(Map<BlockPos, BlockState> plan, BlockPos pos) {
        for (int x = -5; x < -1; x++) {
            xAirGap(plan, pos, x);
        }
    }

    private static void generateStem(Map<BlockPos, BlockState> plan, BlockPos pos) {
        setBlock(plan, pos.offset(1, 1, 0), ModBlocks.GIANT_LILY_PAD.get().defaultBlockState());
        setBlock(plan, pos.offset(0, 1, 1), ModBlocks.GIANT_LILY_PAD.get().defaultBlockState());
        setBlock(plan, pos.offset(-1, 1, 0), ModBlocks.GIANT_LILY_PAD.get().defaultBlockState());
        setBlock(plan, pos.offset(0, 1, -1), ModBlocks.GIANT_LILY_PAD.get().defaultBlockState());
    }

    private static void generateFlower(Map<BlockPos, BlockState> plan, BlockPos pos) {
        setBlock(plan, pos.offset(1, 2, 0), ModBlocks.PETAL_WHITE.get().defaultBlockState());
        setBlock(plan, pos.offset(0, 2, 1), ModBlocks.PETAL_WHITE.get().defaultBlockState());
        setBlock(plan, pos.offset(-1, 2, 0), ModBlocks.PETAL_WHITE.get().defaultBlockState());
        setBlock(plan, pos.offset(0, 2, -1), ModBlocks.PETAL_WHITE.get().defaultBlockState());
        setBlock(plan, pos.offset(1, 2, 1), ModBlocks.PETAL_WHITE.get().defaultBlockState());
        setBlock(plan, pos.offset(1, 2, -1), ModBlocks.PETAL_WHITE.get().defaultBlockState());
        setBlock(plan, pos.offset(-1, 2, 1), ModBlocks.PETAL_WHITE.get().defaultBlockState());
        setBlock(plan, pos.offset(-1, 2, -1), ModBlocks.PETAL_WHITE.get().defaultBlockState());
        setBlock(plan, pos.offset(2, 2, 0), ModBlocks.PETAL_WHITE.get().defaultBlockState());
        setBlock(plan, pos.offset(0, 2, 2), ModBlocks.PETAL_WHITE.get().defaultBlockState());
        setBlock(plan, pos.offset(-2, 2, 0), ModBlocks.PETAL_WHITE.get().defaultBlockState());
        setBlock(plan, pos.offset(0, 2, -2), ModBlocks.PETAL_WHITE.get().defaultBlockState());
        setBlock(plan, pos.offset(2, 3, 0), ModBlocks.PETAL_WHITE.get().defaultBlockState());
        setBlock(plan, pos.offset(0, 3, 2), ModBlocks.PETAL_WHITE.get().defaultBlockState());
        setBlock(plan, pos.offset(-2, 3, 0), ModBlocks.PETAL_WHITE.get().defaultBlockState());
        setBlock(plan, pos.offset(0, 3, -2), ModBlocks.PETAL_WHITE.get().defaultBlockState());
    }

    private static void zAirGap(Map<BlockPos, BlockState> plan, BlockPos pos, int z) {
        setBlock(plan, pos.offset(0, 0, z), Blocks.AIR.defaultBlockState());
        setBlock(plan, pos.offset(0, 1, z), Blocks.AIR.defaultBlockState());
        setBlock(plan, pos.offset(-1, 1, z), ModBlocks.GIANT_LILY_PAD.get().defaultBlockState());
        setBlock(plan, pos.offset(1, 1, z), ModBlocks.GIANT_LILY_PAD.get().defaultBlockState());
    }

    private static void xAirGap(Map<BlockPos, BlockState> plan, BlockPos pos, int x) {
        setBlock(plan, pos.offset(x, 0, 0), Blocks.AIR.defaultBlockState());
        setBlock(plan, pos.offset(x, 1, 0), Blocks.AIR.defaultBlockState());
        setBlock(plan, pos.offset(x, 1, -1), ModBlocks.GIANT_LILY_PAD.get().defaultBlockState());
        setBlock(plan, pos.offset(x, 1, 1), ModBlocks.GIANT_LILY_PAD.get().defaultBlockState());
    }

    private static void setBlock(Map<BlockPos, BlockState> plan, BlockPos pos, BlockState state) {
        plan.put(pos.immutable(), state);
    }

    @Override
    public synchronized boolean isRejected() {
        return rejected;
    }

    @Override
    protected void addAdditionalSaveData(@NonNull StructurePieceSerializationContext context, @NonNull CompoundTag tag) {
        super.addAdditionalSaveData(context, tag);
        tag.putInt("Opening", opening.get2DDataValue());
        tag.putBoolean("Validated", validated);
        tag.putBoolean("Rejected", rejected);
    }

    public BlockPos center() {
        return new BlockPos(boundingBox.minX() + 6, boundingBox.minY(), boundingBox.minZ() + 6);
    }

    public Map<BlockPos, BlockState> layout() {
        var plan = new LinkedHashMap<BlockPos, BlockState>();
        var pos = center();
        generateMainLilyPad(plan, pos);
        switch (opening) {
            case NORTH -> northAirGap(plan, pos);
            case SOUTH -> southAirGap(plan, pos);
            case EAST -> eastAirGap(plan, pos);
            case WEST -> westAirGap(plan, pos);
            default -> throw new IllegalStateException("Non-horizontal dungeon opening");
        }
        generateStem(plan, pos);
        generateFlower(plan, pos);
        plan.put(pos.above(), Blocks.CHEST.defaultBlockState());
        plan.put(pos.above(2), ModBlocks.DRAGON_FLY_SPAWNER.get().defaultBlockState());
        plan.put(pos.above(3), ModBlocks.DRAGON_FLY_SPAWNER.get().defaultBlockState());
        return Collections.unmodifiableMap(plan);
    }

    @Override
    public synchronized void postProcess(@NotNull WorldGenLevel level, @NotNull StructureManager manager, @NotNull ChunkGenerator generator, @NotNull RandomSource random, @NotNull BoundingBox chunkBounds, @NotNull ChunkPos chunkPos, @NotNull BlockPos ignoredReference) {
        if (rejected) return;
        if (!validated) {
            if (!validSite(center(), level::getBlockState, p -> !level.isOutsideBuildHeight(p))) {
                rejected = true;
                return;
            }
            validated = true;
        }
        for (var entry : layout().entrySet()) {
            var pos = entry.getKey();
            if (!chunkBounds.isInside(pos)) continue;
            var existing = level.getBlockState(pos);
            if (!existing.isAir() && !(pos.getY() == center().getY() && existing.is(Blocks.WATER))) continue;
            if (entry.getValue().isAir() && existing.isAir()) continue;
            if (level.setBlock(pos, entry.getValue(), 2) && entry.getValue().is(Blocks.CHEST)
                    && level.getBlockEntity(pos) instanceof ChestBlockEntity chest) {
                chest.setLootTable(ModChestLootTables.DRAGONFLY_DUNGEON);
            }
        }
    }
}
