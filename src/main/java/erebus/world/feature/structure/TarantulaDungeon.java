package erebus.world.feature.structure;

import com.mojang.serialization.MapCodec;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModBiomeTags;
import erebus.registries.world.structure.ModStructureTypes;
import erebus.world.feature.structure.pieces.TarantulaDungeonPiece;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.Blocks;
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
            if (!TarantulaDungeonPiece.soil(terrain.apply(base))) continue;
            return TarantulaDungeonPiece.validSite(base, terrain, inBounds) ? Optional.of(base) : Optional.empty();
        }
        return Optional.empty();
    }

    public static TarantulaDungeon buildConfig(BootstrapContext<Structure> context) {
        return new TarantulaDungeon(new StructureSettings.Builder(context.lookup(Registries.BIOME).getOrThrow(ModBiomeTags.HAS_TARANTULA_DUNGEON))
                .terrainAdapation(TerrainAdjustment.NONE)
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
        Function<BlockPos, BlockState> terrain = p -> {
            var state = raw.apply(p);
            return state.is(ModBlocks.UMBERSTONE) && raw.apply(p.above()).isAir() ? Blocks.RED_SAND.defaultBlockState() : state;
        };
        return selectCandidate(context.chunkPos(), context.heightAccessor().getMinY(), terrain, p -> !context.heightAccessor().isOutsideBuildHeight(p))
                .filter(p -> context.validBiome().test(context.biomeSource().getNoiseBiome(p.getX() >> 2, p.getY() >> 2, p.getZ() >> 2, context.randomState().sampler())))
                .map(p -> new GenerationStub(p, builder -> builder.addPiece(new TarantulaDungeonPiece(context.random(), p))));
    }

    @Override
    public @NonNull StructureType<?> type() {
        return ModStructureTypes.TARANTULA_DUNGEON.get();
    }
}
