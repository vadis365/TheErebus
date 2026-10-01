package erebus.world.feature.structure;

import com.mojang.serialization.MapCodec;
import erebus.registries.data.tags.ModBiomeTags;
import erebus.registries.world.structure.ModStructureTypes;
import erebus.world.feature.structure.pieces.SpiderDungeonPiece;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpiderDungeon extends TerrainCheckedStructure {

    public static final MapCodec<SpiderDungeon> CODEC = simpleCodec(SpiderDungeon::new);

    public SpiderDungeon(StructureSettings settings) {
        super(settings);
    }

    public static List<Candidate> candidates(RandomSource random, ChunkPos chunk, int minY) {
        var result = new ArrayList<Candidate>();
        for (int attempt = 0; attempt < 14; attempt++) {
            int x = chunk.getMinBlockX() + 8 + random.nextInt(16), y = minY + random.nextInt(128), z = chunk.getMinBlockZ() + 8 + random.nextInt(16);
            result.add(new Candidate(new BlockPos(x, y, z), 4 + random.nextInt(4), 4 + random.nextInt(4), random.nextLong()));
        }
        return result;
    }

    public static SpiderDungeon buildConfig(BootstrapContext<Structure> context) {
        return new SpiderDungeon(
                new StructureSettings.Builder(context.lookup(Registries.BIOME).getOrThrow(ModBiomeTags.HAS_SPIDER_DUNGEON))
                        .terrainAdapation(TerrainAdjustment.NONE)
                        .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                        .build()
        );
    }

    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(@NotNull GenerationContext context) {
        var terrain = new StructureTerrainSampler(context.chunkGenerator(), context.heightAccessor(), context.randomState());
        var accepted = candidates(context.random(), context.chunkPos(), context.heightAccessor().getMinY()).stream().filter(c ->
                context.validBiome().test(context.biomeSource().getNoiseBiome(c.center().getX() >> 2, c.center().getY() >> 2, c.center().getZ() >> 2, context.randomState().sampler()))
                        && SpiderDungeonPiece.validSite(c.center(), c.halfX(), c.halfZ(), context.heightAccessor().getMinY(), context.heightAccessor().getMaxY(), terrain::getBlock)).toList();
        if (accepted.isEmpty()) return Optional.empty();
        return Optional.of(new GenerationStub(accepted.getFirst().center(), builder -> accepted.forEach(c ->
                builder.addPiece(new SpiderDungeonPiece(c.center(), c.halfX(), c.halfZ(), c.seed())))));
    }

    @Override
    public @NotNull StructureType<?> type() {
        return ModStructureTypes.SPIDER_DUNGEON.get();
    }

    public record Candidate(BlockPos center, int halfX, int halfZ, long seed) {
    }
}
