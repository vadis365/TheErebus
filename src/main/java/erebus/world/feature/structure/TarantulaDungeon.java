package erebus.world.feature.structure;

import com.mojang.serialization.MapCodec;
import erebus.registries.data.tags.ModBiomeTags;
import erebus.registries.entity.ModEntities;
import erebus.registries.world.structure.ModStructureTypes;
import erebus.world.feature.structure.pieces.TarantulaDungeonPiece;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.*;
import org.jspecify.annotations.NonNull;

import java.util.Map;

public class TarantulaDungeon extends SinglePieceStructure {

    public static final MapCodec<TarantulaDungeon> CODEC = simpleCodec(TarantulaDungeon::new);

    public TarantulaDungeon(StructureSettings settings) {
        super(TarantulaDungeonPiece::new, 31, 31, settings);
    }

    public static TarantulaDungeon buildConfig(BootstrapContext<Structure> context) {
        return new TarantulaDungeon(new StructureSettings.Builder(context.lookup(Registries.BIOME).getOrThrow(ModBiomeTags.HAS_TARANTULA_DUNGEON))
                .terrainAdapation(TerrainAdjustment.BEARD_BOX)
                .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                .spawnOverrides(
                        Map.of(
                                MobCategory.MONSTER,
                                new StructureSpawnOverride(
                                        StructureSpawnOverride.BoundingBoxType.PIECE, WeightedList.of(new MobSpawnSettings.SpawnerData(ModEntities.TARANTULA_MINI_BOSS.get(), 1, 1))
                                )
                        )
                )
                .build());
    }

    @Override
    public @NonNull StructureType<?> type() {
        return ModStructureTypes.TARANTULA_DUNGEON.get();
    }
}
