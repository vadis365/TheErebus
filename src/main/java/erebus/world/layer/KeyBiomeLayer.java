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
import erebus.world.util.LevelUtils;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.LongFunction;

public record KeyBiomeLayer(List<ResourceKey<Biome>> keyBiomes) implements AreaTransformer1 {
    @Override
    public ResourceKey<Biome> applyPixel(RandomContext context, Area layer, int x, int z) {
        final Random random = new Random(LevelUtils.getOverworldSeed() + (x & -4) * 2511L + (z & -4)  * 151121L);
        int ox = random.nextInt(2) + 1;
        int oz = random.nextInt(2) + 1;
        random.setSeed(LevelUtils.getOverworldSeed() + (x / 8) * 2511L + (z / 8) * 151121L);
        int offset = random.nextInt(3);

        if((x & 3) == ox && (z & 3) == oz) {
            if((x & 4) == 0) {
                return (z & 4) == 0 ? getKeyBiomeFor(offset) : getKeyBiomeFor(offset + 1);
            } else {
                return (z & 4) == 0 ? getKeyBiomeFor(offset + 2) : getKeyBiomeFor(offset + 3);
            }
        }

        return layer.getBiome(x, z);
    }

    private ResourceKey<Biome> getKeyBiomeFor(int index) {
        return keyBiomes.get(index & 0b11);
    }

    @Override
    public int getParentX(int x) {
        return x | 3;
    }

    @Override
    public int getParentZ(int z) {
        return z | 3;
    }

    public static final class Factory implements BiomeLayerFactory {

        public static final MapCodec<Factory> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.LONG.fieldOf("salt").forGetter(Factory::salt),
                ResourceKey.codec(Registries.BIOME).listOf().comapFlatMap(list -> Util.fixedSize(list, 4), Function.identity()).fieldOf("key_biomes").forGetter(Factory::keyBiomes),
                BiomeLayerStack.HOLDER_CODEC.fieldOf("parent").forGetter(Factory::parent)
        ).apply(inst, Factory::new));
        private final long salt;
        private final List<ResourceKey<Biome>> keyBiomes;
        private final Holder<BiomeLayerFactory> parent;

        private final KeyBiomeLayer instance;

        public Factory(long salt, List<ResourceKey<Biome>> keyBiomes, Holder<BiomeLayerFactory> parent) {
            this.salt = salt;
            this.keyBiomes = keyBiomes;
            this.parent = parent;

            instance = new KeyBiomeLayer(keyBiomes);
        }

        @Override
        public LazyArea build(LongFunction<LazyAreaContext> contextFactory) {
            return instance.run(contextFactory.apply(salt), parent.value().build(contextFactory));
        }

        @Override
        public BiomeLayerType getType() {
            return ModBiomeLayerTypes.KEY_BIOMES.get();
        }

        public long salt() {
            return salt;
        }

        public List<ResourceKey<Biome>> keyBiomes() {
            return keyBiomes;
        }

        public Holder<BiomeLayerFactory> parent() {
            return parent;
        }
    }
}
