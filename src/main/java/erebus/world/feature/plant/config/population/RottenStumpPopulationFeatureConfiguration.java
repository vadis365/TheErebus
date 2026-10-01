package erebus.world.feature.plant.config.population;

import erebus.registries.world.ModBiomes;
import erebus.world.feature.plant.config.RottenTreeStumpFeatureConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class RottenStumpPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public RottenStumpPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.FUNGAL_FOREST_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 10; attempt++) {
            int height = 6 + random.nextInt(11);
            int radius = 3 + random.nextInt(4);
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + random.nextInt(116);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var soil = new BlockPos(x, y, z);
            if (level.isOutsideBuildHeight(soil)) continue;
            var state = level.getBlockState(soil);
            if (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.MYCELIUM)) {
                placed |= placeStump(context, soil.above(), height, radius);
            }
        }
        return placed;
    }

    protected boolean placeStump(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos origin, int height, int radius) {
        return new RottenTreeStumpFeatureConfiguration(height, radius).place(NoneFeatureConfiguration.INSTANCE,
                context.level(), context.chunkGenerator(), context.random(), origin);
    }
}
