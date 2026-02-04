package erebus.world.biome;

import erebus.registries.entity.ModEntities;
import erebus.registries.world.ModBiomes;
import erebus.registries.world.carver.ModCarvers;
import erebus.registries.world.feature.DecorationFeatures;
import erebus.registries.world.feature.OreFeatures;
import erebus.registries.world.feature.PlantFeatures;
import erebus.registries.world.feature.TreeFeatures;
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

public class UndergroundJungleBiome extends ErebusBiome {

    public UndergroundJungleBiome(int color, int fogColor) {
        super(color, fogColor);
    }

    @Override
    public ResourceKey<Biome> getResourceKey() {
        return ModBiomes.UNDERGROUND_JUNGLE_KEY;
    }

    @Override
    public Biome getBiome(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        return new Biome.BiomeBuilder()
                .temperature(0.95F)
                .hasPrecipitation(false)
                .downfall(0)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(COLOR)
                        .foliageColorOverride(0x29BC05)
                        .build()
                )
                .mobSpawnSettings(new MobSpawnSettings.Builder()
                        .addSpawn(MobCategory.CREATURE, 20, new MobSpawnSettings.SpawnerData(ModEntities.BEETLE_LARVA.get(), 2, 4))
                        .addSpawn(MobCategory.CREATURE, 15, new MobSpawnSettings.SpawnerData(ModEntities.BEETLE.get(), 1, 2))
                        .addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(ModEntities.FLY.get(), 8, 8))
                        .addSpawn(MobCategory.MONSTER, 20, new MobSpawnSettings.SpawnerData(ModEntities.WASP.get(), 4, 8))
                        .addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(ModEntities.CENTIPEDE.get(), 4, 8))
                        .addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(ModEntities.BOT_FLY.get(), 2, 3))
                        .addSpawn(MobCategory.MONSTER, 20, new MobSpawnSettings.SpawnerData(ModEntities.BOMBARDIER_BEETLE_LARVA.get(), 2, 4))
                        .addSpawn(MobCategory.MONSTER, 20, new MobSpawnSettings.SpawnerData(ModEntities.SCYTODES.get(), 1, 4))
                        .addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(ModEntities.VELVET_WORM.get(), 1, 2))
                        .build()
                )
                .generationSettings(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)
                        .addCarver(ModCarvers.CAVE)
                        .addCarver(ModCarvers.CANYON)
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.QUICK_SAND.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.POND.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.AMBER_GROUND.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.AMBER_UMBERSTONE.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, TreeFeatures.ASPER_TREE.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, TreeFeatures.EUCALYPTUS_TREE.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, TreeFeatures.MAHOGANY_TREE.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, TreeFeatures.GIANT_MAHOGANY_TREE.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, TreeFeatures.MOSSBARK_TREE.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.DARK_CAPPED_MUSHROOM.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.DUTCH_CAP_MUSHROOM.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.GRANDMAS_SHOES_MUSHROOM.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.KAIZERS_FINGERS_MUSHROOM.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.SARCASTIC_CZECH_MUSHROOM.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.BAMBOO.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.TURNIP.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.MELON.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.FERN.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, DecorationFeatures.RED_GEM.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.IRON_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.GOLD_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.LAPIS_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.EMERALD_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.ALUMINUM_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.COPPER_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.LEAD_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.SILVER_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.TIN_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.QUARTZ_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.GNEISS_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.TEMPLE_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.COAL_ORE_UNDERGROUND_JUNGLE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.COAL_ORE_UNDERGROUND_JUNGLE_LARGE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.DIAMOND_ORE_UNDERGROUND_JUNGLE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.JADE_ORE_UNDERGROUND_JUNGLE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.PETRIFIED_WOOD_ORE_UNDERGROUND_JUNGLE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.FOSSIL_ORE_UNDERGROUND_JUNGLE.getPlacedResourceKey())
                        .build())
                .build();
    }
}
