package erebus.world.layer.trait;

import erebus.world.layer.area.Area;
import erebus.world.layer.context.BigContext;
import erebus.world.layer.context.RandomContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public interface AreaTransformer1 extends DimensionTransformer {
    default <A extends Area> A run(BigContext<A> context, A area) {
        return context.createResult((x, z) -> {
            RandomContext random = new RandomContext(context.getSeed());
            random.initRandom(x, z);
            return applyPixel(random, area, x, z);
        }, area);
    }

    ResourceKey<Biome> applyPixel(RandomContext randomContext, Area layer, int x, int z);
}
