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
import org.jspecify.annotations.NonNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class DungPilePiece extends ScatteredFeaturePiece implements TerrainCheckedPiece {
    private final long shapeSeed;
    private boolean validated, rejected;

    public DungPilePiece(RandomSource random, BlockPos center) {
        super(ModStructurePieces.DUNG_PILE.get(), center.getX() - 5, center.getY() - 1, center.getZ() - 5, 11, 7, 11, Direction.SOUTH);
        shapeSeed = random.nextLong();
    }

    public DungPilePiece(StructurePieceSerializationContext context, CompoundTag tag) {
        super(ModStructurePieces.DUNG_PILE.get(), tag);
        shapeSeed = tag.getLongOr("ShapeSeed", 0);
        validated = tag.getBooleanOr("Validated", false);
        rejected = tag.getBooleanOr("Rejected", false) || !tag.contains("ShapeSeed");
    }

    public static boolean soil(BlockState state) {
        return state == Blocks.GRASS_BLOCK.defaultBlockState() || state == Blocks.DIRT.defaultBlockState()
                || state == Blocks.SAND.defaultBlockState() || state == Blocks.RED_SAND.defaultBlockState();
    }

    public static boolean validSite(BlockPos center, Function<BlockPos, BlockState> blocks, Predicate<BlockPos> inBounds) {
        for (int x = -5; x <= 5; x++)
            for (int z = -5; z <= 5; z++) {
                var below = center.offset(x, -1, z);
                if (!inBounds.test(below) || !soil(blocks.apply(below))) return false;
                for (int y = 0; y < 5; y++) {
                    var pos = center.offset(x, y, z);
                    if (!inBounds.test(pos) || !blocks.apply(pos).isAir()) return false;
                }
            }
        return inBounds.test(center.above(5)) && blocks.apply(center.above(5)).isAir();
    }

    @Override
    public synchronized boolean isRejected() {
        return rejected;
    }

    @Override
    protected void addAdditionalSaveData(@NonNull StructurePieceSerializationContext context, @NonNull CompoundTag tag) {
        super.addAdditionalSaveData(context, tag);
        tag.putLong("ShapeSeed", shapeSeed);
        tag.putBoolean("Validated", validated);
        tag.putBoolean("Rejected", rejected);
    }

    public BlockPos center() {
        return new BlockPos(boundingBox.minX() + 5, boundingBox.minY() + 1, boundingBox.minZ() + 5);
    }

    public Map<BlockPos, BlockState> layout() {
        var map = new LinkedHashMap<BlockPos, BlockState>();
        var random = RandomSource.create(shapeSeed);
        var center = center();
        var dung = ModBlocks.DUNG.get().defaultBlockState();
        for (int x = -5; x <= 5; x++)
            for (int z = -5; z <= 5; z++) {
                for (int y = 0; y < 5; y++) {
                    long distance = Math.round(Math.sqrt(x * x + y * y + z * z));
                    if (y == 0 && random.nextBoolean() && distance == 5) map.put(center.offset(x, -1, z), dung);
                    if (distance < 5) map.put(center.offset(x, y, z), dung);
                }
                if (random.nextInt(5) == 0) map.put(center.offset(x, 0, z), dung);
            }
        map.put(center, Blocks.CHEST.defaultBlockState());
        map.put(center.offset(-1, 1, 1), ModBlocks.DUNG_SPAWNER_BOT_FLY.get().defaultBlockState());
        map.put(center.offset(1, 1, 1), ModBlocks.DUNG_SPAWNER_FLY.get().defaultBlockState());
        map.put(center.offset(1, 1, -1), ModBlocks.DUNG_SPAWNER_BOT_FLY.get().defaultBlockState());
        map.put(center.offset(-1, 1, -1), ModBlocks.DUNG_SPAWNER_FLY.get().defaultBlockState());
        map.put(center.above(5), ModBlocks.DUNG_SPAWNER_FLY.get().defaultBlockState());
        return Collections.unmodifiableMap(map);
    }

    @Override
    public synchronized void postProcess(@NonNull WorldGenLevel level, @NonNull StructureManager manager, @NonNull ChunkGenerator generator, @NonNull RandomSource random, @NonNull BoundingBox chunkBounds, @NonNull ChunkPos chunk, @NonNull BlockPos ignored) {
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
            var current = level.getBlockState(pos);
            if (!current.isAir() && !(pos.getY() == center().getY() - 1 && soil(current))) continue;
            if (level.setBlock(pos, entry.getValue(), 2) && entry.getValue().is(Blocks.CHEST) && level.getBlockEntity(pos) instanceof ChestBlockEntity chest) chest.setLootTable(ModChestLootTables.DUNG_PILE);
        }
    }
}
