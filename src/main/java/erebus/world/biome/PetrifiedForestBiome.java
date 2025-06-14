package erebus.world.biome;

import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import erebus.registries.world.feature.DecorationFeatures;
import erebus.registries.world.feature.OreFeatures;
import erebus.world.biome.util.ErebusBiome;
import erebus.world.carver.ModCarvers;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.SURFACE_STRUCTURES;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES;

public class PetrifiedForestBiome extends ErebusBiome {

    public static final ResourceKey<Biome> RESOURCE_KEY = makeKey("petrified_forest");

    public PetrifiedForestBiome(int color, int fogColor) {
        super(color, fogColor);
    }

    @Override
    public ResourceKey<Biome> getResourceKey() {
        return RESOURCE_KEY;
    }

    @Override
    public Biome getBiome(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        return new Biome.BiomeBuilder()
                .temperature(1.1F)
                .hasPrecipitation(false)
                .downfall(0)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(COLOR)
                        .foliageColorOverride(COLOR)
                        .skyColor(COLOR)
                        .grassColorOverride(COLOR)
                        .fogColor(FOG_COLOR)
                        .waterFogColor(FOG_COLOR)
                        .ambientLoopSound(ModSounds.AMBIENT_FEINT_SLEEPLESS)
                        .build()
                )
                .mobSpawnSettings(new MobSpawnSettings.Builder()
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.FLY.get(), 10, 8, 8))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.BOT_FLY.get(), 10, 2, 3))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.BLACK_WIDOW.get(), 5, 1, 1))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.MOTH.get(), 15, 2, 3))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.SCYTODES.get(), 20, 1, 4))
                        .build()
                )
                .generationSettings(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)
                        .addCarver(GenerationStep.Carving.AIR, ModCarvers.EREBUS_CAVE)
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.PETRIFIED_TREE_BROWN_SMALL.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.PETRIFIED_TREE_BROWN_MEDIUM.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.PETRIFIED_TREE_BROWN_LARGE.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.PETRIFIED_TREE_RED_SMALL.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.PETRIFIED_TREE_RED_MEDIUM.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.PETRIFIED_TREE_RED_LARGE.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.LAVA_LAKE.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.ROCK_SPIKE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, DecorationFeatures.RED_GEM.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.IRON_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.LAPIS_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.EMERALD_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.PETRIFIED_WOOD_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.ALUMINUM_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.COPPER_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.LEAD_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.SILVER_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.TIN_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.QUARTZ_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.GNEISS_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.TEMPLE_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.GOLD_ORE_PETRIFIED_FOREST.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.ENCRUSTED_DIAMOND_ORE_PETRIFIED_FOREST.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.JADE_ORE_PETRIFIED_FOREST.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.FOSSIL_ORE_PETRIFIED_FOREST.getPlacedResourceKey())
                        .build())
                .build();
    }
}
