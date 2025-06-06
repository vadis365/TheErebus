package erebus.world.biome;

import erebus.registries.ModSounds;
import erebus.registries.world.feature.DecorationFeatures;
import erebus.registries.world.feature.OreFeatures;
import erebus.registries.world.feature.PlantFeatures;
import erebus.registries.world.feature.TreeFeatures;
import erebus.world.carver.ModCarvers;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.*;

public class FungalForestBiome extends ErebusBiome {

    public static final ResourceKey<Biome> RESOURCE_KEY = makeKey("fungal_forest");

    public FungalForestBiome(int color, int fogColor) {
        super(color, fogColor);
    }

    @Override
    public ResourceKey<Biome> getResourceKey() {
        return RESOURCE_KEY;
    }

    @Override
    public Biome getBiome(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        return new Biome.BiomeBuilder()
                .temperature(0.7F)
                .hasPrecipitation(false)
                .downfall(0)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(COLOR)
                        .foliageColorOverride(COLOR)
                        .skyColor(COLOR)
                        .grassColorOverride(COLOR)
                        .fogColor(FOG_COLOR)
                        .waterFogColor(FOG_COLOR)
                        .ambientLoopSound(ModSounds.AMBIENT_BUG_IN_THE_SYSTEM)
                        .build()
                )
                .mobSpawnSettings(new MobSpawnSettings.Builder()
                        .build()
                )
                .generationSettings(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)
                        .addCarver(GenerationStep.Carving.AIR, ModCarvers.EREBUS_CAVE)
                        .addFeature(SURFACE_STRUCTURES, PlantFeatures.BIG_LOGS_X.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, PlantFeatures.BIG_LOGS_Z.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, PlantFeatures.ROTTEN_TREE_STUMP.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.ROTTEN_ACACIA.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, TreeFeatures.CYPRESS_TREE.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.DARK_CAPPED_MUSHROOM.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.DUTCH_CAP_MUSHROOM.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.GRANDMAS_SHOES_MUSHROOM.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.KAIZERS_FINGERS_MUSHROOM.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.SARCASTIC_CZECH_MUSHROOM.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.TANGLED_STALK.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.HIGH_CAPPED_MUSHROOM.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.FERN.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.MOSS.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, DecorationFeatures.RED_GEM.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.LAPIS_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.DIAMOND_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.ALUMINUM_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.COPPER_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.LEAD_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.SILVER_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.TIN_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.QUARTZ_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.GNEISS_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.TEMPLE_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.COAL_ORE_FUNGAL_FOREST.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.IRON_ORE_FUNGAL_FOREST.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.GOLD_ORE_FUNGAL_FOREST.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.EMERALD_ORE_FUNGAL_FOREST.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.JADE_ORE_FUNGAL_FOREST.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.FOSSIL_ORE_FUNGAL_FOREST.getPlacedResourceKey())
                        .build())
                .build();
    }
}
