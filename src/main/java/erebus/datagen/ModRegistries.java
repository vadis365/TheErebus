package erebus.datagen;

import erebus.Erebus;
import erebus.registries.world.ModBiomes;
import erebus.registries.world.ModDimensionRegistries;
import erebus.registries.world.ModFeatures;
import erebus.world.ModNoiseGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModRegistries extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModFeatures::bootstrapConfiguredFeatures)
            .add(Registries.PLACED_FEATURE, ModFeatures::bootstrapPlacedFeatures)
            .add(Registries.DIMENSION_TYPE, ModDimensionRegistries::bootstrapType)
            .add(Registries.NOISE_SETTINGS, ModNoiseGenerator::bootstrap)
            .add(Registries.LEVEL_STEM, ModDimensionRegistries::bootstrapStem);

    public ModRegistries(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future, BUILDER, Set.of("minecraft", Erebus.MODID));
    }
}
