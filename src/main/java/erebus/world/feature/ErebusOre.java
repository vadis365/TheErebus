package erebus.world.feature;

import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ErebusOre extends ErebusFeature {
    private final List<PlacementModifier> placement;

    public ErebusOre(String name, List<PlacementModifier> placement) {
        super(name);
        this.placement = placement;
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return placement;
    }
}
