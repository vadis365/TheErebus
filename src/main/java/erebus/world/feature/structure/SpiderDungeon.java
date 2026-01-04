package erebus.world.feature.structure;

import com.mojang.serialization.MapCodec;
import erebus.registries.world.structure.ModStructureTypes;
import erebus.world.feature.structure.pieces.SpiderDungeonPiece;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

public class SpiderDungeon extends Structure {

    public static final MapCodec<SpiderDungeon> CODEC = simpleCodec(SpiderDungeon::new);

    public SpiderDungeon(StructureSettings settings) {
        super(settings);
    }

    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(@NotNull GenerationContext context) {
        return onTopOfChunkCenter(context, Heightmap.Types.WORLD_SURFACE_WG, builder -> generatePieces(builder, context));
    }

    private static void generatePieces(StructurePiecesBuilder builder, GenerationContext context) {
        builder.addPiece(new SpiderDungeonPiece(context.random(), context.chunkPos().getMinBlockX(), context.chunkPos().getMinBlockZ()));
    }

    public static SpiderDungeon buildConfig(BootstrapContext<Structure> context) {
        return new SpiderDungeon(
                new StructureSettings.Builder(context.lookup(Registries.BIOME).getOrThrow(ModTags.HAS_SPIDER_DUNGEON))
                        .spawnOverrides(
                                Map.of(
                                        MobCategory.MONSTER,
                                        new StructureSpawnOverride(
                                                StructureSpawnOverride.BoundingBoxType.STRUCTURE,
                                                WeightedRandomList.create()
                                        )
                                )
                        )
                        .terrainAdapation(TerrainAdjustment.BEARD_BOX)
                        .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                        .build()
        );
    }

    @Override
    public @NotNull StructureType<?> type() {
        return ModStructureTypes.SPIDER_DUNGEON.get();
    }
}
