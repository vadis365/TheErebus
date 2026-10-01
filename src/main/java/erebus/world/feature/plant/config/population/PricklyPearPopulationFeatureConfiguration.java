package erebus.world.feature.plant.config.population;

import erebus.registries.world.ModBiomes;
import erebus.world.feature.plant.config.PricklyPearPatchFeatureConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class PricklyPearPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final PricklyPearPatchFeatureConfiguration patch = new PricklyPearPatchFeatureConfiguration();

    public PricklyPearPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.VOLCANIC_DESERT_KEY)) return false;
        var random = context.random();
        if (random.nextInt(20) != 0) return false;
        boolean placed = false;
        for (int attempt = 0; attempt < random.nextInt(4); attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int startY = 25 + random.nextInt(75);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            for (int y = startY; y > 20; y--) {
                var soil = new BlockPos(x, level.getMinY() + y, z);
                if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
                var host = level.getBlockState(soil);
                if (host == Blocks.SAND.defaultBlockState() || host == Blocks.RED_SAND.defaultBlockState()) {
                    placed |= placePatch(context, soil.above());
                    break;
                }
            }
        }
        return placed;
    }

    protected boolean placePatch(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return patch.place(NoneFeatureConfiguration.INSTANCE, context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
