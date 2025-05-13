package erebus.world.biome;

import erebus.registries.entity.ModEntities;
import erebus.registries.world.feature.DecorationFeatures;
import erebus.registries.world.feature.OreFeatures;
import erebus.registries.world.feature.PlantFeatures;
import erebus.registries.world.feature.TreeFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class SubmergedSwampBiome extends ErebusBiome {

    public static final ResourceKey<Biome> RESOURCE_KEY = makeKey("submerged_swamp");

    public SubmergedSwampBiome(int color, int fogColor) {
        super(color, fogColor);
    }

    @Override
    public ResourceKey<Biome> getResourceKey() {
        return RESOURCE_KEY;
    }

    @Override
    public Biome getBiome(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        return new Biome.BiomeBuilder()
                .temperature(0.8F)
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
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.DRAGON_FLY.get(), 20, 1, 3))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.BEETLE.get(), 20, 1, 2))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.BEETLE_LARVA.get(), 25, 2, 4))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.CENTIPEDE.get(), 10, 4, 8))
                        .build()
                )
                .generationSettings(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)
                        .addFeature(Decoration.VEGETAL_DECORATION, TreeFeatures.MARSHWOOD_TREE.getPlacedResourceKey())
                        .addFeature(Decoration.VEGETAL_DECORATION, TreeFeatures.MOSSBARK_TREE.getPlacedResourceKey())
                        .addFeature(Decoration.VEGETAL_DECORATION, PlantFeatures.VINES.getPlacedResourceKey())
                        .addFeature(Decoration.VEGETAL_DECORATION, PlantFeatures.SWAMP_PLANT.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, DecorationFeatures.RED_GEM.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.IRON_ORE.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.GOLD_ORE.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.COAL_ORE.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.DIAMOND_ORE.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.EMERALD_ORE.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.LAPIS_ORE.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.QUARTZ_ORE.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.COPPER_ORE.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.SILVER_ORE.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.TIN_ORE.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.LEAD_ORE.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.ALUMINUM_ORE.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.JADE_ORE.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.FOSSIL_ORE.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.GNEISS_ORE.getPlacedResourceKey())
                        .addFeature(Decoration.UNDERGROUND_ORES, OreFeatures.TEMPLE_ORE.getPlacedResourceKey())
                        .build())
                .build();
    }
}
