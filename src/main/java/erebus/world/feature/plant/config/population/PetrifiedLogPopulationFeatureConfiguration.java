package erebus.world.feature.plant.config.population;

import erebus.Config;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import erebus.world.feature.plant.config.BigLogsFeatureConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class PetrifiedLogPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public PetrifiedLogPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.PETRIFIED_FOREST_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int pass = 0; pass < 4; pass++) {
            for (int attempt = 0; attempt < (pass < 2 ? 5 : 3); attempt++) {
                int length = 4 + random.nextInt(5);
                int radius = 2 + random.nextInt(3);
                Direction direction = random.nextBoolean() ? Direction.NORTH : Direction.WEST;
                var soil = new BlockPos(context.origin().getX() + 16, level.getMinY() + random.nextInt(118), context.origin().getZ() + 16);
                if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
                if (level.getBlockState(soil).is(ModBlocks.VOLCANIC_ROCK)) {
                    placed |= placeLog(context, soil.above(), length, radius, direction, pass);
                    break;
                }
            }
        }
        return placed;
    }

    protected boolean placeLog(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos, int length, int radius, Direction direction, int pass) {
        var bark = pass % 2 == 0 ? ModBlocks.PETRIFIED_BARK_BROWN : ModBlocks.PETRIFIED_BARK_RED;
        var filler = Config.petrifiedQuartzGen ? ModBlocks.ORE_PETRIFIED_QUARTZ : ModBlocks.PETRIFIED_WOOD_ROCK;
        var log = new BigLogsFeatureConfiguration(direction, bark, ModBlocks.PETRIFIED_LOG_INNER, filler, pass < 2) {
            @Override
            protected boolean isValidGround(BlockState state) {
                return state.is(ModBlocks.VOLCANIC_ROCK);
            }
        };
        return log.placeSized(context.level(), pos, context.random(), length, radius);
    }
}
