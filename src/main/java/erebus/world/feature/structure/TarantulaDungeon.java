package erebus.world.feature.structure;

import com.mojang.serialization.MapCodec;
import erebus.registries.data.tags.ModBiomeTags;
import erebus.registries.world.structure.ModStructureTypes;
import erebus.world.feature.structure.pieces.AdaptedStructureTerrain;
import erebus.world.feature.structure.pieces.TarantulaDungeonPiece;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class TarantulaDungeon extends TerrainCheckedStructure {

    public static final MapCodec<TarantulaDungeon> CODEC = simpleCodec(TarantulaDungeon::new);

    public TarantulaDungeon(StructureSettings settings) {
        super(settings);
    }

    public static boolean isPlacementChunk(long seed, ChunkPos chunk) {
        long own = priority(seed, chunk.x(), chunk.z());
        for (int dx = -6; dx <= 6; dx++)
            for (int dz = -6; dz <= 6; dz++) {
                if (dx == 0 && dz == 0) continue;
                int otherX = chunk.x() + dx, otherZ = chunk.z() + dz;
                int comparison = Long.compareUnsigned(priority(seed, otherX, otherZ), own);
                if (comparison < 0 || comparison == 0 && (otherX < chunk.x() || otherX == chunk.x() && otherZ < chunk.z()))
                    return false;
            }
        return true;
    }

    private static long priority(long seed, int x, int z) {
        long value = seed ^ (((long) x << 32) ^ (z & 0xffffffffL)) ^ 0xD1B54A32D192ED03L;
        value = (value ^ (value >>> 30)) * 0xBF58476D1CE4E5B9L;
        value = (value ^ (value >>> 27)) * 0x94D049BB133111EBL;
        return value ^ (value >>> 31);
    }

    public static Optional<BlockPos> selectCandidate(ChunkPos chunk, int minY, Function<BlockPos, BlockState> terrain, Predicate<BlockPos> inBounds) {
        for (int y = 16; y <= 80; y++) {
            var base = new BlockPos(chunk.getMinBlockX() + 16, minY + y, chunk.getMinBlockZ() + 16);
            if (!AdaptedStructureTerrain.ground(terrain.apply(base)) || !terrain.apply(base.above()).isAir()) continue;
            if (TarantulaDungeonPiece.canAdaptSite(base, terrain, inBounds)) return Optional.of(base);
        }
        return Optional.empty();
    }

    public static TarantulaDungeon buildConfig(BootstrapContext<Structure> context) {
        return new TarantulaDungeon(new StructureSettings.Builder(context.lookup(Registries.BIOME).getOrThrow(ModBiomeTags.HAS_TARANTULA_DUNGEON))
                .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                .build());
    }

    @Override
    protected @NonNull Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        if (!isPlacementChunk(context.seed(), context.chunkPos())) return Optional.empty();
        var columns = new HashMap<Long, NoiseColumn>();
        Function<BlockPos, BlockState> raw = p ->
                columns.computeIfAbsent(((long) p.getX() << 32) ^ (p.getZ() & 0xffffffffL), _ -> context.chunkGenerator()
                        .getBaseColumn(p.getX(), p.getZ(), context.heightAccessor(), context.randomState())).getBlock(p.getY());
        var candidate = selectCandidate(context.chunkPos(), context.heightAccessor().getMinY(), raw,
                p -> !context.heightAccessor().isOutsideBuildHeight(p));
        if (candidate.isEmpty()) return Optional.empty();
        var base = candidate.get();
        var biome = context.biomeSource().getNoiseBiome(base.getX() >> 2, base.getY() >> 2, base.getZ() >> 2, context.randomState().sampler());
        if (!context.validBiome().test(biome)) return Optional.empty();
        return Optional.of(new GenerationStub(base, builder -> builder.addPiece(new TarantulaDungeonPiece(context.random(), base))));
    }

    @Override
    public @NonNull StructureType<?> type() {
        return ModStructureTypes.TARANTULA_DUNGEON.get();
    }
}
