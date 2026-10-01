package erebus.world.feature.structure;

import com.mojang.serialization.MapCodec;
import erebus.registries.data.tags.ModBiomeTags;
import erebus.registries.world.structure.ModStructureTypes;
import erebus.world.feature.structure.pieces.WaspDungeonPiece;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.MobSpawnSettings;
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

public class WaspDungeon extends TerrainCheckedStructure {

    public static final MapCodec<WaspDungeon> CODEC = simpleCodec(WaspDungeon::new);

    public WaspDungeon(StructureSettings settings) {
        super(settings);
    }

    public static Optional<BlockPos> selectCandidate(RandomSource random, ChunkPos chunk, int minY, Predicate<BlockPos> valid) {
        if (random.nextInt(60) != 0) return Optional.empty();
        for (int attempt = 0; attempt < 5; attempt++) {
            int x = chunk.getMinBlockX() + 8 + random.nextInt(16), z = chunk.getMinBlockZ() + 8 + random.nextInt(16);
            var top = new BlockPos(x, minY + 127 - 12 - random.nextInt(14), z);
            if (valid.test(top)) return Optional.of(top);
        }
        return Optional.empty();
    }

    public static WaspDungeon buildConfig(BootstrapContext<Structure> context) {
        return new WaspDungeon(
                new StructureSettings.Builder(context.lookup(Registries.BIOME).getOrThrow(ModBiomeTags.HAS_WASP_DUNGEON))
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
        Function<BlockPos, BlockState> terrain = p ->
                columns.computeIfAbsent(((long) p.getX() << 32) ^ (p.getZ() & 0xffffffffL), _ -> context.chunkGenerator()
                        .getBaseColumn(p.getX(), p.getZ(), context.heightAccessor(), context.randomState())).getBlock(p.getY());
        var top = selectCandidate(context.random(), context.chunkPos(), context.heightAccessor().getMinY(), p ->
                context.validBiome().test(context.biomeSource().getNoiseBiome(p.getX() >> 2, p.getY() >> 2, p.getZ() >> 2, context.randomState().sampler()))
                        && WaspDungeonPiece.validSite(p, terrain, pos -> !context.heightAccessor().isOutsideBuildHeight(pos)));
        return top.map(p -> new GenerationStub(p, builder -> builder.addPiece(new WaspDungeonPiece(p))));
    }

    @Override
    public @NotNull StructureType<?> type() {
        return ModStructureTypes.WASP_DUNGEON.get();
    }
}
