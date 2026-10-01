package erebus.world.feature.plant;

import erebus.world.feature.ErebusFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class GiantFlowerPopulationFeature extends ErebusFeature {
    public GiantFlowerPopulationFeature() {
        super("giant_flower_population");
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return List.of(BiomeFilter.biome());
    }
}
