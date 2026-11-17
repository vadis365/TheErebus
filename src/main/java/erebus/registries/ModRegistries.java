package erebus.registries;

import erebus.Erebus;
import erebus.world.layer.biome.BiomeDensitySource;
import erebus.world.layer.biome.BiomeLayerFactory;
import erebus.world.layer.biome.BiomeLayerType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.RegistryBuilder;

public final class ModRegistries {
    public static final ResourceKey<Registry<BiomeLayerType>> BIOME_LAYER_TYPE_KEY = ResourceKey.createRegistryKey(Erebus.prefix("biome_layer_type"));
    public static final ResourceKey<Registry<BiomeLayerFactory>> BIOME_STACK = ResourceKey.createRegistryKey(Erebus.prefix("biome_layer_stack"));
    public static final ResourceKey<Registry<BiomeDensitySource>> BIOME_TERRAIN_DATA = ResourceKey.createRegistryKey(Erebus.prefix("biome_terrain_data"));

    public static final Registry<BiomeLayerType> BIOME_LAYER_TYPE = new RegistryBuilder<>(BIOME_LAYER_TYPE_KEY).create();

    private ModRegistries() {
    }
}
