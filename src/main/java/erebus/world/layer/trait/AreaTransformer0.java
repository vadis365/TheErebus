package erebus.world.layer.trait;

import erebus.world.layer.area.Area;
import erebus.world.layer.context.BigContext;
import erebus.world.layer.context.RandomContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public interface AreaTransformer0 {
    default <A extends Area> A run(BigContext<A> context) {
        return context.createResult((x, z) -> {
            RandomContext random = new RandomContext(context.getSeed());
            random.initRandom(x, z);
            return applyPixel(random, x, z);
        });
    }

    ResourceKey<Biome> applyPixel(RandomContext random, int x, int z);
}
