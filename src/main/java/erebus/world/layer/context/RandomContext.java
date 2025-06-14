package erebus.world.layer.context;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.LinearCongruentialGenerator;
import net.minecraft.world.level.biome.Biome;

public class RandomContext implements Context {

    private final long seed;
    private long random;

    public RandomContext(long seed) {
        this.seed = seed;
    }

    public void initRandom(long x, long z) {
        long seed = this.seed;
        seed = LinearCongruentialGenerator.next(seed, x);
        seed = LinearCongruentialGenerator.next(seed, z);
        seed = LinearCongruentialGenerator.next(seed, x);
        seed = LinearCongruentialGenerator.next(seed, z);
        this.random = seed;
    }

    @Override
    public long getSeed() {
        return seed;
    }

    public ResourceKey<Biome> random(ResourceKey<Biome> biome, ResourceKey<Biome> otherBiome) {
        return nextRandom(2) == 0 ? biome : otherBiome;
    }

    public ResourceKey<Biome> random(ResourceKey<Biome> biome1, ResourceKey<Biome> biome2, ResourceKey<Biome> biome3, ResourceKey<Biome> biome4) {
        return switch (nextRandom(4)) {
            case 0 -> biome1;
            case 1 -> biome2;
            case 2 -> biome3;
            default -> biome4;
        };
    }

    public int nextRandom(int limit) {
        int result = Math.floorMod(random >> 24, limit);
        random = LinearCongruentialGenerator.next(random, seed);
        return result;
    }
}
