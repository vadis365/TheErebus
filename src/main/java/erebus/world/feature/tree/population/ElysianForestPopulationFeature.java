package erebus.world.feature.tree.population;

import erebus.world.feature.ErebusFeature;
import erebus.world.biome.util.TerrainBuilder;
import net.minecraft.core.QuartPos;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ElysianForestPopulationFeature extends ErebusFeature {
    private final boolean glowrot;

    public ElysianForestPopulationFeature(boolean glowrot) {
        super(glowrot ? "glowrot_canopy" : "elysian_forest_population");
        this.glowrot = glowrot;
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        if (!glowrot) return List.of(BiomeFilter.biome());

        var upper = VerticalAnchor.absolute(TerrainBuilder.GLOWROT_UPPER_START_Y + QuartPos.SIZE);
        return List.of(HeightRangePlacement.uniform(upper, upper), BiomeFilter.biome());
    }
}
