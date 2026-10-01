package erebus.world.feature.misc.config;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.function.Supplier;

public class PetrifiedTreePopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final boolean small;
    private final Supplier<? extends Feature<NoneFeatureConfiguration>> tree;

    public PetrifiedTreePopulationFeatureConfiguration(boolean small, Supplier<? extends Feature<NoneFeatureConfiguration>> tree) {
        super(NoneFeatureConfiguration.CODEC);
        this.small = small;
        this.tree = tree;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.PETRIFIED_FOREST_KEY)) return false;
        var random = context.random();
        for (int attempt = 0; attempt < (small ? 30 : 5); attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + random.nextInt(120);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var soil = new BlockPos(x, y, z);
            if (level.isOutsideBuildHeight(soil.below(2)) || level.isOutsideBuildHeight(soil.above())) continue;
            var state = level.getBlockState(soil);
            if ((state.is(ModBlocks.VOLCANIC_ROCK) || small && state.is(ModBlocks.DUST)) && !level.isEmptyBlock(soil.below(2))) {
                return placeTree(context, soil.above());
            }
        }
        return false;
    }

    protected boolean placeTree(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return tree.get().place(NoneFeatureConfiguration.INSTANCE, context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
