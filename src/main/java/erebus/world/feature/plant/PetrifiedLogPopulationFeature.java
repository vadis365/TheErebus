package erebus.world.feature.plant;

import erebus.world.feature.ErebusFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class PetrifiedLogPopulationFeature extends ErebusFeature {
    public PetrifiedLogPopulationFeature() {
        super("petrified_fallen_logs");
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return List.of(BiomeFilter.biome());
    }
}
