package erebus.world.feature.structure;

import com.mojang.serialization.MapCodec;
import erebus.registries.data.tags.ModBiomeTags;
import erebus.registries.world.structure.ModStructureTypes;
import erebus.world.feature.structure.pieces.DragonflyDungeonPiece;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
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

public class DragonflyDungeon extends Structure {

    public static final MapCodec<DragonflyDungeon> CODEC = simpleCodec(DragonflyDungeon::new);

    public DragonflyDungeon(StructureSettings settings) {
        super(settings);
    }

    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(@NotNull GenerationContext context) {
        return onTopOfChunkCenter(context, Heightmap.Types.WORLD_SURFACE_WG, builder -> generatePieces(builder, context));
    }

    private static void generatePieces(StructurePiecesBuilder builder, GenerationContext context) {
        builder.addPiece(new DragonflyDungeonPiece(context.random(), context.chunkPos().getMinBlockX(), context.chunkPos().getMinBlockZ()));
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
                        .terrainAdapation(TerrainAdjustment.BEARD_BOX)
                        .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                        .build()
        );
    }

    @Override
    public @NotNull StructureType<?> type() {
        return ModStructureTypes.DRAGONFLY_DUNGEON.get();
    }
}
