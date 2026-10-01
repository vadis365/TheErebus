package erebus.world.feature.structure;

import com.mojang.serialization.MapCodec;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModBiomeTags;
import erebus.registries.world.structure.ModStructureTypes;
import erebus.world.feature.structure.pieces.DungPilePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class DungPile extends TerrainCheckedStructure {

    public static final MapCodec<DungPile> CODEC = simpleCodec(DungPile::new);

    public DungPile(StructureSettings settings) {
        super(settings);
    }

    public static Optional<BlockPos> selectCandidate(RandomSource random, ChunkPos chunk, int minY, Predicate<BlockPos> valid) {
        if (!random.nextBoolean() || !random.nextBoolean()) return Optional.empty();
        int x = chunk.getMinBlockX() + 8 + random.nextInt(16), z = chunk.getMinBlockZ() + 8 + random.nextInt(16);
        for (int y = 100; y > 20; y--) {
            var center = new BlockPos(x, minY + y + 1, z);
            if (valid.test(center)) return Optional.of(center);
        }
        return Optional.empty();
    }

    public static DungPile buildConfig(BootstrapContext<Structure> context) {
        return new DungPile(
                new StructureSettings.Builder(context.lookup(Registries.BIOME).getOrThrow(ModBiomeTags.HAS_DUNG_PILE))
                        .terrainAdapation(TerrainAdjustment.NONE)
                        .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                        .build()
        );
    }

    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(@NotNull GenerationContext context) {
        var columns = new HashMap<Long, NoiseColumn>();
        Function<BlockPos, BlockState> terrain = pos -> columns.computeIfAbsent(((long) pos.getX() << 32) ^ (pos.getZ() & 0xffffffffL), _ -> context.chunkGenerator().getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor(), context.randomState())).getBlock(pos.getY());
        var center = selectCandidate(context.random(), context.chunkPos(), context.heightAccessor().getMinY(), pos -> {
            if (!context.validBiome().test(context.biomeSource().getNoiseBiome(pos.getX() >> 2, pos.getY() >> 2, pos.getZ() >> 2, context.randomState().sampler())))
                return false;
            return DungPilePiece.validSite(pos, p -> {
                var state = terrain.apply(p);
                return p.getY() == pos.getY() - 1 && state.is(ModBlocks.UMBERSTONE) && terrain.apply(p.above()).isAir() ? Blocks.RED_SAND.defaultBlockState() : state;
            }, p -> !context.heightAccessor().isOutsideBuildHeight(p));
        });
        return center.map(pos -> new GenerationStub(pos, builder -> builder.addPiece(new DungPilePiece(context.random(), pos))));
    }

    @Override
    public @NotNull StructureType<?> type() {
        return ModStructureTypes.DUNG_PILE.get();
    }
}
