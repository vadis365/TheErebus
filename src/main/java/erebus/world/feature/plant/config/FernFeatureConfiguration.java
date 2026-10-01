package erebus.world.feature.plant.config;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
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

        if (level.isOutsideBuildHeight(pos) || !level.isEmptyBlock(pos)) return false;
        var soil = level.getBlockState(pos.below());
        if (!soil.is(Blocks.GRASS_BLOCK) && !soil.is(Blocks.MYCELIUM)) return false;

        return placeSelected(level, pos, random.nextInt(10) == 0);
    }

    public boolean placeSelected(WorldGenLevel level, BlockPos pos, boolean tall) {
        if (level.isOutsideBuildHeight(pos) || !level.isEmptyBlock(pos)) return false;
        var soil = level.getBlockState(pos.below());
        if (!soil.is(Blocks.GRASS_BLOCK) && !soil.is(Blocks.MYCELIUM)) return false;

        if (tall && !level.isOutsideBuildHeight(pos.above()) && level.isEmptyBlock(pos.above())) {
            var state = ModBlocks.TALL_FERN.get().defaultBlockState();
            if (!state.canSurvive(level, pos)) return false;
            DoublePlantBlock.placeAt(level, state, pos, 2);
            return true;
        }
        var state = ModBlocks.FERN.get().defaultBlockState();
        return state.canSurvive(level, pos) && level.setBlock(pos, state, 2);
    }
}
