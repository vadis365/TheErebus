package erebus.world.feature.structure;

import com.mojang.serialization.MapCodec;
import erebus.registries.data.ModTags;
import erebus.registries.world.structure.ModStructureTypes;
import erebus.world.feature.structure.pieces.DungPilePiece;
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

public class DungPile extends Structure {

    public static final MapCodec<DungPile> CODEC = simpleCodec(DungPile::new);

    public DungPile(StructureSettings settings) {
        super(settings);
    }

    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(@NotNull GenerationContext context) {
        return onTopOfChunkCenter(context, Heightmap.Types.WORLD_SURFACE_WG, builder -> generatePieces(builder, context));
    }

    private static void generatePieces(StructurePiecesBuilder builder, GenerationContext context) {
        builder.addPiece(new DungPilePiece(context.random(), context.chunkPos().getMinBlockX(), context.chunkPos().getMinBlockZ()));
    }

    public static DungPile buildConfig(BootstrapContext<Structure> context) {
        return new DungPile(
                new StructureSettings.Builder(context.lookup(Registries.BIOME).getOrThrow(ModTags.HAS_DUNG_PILE))
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
        return ModStructureTypes.DUNG_PILE.get();
    }
}
