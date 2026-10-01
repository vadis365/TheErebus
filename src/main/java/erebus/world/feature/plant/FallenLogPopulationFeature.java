package erebus.world.feature.plant;

import erebus.world.feature.ErebusFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class FallenLogPopulationFeature extends ErebusFeature {
    public FallenLogPopulationFeature() {
        super("fallen_log_population");
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return List.of(BiomeFilter.biome());
    }
}
