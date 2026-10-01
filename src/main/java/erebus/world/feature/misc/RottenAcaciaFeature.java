package erebus.world.feature.misc;

import erebus.world.feature.ErebusFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class RottenAcaciaFeature extends ErebusFeature {

    public RottenAcaciaFeature() {
        super("rotten_acacia");
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return List.of(BiomeFilter.biome());
    }
}
