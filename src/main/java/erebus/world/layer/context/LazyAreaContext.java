package erebus.world.layer.context;

import erebus.world.layer.area.Area;
import erebus.world.layer.area.LazyArea;
import erebus.world.util.LevelUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.LinearCongruentialGenerator;
import net.minecraft.world.level.biome.Biome;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class LazyAreaContext implements BigContext<LazyArea> {
    private final ConcurrentHashMap<Long, ResourceKey<Biome>> cache;
    private final LinkedBlockingQueue<Long> evictionQueue;
    private final int maxCacheSize;
    private final long seed;

    public LazyAreaContext(int maxCacheSize, long salt) {
        this.seed = mixSeed(LevelUtils.getOverworldSeed(), salt);
        this.cache = new ConcurrentHashMap<>();
        this.evictionQueue = new LinkedBlockingQueue<>();
        this.maxCacheSize = maxCacheSize;
    }

    private static long mixSeed(long seed, long salt) {
        long z = LinearCongruentialGenerator.next(salt, salt);
        z = LinearCongruentialGenerator.next(z, salt);
        z = LinearCongruentialGenerator.next(z, salt);
        long x = LinearCongruentialGenerator.next(seed, z);
        x = LinearCongruentialGenerator.next(x, z);
        x = LinearCongruentialGenerator.next(x, z);
        return x;
    }

    @Override
    public LazyArea createResult(Area transformer) {
        return new LazyArea(cache, evictionQueue, maxCacheSize, transformer);
    }

    @Override
    public LazyArea createResult(Area transformer, LazyArea layer) {
        return new LazyArea(cache, evictionQueue, Math.min(1024, layer.getMaxCacheSize() * 4), transformer);
    }

    @Override
    public LazyArea createResult(Area transformer, LazyArea layer1, LazyArea layer2) {
        return new LazyArea(cache, evictionQueue, Math.min(1024, Math.max(layer1.getMaxCacheSize(), layer2.getMaxCacheSize()) * 4), transformer);
    }

    @Override
    public long getSeed() {
        return seed;
    }
}
