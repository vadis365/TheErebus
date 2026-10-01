package erebus.world;

import erebus.Config;
import erebus.Erebus;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
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
        return biome.is(ModBiomes.SUBMERGED_SWAMP_KEY) || biome.is(ModBiomes.VOLCANIC_DESERT_KEY) || biome.is(ModBiomes.ELYSIAN_FIELDS_KEY) || biome.is(ModBiomes.PETRIFIED_FOREST_KEY);
    }

    public static void apply(int x, int z, int minY, int maxY, BiomeSource biomes, RandomState state, IntFunction<BlockState> read, BiConsumer<Integer, BlockState> write) {
        var biome = biomes.getNoiseBiome(x >> 2, (minY + 24) >> 2, z >> 2, state.sampler());
        boolean swamp = biome.is(ModBiomes.SUBMERGED_SWAMP_KEY);
        boolean volcanic = biome.is(ModBiomes.VOLCANIC_DESERT_KEY);
        boolean fields = biome.is(ModBiomes.ELYSIAN_FIELDS_KEY);
        if (!modifiesTerrain(biome)) return;
        var noises = NOISES.computeIfAbsent(state, Noises::new);
        double noise = noises.value(noises.first, x, z);
        if (swamp) {
            var random = state.getOrCreateRandomFactory(Erebus.prefix("swamp_basin_columns")).at(x, 0, z);
            shape(minY, maxY, noise, noises.value(noises.second, x, z), random, Config.generateVents, read, write);
        } else {
            shallowPool(minY, maxY, volcanic ? 32 : fields ? 26 : 25, noise,
                    volcanic ? Blocks.LAVA.defaultBlockState() : fields ? Blocks.WATER.defaultBlockState()
                            : ModBlocks.FLUID_FORMIC_ACID_BLOCK.get().defaultBlockState(), read, write);
        }
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

    public static void shape(int minY, int maxY, double noise, double secondary, RandomSource random, boolean vents, IntFunction<BlockState> read, BiConsumer<Integer, BlockState> write) {
        if (minY + 36 > maxY || noise <= -0.15) return;
        int top = -1;
        for (int y = minY + 25; y <= minY + (noise > 0 ? 35 : 30); y++) {
            if (read.apply(y).isAir()) {
                top = y;
                break;
            }
        }
        if (top < 0) return;
        if (noise > 0) {
            for (int y = top; y > minY + 23.08 - noise && y >= minY + 5; y--) {
                if (y > minY + 24) {
                    write.accept(y, Blocks.AIR.defaultBlockState());
                    continue;
                }
                if (y == minY + 24 && random.nextInt(32) == 0) write.accept(y + 1, Blocks.LILY_PAD.defaultBlockState());
                write.accept(y, Blocks.WATER.defaultBlockState());
                if (noise < 0.08) {
                    write.accept(y, vents && random.nextInt(25) == 0 ? ModBlocks.SWAMP_VENT.get().defaultBlockState() : ModBlocks.UMBERSTONE.get().defaultBlockState());

                    if (read.apply(y + 1).is(Blocks.LILY_PAD))
                        write.accept(y + 1, Blocks.AIR.defaultBlockState());

                } else if (noise < 0.5) write.accept(y - 1, Blocks.SAND.defaultBlockState());
                else if (noise <= 2) write.accept(y - 2, ModBlocks.QUICK_SAND.get().defaultBlockState());
                else if (secondary > 2) write.accept(y - 3, ModBlocks.MUD.get().defaultBlockState());
                else write.accept(y - 4, Blocks.CLAY.defaultBlockState());
            }
        } else {
            int bank = top - 1;
            if (read.apply(bank).isAir() || !read.apply(bank).getFluidState().isEmpty() || read.apply(bank - 1).isAir() || !read.apply(bank - 1).getFluidState().isEmpty()) return;
            write.accept(bank, ModBlocks.MUD.get().defaultBlockState());
            write.accept(bank - 1, ModBlocks.MUD.get().defaultBlockState());
            if (random.nextInt(8) == 0 && read.apply(top).isAir() && read.apply(top + 1).isAir()) {
                write.accept(top, ModBlocks.BULLRUSH.get().defaultBlockState());
                write.accept(top + 1, ModBlocks.BULLRUSH.get().defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));
            }
        }
    }

    private static final class Noises {
        private final SimplexNoise[] first = new SimplexNoise[4], second = new SimplexNoise[4];

        Noises(RandomState state) {
            var factory = state.getOrCreateRandomFactory(Erebus.prefix("swamp_basin_noise"));
            var a = factory.fromHashOf(Erebus.prefix("first"));
            var b = factory.fromHashOf(Erebus.prefix("second"));
            for (int i = 0; i < 4; i++) {
                first[i] = new SimplexNoise(a);
                second[i] = new SimplexNoise(b);
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
