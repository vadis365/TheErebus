package erebus.registries.world;

import com.mojang.datafixers.util.Pair;
import erebus.Erebus;
import erebus.world.gen.TerrainPoint;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class ModBiomes {
    public static final ResourceKey<Biome> ELYSIAN_FIELDS = makeKey("elysian_fields");
    public static final ResourceKey<Biome> FUNGAL_FOREST = makeKey("fungal_forest");
    public static final ResourceKey<Biome> PETRIFIED_FOREST = makeKey("petrified_forest");
    public static final ResourceKey<Biome> SUBMERGED_SWAMP = makeKey("submerged_swamp");
    public static final ResourceKey<Biome> SUBTERRANEAN_SAVANNAH = makeKey("subterranean_savannah");
    public static final ResourceKey<Biome> ULTERIOR_OUTBACK = makeKey("ulterior_outback");
    public static final ResourceKey<Biome> UNDERGROUND_JUNGLE = makeKey("underground_jungle");
    public static final ResourceKey<Biome> VOLCANIC_DESERT = makeKey("volcanic_desert");

    private static ResourceKey<Biome> makeKey(String name) {
        return ResourceKey.create(Registries.BIOME, Erebus.prefix(name));
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> featureGetter = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(ELYSIAN_FIELDS, new Biome.BiomeBuilder()
                .temperature(0.85F)
                .hasPrecipitation(false)
                .downfall(0)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xC6FF54)
                        .foliageColorOverride(0xC6FF54)
                        .skyColor(0xC6FF54)
                        .grassColorOverride(0xC6FF54)
                        .fogColor(0xD5E47F)
                        .waterFogColor(0xD5E47F)
                        .build()
                )
                .mobSpawnSettings(MobSpawnSettings.EMPTY)
                .generationSettings(BiomeGenerationSettings.EMPTY)
                .build()
        );

        context.register(FUNGAL_FOREST, new Biome.BiomeBuilder()
                .temperature(0.85F)
                .hasPrecipitation(false)
                .downfall(0)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xC6FF54)
                        .foliageColorOverride(0xC6FF54)
                        .skyColor(0xC6FF54)
                        .grassColorOverride(0xC6FF54)
                        .fogColor(0xD5E47F)
                        .waterFogColor(0xD5E47F)
                        .build()
                )
                .mobSpawnSettings(MobSpawnSettings.EMPTY)
                .generationSettings(BiomeGenerationSettings.EMPTY)
                .build()
        );

        context.register(PETRIFIED_FOREST, new Biome.BiomeBuilder()
                .temperature(0.85F)
                .hasPrecipitation(false)
                .downfall(0)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xC6FF54)
                        .foliageColorOverride(0xC6FF54)
                        .skyColor(0xC6FF54)
                        .grassColorOverride(0xC6FF54)
                        .fogColor(0xD5E47F)
                        .waterFogColor(0xD5E47F)
                        .build()
                )
                .mobSpawnSettings(MobSpawnSettings.EMPTY)
                .generationSettings(BiomeGenerationSettings.EMPTY)
                .build()
        );

        context.register(SUBMERGED_SWAMP, new Biome.BiomeBuilder()
                .temperature(0.85F)
                .hasPrecipitation(false)
                .downfall(0)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xC6FF54)
                        .foliageColorOverride(0xC6FF54)
                        .skyColor(0xC6FF54)
                        .grassColorOverride(0xC6FF54)
                        .fogColor(0xD5E47F)
                        .waterFogColor(0xD5E47F)
                        .build()
                )
                .mobSpawnSettings(MobSpawnSettings.EMPTY)
                .generationSettings(BiomeGenerationSettings.EMPTY)
                .build()
        );

        context.register(SUBTERRANEAN_SAVANNAH, new Biome.BiomeBuilder()
                .temperature(0.85F)
                .hasPrecipitation(false)
                .downfall(0)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xC6FF54)
                        .foliageColorOverride(0xC6FF54)
                        .skyColor(0xC6FF54)
                        .grassColorOverride(0xC6FF54)
                        .fogColor(0xD5E47F)
                        .waterFogColor(0xD5E47F)
                        .build()
                )
                .mobSpawnSettings(MobSpawnSettings.EMPTY)
                .generationSettings(BiomeGenerationSettings.EMPTY)
                .build()
        );

        context.register(ULTERIOR_OUTBACK, new Biome.BiomeBuilder()
                .temperature(0.85F)
                .hasPrecipitation(false)
                .downfall(0)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xC6FF54)
                        .foliageColorOverride(0xC6FF54)
                        .skyColor(0xC6FF54)
                        .grassColorOverride(0xC6FF54)
                        .fogColor(0xD5E47F)
                        .waterFogColor(0xD5E47F)
                        .build()
                )
                .mobSpawnSettings(MobSpawnSettings.EMPTY)
                .generationSettings(BiomeGenerationSettings.EMPTY)
                .build()
        );

        context.register(UNDERGROUND_JUNGLE, new Biome.BiomeBuilder()
                .temperature(0.85F)
                .hasPrecipitation(false)
                .downfall(0)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xC6FF54)
                        .foliageColorOverride(0xC6FF54)
                        .skyColor(0xC6FF54)
                        .grassColorOverride(0xC6FF54)
                        .fogColor(0xD5E47F)
                        .waterFogColor(0xD5E47F)
                        .build()
                )
                .mobSpawnSettings(MobSpawnSettings.EMPTY)
                .generationSettings(BiomeGenerationSettings.EMPTY)
                .build()
        );

        context.register(VOLCANIC_DESERT, new Biome.BiomeBuilder()
                .temperature(0.85F)
                .hasPrecipitation(false)
                .downfall(0)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xC6FF54)
                        .foliageColorOverride(0xC6FF54)
                        .skyColor(0xC6FF54)
                        .grassColorOverride(0xC6FF54)
                        .fogColor(0xD5E47F)
                        .waterFogColor(0xD5E47F)
                        .build()
                )
                .mobSpawnSettings(MobSpawnSettings.EMPTY)
                .generationSettings(BiomeGenerationSettings.EMPTY)
                .build()
        );
    }

    public static List<Pair<TerrainPoint, Holder<Biome>>> biomeParameters(HolderGetter<Biome> registry) {
        return List.of(
               pairBiome(registry, 20, -0.125F, 0.475F, ELYSIAN_FIELDS),
               pairBiome(registry, 12, -0.125F, 0.475F, FUNGAL_FOREST),
               pairBiome(registry, 15, -0.125F, 0.475F, PETRIFIED_FOREST),
               pairBiome(registry, 20, -0.125F, 0.475F, SUBMERGED_SWAMP),
               pairBiome(registry, 20, -0.125F, 0.475F, SUBTERRANEAN_SAVANNAH),
               pairBiome(registry, 15, -0.125F, 0.475F, ULTERIOR_OUTBACK),
               pairBiome(registry, 22, -0.125F, 0.475F, UNDERGROUND_JUNGLE),
               pairBiome(registry, 16, -0.125F, 0.475F, VOLCANIC_DESERT)
        );
    }

    private static Pair<TerrainPoint, Holder<Biome>> pairBiome(HolderGetter<Biome> registry, int weight, float depth, float scale, ResourceKey<Biome> biome) {
        return Pair.of(new TerrainPoint((short) weight, depth, scale), registry.getOrThrow(biome));
    }
}
