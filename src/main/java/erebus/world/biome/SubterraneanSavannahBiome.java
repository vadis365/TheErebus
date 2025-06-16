package erebus.world.biome;

import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import erebus.registries.world.carver.ModCarvers;
import erebus.registries.world.feature.DecorationFeatures;
import erebus.registries.world.feature.OreFeatures;
import erebus.registries.world.feature.PlantFeatures;
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
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.*;

public class SubterraneanSavannahBiome extends ErebusBiome {
    public static final ResourceKey<Biome> RESOURCE_KEY = makeKey("subterranean_savannah");

    public SubterraneanSavannahBiome(int color, int fogColor) {
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
                        .foliageColorOverride(0xA3A52D)
                        .skyColor(COLOR)
                        .grassColorOverride(COLOR)
                        .fogColor(FOG_COLOR)
                        .waterFogColor(FOG_COLOR)
                        .ambientLoopSound(ModSounds.AMBIENT_BUG_IN_THE_SYSTEM)
                        .build()
                )
                .mobSpawnSettings(new MobSpawnSettings.Builder()
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.BEETLE_LARVA.get(), 25, 2, 4))
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.BEETLE.get(), 20, 1, 2))
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.GRASSHOPPER.get(), 10, 4, 8))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.FLY.get(), 15, 1, 3))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.WASP.get(), 20, 4, 8))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.SCYTODES.get(), 20, 1, 4))
                        .build()
                )
                .generationSettings(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)
                        .addCarver(GenerationStep.Carving.AIR, ModCarvers.CAVE)
                        .addCarver(GenerationStep.Carving.AIR, ModCarvers.CANYON)
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.POND.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.SAVANNAH_ROCK.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.ROTTEN_ACACIA.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.AMBER_GROUND.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.AMBER_UMBERSTONE.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, VegetationPlacements.TREES_SAVANNA)
                        .addFeature(VEGETAL_DECORATION, TreeFeatures.ASPER_TREE.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, TreeFeatures.BAOBAB_TREE.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.BAMBOO.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, DecorationFeatures.RED_GEM.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.COAL_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.IRON_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.LAPIS_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.FOSSIL_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.ALUMINUM_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.COPPER_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.LEAD_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.SILVER_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.TIN_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.QUARTZ_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.GNEISS_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.TEMPLE_ORE.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.GOLD_ORE_SUBTERRANEAN_SAVANNAH_HIGH.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.GOLD_ORE_SUBTERRANEAN_SAVANNAH.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.GOLD_ORE_SUBTERRANEAN_SAVANNAH_LOW.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.EMERALD_ORE_SUBTERRANEAN_SAVANNAH.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.DIAMOND_ORE_SUBTERRANEAN_SAVANNAH.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.JADE_ORE_SUBTERRANEAN_SAVANNAH.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.PETRIFIED_WOOD_ORE_SUBTERRANEAN_SAVANNAH.getPlacedResourceKey())
                        .build())
                .build();
    }
}
