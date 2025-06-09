package erebus.world.feature.old_structure;

import erebus.world.feature.ErebusFeature;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class SwampHutFeature extends ErebusFeature {
    public SwampHutFeature(String name) {
        super(name);
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return List.of(CountPlacement.of(10), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }
}
