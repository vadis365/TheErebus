package erebus.world.feature.structure.pieces;

import erebus.block.BlockOfBonesBlock;
import erebus.block.entity.BlockOfBonesBlockEntity;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class LocustShrinePiece extends ScatteredFeaturePiece implements TerrainCheckedPiece {
    private final long shapeSeed;
    private boolean validated, rejected;

    public LocustShrinePiece(RandomSource random, BlockPos center) {
        super(ModStructurePieces.LOCUST_SHRINE.get(), center.getX() - 4, center.getY() - 1, center.getZ() - 4, 9, 8, 9, Direction.SOUTH);
        shapeSeed = random.nextLong();
    }

    public LocustShrinePiece(StructurePieceSerializationContext context, CompoundTag tag) {
        super(ModStructurePieces.LOCUST_SHRINE.get(), tag);
        shapeSeed = tag.getLongOr("ShapeSeed", 0);
        validated = tag.getBooleanOr("Validated", false);
        rejected = tag.getBooleanOr("Rejected", false) || !tag.contains("ShapeSeed");
    }

    public static boolean soil(BlockState state) {
        return state == Blocks.GRASS_BLOCK.defaultBlockState() || state == Blocks.MYCELIUM.defaultBlockState();
    }

    public static boolean validSite(BlockPos center, Function<BlockPos, BlockState> blocks, Predicate<BlockPos> inBounds) {
        for (int x = -4; x <= 4; x++)
            for (int z = -4; z <= 4; z++) {
                var below = center.offset(x, -1, z);
                if (!inBounds.test(below) || !soil(blocks.apply(below))) return false;
                for (int y = 0; y < 7; y++) {
                    var pos = center.offset(x, y, z);
                    if (!inBounds.test(pos) || !blocks.apply(pos).isAir()) return false;
                }
            }
        return true;
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
        return new BlockPos(boundingBox.minX() + 4, boundingBox.minY() + 1, boundingBox.minZ() + 4);
    }

    public Map<BlockPos, BlockState> layout() {
        var map = new LinkedHashMap<BlockPos, BlockState>();
        var random = RandomSource.create(shapeSeed);
        var center = center();
        for (int x = -4; x <= 4; x++)
            for (int z = -4; z <= 4; z++) {
                if (Math.round(Math.sqrt(x * x + z * z)) >= 5) continue;
                boolean dirt = random.nextInt(5) != 0;
                map.put(center.offset(x, -1, z), dirt ? Blocks.COARSE_DIRT.defaultBlockState() : ModBlocks.UMBERGRAVEL.get().defaultBlockState());
                if (dirt && random.nextInt(3) == 0) map.put(center.offset(x, 0, z), Blocks.DEAD_BUSH.defaultBlockState());
            }
        var stone = ModBlocks.UMBERCOBBLE_MOSSY.get().defaultBlockState();
        var wall = ModBlocks.WALL_UMBERCOBBLE_MOSSY.get().defaultBlockState();
        for (int y = 0; y < 4; y++) map.put(center.above(y), y == 2 ? wall : stone);
        map.put(center.above(4), ModBlocks.LOCUST_SPAWNER.get().defaultBlockState());
        int[][] corners = {{3, 3}, {3, -3}, {-3, 3}, {-3, -3}};
        Direction[] facings = {Direction.EAST, Direction.NORTH, Direction.SOUTH, Direction.WEST};
        for (int i = 0; i < 4; i++) {
            var base = center.offset(corners[i][0], 0, corners[i][1]);
            for (int y = 0; y < 6; y++) map.put(base.above(y), y == 1 || y == 4 ? wall : stone);
            map.put(base.above(6), ModBlocks.BLOCK_OF_BONES.get().defaultBlockState().setValue(BlockOfBonesBlock.FACING, facings[i]));
        }
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
            if (level.setBlock(pos, entry.getValue(), 2) && level.getBlockEntity(pos) instanceof BlockOfBonesBlockEntity bones) {
                var lootRandom = RandomSource.create(shapeSeed ^ pos.asLong());
                var params = new LootParams.Builder(level.getLevel())
                        .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                        .create(LootContextParamSets.CHEST);
                var table = level.getLevel().getServer().reloadableRegistries().getLootTable(ModChestLootTables.LOCUST_SHRINE);
                for (var stack : table.getRandomItems(params, lootRandom)) bones.setItem(lootRandom.nextInt(bones.getContainerSize()), stack);
            }
        }
    }
}
