package erebus.world.feature.plant;

import erebus.world.feature.ErebusFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class GrassFeature extends ErebusFeature {
    public GrassFeature() {
        super("grass");
    }

    @Override
    public List<PlacementModifier> getPlacementModifiers() {
        return noiseWithFilter(-0.8, 5, 10, BlockPredicate.wouldSurvive(Blocks.SHORT_GRASS.defaultBlockState(), BlockPos.ZERO));
    }
}
