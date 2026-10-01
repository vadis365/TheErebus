package erebus.world.feature.misc;

import erebus.world.feature.ErebusFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class GasVentFeature extends ErebusFeature {

    public GasVentFeature() {
        super("gas_vent");
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return List.of(BiomeFilter.biome());
    }
}
