package erebus.registries.world;

import com.mojang.serialization.Codec;
import erebus.Erebus;
import erebus.datagen.ModRegistries;
import erebus.world.layer.*;
import erebus.world.layer.biome.BiomeLayerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBiomeLayerTypes {

    public static final DeferredRegister<BiomeLayerType> BIOME_LAYER_TYPES = DeferredRegister.create(ModRegistries.BIOME_LAYER_TYPE_KEY, Erebus.MODID);
    public static final Codec<BiomeLayerType> CODEC = Codec.lazyInitialized(ModRegistries.BIOME_LAYER_TYPE::byNameCodec);

    public static final DeferredHolder<BiomeLayerType, BiomeLayerType> RANDOM_BIOMES = registerType("random_biomes", () -> () -> RandomBiomeLayer.Factory.CODEC);
    public static final DeferredHolder<BiomeLayerType, BiomeLayerType> KEY_BIOMES = registerType("key_biomes", () -> () -> KeyBiomeLayer.Factory.CODEC);
    public static final DeferredHolder<BiomeLayerType, BiomeLayerType> COMPANION_BIOMES = registerType("companion_biomes", () -> () -> CompanionBiomeLayer.Factory.CODEC);
    public static final DeferredHolder<BiomeLayerType, BiomeLayerType> ZOOM = registerType("zoom", () -> () -> ZoomLayer.Factory.CODEC);
    public static final DeferredHolder<BiomeLayerType, BiomeLayerType> STABILIZE = registerType("stabilize", () -> () -> StabilizeLayer.Factory.CODEC);

    private static DeferredHolder<BiomeLayerType, BiomeLayerType> registerType(String name, Supplier<BiomeLayerType> factory) {
        return BIOME_LAYER_TYPES.register(name, factory);
    }
}
