package erebus.registries.world;

import erebus.Erebus;
import erebus.datagen.ModRegistries;
import erebus.world.ModNoiseGenerator;
import erebus.world.biome.util.ErebusBiomeProvider;
import erebus.world.layer.biome.BiomeLayerStack;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.attribute.*;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;

import java.util.Optional;

public class ModDimensionRegistries {
    public static final ResourceKey<Level> DIMENSION_KEY = ResourceKey.create(Registries.DIMENSION, Erebus.prefix(Erebus.MODID));
    public static final ResourceKey<DimensionType> DIMENSION_TYPE_KEY = ResourceKey.create(Registries.DIMENSION_TYPE, Erebus.prefix(Erebus.MODID));
    public static final ResourceKey<LevelStem> LEVEL_STEM_KEY = ResourceKey.create(Registries.LEVEL_STEM, Erebus.prefix(Erebus.MODID));

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        var clocks = context.lookup(Registries.WORLD_CLOCK);
        context.register(DIMENSION_TYPE_KEY, new DimensionType(
                true,
                false,
                true,
                false,
                4.0,
                0,
                128,
                128,
                BlockTags.INFINIBURN_OVERWORLD,
                0.1F,
                new DimensionType.MonsterSettings(UniformInt.of(0, 7), 7),
                DimensionType.Skybox.NONE,
                CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.builder()
                        .set(EnvironmentAttributes.SUN_ANGLE, 180.0F)
                        .set(EnvironmentAttributes.MOON_ANGLE, 0.0F)
                        .set(EnvironmentAttributes.STAR_ANGLE, 180.0F)
                        .set(EnvironmentAttributes.SKY_LIGHT_LEVEL, 4.0F)
                        .set(EnvironmentAttributes.SKY_LIGHT_FACTOR, 0.0F)
                        .set(EnvironmentAttributes.FOG_COLOR, -4138753)
                        .set(EnvironmentAttributes.SKY_COLOR, OverworldBiomes.calculateSkyColor(0.8F))
                        .set(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, -16119286)
                        .set(EnvironmentAttributes.BACKGROUND_MUSIC, BackgroundMusic.OVERWORLD)
                        .set(EnvironmentAttributes.BED_RULE, new BedRule(BedRule.Rule.NEVER, BedRule.Rule.ALWAYS, false, Optional.of(Component.translatable("block.erebus.bed.respawn_only"))))
                        .set(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, false)
                        .set(EnvironmentAttributes.AMBIENT_SOUNDS, AmbientSounds.LEGACY_CAVE_SETTINGS)
                        .build(),
                HolderSet.empty(),
                Optional.of(clocks.getOrThrow(WorldClocks.OVERWORLD))
        ));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        var type = context.lookup(Registries.DIMENSION_TYPE);
        var noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);
        var biomeDataRegistry = context.lookup(ModRegistries.BIOME_TERRAIN_DATA);

        var noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                new ErebusBiomeProvider(biomeDataRegistry.getOrThrow(BiomeLayerStack.BIOME_GRID)),
                noiseGenSettings.getOrThrow(ModNoiseGenerator.NOISE_GENERATOR)
        );

        var stem = new LevelStem(type.getOrThrow(DIMENSION_TYPE_KEY), noiseBasedChunkGenerator);
        context.register(LEVEL_STEM_KEY, stem);
    }
}
