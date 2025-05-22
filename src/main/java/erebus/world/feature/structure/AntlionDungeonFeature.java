package erebus.world.feature.structure;

import erebus.world.feature.ErebusFeature;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class AntlionDungeonFeature extends ErebusFeature {
    public AntlionDungeonFeature(String name) {
        super(name);
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return List.of(CountPlacement.of(1), RarityFilter.onAverageOnceEvery(200), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }
}
