package erebus.world.gen.layer.util;

import it.unimi.dsi.fastutil.longs.Long2IntLinkedOpenHashMap;
import net.minecraft.util.LinearCongruentialGenerator;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;

public class LazyAreaContext implements BigContext<LazyArea> {
    private final Long2IntLinkedOpenHashMap cache;
    private final int maxCache;
    private final ImprovedNoise noise;
    private final long seed;
    private long random;

    public LazyAreaContext(int maxCache, long seed, long modifier) {
        this.seed = mixSeed(seed, modifier);
        this.noise = new ImprovedNoise(new LegacyRandomSource(seed));
        this.cache = new Long2IntLinkedOpenHashMap(16, 0.25F);
        this.cache.defaultReturnValue(Integer.MIN_VALUE);
        this.maxCache = maxCache;
    }

    private static long mixSeed(long left, long right) {
        long c = LinearCongruentialGenerator.next(left, right);
        c = LinearCongruentialGenerator.next(c, right);
        c = LinearCongruentialGenerator.next(c, right);
        long d = LinearCongruentialGenerator.next(left, c);
        d = LinearCongruentialGenerator.next(d, c);
        return LinearCongruentialGenerator.next(d, c);
    }

    @Override
    public void initRandom(long x, long z) {
        long seed = this.seed;
        seed = LinearCongruentialGenerator.next(seed, x);
        seed = LinearCongruentialGenerator.next(seed, z);
        seed = LinearCongruentialGenerator.next(seed, x);
        seed = LinearCongruentialGenerator.next(seed, z);
        this.random = seed;
    }

    @Override
    public LazyArea createResult(PixelTransformer transformer) {
        return new LazyArea(this.cache, this.maxCache, transformer);
    }

    @Override
    public LazyArea createResult(PixelTransformer transformer, LazyArea area) {
        return new LazyArea(this.cache, Math.min(1024, area.getMaxCache() * 4), transformer);
    }

    @Override
    public LazyArea createResult(PixelTransformer transformer, LazyArea first, LazyArea second) {
        return new LazyArea(this.cache, Math.min(first.getMaxCache(), second.getMaxCache()) * 4, transformer);
    }

    @Override
    public int nextRandom(int bound) {
        int c = Math.floorMod(this.random >> 24, bound);
        this.random = LinearCongruentialGenerator.next(random, seed);
        return c;
    }

    @Override
    public ImprovedNoise getBiomeNoise() {
        return noise;
    }
}
