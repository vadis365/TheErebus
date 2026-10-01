package erebus.world.feature.structure;

import com.mojang.serialization.MapCodec;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModBiomeTags;
import erebus.registries.world.structure.ModStructureTypes;
import erebus.world.feature.structure.pieces.SwampHutPiece;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class SwampHut extends TerrainCheckedStructure {

    public static final MapCodec<SwampHut> CODEC = simpleCodec(SwampHut::new);

    public SwampHut(StructureSettings settings) {
        super(settings);
    }

    public static Optional<BlockPos> selectCandidate(ChunkPos chunk, int minY, int maxY, Predicate<BlockPos> valid) {
        for (int y = maxY - 12; y > minY; y--) {
            var origin = new BlockPos(chunk.getMinBlockX(), y, chunk.getMinBlockZ());
            if (valid.test(origin)) return Optional.of(origin);
        }
        return Optional.empty();
    }

    public static SwampHut buildConfig(BootstrapContext<Structure> context) {
        return new SwampHut(
                new StructureSettings.Builder(context.lookup(Registries.BIOME).getOrThrow(ModBiomeTags.HAS_SWAMP_HUT))
                        .spawnOverrides(
                                Map.of(
                                        MobCategory.MONSTER,
                                        new StructureSpawnOverride(
                                                StructureSpawnOverride.BoundingBoxType.STRUCTURE,
                                                WeightedList.<MobSpawnSettings.SpawnerData>builder().build()
                                        )
                                )
                        )
                        .terrainAdapation(TerrainAdjustment.NONE)
                        .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                        .build()
        );
    }

    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(@NotNull GenerationContext context) {
        var columns = new HashMap<Long, NoiseColumn>();
        Function<BlockPos, BlockState> terrain = pos ->
                columns.computeIfAbsent(((long) pos.getX() << 32) ^ (pos.getZ() & 0xffffffffL), _ -> context.chunkGenerator()
                        .getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor(), context.randomState())).getBlock(pos.getY());
        var origin = selectCandidate(context.chunkPos(), context.heightAccessor().getMinY(), context.heightAccessor().getMaxY(), pos -> {
            var center = pos.offset(7, 0, 7);
            if (!context.validBiome().test(context.biomeSource().getNoiseBiome(center.getX() >> 2, center.getY() >> 2, center.getZ() >> 2, context.randomState().sampler())))
                return false;
            return SwampHutPiece.validSite(pos, p -> {
                var state = terrain.apply(p);
                return p.getY() == pos.getY() - 1 && state.is(ModBlocks.UMBERSTONE) && terrain.apply(p.above()).isAir() ? Blocks.GRASS_BLOCK.defaultBlockState() : state;
            }, p -> !context.heightAccessor().isOutsideBuildHeight(p));
        });
        return origin.map(pos -> new GenerationStub(pos, builder -> builder.addPiece(new SwampHutPiece(context.random(), pos))));
    }

    @Override
    public @NotNull StructureType<?> type() {
        return ModStructureTypes.SWAMP_HUT.get();
    }
}
