package erebus.world.feature.structure;

import erebus.mixin.StructureManagerAccessor;
import erebus.world.feature.structure.pieces.TerrainCheckedPiece;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;

import java.util.Map;

/** Keeps failed terrain predictions out of both saved starts and vanilla locate's cache. */
public abstract class TerrainCheckedStructure extends Structure {
    protected TerrainCheckedStructure(StructureSettings settings) {
        super(settings);
    }

    @Override
    public void afterPlace(WorldGenLevel level, StructureManager manager, ChunkGenerator generator,
                           RandomSource random, BoundingBox clip, ChunkPos chunkPos, PiecesContainer pieces) {
        if (pieces.pieces().isEmpty() || !pieces.pieces().stream().allMatch(
                piece -> piece instanceof TerrainCheckedPiece checked && checked.isRejected())) return;

        // Pieces can cross their start chunk's border, and Spider starts can contain several rooms.
        // Resolve the actual owning start and retain it if even one room has not rejected placement.
        for (var start : manager.startsForStructure(chunkPos, structure -> structure == this)) {
            if (!start.getPieces().equals(pieces.pieces())) continue;
            var startPos = start.getChunkPos();
            var chunk = level.getChunk(startPos.x(), startPos.z(), ChunkStatus.STRUCTURE_STARTS);
            if (chunk.getStartForStructure(this) != start) continue;
            chunk.setStartForStructure(this, StructureStart.INVALID_START);
            var starts = Map.copyOf(chunk.getAllStarts());
            var serverLevel = level.getLevel();
            serverLevel.getServer().execute(() -> ((StructureManagerAccessor) serverLevel.structureManager())
                    .erebus$structureCheck().onStructureLoad(startPos, starts));
        }
    }
}
