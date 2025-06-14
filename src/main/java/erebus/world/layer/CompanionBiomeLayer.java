package erebus.world.layer;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.ModBiomeLayerTypes;
import erebus.world.layer.area.LazyArea;
import erebus.world.layer.biome.BiomeLayerFactory;
import erebus.world.layer.biome.BiomeLayerStack;
import erebus.world.layer.biome.BiomeLayerType;
import erebus.world.layer.context.LazyAreaContext;
import erebus.world.layer.context.RandomContext;
import erebus.world.layer.trait.CastleTransformer;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.List;
import java.util.function.LongFunction;

public record CompanionBiomeLayer(List<Pair<ResourceKey<Biome>, ResourceKey<Biome>>> biomeCompanions) implements CastleTransformer {
    @Override
    public ResourceKey<Biome> apply(RandomContext context, ResourceKey<Biome> north, ResourceKey<Biome> south, ResourceKey<Biome> east, ResourceKey<Biome> west, ResourceKey<Biome> center) {
        for (Pair<ResourceKey<Biome>, ResourceKey<Biome>> pair : biomeCompanions) {
            if (isKey(pair.getFirst(), center, east, west, north, south)) return pair.getSecond();
        }

        return center;
    }

    private static boolean isKey(ResourceKey<Biome> biome, ResourceKey<Biome> center, ResourceKey<Biome> east, ResourceKey<Biome> west, ResourceKey<Biome> north, ResourceKey<Biome> south) {
        return biome != center && (biome == north || biome == south || biome == east || biome == west);
    }

    public static final class Factory implements BiomeLayerFactory {

        public static final MapCodec<Factory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.LONG.fieldOf("salt").forGetter(o -> o.salt),
                Codec.mapPair(
                        ResourceKey.codec(Registries.BIOME).fieldOf("key"), ResourceKey.codec(Registries.BIOME).fieldOf("companion")
                ).codec().listOf().fieldOf("keys_to_companions").forGetter(o -> o.biomeCompanions),
                BiomeLayerStack.HOLDER_CODEC.fieldOf("parent").forGetter(o -> o.parent)
        ).apply(instance, Factory::new));
        private final long salt;
        private final List<Pair<ResourceKey<Biome>, ResourceKey<Biome>>> biomeCompanions;
        private final Holder<BiomeLayerFactory> parent;
        private final CompanionBiomeLayer instance;

        public Factory(long salt, List<Pair<ResourceKey<Biome>, ResourceKey<Biome>>> biomeCompanions, Holder<BiomeLayerFactory> parent) {
            this.salt = salt;
            this.biomeCompanions = biomeCompanions;
            this.parent = parent;

            instance = new CompanionBiomeLayer(biomeCompanions);
        }

        @Override
        public LazyArea build(LongFunction<LazyAreaContext> context) {
            return instance.run(context.apply(salt), parent.value().build(context));
        }

        @Override
        public BiomeLayerType getType() {
            return ModBiomeLayerTypes.COMPANION_BIOMES.get();
        }
    }
}
