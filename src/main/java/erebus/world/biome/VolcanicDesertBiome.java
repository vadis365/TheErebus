package erebus.world.biome;

import erebus.registries.entity.ModEntities;
import erebus.registries.world.ModBiomes;
import erebus.registries.world.carver.ModCarvers;
import erebus.registries.world.feature.DecorationFeatures;
import erebus.registries.world.feature.OreFeatures;
import erebus.registries.world.feature.PlantFeatures;
import erebus.world.biome.util.ErebusBiome;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.*;

public class VolcanicDesertBiome extends ErebusBiome {

    public VolcanicDesertBiome(int color, int fogColor) {
        super(color, fogColor);
    }

    @Override
    public ResourceKey<Biome> getResourceKey() {
        return ModBiomes.VOLCANIC_DESERT_KEY;
    }

    @Override
    public Biome getBiome(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        return new Biome.BiomeBuilder()
                .temperature(2.0F)
                .hasPrecipitation(false)
                .downfall(0)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(COLOR)
                        .foliageColorOverride(0x91A922)
                        .grassColorOverride(COLOR)
                        .build()
                )
                .mobSpawnSettings(new MobSpawnSettings.Builder()
                        .addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(ModEntities.FLY.get(), 8, 8))
                        .addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(ModEntities.BOT_FLY.get(), 2, 3))
                        .addSpawn(MobCategory.MONSTER, 30, new MobSpawnSettings.SpawnerData(ModEntities.FIRE_ANT.get(), 1, 8))
                        .addSpawn(MobCategory.MONSTER, 30, new MobSpawnSettings.SpawnerData(ModEntities.FIRE_ANT_SOLDIER.get(), 1, 3))
                        .addSpawn(MobCategory.MONSTER, 5, new MobSpawnSettings.SpawnerData(ModEntities.BLACK_WIDOW.get(), 1, 1))
                        .addSpawn(MobCategory.MONSTER, 200, new MobSpawnSettings.SpawnerData(ModEntities.ANTLION.get(), 1, 3))
                        .addSpawn(MobCategory.MONSTER, 25, new MobSpawnSettings.SpawnerData(ModEntities.SOLIFUGE.get(), 1, 4))
                        .addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(ModEntities.CHAMELEON_TICK.get(), 1, 2))
                        .addSpawn(MobCategory.MONSTER, 20, new MobSpawnSettings.SpawnerData(ModEntities.SCORPION.get(), 1, 4))
                        .addSpawn(MobCategory.MONSTER, 300, new MobSpawnSettings.SpawnerData(ModEntities.LAVA_WEB_SPIDER.get(), 1, 1))
                        .build()
                )
                .generationSettings(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)
                        .addCarver(ModCarvers.CAVE)
                        .addCarver(ModCarvers.CANYON)
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.SCORCHED_WOOD.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.LAVA_LAKE.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.DESERT_ROCK_GNEISS.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.PRICKLY_PEAR.getPlacedResourceKey())
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
                        .addFeature(UNDERGROUND_ORES, OreFeatures.GOLD_ORE_VOLCANIC_DESERT.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.ENCRUSTED_DIAMOND_ORE_VOLCANIC_DESERT.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.JADE_ORE_VOLCANIC_DESERT.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.FOSSIL_ORE_VOLCANIC_DESERT.getPlacedResourceKey())
                        .build())
                .build();
    }
}
