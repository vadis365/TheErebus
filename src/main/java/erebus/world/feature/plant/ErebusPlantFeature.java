package erebus.world.feature.plant;

import erebus.world.feature.ErebusFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class ErebusPlantFeature extends ErebusFeature {
    public ErebusPlantFeature(String name) {
        super(name);
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return patchWithFilter(20, BlockPredicate.wouldSurvive(Blocks.SHORT_GRASS.defaultBlockState(), BlockPos.ZERO));
    }
}
