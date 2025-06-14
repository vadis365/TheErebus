package erebus.world.layer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.world.ModBiomeLayerTypes;
import erebus.world.layer.area.LazyArea;
import erebus.world.layer.biome.BiomeLayerFactory;
import erebus.world.layer.biome.BiomeLayerType;
import erebus.world.layer.context.LazyAreaContext;
import erebus.world.layer.context.RandomContext;
import erebus.world.layer.trait.AreaTransformer0;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.List;
import java.util.function.LongFunction;

public class RandomBiomeLayer implements AreaTransformer0 {
    private final int rareBiomeChance;
    private final List<ResourceKey<Biome>> commonBiomes;
    private final List<ResourceKey<Biome>> rareBiomes;

    public RandomBiomeLayer(int rareBiomeChance, List<ResourceKey<Biome>> commonBiomes, List<ResourceKey<Biome>> rareBiomes) {
        this.rareBiomeChance = rareBiomeChance;
        this.commonBiomes = commonBiomes;
        this.rareBiomes = rareBiomes;
    }

    @Override
    public ResourceKey<Biome> applyPixel(RandomContext random, int x, int z) {
        if(random.nextRandom(rareBiomeChance) == 0) return rareBiomes.get(random.nextRandom(rareBiomes.size()));
        return commonBiomes.get(random.nextRandom(commonBiomes.size()));
    }

    public static final class Factory implements BiomeLayerFactory {

        public static final MapCodec<Factory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.LONG.fieldOf("salt").forGetter(Factory::getSalt),
                Codec.INT.fieldOf("rare_biome_chance").forGetter(Factory::getRareBiomeChance),
                ResourceKey.codec(Registries.BIOME).listOf().fieldOf("common_biomes").forGetter(Factory::getCommonBiomes),
                ResourceKey.codec(Registries.BIOME).listOf().fieldOf("rare_biomes").forGetter(Factory::getRareBiomes)
        ).apply(instance, Factory::new));

        private final long salt;
        private final int rareBiomeChance;
        private final List<ResourceKey<Biome>> commonBiomes;
        private final List<ResourceKey<Biome>> rareBiomes;

        private final RandomBiomeLayer instance;

        public Factory(long salt, int rareBiomeChance, List<ResourceKey<Biome>> commonBiomes, List<ResourceKey<Biome>> rareBiomes) {
            this.salt = salt;
            this.rareBiomeChance = rareBiomeChance;
            this.commonBiomes = commonBiomes;
            this.rareBiomes = rareBiomes;

            instance = new RandomBiomeLayer(rareBiomeChance, commonBiomes, rareBiomes);
        }

        @Override
        public LazyArea build(LongFunction<LazyAreaContext> context) {
            return instance.run(context.apply(salt));
        }

        public long getSalt() {
            return salt;
        }

        public int getRareBiomeChance() {
            return rareBiomeChance;
        }

        public List<ResourceKey<Biome>> getCommonBiomes() {
            return commonBiomes;
        }

        public List<ResourceKey<Biome>> getRareBiomes() {
            return rareBiomes;
        }

        @Override
        public BiomeLayerType getType() {
            return ModBiomeLayerTypes.RANDOM_BIOMES.get();
        }
    }
}
