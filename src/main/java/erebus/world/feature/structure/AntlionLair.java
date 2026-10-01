package erebus.world.feature.structure;

import com.mojang.serialization.MapCodec;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModBiomeTags;
import erebus.registries.world.ModBiomes;
import erebus.registries.world.structure.ModStructureTypes;
import erebus.world.feature.structure.pieces.AntlionLairPiece;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.util.RandomSource;
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

public class AntlionLair extends TerrainCheckedStructure {

    public static final MapCodec<AntlionLair> CODEC = simpleCodec(AntlionLair::new);

    public AntlionLair(StructureSettings settings) {
        super(settings);
    }

    public static Optional<BlockPos> selectCandidate(RandomSource random,
                                                     ChunkPos chunk, int minY, Predicate<BlockPos> valid) {
        if (random.nextInt(5) != 0) return Optional.empty();
        for (int i = 0; i < 300; i++) {
            var pos = new BlockPos(chunk.getMinBlockX() + 13 + random.nextInt(6), minY + 15 + random.nextInt(35), chunk.getMinBlockZ() + 13 + random.nextInt(6));
            if (valid.test(pos)) return Optional.of(pos);
        }
        return Optional.empty();
    }

    public static AntlionLair buildConfig(BootstrapContext<Structure> context) {
        return new AntlionLair(
                new StructureSettings.Builder(context.lookup(Registries.BIOME).getOrThrow(ModBiomeTags.HAS_ANTLION_LAIR))
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
                        .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                        .build()
        );
    }

    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(@NotNull GenerationContext context) {
        var columns = new HashMap<Long, NoiseColumn>();
        Function<BlockPos, BlockState> terrain = p -> {
            var column = columns.computeIfAbsent(((long) p.getX() << 32) ^ (p.getZ() & 0xffffffffL), _ ->
                    context.chunkGenerator().getBaseColumn(p.getX(), p.getZ(), context.heightAccessor(), context.randomState()));
            var state = column.getBlock(p.getY());
            if (state.is(ModBlocks.UMBERSTONE.get()) && column.getBlock(p.getY() + 1).isAir()
                    && context.biomeSource().getNoiseBiome(p.getX() >> 2, p.getY() >> 2, p.getZ() >> 2, context.randomState().sampler())
                    .is(ModBiomes.VOLCANIC_DESERT_KEY)) return Blocks.SAND.defaultBlockState();
            return state;
        };
        return selectCandidate(context.random(), context.chunkPos(), context.heightAccessor().getMinY(), p ->
                context.validBiome().test(context.biomeSource().getNoiseBiome(p.getX() >> 2, p.getY() >> 2, p.getZ() >> 2, context.randomState().sampler()))
                        && AntlionLairPiece.validSite(p, terrain, q -> !context.heightAccessor().isOutsideBuildHeight(q)))
                .map(p -> new GenerationStub(p, builder -> builder.addPiece(new AntlionLairPiece(p))));
    }

    @Override
    public @NotNull StructureType<?> type() {
        return ModStructureTypes.ANTLION_LAIR.get();
    }
}
