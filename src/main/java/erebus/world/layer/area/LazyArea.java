package erebus.world.layer.area;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class LazyArea implements Area{
    private final Area transformer;
    private final ConcurrentHashMap<Long, ResourceKey<Biome>> cachedSamples;
    private final LinkedBlockingQueue<Long> evictionQueue;
    private final int maxCacheSize;

    public LazyArea(ConcurrentHashMap<Long, ResourceKey<Biome>> cachedSamples, LinkedBlockingQueue<Long> evictionQueue, int maxCacheSize, Area transformer) {
        this.cachedSamples = cachedSamples;
        this.evictionQueue = evictionQueue;
        this.maxCacheSize = maxCacheSize;
        this.transformer = transformer;
    }

    @Override
    public ResourceKey<Biome> getBiome(int x, int z) {
        long key = new ChunkPos(x, z).pack();
        ResourceKey<Biome> biome = cachedSamples.get(key);

        if(biome != null && biome != Biomes.THE_VOID) return biome;

        ResourceKey<Biome> computed = transformer.getBiome(x, z);
        ResourceKey<Biome> existing = cachedSamples.putIfAbsent(key, computed);

        if(existing != null && existing != Biomes.THE_VOID) return existing;

        evictionQueue.offer(key);
        if(evictionQueue.size() > maxCacheSize) {
            for(int c = 0, limit = maxCacheSize / 16; c < limit; c++) {
                Long oldest = evictionQueue.poll();
                if(oldest != null) cachedSamples.remove(oldest);
            }
        }

        return computed;
    }

    public int getMaxCacheSize() {
        return maxCacheSize;
    }
}
