package erebus.world.feature.structure;

import com.mojang.serialization.MapCodec;
import erebus.registries.data.tags.ModBiomeTags;
import erebus.registries.world.structure.ModStructureTypes;
import erebus.world.feature.structure.pieces.DragonflyDungeonPiece;
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

public class DragonflyDungeon extends TerrainCheckedStructure {

    public static final MapCodec<DragonflyDungeon> CODEC = simpleCodec(DragonflyDungeon::new);

    public DragonflyDungeon(StructureSettings settings) {
        super(settings);
    }

    public static Optional<BlockPos> selectCandidate(RandomSource random,
                                                     ChunkPos chunk, int minY, Predicate<BlockPos> valid) {
        for (int attempt = 0; attempt < 15; attempt++) {
            var pos = new BlockPos(chunk.getMinBlockX() + 8 + random.nextInt(16), minY + 24, chunk.getMinBlockZ() + 8 + random.nextInt(16));
            if (valid.test(pos)) return Optional.of(pos);
        }
        return Optional.empty();
    }

    public static DragonflyDungeon buildConfig(BootstrapContext<Structure> context) {
        return new DragonflyDungeon(
                new StructureSettings.Builder(context.lookup(Registries.BIOME).getOrThrow(ModBiomeTags.HAS_DRAGONFLY_DUNGEON))
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
        Function<BlockPos, BlockState> terrain = pos -> columns.computeIfAbsent(((long) pos.getX() << 32) ^ (pos.getZ() & 0xffffffffL), _ -> context.chunkGenerator().getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor(), context.randomState())).getBlock(pos.getY());
        var center = selectCandidate(context.random(), context.chunkPos(), context.heightAccessor().getMinY(), pos ->
                context.validBiome().test(context.biomeSource().getNoiseBiome(pos.getX() >> 2, pos.getY() >> 2, pos.getZ() >> 2, context.randomState().sampler()))
                        && DragonflyDungeonPiece.validSite(pos, terrain, p -> !context.heightAccessor().isOutsideBuildHeight(p)));
        return center.map(pos -> new GenerationStub(pos, builder -> builder.addPiece(new DragonflyDungeonPiece(context.random(), pos))));
    }

    @Override
    public @NotNull StructureType<?> type() {
        return ModStructureTypes.DRAGONFLY_DUNGEON.get();
    }
}
