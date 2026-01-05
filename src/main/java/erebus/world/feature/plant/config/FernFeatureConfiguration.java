
package erebus.world.feature.plant.config;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class FernFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public FernFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        if (random.nextInt(10) == 0 && level.isEmptyBlock(pos.above(2)) && level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)) {
            setBlock(level, pos, ModBlocks.TALL_FERN.get().defaultBlockState());
        } else if (level.isEmptyBlock(pos.above()) && level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)) {
            setBlock(level, pos, ModBlocks.FERN.get().defaultBlockState());
        }

        return true;
    }
}
