package erebus.world.feature.misc.config;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DesertRockGneissPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final DesertRockGneissFeatureConfiguration boulder = new DesertRockGneissFeatureConfiguration();

    public DesertRockGneissPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.VOLCANIC_DESERT_KEY)) return false;
        var random = context.random();
        if (!random.nextBoolean() || !random.nextBoolean()) return false;
        int x = context.origin().getX() + 8 + random.nextInt(16);
        int z = context.origin().getZ() + 8 + random.nextInt(16);
        for (int y = 100; y > 20; y--) {
            var pos = new BlockPos(x, level.getMinY() + y, z);
            if (level.isOutsideBuildHeight(pos)) continue;
            var state = level.getBlockState(pos);
            if ((state.is(Blocks.SAND) || state.is(Blocks.RED_SAND)) && placeBoulder(context, pos)) return true;
        }
        return false;
    }

    protected boolean placeBoulder(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return boulder.place(NoneFeatureConfiguration.INSTANCE, context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
