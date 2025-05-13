package erebus.world.feature.plant;

import erebus.world.feature.ErebusFeature;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class WeepingBluebell extends ErebusFeature {
    public WeepingBluebell(String name) {
        super(name);
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return List.of(CountPlacement.of(5), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }
}
