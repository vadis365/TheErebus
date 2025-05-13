package erebus.world.feature.plant;

import erebus.world.feature.ErebusFeature;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class RottenTreeStumpFeature extends ErebusFeature {
    public RottenTreeStumpFeature(String name) {
        super(name);
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }
}
