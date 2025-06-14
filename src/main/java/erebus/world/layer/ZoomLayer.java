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
import erebus.world.layer.context.RandomContext;
import erebus.world.layer.trait.AreaTransformer1;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.function.LongFunction;

public enum ZoomLayer implements AreaTransformer1 {
    NORMAL,
    FUZZY {

    };

    @Override
    public ResourceKey<Biome> applyPixel(RandomContext context, Area layer, int x, int z) {
        ResourceKey<Biome> biome = layer.getBiome(getParentX(x), getParentZ(z));
        context.initRandom(x >> 1 << 1, z >> 1 << 1);
        int xOff = x & 1;
        int zOff = z & 1;

        if(xOff == 0 && zOff == 0) return biome;

        ResourceKey<Biome> south = layer.getBiome(getParentX(x), getParentZ(z + 1));
        ResourceKey<Biome> random = context.random(biome, south);

        if(xOff == 0) return random;

        ResourceKey<Biome> east = layer.getBiome(getParentX(x + 1), getParentZ(z));
        random = context.random(biome, east);

        if(zOff == 0) return random;

        ResourceKey<Biome> southEast = layer.getBiome(getParentX(x + 1), getParentZ(z + 1));
        return modeOrRandom(context, biome, east, south, southEast);
    }

    protected ResourceKey<Biome> modeOrRandom(RandomContext context, ResourceKey<Biome> biome, ResourceKey<Biome> east, ResourceKey<Biome> south, ResourceKey<Biome> southEast) {

        boolean allNeighborsSame = east == south && south == southEast;
        if (allNeighborsSame) {
            return east;
        }

        if (isMatchingTwoBiomes(biome, east, south) ||
                isMatchingTwoBiomes(biome, east, southEast) ||
                isMatchingTwoBiomes(biome, south, southEast)) {
            return biome;
        }

        if (isDifferentFromOne(biome, east, south, southEast)) {
            return biome;
        }

        if (areTwoNeighborsEqual(east, south, biome, southEast) ||
                areTwoNeighborsEqual(east, southEast, biome, south)) {
            return east;
        }

        if (areTwoNeighborsEqual(south, southEast, biome, east)) {
            return south;
        }

        return context.random(biome, east, south, southEast);
    }

    private boolean isMatchingTwoBiomes(ResourceKey<Biome> main, ResourceKey<Biome> first, ResourceKey<Biome> second) {
        return main == first && main == second;
    }

    private boolean isDifferentFromOne(ResourceKey<Biome> main, ResourceKey<Biome> east,
                                       ResourceKey<Biome> south, ResourceKey<Biome> southEast) {
        return (main == east && south != southEast) ||
                (main == south && east != southEast) ||
                (main == southEast && east != south);
    }

    private boolean areTwoNeighborsEqual(ResourceKey<Biome> first, ResourceKey<Biome> second,
                                         ResourceKey<Biome> excluded, ResourceKey<Biome> other) {
        return first == second && excluded != other;
    }

    @Override
    public int getParentX(int x) {
        return x >> 1;
    }

    @Override
    public int getParentZ(int z) {
        return z >> 1;
    }

    public record Factory(long salt, boolean fuzzy, Holder<BiomeLayerFactory> parent) implements BiomeLayerFactory {

        public static final MapCodec<Factory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.LONG.fieldOf("salt").forGetter(o -> o.salt),
                Codec.BOOL.fieldOf("fuzzy").forGetter(o -> o.fuzzy),
                BiomeLayerStack.HOLDER_CODEC.fieldOf("parent").forGetter(o -> o.parent)
        ).apply(instance, Factory::new));

        @Override
        public LazyArea build(LongFunction<LazyAreaContext> context) {
            LazyAreaContext seeded = context.apply(salt);
            LazyArea layer = parent.value().build(context);

            return fuzzy ? FUZZY.run(seeded, layer) : NORMAL.run(seeded, layer);
        }

        @Override
        public BiomeLayerType getType() {
            return ModBiomeLayerTypes.ZOOM.get();
        }
    }
}
