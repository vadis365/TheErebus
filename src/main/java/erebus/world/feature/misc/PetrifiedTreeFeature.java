package erebus.world.feature.misc;

import erebus.world.feature.ErebusFeature;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class PetrifiedTreeFeature extends ErebusFeature {

    private final int chance;

    public PetrifiedTreeFeature(String name, int chance) {
        super(name);
        this.chance = chance;
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return List.of(CountPlacement.of(chance), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }
}
