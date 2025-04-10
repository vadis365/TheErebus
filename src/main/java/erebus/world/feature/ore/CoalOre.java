package erebus.world.feature.ore;

import erebus.world.ModOrePlacement;
import erebus.world.feature.ErebusFeature;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class CoalOre extends ErebusFeature {
    public CoalOre() {
        super("ore_coal");
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80)));
    }
}
