package erebus.world.layer.trait;

import erebus.world.layer.area.Area;
import erebus.world.layer.context.RandomContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public interface CastleTransformer extends AreaTransformer1, DimensionOffset1Transformer {
    ResourceKey<Biome> apply(RandomContext context, ResourceKey<Biome> north, ResourceKey<Biome> south, ResourceKey<Biome> east, ResourceKey<Biome> west, ResourceKey<Biome> center);

    @Override
    default ResourceKey<Biome> applyPixel(RandomContext randomContext, Area layer, int x, int z) {
        return apply(randomContext, layer.getBiome(getParentX(x + 1), getParentZ(z)), layer.getBiome(getParentX(x + 2), getParentZ(z + 1)), layer.getBiome(getParentX(x + 1), getParentZ(z + 2)), layer.getBiome(getParentX(x), getParentZ(z + 1)), layer.getBiome(getParentX(x + 1), getParentZ(z + 1)));
    }
}
