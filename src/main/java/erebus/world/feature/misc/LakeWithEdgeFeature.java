package erebus.world.feature.misc;

import erebus.world.feature.ErebusFeature;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class LakeWithEdgeFeature extends ErebusFeature {

    private final boolean population;

    public LakeWithEdgeFeature(String name) {
        super(name);
        population = name.equals("lava_lake");
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        if (population) return List.of(BiomeFilter.biome());
        return List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }
}
