package erebus.world.gen.layer;

import com.mojang.datafixers.util.Pair;
import erebus.world.gen.ModBiomeSource;
import erebus.world.gen.TerrainPoint;
import erebus.world.gen.layer.util.Context;
import erebus.world.gen.layer.util.transformers.AreaTransformer0;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public class ModBiomeLayer implements AreaTransformer0 {

    private final HolderGetter<Biome> registry;
    private final List<Pair<TerrainPoint, Holder<Biome>>> biomes;
    private int totalWeight = 0;

    public ModBiomeLayer(HolderGetter<Biome> registry, List<Pair<TerrainPoint, Holder<Biome>>> biomes) {
        this.registry = registry;
        this.biomes = biomes;

        for (Pair<TerrainPoint, Holder<Biome>> biome : biomes) {
            if (biome.getFirst().weight() > 0) {
                totalWeight += biome.getFirst().weight();
            }
        }
    }

    @Override
    public int apply(Context context, int x, int z) {
        return ModBiomeSource.getBiomeId(getRandomItem(biomes, context.nextRandom(totalWeight)).getKey(), registry);
    }

    public Holder<Biome> getRandomItem(List<Pair<TerrainPoint, Holder<Biome>>> biomes, int weight) {
        if (biomes.isEmpty()) return null;
        if (totalWeight == 0) return biomes.getFirst().getSecond();

        for (Pair<TerrainPoint, Holder<Biome>> biome : biomes) {
            weight -= biome.getFirst().weight();
            if (weight < 0) return biome.getSecond();
        }

        return null;
    }
}
