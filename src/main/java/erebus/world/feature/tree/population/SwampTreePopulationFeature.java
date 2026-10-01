package erebus.world.feature.tree.population;

import erebus.world.feature.ErebusFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class SwampTreePopulationFeature extends ErebusFeature {
    public SwampTreePopulationFeature() {
        super("swamp_tree_population");
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return List.of(BiomeFilter.biome());
    }
}
