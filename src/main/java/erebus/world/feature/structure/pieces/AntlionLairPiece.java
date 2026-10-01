package erebus.world.feature.structure.pieces;

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
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import org.jspecify.annotations.NonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class AntlionLairPiece extends ScatteredFeaturePiece implements TerrainCheckedPiece {
    private final Set<Long> completedChunks = new HashSet<>();
    private boolean validated, rejected, spawned;

    public AntlionLairPiece(BlockPos center) {
        super(ModStructurePieces.ANTLION_LAIR.get(), center.getX() - 5, center.getY() - 7, center.getZ() - 5,
                11, 8, 11, Direction.SOUTH);
    }

    public AntlionLairPiece(StructurePieceSerializationContext context, CompoundTag tag) {
        super(ModStructurePieces.ANTLION_LAIR.get(), tag);
        validated = tag.getBooleanOr("Validated", false);
        rejected = tag.getBooleanOr("Rejected", false) || !tag.contains("LairVersion");
        spawned = tag.getBooleanOr("Spawned", false);
        for (long chunk : tag.getLongArray("CompletedChunks").orElse(new long[0])) completedChunks.add(chunk);
    }

    public static boolean excavatable(BlockState state) {
        return !state.hasBlockEntity() && state.getFluidState().isEmpty() && (state.isAir()
                || state.is(BlockTags.BASE_STONE_OVERWORLD) || state.is(ModBlocks.UMBERSTONE.get())
                || state.is(ModBlocks.VOLCANIC_ROCK.get()) || state.is(Blocks.SAND) || state.is(Blocks.RED_SAND)
                || state.is(Blocks.SANDSTONE) || state.is(Blocks.RED_SANDSTONE));
    }

    public static boolean validSite(BlockPos center, Function<BlockPos, BlockState> blocks, Predicate<BlockPos> inBounds) {
        for (var p : BlockPos.betweenClosed(center.offset(-4, 0, -4), center.offset(4, 0, 4))) {
            if (!inBounds.test(p) || !inBounds.test(p.below()) || !blocks.apply(p).isAir()) return false;
            var ground = blocks.apply(p.below());
            if (!ground.is(Blocks.SAND) && !ground.is(Blocks.RED_SAND)) return false;
        }

        for (var p : BlockPos.betweenClosed(center.offset(-5, -7, -5), center.offset(5, -1, 5)))
            if (!inBounds.test(p) || !excavatable(blocks.apply(p))) return false;
        return true;
    }

    public static boolean hollow(int x, int layer, int z) {
        return x * x + z * z < 4.9 * 4.9 && layer < 6 && (layer <= 2 || Math.abs(x) <= 7 - layer && Math.abs(z) <= 7 - layer);
    }

    @Override
    public synchronized boolean isRejected() {
        return rejected;
    }

    @Override
    protected void addAdditionalSaveData(@NonNull StructurePieceSerializationContext context, @NonNull CompoundTag tag) {
        super.addAdditionalSaveData(context, tag);
        tag.putInt("LairVersion", 1);
        tag.putBoolean("Validated", validated);
        tag.putBoolean("Rejected", rejected);
        tag.putBoolean("Spawned", spawned);
        tag.putLongArray("CompletedChunks", completedChunks.stream().mapToLong(Long::longValue).toArray());
    }

    public BlockPos center() {
        return new BlockPos(boundingBox.minX() + 5, boundingBox.minY() + 7, boundingBox.minZ() + 5);
    }

    @Override
    public synchronized void postProcess(@NonNull WorldGenLevel level, @NonNull StructureManager manager, @NonNull ChunkGenerator generator, @NonNull RandomSource random, @NonNull BoundingBox clip, @NonNull ChunkPos chunk, @NonNull BlockPos ignoredReference) {
        if (rejected) return;
        var center = center();
        if (!validated) {
            if (!validSite(center, level::getBlockState, p -> !level.isOutsideBuildHeight(p))) {
                rejected = true;
                return;
            }
            validated = true;
        }
        long chunkKey = ((long) chunk.x() << 32) ^ (chunk.z() & 0xffffffffL);
        if (!completedChunks.contains(chunkKey)) {
            boolean complete = true;
            for (int x = -5; x <= 5; x++)
                for (int z = -5; z <= 5; z++)
                    for (int layer = 0; layer < 7; layer++) {
                        var pos = center.offset(x, -1 - layer, z);
                        if (!clip.isInside(pos)) continue;
                        var existing = level.getBlockState(pos);
                        if (!excavatable(existing)) continue;
                        BlockState replacement = null;
                        if (hollow(x, layer, z))
                            replacement = layer == 0 ? ModBlocks.GHOST_SAND.get().defaultBlockState() : Blocks.AIR.defaultBlockState();
                        else if (layer > 0 && !existing.isAir()) replacement = Blocks.SAND.defaultBlockState();
                        if (pos.equals(center.below(7))) replacement = Blocks.CHEST.defaultBlockState();
                        if (replacement == null || replacement.equals(existing)) continue;
                        if (!level.setBlock(pos, replacement, 2)) {
                            complete = false;
                            continue;
                        }
                        if (replacement.is(Blocks.CHEST) && level.getBlockEntity(pos) instanceof ChestBlockEntity chest)
                            chest.setLootTable(ModChestLootTables.ANTLION_LAIR);
                    }
            if (complete) completedChunks.add(chunkKey);
        }
        var spawn = center.below(5);
        if (!spawned && clip.isInside(spawn) && completedChunks.contains(chunkKey)
                && level.isEmptyBlock(spawn) && level.isEmptyBlock(spawn.above())) {
            var antlion = ModEntities.ANTLION_MINI_BOSS.get().create(level.getLevel(), EntitySpawnReason.STRUCTURE);
            if (antlion != null) {
                antlion.setPos(spawn.getX() + 0.5, spawn.getY(), spawn.getZ() + 0.5);
                antlion.setYRot(random.nextFloat() * 360);
                antlion.setPersistenceRequired();
                spawned = level.addFreshEntity(antlion);
            }
        }
    }
}
