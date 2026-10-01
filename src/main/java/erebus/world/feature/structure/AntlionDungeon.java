package erebus.world.feature.structure;

import com.mojang.serialization.MapCodec;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModBiomeTags;
import erebus.registries.world.structure.ModStructureTypes;
import erebus.world.feature.structure.pieces.AntlionDungeonPiece;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
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

public class AntlionDungeon extends Structure {

    public static final MapCodec<AntlionDungeon> CODEC = simpleCodec(AntlionDungeon::new);

    public AntlionDungeon(StructureSettings settings) {
        super(settings);
    }

    public static Optional<BlockPos> selectCandidate(ChunkPos chunk, int minY, Function<BlockPos, BlockState> terrain,
                                                     Predicate<BlockPos> inBounds, Predicate<BlockPos> biome) {
        // Locate an interior cavern floor rather than the dimension's solid roof heightmap.
        for (int y = minY + 16; y <= minY + 80; y++) {
            var floor = new BlockPos(chunk.getMiddleBlockX(), y, chunk.getMiddleBlockZ());
            var ground = terrain.apply(floor);
            if (!(ground.is(ModBlocks.UMBERSTONE) || ground.is(ModBlocks.VOLCANIC_ROCK)
                    || ground.is(net.minecraft.tags.BlockTags.SAND)) || !terrain.apply(floor.above()).isAir()) continue;
            var center = floor.above(4);
            if (!inBounds.test(center.offset(-60, -4, -60)) || !inBounds.test(center.offset(60, 21, 60))) continue;
            if (!biome.test(center) || !biome.test(center.north(64)) || !biome.test(center.south(64))
                    || !biome.test(center.east(64)) || !biome.test(center.west(64))) continue;
            return Optional.of(center);
        }
        return Optional.empty();
    }

    public static AntlionDungeon buildConfig(BootstrapContext<Structure> context) {
        return new AntlionDungeon(
                new StructureSettings.Builder(context.lookup(Registries.BIOME).getOrThrow(ModBiomeTags.HAS_ANTLION_DUNGEON))
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
        Function<BlockPos, BlockState> terrain = pos -> columns.computeIfAbsent(
                ((long) pos.getX() << 32) ^ (pos.getZ() & 0xffffffffL), ignored -> context.chunkGenerator()
                        .getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor(), context.randomState())).getBlock(pos.getY());
        return selectCandidate(context.chunkPos(), context.heightAccessor().getMinY(), terrain,
                pos -> !context.heightAccessor().isOutsideBuildHeight(pos),
                pos -> context.validBiome().test(context.biomeSource().getNoiseBiome(pos.getX() >> 2, pos.getY() >> 2,
                        pos.getZ() >> 2, context.randomState().sampler())))
                .map(pos -> new GenerationStub(pos, builder -> builder.addPiece(new AntlionDungeonPiece(pos, context.random().nextLong()))));
    }

    @Override
    public @NotNull StructureType<?> type() {
        return ModStructureTypes.ANTLION_DUNGEON.get();
    }
}
