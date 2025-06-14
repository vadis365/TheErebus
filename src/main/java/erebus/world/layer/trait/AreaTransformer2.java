package erebus.world.layer.trait;

import erebus.world.layer.area.Area;
import erebus.world.layer.context.BigContext;
import erebus.world.layer.context.RandomContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public interface AreaTransformer2 extends DimensionTransformer {
    default <A extends Area> A run(BigContext<A> context, A area1, A area2) {
        return context.createResult((x, z) -> {
            RandomContext random = new RandomContext(context.getSeed());
            random.initRandom(x, z);
            return applyPixel(random, area1, area2, x, z);
        }, area1, area2);
    }

    ResourceKey<Biome> applyPixel(RandomContext random, Area layer1, Area layer2, int x, int z);
}
