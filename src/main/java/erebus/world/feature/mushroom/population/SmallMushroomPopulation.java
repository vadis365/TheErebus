package erebus.world.feature.mushroom.population;

import erebus.world.feature.ErebusFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class SmallMushroomPopulation extends ErebusFeature {
    public SmallMushroomPopulation() {
        super("small_mushroom_population");
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return List.of(BiomeFilter.biome());
    }
}
