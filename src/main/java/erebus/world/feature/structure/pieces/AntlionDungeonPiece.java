package erebus.world.feature.structure.pieces;

import erebus.block.entity.BlockOfBonesBlockEntity;
import erebus.block.entity.TempleTeleporterBlockEntity;
import erebus.datagen.loot.ModChestLootTables;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
import erebus.registries.world.structure.ModStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public final class AntlionDungeonPiece extends ScatteredFeaturePiece {
    private final long layoutSeed;
    private final boolean rejected;
    private final Set<Long> completedChunks = new HashSet<>();
    private int spawnedGuardians;
    private @Nullable AntlionDungeonLayout layout;

    public AntlionDungeonPiece(BlockPos center, long layoutSeed) {
        super(ModStructurePieces.ANTLION_DUNGEON.get(), center.getX() - 60, center.getY() - 4, center.getZ() - 60, 121, 26, 121, Direction.SOUTH);
        this.layoutSeed = layoutSeed;
        rejected = false;
    }

    public AntlionDungeonPiece(StructurePieceSerializationContext ignored, CompoundTag tag) {
        super(ModStructurePieces.ANTLION_DUNGEON.get(), tag);
        layoutSeed = tag.getLongOr("LayoutSeed", 0);
        rejected = tag.getIntOr("DungeonVersion", 0) != 1;
        spawnedGuardians = tag.getIntOr("SpawnedGuardians", 0) & 15;
        for (long chunk : tag.getLongArray("CompletedChunks").orElse(new long[0])) completedChunks.add(chunk);
    }

    private static boolean replaceable(BlockState state) {
        return !state.hasBlockEntity() && state.getFluidState().isEmpty() && (state.canBeReplaced() || state.isAir()
                || state.is(BlockTags.BASE_STONE_OVERWORLD) || state.is(BlockTags.DIRT) || state.is(BlockTags.SAND)
                || state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.SANDSTONE) || state.is(Blocks.RED_SANDSTONE)
                || state.is(ModBlocks.UMBERSTONE) || state.is(ModBlocks.VOLCANIC_ROCK));
    }

    @Override
    protected void addAdditionalSaveData(@NonNull StructurePieceSerializationContext context, @NonNull CompoundTag tag) {
        super.addAdditionalSaveData(context, tag);
        tag.putInt("DungeonVersion", rejected ? 0 : 1);
        tag.putLong("LayoutSeed", layoutSeed);
        tag.putInt("SpawnedGuardians", spawnedGuardians);
        tag.putLongArray("CompletedChunks", completedChunks.stream().mapToLong(Long::longValue).toArray());
    }

    public BlockPos center() {
        return new BlockPos(boundingBox.minX() + 60, boundingBox.minY() + 4, boundingBox.minZ() + 60);
    }

    public synchronized AntlionDungeonLayout layout() {
        if (layout == null) layout = new AntlionDungeonLayout(center(), layoutSeed);
        return layout;
    }

    @Override
    public synchronized void postProcess(@NonNull WorldGenLevel level, @NonNull StructureManager manager, @NonNull ChunkGenerator generator, @NonNull RandomSource random, @NonNull BoundingBox clip, @NonNull ChunkPos chunk, @NonNull BlockPos reference) {
        if (rejected || level.isOutsideBuildHeight(boundingBox.minY()) || level.isOutsideBuildHeight(boundingBox.maxY())) return;
        var plan = layout();
        long chunkKey = ((long) chunk.x() << 32) ^ (chunk.z() & 0xffffffffL);
        if (!completedChunks.contains(chunkKey)) {
            boolean complete = true;
            for (var entry : plan.blocks().entrySet()) {
                var pos = entry.getKey();
                if (!clip.isInside(pos) || (pos.getX() >> 4) != chunk.x() || (pos.getZ() >> 4) != chunk.z()) continue;
                var existing = level.getBlockState(pos);
                var state = entry.getValue();
                if (state.equals(existing) || !replaceable(existing)) continue;
                if (!level.setBlock(pos, state, 18)) {
                    complete = false;
                    continue;
                }
                var blockEntity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
                if (blockEntity instanceof TempleTeleporterBlockEntity teleporter) {
                    var destination = plan.teleporters().get(pos);
                    if (destination != null) teleporter.setDestination(destination);
                } else if (blockEntity instanceof ChestBlockEntity chest) {
                    if (pos.equals(plan.jadeChest())) chest.setItem(0, new ItemStack(ModItems.JADE.get(), 8));
                    else if (plan.lootChests().contains(pos)) chest.setLootTable(ModChestLootTables.ANTLION_DUNGEON, layoutSeed ^ pos.asLong());
                } else if (blockEntity instanceof BlockOfBonesBlockEntity bones && plan.lootBones().contains(pos)) {
                    var server = level.getLevel();
                    var params = new LootParams.Builder(server).withParameter(LootContextParams.ORIGIN, pos.getCenter()).create(LootContextParamSets.CHEST);
                    server.getServer().reloadableRegistries().getLootTable(ModChestLootTables.ANTLION_DUNGEON).fill(bones, params, layoutSeed ^ pos.asLong());
                    bones.setChanged();
                }
                var fluid = state.getFluidState();
                if (!fluid.isEmpty()) level.scheduleTick(pos, fluid.getType(), 0);
            }
            if (complete) completedChunks.add(chunkKey);
        }
        if (!completedChunks.contains(chunkKey)) return;
        for (var spawn : plan.guardians()) {
            int mask = 1 << spawn.variant();
            var pos = BlockPos.containing(spawn.position());
            if ((spawnedGuardians & mask) != 0 || !clip.isInside(pos) || (pos.getX() >> 4) != chunk.x() || (pos.getZ() >> 4) != chunk.z() || !level.isEmptyBlock(pos) || !level.isEmptyBlock(pos.above())) continue;
            var guardian = ModEntities.DUNGEON_UMBER_GOLEM.get().create(level.getLevel(), EntitySpawnReason.STRUCTURE);
            if (guardian == null) continue;
            guardian.setVariant(spawn.variant());
            guardian.setPos(spawn.position());
            if (level.addFreshEntity(guardian)) spawnedGuardians |= mask;
        }
    }
}
