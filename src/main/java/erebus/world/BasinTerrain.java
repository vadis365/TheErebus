package erebus.world;

import erebus.Erebus;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;

public final class BasinTerrain {
    public static final int MAX_AFFECTED_Y_OFFSET = 36;
    private static final Map<RandomState, Noises> NOISES = Collections.synchronizedMap(new WeakHashMap<>());

    private BasinTerrain() {
    }

    public static boolean modifiesTerrain(Holder<Biome> biome) {
        // Water and lava use enclosed pond/lake features. Column-local pools cannot
        // check their banks and spill when neighboring terrain is lower.
        return biome.is(ModBiomes.PETRIFIED_FOREST_KEY);
    }

    public static void apply(int x, int z, int minY, int maxY, BiomeSource biomes, RandomState state, IntFunction<BlockState> read, BiConsumer<Integer, BlockState> write) {
        var biome = biomes.getNoiseBiome(x >> 2, (minY + 24) >> 2, z >> 2, state.sampler());
        if (!modifiesTerrain(biome)) return;
        var noises = NOISES.computeIfAbsent(state, Noises::new);
        double noise = noises.value(noises.first, x, z);
        shallowPool(minY, maxY, 25, noise,
                ModBlocks.FLUID_FORMIC_ACID_BLOCK.get().defaultBlockState(), read, write);
    }

    public static void shallowPool(int minY, int maxY, int searchTop, double noise, BlockState fluid, IntFunction<BlockState> read, BiConsumer<Integer, BlockState> write) {
        if (Math.abs(noise) >= 1 || minY + searchTop > maxY) return;
        for (int y = minY + 25; y <= minY + searchTop; y++) {
            if (!read.apply(y).isAir()) continue;

            write.accept(y, Blocks.AIR.defaultBlockState());
            for (int depth = y - 1; depth > y - 1 - 3 * (1 - Math.abs(noise)); depth--) {
                write.accept(depth, fluid);
            }
            return;
        }
    }

    private static final class Noises {
        private final SimplexNoise[] first = new SimplexNoise[4];

        Noises(RandomState state) {
            var factory = state.getOrCreateRandomFactory(Erebus.prefix("swamp_basin_noise"));
            var a = factory.fromHashOf(Erebus.prefix("first"));
            for (int i = 0; i < 4; i++) {
                first[i] = new SimplexNoise(a);
            }
        }

        double value(SimplexNoise[] octaves, int x, int z) {
            double scale = 1, result = 0;
            for (var octave : octaves) {
                result += octave.getValue(x * 0.0625 * scale + octave.xo, z * 0.0625 * scale + octave.yo) * 0.55 / scale;
                scale *= 0.5;
            }
            return result;
        }
    }
}
