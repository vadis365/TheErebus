package erebus.world.gen;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.world.gen.layer.ModBiomeLayer;
import erebus.world.gen.layer.ThinMaskLayer;
import erebus.world.gen.layer.ZoomIncrementLayer;
import erebus.world.gen.layer.util.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.stream.Stream;

public class ModBiomeSource extends BiomeSource {
    public static final MapCodec<ModBiomeSource> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            RecordCodecBuilder.<Pair<TerrainPoint, Holder<Biome>>>create(pair -> pair.group(
                    TerrainPoint.CODEC.fieldOf("parameters").forGetter(Pair::getFirst),
                    Biome.CODEC.fieldOf("biome").forGetter(Pair::getSecond)
            ).apply(pair, Pair::of)).listOf().fieldOf("biomes").forGetter(o -> o.list),
            Codec.FLOAT.fieldOf("base_offset").forGetter(o -> o.offset),
            Codec.FLOAT.fieldOf("base_factor").forGetter(o -> o.factor),
            ExtraCodecs.POSITIVE_INT.fieldOf("biome_size").forGetter(o -> o.biomeSize),
            RegistryOps.retrieveGetter(Registries.BIOME)
    ).apply(instance, ModBiomeSource::new));
    private final List<Pair<TerrainPoint, Holder<Biome>>> list;
    private final float offset;
    private final float factor;
    private final int biomeSize;
    private final HolderGetter<Biome> registry;
    private Layer genBiomes;


    public ModBiomeSource(List<Pair<TerrainPoint, Holder<Biome>>> list, float offset, float factor, int biomeSize, HolderGetter<Biome> registry) {
        this.list = list;
        this.offset = offset;
        this.factor = factor;
        this.biomeSize = biomeSize;
        this.registry = registry;
    }

    public static int getBiomeId(ResourceKey<Biome> biome, HolderGetter<Biome> registry) {
        return ServerLifecycleHooks.getCurrentServer().registryAccess().lookupOrThrow(Registries.BIOME).get(registry.get(biome).get().key()).hashCode();
    }

    public static long getSeed() {
        return Objects.requireNonNull(ServerLifecycleHooks.getCurrentServer()).getWorldData().worldGenOptions().seed();
    }

    public static Layer makeLayers(long seed, HolderGetter<Biome> registry, List<Pair<TerrainPoint, Holder<Biome>>> biomes, int size) {
        AreaFactory<LazyArea> factory = makeLayers(context -> new LazyAreaContext(25, seed, context), biomes, registry, size);
        return new Layer(factory);
    }

    public static <A extends Area, C extends BigContext<A>> AreaFactory<A> makeLayers(LongFunction<C> context, List<Pair<TerrainPoint, Holder<Biome>>> biomes, HolderGetter<Biome> registry, int size) {
        AreaFactory<A> genLayer = new ModBiomeLayer(registry, biomes).run(context.apply(100L));
        genLayer = repeatZoom(2000L, genLayer, 2, context);

        return genLayer;
    }

    private static <A extends Area, C extends BigContext<A>> AreaFactory<A> repeatZoom(long seed, AreaFactory<A> layer, int count, LongFunction<C> context) {
        AreaFactory<A> factory = layer;

        for (int c = 0; c < count; ++c) {
            factory = new ZoomIncrementLayer().run(context.apply(seed + c), factory);
        }

        return factory;
    }

    private static <A extends Area, C extends BigContext<A>> AreaFactory<A> repeatThin(long seed, AreaFactory<A> layer, HolderGetter<Biome> registry, ResourceKey<Biome> biome, int range, float chance, int count, LongFunction<C> context) {
        AreaFactory<A> factory = layer;

        for (int c = 0; c < count; ++c) {
            factory = new ThinMaskLayer(registry, biome, range, chance).run(context.apply(seed + 1), factory);
        }

        return factory;
    }

    @Override
    protected MapCodec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    protected Stream<Holder<Biome>> collectPossibleBiomes() {
        return list.stream().map(Pair::getSecond);
    }

    @Override
    public Holder<Biome> getNoiseBiome(int x, int y, int z, Climate.Sampler sampler) {
        lazyLoad();
        return genBiomes.get(registry, x, z);
    }

    public float getBaseOffset() {
        return offset;
    }

    public float getBaseFactor() {
        return factor;
    }

    public float getBiomeDepth(int x, int y, int z, Climate.Sampler sampler) {
        Biome biome = getNoiseBiome(x, y, z, sampler).value();
        return getBiomeDepth(biome);
    }

    public float getBiomeDepth(Biome biome) {
        return getBiomeValue(biome, TerrainPoint::depth);
    }

    public float getBiomeScale(int x, int y, int z, Climate.Sampler sampler) {
        Biome biome = getNoiseBiome(x, y, z, sampler).value();
        return getBiomeScale(biome);
    }

    public float getBiomeScale(Biome biome) {
        return getBiomeValue(biome, TerrainPoint::scale);
    }

    private float getBiomeValue(Biome biome, Function<? super TerrainPoint, Float> function) {
        lazyLoad();
        return list.stream().filter(p -> p.getSecond().value().equals(biome)).map(Pair::getFirst).map(function).findFirst().orElse(0.0F);
    }

    private void lazyLoad() {
        if (genBiomes == null) genBiomes = makeLayers(getSeed(), registry, list, biomeSize);
    }
}
