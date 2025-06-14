package erebus.world.layer.biome;

import erebus.world.layer.area.LazyArea;
import erebus.world.layer.context.LazyAreaContext;

import java.util.function.LongFunction;

public interface BiomeLayerFactory {
    LazyArea build(LongFunction<LazyAreaContext> context);
    BiomeLayerType getType();
}
