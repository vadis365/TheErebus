package erebus.world.layer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.ModBiomeLayerTypes;
import erebus.world.layer.area.Area;
import erebus.world.layer.area.LazyArea;
import erebus.world.layer.biome.BiomeLayerFactory;
import erebus.world.layer.biome.BiomeLayerStack;
import erebus.world.layer.biome.BiomeLayerType;
import erebus.world.layer.context.LazyAreaContext;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.RandomSupport;

import java.util.List;
import java.util.function.LongFunction;

public record TransitionBiomeLayer(long salt, List<Border> borders, Holder<BiomeLayerFactory> parent) implements BiomeLayerFactory {
    public static final MapCodec<TransitionBiomeLayer> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.LONG.fieldOf("salt").forGetter(TransitionBiomeLayer::salt),
            Border.CODEC.listOf().fieldOf("borders").forGetter(TransitionBiomeLayer::borders),
            BiomeLayerStack.HOLDER_CODEC.fieldOf("parent").forGetter(TransitionBiomeLayer::parent)
    ).apply(instance, TransitionBiomeLayer::new));

    private static final int[][] CARDINALS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final int NOISE_SPACING = 8;

    @Override
    public LazyArea build(LongFunction<LazyAreaContext> contexts) {
        var context = contexts.apply(salt);
        var input = parent.value().build(contexts);
        return context.createResult((x, z) -> sample(input, context.getSeed(), x, z), input);
    }

    public ResourceKey<Biome> sample(Area input, long seed, int x, int z) {
        var center = input.getBiome(x, z);
        var result = center;
        int nearest = 3;
        // A quart is four blocks. Coherent noise leaves breaks and allows two-quart pockets.
        // At junctions the nearest eligible border wins, then the serialized list order.
        for (var border : borders) {
            var opposite = border.opposite(center);
            if (opposite == null) continue;
            long pairSeed = seed ^ border.transition().identifier().hashCode();
            double noise = widthNoise(pairSeed, x, z);
            int width = noise < 0.28 ? 0 : noise < 0.7 ? 1 : 2;
            for (var direction : CARDINALS) {
                for (int distance = 1; distance <= width && distance < nearest; distance++) {
                    var neighbor = input.getBiome(x + direction[0] * distance, z + direction[1] * distance);
                    if (neighbor.equals(center)) continue;
                    if (neighbor.equals(opposite)) {
                        nearest = distance;
                        result = border.transition();
                    }
                    break;
                }
            }
        }
        return result;
    }

    private static double widthNoise(long seed, int x, int z) {
        int cellX = Math.floorDiv(x, NOISE_SPACING);
        int cellZ = Math.floorDiv(z, NOISE_SPACING);
        double dx = Math.floorMod(x, NOISE_SPACING) / (double) NOISE_SPACING;
        double dz = Math.floorMod(z, NOISE_SPACING) / (double) NOISE_SPACING;
        return Mth.lerp(dz,
                Mth.lerp(dx, corner(seed, cellX, cellZ), corner(seed, cellX + 1, cellZ)),
                Mth.lerp(dx, corner(seed, cellX, cellZ + 1), corner(seed, cellX + 1, cellZ + 1)));
    }

    private static double corner(long seed, int x, int z) {
        return (RandomSupport.mixStafford13(seed ^ (x * 341873128712L) ^ (z * 132897987541L)) >>> 11) * 0x1.0p-53;
    }

    @Override
    public BiomeLayerType getType() {
        return ModBiomeLayerTypes.TRANSITION_BIOMES.get();
    }

    public record Border(ResourceKey<Biome> first, ResourceKey<Biome> second, ResourceKey<Biome> transition) {
        private static final Codec<ResourceKey<Biome>> BIOME = ResourceKey.codec(Registries.BIOME);
        public static final Codec<Border> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                BIOME.fieldOf("first").forGetter(Border::first),
                BIOME.fieldOf("second").forGetter(Border::second),
                BIOME.fieldOf("transition").forGetter(Border::transition)
        ).apply(instance, Border::new));

        private ResourceKey<Biome> opposite(ResourceKey<Biome> center) {
            if (center.equals(first)) return second;
            if (center.equals(second)) return first;
            return null;
        }
    }
}
