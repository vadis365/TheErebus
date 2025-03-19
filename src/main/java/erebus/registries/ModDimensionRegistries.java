package erebus.registries;

import erebus.Erebus;
import erebus.world.ErebusSurfaceRuleData;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensionRegistries {
    public static final ResourceLocation DIMENSION_RENDERER = Erebus.prefix("renderer");
    public static final ResourceKey<Level> DIMENSION_KEY = ResourceKey.create(Registries.DIMENSION, Erebus.prefix(Erebus.MODID));
    public static final ResourceKey<DimensionType> DIMENSION_TYPE_KEY = ResourceKey.create(Registries.DIMENSION_TYPE, Erebus.prefix(Erebus.MODID));
    public static final ResourceKey<NoiseGeneratorSettings> NOISE_SETTINGS_KEY = ResourceKey.create(Registries.NOISE_SETTINGS, Erebus.prefix(Erebus.MODID));
    public static final ResourceKey<LevelStem> LEVEL_STEM_KEY = ResourceKey.create(Registries.LEVEL_STEM, Erebus.prefix(Erebus.MODID));

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        context.register(DIMENSION_TYPE_KEY, new DimensionType(
                OptionalLong.empty(),
                false,
                true,
                false,
                true,
                1.0D,
                true,
                true,
                -64,
                384,
                384,
                BlockTags.INFINIBURN_OVERWORLD,
                DIMENSION_RENDERER,
                0.0F,
                new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 7), 7)
        ));
    }

    public static void bootstrapNoise(BootstrapContext<NoiseGeneratorSettings> context) {
        context.register(NOISE_SETTINGS_KEY, new NoiseGeneratorSettings(
                NoiseSettings.create(-64, 384, 1, 2),
                ModBlocks.UMBERSTONE.get().defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                new NoiseRouter(
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(), // TODO add a density function
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero()
                ),
                ErebusSurfaceRuleData.erebus(),
                List.of(),
                120,
                false,
                false,
                false,
                false
        ));
    }
}
