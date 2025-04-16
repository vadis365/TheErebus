package erebus.registries.world;

import com.mojang.datafixers.util.Pair;
import erebus.Erebus;
import erebus.world.ModNoiseGenerator;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensionRegistries {
    public static final ResourceKey<Level> DIMENSION_KEY = ResourceKey.create(Registries.DIMENSION, Erebus.prefix(Erebus.MODID));
    public static final ResourceKey<DimensionType> DIMENSION_TYPE_KEY = ResourceKey.create(Registries.DIMENSION_TYPE, Erebus.prefix(Erebus.MODID));
    public static final ResourceKey<LevelStem> LEVEL_STEM_KEY = ResourceKey.create(Registries.LEVEL_STEM, Erebus.prefix(Erebus.MODID));

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        context.register(DIMENSION_TYPE_KEY, new DimensionType(
                OptionalLong.empty(),
                false,
                true,
                false,
                true,
                4.0D,
                true,
                true,
                0,
                128,
                128,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                1.0F,
                new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 7), 7)
        ));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> type = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(
                                List.of(
                                        Pair.of(
                                                Climate.parameters(
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F
                                                ), biomeRegistry.getOrThrow(ModBiomes.ELYSIAN_FIELDS.getResourceKey())),
                                        Pair.of(
                                                Climate.parameters(
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F
                                                ), biomeRegistry.getOrThrow(ModBiomes.ELYSIAN_FOREST.getResourceKey())),
                                        Pair.of(
                                                Climate.parameters(
                                                        0.4F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F),
                                                biomeRegistry.getOrThrow(ModBiomes.FUNGAL_FOREST.getResourceKey())),
                                        Pair.of(
                                                Climate.parameters(
                                                        0.0F,
                                                        0.5F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F),
                                                biomeRegistry.getOrThrow(ModBiomes.PETRIFIED_FOREST.getResourceKey())),
                                        Pair.of(
                                                Climate.parameters(
                                                        0.0F,
                                                        -0.5F,
                                                        0.2F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F),
                                                biomeRegistry.getOrThrow(ModBiomes.SUBMERGED_SWAMP.getResourceKey())),
                                        Pair.of(
                                                Climate.parameters(
                                                        0.0F,
                                                        -0.5F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F),
                                                biomeRegistry.getOrThrow(ModBiomes.SUBTERRANEAN_SAVANNAH.getResourceKey())),
                                        Pair.of(
                                                Climate.parameters(
                                                        0.0F,
                                                        0.5F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.375F),
                                                biomeRegistry.getOrThrow(ModBiomes.ULTERIOR_OUTBACK.getResourceKey())),
                                        Pair.of(
                                                Climate.parameters(
                                                        0.4F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F),
                                                biomeRegistry.getOrThrow(ModBiomes.UNDERGROUND_JUNGLE.getResourceKey())),
                                        Pair.of(
                                                Climate.parameters(
                                                        -0.5F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.0F,
                                                        0.175F),
                                                biomeRegistry.getOrThrow(ModBiomes.VOLCANIC_DESERT.getResourceKey()))

                                )
                        )
                ),
                noiseGenSettings.getOrThrow(ModNoiseGenerator.NOISE_GENERATOR));

        LevelStem stem = new LevelStem(type.getOrThrow(DIMENSION_TYPE_KEY), noiseBasedChunkGenerator);
        context.register(LEVEL_STEM_KEY, stem);
    }
}
