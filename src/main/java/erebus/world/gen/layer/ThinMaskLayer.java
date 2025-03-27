package erebus.world.gen.layer;

import erebus.world.gen.ModBiomeSource;
import erebus.world.gen.layer.util.Area;
import erebus.world.gen.layer.util.BigContext;
import erebus.world.gen.layer.util.transformers.AreaTransformer1;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class ThinMaskLayer implements AreaTransformer1 {

    private final HolderGetter<Biome> registry;
    private final ResourceKey<Biome> biome;
    private final int range;
    private final float chance;

    public ThinMaskLayer(HolderGetter<Biome> registry, ResourceKey<Biome> biome, int range, float chance) {
        this.registry = registry;
        this.biome = biome;
        this.range = range;
        this.chance = chance;
    }

    @Override
    public int apply(BigContext<?> context, Area area, int x, int z) {
        for (int xo = 0; xo <= range; xo++) {
            for (int zo = 0; zo <= range; zo++) {
                if ((xo != 0 || zo != 0) && Math.pow(xo, 2) + Math.pow(zo, 2) <= Math.pow(range, 2) + 1) {
                    if (area.get(getParentX(x + xo), getParentY(z + zo)) == ModBiomeSource.getBiomeId(biome, registry) && context.nextRandom(10000) <= chance * 10000) {
                        return -1;
                    }
                }
            }
        }
        return area.get(x, z);
    }

    @Override
    public int getParentX(int x) {
        return x - range;
    }

    @Override
    public int getParentY(int y) {
        return y - range;
    }
}
