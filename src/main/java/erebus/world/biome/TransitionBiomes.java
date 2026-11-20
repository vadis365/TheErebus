package erebus.world.biome;

import erebus.registries.entity.ModEntities;
import erebus.registries.world.carver.ModCarvers;
import erebus.registries.world.feature.DecorationFeatures;
import erebus.registries.world.feature.OreFeatures;
import erebus.registries.world.feature.PlantFeatures;
import erebus.registries.world.feature.TreeFeatures;
import erebus.world.feature.misc.TransitionLandmark.Kind;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static erebus.registries.world.ModBiomes.*;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.*;

public final class TransitionBiomes {
    private TransitionBiomes() {}

    public static void bootstrap(BootstrapContext<Biome> context, HolderGetter<PlacedFeature> features,
                                 HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        context.register(ROOTWATER_MARSH_KEY, biome(0.9F, 0.95F, 0x385A3C, 0x65A350, 0x45624C,
                spawn(spawn(new MobSpawnSettings.Builder(), ModEntities.MOSQUITO.get(), 14, 3),
                        ModEntities.POND_SKATER.get(), 12, 2),
                terrain(features, carvers).addFeature(SURFACE_STRUCTURES, Kind.ROOT_CROSSINGS.feature.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, Kind.ROOTWATER_POOLS.feature.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, TreeFeatures.JUNGLE_TREE_POPULATION.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.FERN.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.FIDDLE_HEAD.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.MIRE_CORAL.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.DARK_FRUIT_VINE_POPULATION.getPlacedResourceKey())));
        context.register(CINDER_SCRUB_KEY, biome(1.7F, 0.05F, 0x847350, 0xA3A057, 0xA18366,
                spawn(spawn(new MobSpawnSettings.Builder(), ModEntities.FIRE_ANT.get(), 6, 2),
                        ModEntities.SCORPION.get(), 18, 3),
                terrain(features, carvers).addFeature(SURFACE_STRUCTURES, Kind.SCORCHED_OUTCROPS.feature.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.DESERT_SHRUB.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.PRICKLY_PEAR.getPlacedResourceKey())));
        context.register(GLOWROT_GROVES_KEY, biome(0.65F, 0.85F, 0x55706B, 0x679946, 0x84968E,
                spawn(spawn(new MobSpawnSettings.Builder(), ModEntities.GLOW_WORM.get(), 12, 2),
                        ModEntities.FUNGAL_WEEVIL.get(), 14, 2),
                terrain(features, carvers).addFeature(SURFACE_STRUCTURES, Kind.GLOWROT_LOGS.feature.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, PlantFeatures.ROTTEN_TREE_STUMP.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, TreeFeatures.GLOWROT_CANOPY.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.GIANT_MUSHROOM_POPULATION.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.FERN.getPlacedResourceKey())));
        context.register(AMBER_THICKETS_KEY, biome(0.95F, 0.65F, 0x747846, 0x83AF4C, 0x878059,
                spawn(spawn(new MobSpawnSettings.Builder(), ModEntities.GRASSHOPPER.get(), 14, 3),
                        ModEntities.BEETLE.get(), 12, 2),
                terrain(features, carvers).addFeature(SURFACE_STRUCTURES, Kind.AMBER_BANKS.feature.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, TreeFeatures.SAVANNAH_ACACIA.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.JUNGLE_BAMBOO_POPULATION.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.FERN.getPlacedResourceKey())
                        .addFeature(VEGETAL_DECORATION, PlantFeatures.DARK_FRUIT_VINE_POPULATION.getPlacedResourceKey())));
        context.register(FOSSIL_BARRENS_KEY, biome(1.35F, 0.1F, 0x887254, 0x9D935B, 0x998776,
                spawn(spawn(new MobSpawnSettings.Builder(), ModEntities.BLACK_WIDOW.get(), 10, 1),
                        ModEntities.SCYTODES.get(), 14, 2),
                terrain(features, carvers).addFeature(SURFACE_STRUCTURES, Kind.FOSSIL_STANDS.feature.getPlacedResourceKey())
                        .addFeature(SURFACE_STRUCTURES, DecorationFeatures.SCORCHED_WOOD.getPlacedResourceKey())
                        .addFeature(UNDERGROUND_ORES, OreFeatures.FOSSIL_ORE_PETRIFIED_FOREST.getPlacedResourceKey())));
    }

    private static Biome biome(float temperature, float downfall, int water, int foliage, int fog,
                               MobSpawnSettings.Builder spawns, BiomeGenerationSettings.Builder terrain) {
        return new Biome.BiomeBuilder().temperature(temperature).downfall(downfall).hasPrecipitation(false)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 0xFF000000 | fog)
                .specialEffects(new BiomeSpecialEffects.Builder().waterColor(water)
                        .foliageColorOverride(foliage).grassColorOverride(foliage).build())
                .mobSpawnSettings(spawns.build()).generationSettings(terrain.build()).build();
    }

    private static MobSpawnSettings.Builder spawn(MobSpawnSettings.Builder spawns, EntityType<?> entity,
                                                   int weight, int groupSize) {
        return spawns.addSpawn(entity.getCategory(), weight, new MobSpawnSettings.SpawnerData(entity, 1, groupSize));
    }

    private static BiomeGenerationSettings.Builder terrain(HolderGetter<PlacedFeature> features,
                                                            HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return new BiomeGenerationSettings.Builder(features, carvers)
                .addCarver(ModCarvers.CAVE).addCarver(ModCarvers.CANYON)
                .addFeature(UNDERGROUND_ORES, OreFeatures.IRON_ORE.getPlacedResourceKey())
                .addFeature(UNDERGROUND_ORES, OreFeatures.COPPER_ORE.getPlacedResourceKey())
                .addFeature(UNDERGROUND_ORES, OreFeatures.QUARTZ_ORE.getPlacedResourceKey())
                .addFeature(UNDERGROUND_ORES, OreFeatures.GNEISS_ORE.getPlacedResourceKey())
                .addFeature(UNDERGROUND_ORES, OreFeatures.TEMPLE_ORE.getPlacedResourceKey());
    }
}
