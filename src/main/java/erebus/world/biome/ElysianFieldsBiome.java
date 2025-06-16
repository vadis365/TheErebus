package erebus.world.biome;

import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import erebus.registries.world.feature.DecorationFeatures;
import erebus.registries.world.feature.OreFeatures;
import erebus.registries.world.feature.PlantFeatures;
import erebus.registries.world.feature.TreeFeatures;
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

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION;

public class ElysianFieldsBiome extends ErebusBiome {
    public static final ResourceKey<Biome> RESOURCE_KEY = makeKey("elysian_fields");

    public ElysianFieldsBiome(int color, int fogColor) {
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
                        .ambientLoopSound(ModSounds.AMBIENT_BUG_IN_THE_SYSTEM)
                        .build()
                )
                .mobSpawnSettings(new MobSpawnSettings.Builder()
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.BEETLE.get(), 20, 3, 5))
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.BEETLE_LARVA.get(), 10, 2, 3))
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.GRASSHOPPER.get(), 10, 1, 3))
                        //.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.FLY.get(), 10, 1, 2)) Worker Bee
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.FLY.get(), 10, 1, 2))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.DRAGON_FLY.get(), 10, 8, 8))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.BOT_FLY.get(), 10, 2, 3))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.MOTH.get(), 15, 2, 3))
                        .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.VELVET_WORM.get(), 10, 1, 2))
                        //.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.VELVET_WORM.get(), 10, 1, 2)) Crop Weevil
                        //.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.VELVET_WORM.get(), 10, 1, 2)) Chameleon Tick
                        //.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.VELVET_WORM.get(), 10, 1, 2)) Cicada
                        //.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.VELVET_WORM.get(), 10, 1, 2)) Glow Worm
                        //.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.VELVET_WORM.get(), 10, 1, 2)) Titan Beetle
                        .build()
                )
                .generationSettings(new BiomeGenerationSettings.Builder(featureGetter, carverGetter)
                        .addCarver(GenerationStep.Carving.AIR, ModCarvers.CAVE)
                        .addCarver(GenerationStep.Carving.AIR, ModCarvers.CANYON)
                        .addFeature(VEGETAL_DECORATION, TreeFeatures.CYPRESS_TREE.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.GIANT_FLOWER.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.NETTLE.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.TALL_BLOOM.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.WEEPING_BLUEBELL.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.FERN.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.MOSS.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.GRASS.getPlacedResourceKey())
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
                        .addFeature(UNDERGROUND_ORES, OreFeatures.COAL_ORE_ELYSIAN_FIELDS.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.IRON_ORE_ELYSIAN_FIELDS.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.GOLD_ORE_ELYSIAN_FIELDS.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.EMERALD_ORE_ELYSIAN_FIELDS.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.JADE_ORE_ELYSIAN_FIELDS.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.FOSSIL_ORE_ELYSIAN_FIELDS.getPlacedResourceKey())
                        .build())
                .build();
    }
}
