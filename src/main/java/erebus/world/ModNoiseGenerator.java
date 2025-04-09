package erebus.world;

import erebus.Erebus;
import erebus.registries.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;

public class ModNoiseGenerator {
    public static final ResourceKey<NoiseGeneratorSettings> NOISE_GENERATOR = ResourceKey.create(Registries.NOISE_SETTINGS, Erebus.prefix(Erebus.MODID));
    private static final NoiseSettings EREBUS_NOISE_SETTINGS = NoiseSettings.create(-64, 384, 1, 2);

    public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> context) {
        context.register(NOISE_GENERATOR, erebus(context));
    }

    private static NoiseGeneratorSettings erebus(BootstrapContext<?> context) {
        return new NoiseGeneratorSettings(
                EREBUS_NOISE_SETTINGS,
                ModBlocks.UMBERSTONE.get().defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                ModNoiseRouter.erebus(context.lookup(Registries.DENSITY_FUNCTION), context.lookup(Registries.NOISE)),
                ModSurfaceRules.erebus(),
                (new OverworldBiomeBuilder()).spawnTarget(),
                32,
                false,
                true,
                false,
                true
        );
    }
}
