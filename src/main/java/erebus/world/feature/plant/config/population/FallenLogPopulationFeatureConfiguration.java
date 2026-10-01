package erebus.world.feature.plant.config.population;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import erebus.world.feature.plant.config.BigLogsFeatureConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class FallenLogPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final BigLogsFeatureConfiguration north = new BigLogsFeatureConfiguration(Direction.NORTH, ModBlocks.LOG_ROTTEN);
    private final BigLogsFeatureConfiguration west = new BigLogsFeatureConfiguration(Direction.WEST, ModBlocks.LOG_ROTTEN);

    public FallenLogPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var biome = level.getBiome(context.origin());
        int attempts;
        if (biome.is(ModBiomes.FUNGAL_FOREST_KEY)) attempts = 10;
        else if (biome.is(ModBiomes.SUBMERGED_SWAMP_KEY)) attempts = 40;
        else return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < attempts; attempt++) {
            int length = 4 + random.nextInt(5);
            int radius = 2 + random.nextInt(3);
            Direction direction = random.nextBoolean() ? Direction.NORTH : Direction.WEST;
            var soil = new BlockPos(context.origin().getX() + 16, level.getMinY() + random.nextInt(118), context.origin().getZ() + 16);
            if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
            var state = level.getBlockState(soil);
            if (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.MYCELIUM)) {
                placed |= placeLog(context, soil.above(), length, radius, direction);
            }
        }
        return placed;
    }

    protected boolean placeLog(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos origin, int length, int radius, Direction direction) {
        return (direction == Direction.NORTH ? north : west).placeSized(context.level(), origin, context.random(), length, radius);
    }
}
