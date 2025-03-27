package erebus.world.gen.layer.util;

import it.unimi.dsi.fastutil.longs.Long2IntLinkedOpenHashMap;
import net.minecraft.world.level.ChunkPos;

import java.util.stream.IntStream;

public class LazyArea implements Area {
    private final PixelTransformer transformer;
    private final Long2IntLinkedOpenHashMap cache;
    private final int maxCache;

    public LazyArea(Long2IntLinkedOpenHashMap cache, int maxCache, PixelTransformer transformer) {
        this.cache = cache;
        this.maxCache = maxCache;
        this.transformer = transformer;
    }

    @Override
    public int get(int x, int z) {
        long id = ChunkPos.asLong(x, z);
        synchronized (cache) {
            int chunk = cache.get(id);

            if (chunk != Integer.MIN_VALUE) return chunk;

            int c = transformer.apply(x, z);
            cache.put(id, c);

            if (cache.size() > maxCache) {
                IntStream.range(0, maxCache / 16).forEach(d -> cache.removeFirstInt());
            }

            return c;
        }
    }

    public int getMaxCache() {
        return maxCache;
    }
}
