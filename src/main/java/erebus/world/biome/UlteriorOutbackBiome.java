package erebus.world.biome;

import erebus.registries.entity.ModEntities;
import erebus.registries.world.carver.ModCarvers;
import erebus.registries.world.feature.DecorationFeatures;
import erebus.registries.world.feature.OreFeatures;
import erebus.registries.world.feature.TreeFeatures;
import erebus.world.biome.util.ErebusBiome;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.*;

public class UlteriorOutbackBiome extends ErebusBiome {
    public static final ResourceKey<Biome> RESOURCE_KEY = makeKey("ulterior_outback");

    public UlteriorOutbackBiome(int color, int fogColor) {
        super(color, fogColor);
    }

    @Override
    public ResourceKey<Biome> getResourceKey() {
        return RESOURCE_KEY;
    }

    @Override
    public Biome getBiome(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        return new Biome.BiomeBuilder()
                .temperature(2.0F)
                .hasPrecipitation(false)
                .downfall(0)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(COLOR)
                        .foliageColorOverride(COLOR)
                        .build()
                )
                .mobSpawnSettings(new MobSpawnSettings.Builder()
                        .addSpawn(MobCategory.CREATURE, 15, new MobSpawnSettings.SpawnerData(ModEntities.BEETLE_LARVA.get(), 2, 4))
                        .addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(ModEntities.FLY.get(), 2, 2))
                        .addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(ModEntities.CENTIPEDE.get(), 1, 4))
                        .addSpawn(MobCategory.MONSTER, 5, new MobSpawnSettings.SpawnerData(ModEntities.BLACK_WIDOW.get(), 1, 1))
                        .addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(ModEntities.BOT_FLY.get(), 2, 3))
                        .addSpawn(MobCategory.MONSTER, 20, new MobSpawnSettings.SpawnerData(ModEntities.SCYTODES.get(), 1, 4))
                        .build()
                )
                .generationSettings(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)
                        .addCarver(ModCarvers.CAVE)
                        .addCarver(ModCarvers.CANYON)
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.ROTTEN_ACACIA.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_SAVANNA)
                        .addFeature(VEGETAL_DECORATION, VegetationPlacements.TREES_SAVANNA)
                        .addFeature(VEGETAL_DECORATION, TreeFeatures.BALSAM_TREE.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, TreeFeatures.EUCALYPTUS_TREE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, DecorationFeatures.RED_GEM.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.IRON_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.GOLD_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.LAPIS_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.JADE_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.ALUMINUM_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.COPPER_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.LEAD_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.SILVER_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.TIN_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.QUARTZ_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.GNEISS_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.TEMPLE_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.COAL_ORE_ULTERIOR_OUTBACK.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.EMERALD_ORE_ULTERIOR_OUTBACK.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.DIAMOND_ORE_ULTERIOR_OUTBACK.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.PETRIFIED_WOOD_ORE_ULTERIOR_OUTBACK.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.FOSSIL_ORE_ULTERIOR_OUTBACK.getPlacedResourceKey())

                        .build())
                .build();
    }
}
