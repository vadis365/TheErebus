package erebus.world.biome;

import erebus.registries.world.ModFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION;

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
                        .build()
                )
                .mobSpawnSettings(new MobSpawnSettings.Builder()
                        .build()
                )
                .generationSettings(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)
                        .addFeature(VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_FOREST)
                        .addFeature(VEGETAL_DECORATION, VegetationPlacements.MUSHROOM_ISLAND_VEGETATION)
                        .addFeature(VEGETAL_DECORATION, ModFeatures.CYPRESS_TREE.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, ModFeatures.NETTLE.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, ModFeatures.TALL_BLOOM.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, ModFeatures.WEEPING_BLUEBELL.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, ModFeatures.FERN.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, ModFeatures.TALL_FERN.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.IRON_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.GOLD_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.COAL_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.DIAMOND_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.EMERALD_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.LAPIS_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.QUARTZ_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.COPPER_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.SILVER_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.TIN_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.LEAD_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.ALUMINUM_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.JADE_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.FOSSIL_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.GNEISS_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, ModFeatures.TEMPLE_ORE.getPlacedResourceKey())
                        .build())
                .build();
    }
}
