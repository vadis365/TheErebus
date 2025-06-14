package erebus.world.layer.biome;

import com.mojang.serialization.MapCodec;

@FunctionalInterface
public interface BiomeLayerType {
    MapCodec<? extends BiomeLayerFactory> getCodec();
}
