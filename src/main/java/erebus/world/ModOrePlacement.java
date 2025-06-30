package erebus.world;

import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {

    public static List<PlacementModifier> orePlacement(int count, int low, int high) {
        return List.of(CountPlacement.of(count), HeightRangePlacement.uniform(VerticalAnchor.absolute(low),  VerticalAnchor.absolute(high)), BiomeFilter.biome());
    }

    public static List<PlacementModifier> orePlacement(int count) {
        return orePlacement(count, 5, 112);
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier countPlacement, PlacementModifier heightRange) {
        return List.of(countPlacement, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }

    public static List<PlacementModifier> rareOrePlacement(int chance) {
        return rareOrePlacement(chance, 5, 112);
    }

    public static List<PlacementModifier> rareOrePlacement(int chance, int low, int high) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), HeightRangePlacement.uniform(VerticalAnchor.absolute(low),  VerticalAnchor.absolute(high)));
    }
}
