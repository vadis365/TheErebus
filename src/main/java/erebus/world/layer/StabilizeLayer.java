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

public enum StabilizeLayer implements AreaTransformer1 {
    INSTANCE;

    @Override
    public ResourceKey<Biome> applyPixel(RandomContext randomContext, Area layer, int x, int z) {
        int xOff = getParentX(x << 4);
        int zOff = getParentZ(z << 4);
        int centerX = ((x + xOff + 1) &  -4) - xOff;
        int centerZ = ((z + zOff + 1) &  -4) - zOff;

        if(x <= centerX + 1 && x >= centerX - 1 && z <= centerZ + 1 && z >= centerZ - 1) {
            return layer.getBiome(centerX, centerZ);
        }

        return layer.getBiome(x, z);
    }

    @Override
    public int getParentX(int x) {
        return x & 3;
    }

    @Override
    public int getParentZ(int z) {
        return z & 3;
    }

    public record Factory(long salt, Holder<BiomeLayerFactory> parent) implements BiomeLayerFactory {

        public static final MapCodec<Factory> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.LONG.fieldOf("salt").forGetter(o -> o.salt),
                BiomeLayerStack.HOLDER_CODEC.fieldOf("parent").forGetter(o -> o.parent)
        ).apply(instance, Factory::new));

        @Override
        public LazyArea build(LongFunction<LazyAreaContext> context) {
            return INSTANCE.run(context.apply(salt), parent.value().build(context));
        }

        @Override
        public BiomeLayerType getType() {
            return ModBiomeLayerTypes.STABILIZE.get();
        }
    }
}
