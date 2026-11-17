package erebus.datagen;

import erebus.Erebus;
import erebus.registries.world.ModBiomes;
import erebus.registries.world.ModDimensionRegistries;
import erebus.registries.world.carver.ModCarvers;
import erebus.registries.world.feature.ModFeatures;
import erebus.registries.world.structure.ModStructureSets;
import erebus.registries.world.structure.ModStructures;
import erebus.world.ModNoiseGenerator;
import erebus.world.layer.biome.BiomeLayerStack;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static erebus.registries.ModRegistries.BIOME_STACK;
import static erebus.registries.ModRegistries.BIOME_TERRAIN_DATA;

public class ModRegistries extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModFeatures::bootstrapConfiguredFeatures)
            .add(Registries.PLACED_FEATURE, ModFeatures::bootstrapPlacedFeatures)
            .add(Registries.DIMENSION_TYPE, ModDimensionRegistries::bootstrapType)
            .add(Registries.NOISE_SETTINGS, ModNoiseGenerator::bootstrap)
            .add(Registries.CONFIGURED_CARVER, ModCarvers::bootstrap)
            .add(Registries.STRUCTURE, ModStructures::registerStructures)
            .add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap)
            .add(BIOME_STACK, BiomeLayerStack::bootstrap)
            .add(BIOME_TERRAIN_DATA, BiomeLayerStack::bootstrapData)
            .add(Registries.LEVEL_STEM, ModDimensionRegistries::bootstrapStem);

    public ModRegistries(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future, BUILDER, Set.of("minecraft", Erebus.MODID));
    }
}
