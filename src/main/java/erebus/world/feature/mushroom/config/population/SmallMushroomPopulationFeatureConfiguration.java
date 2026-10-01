package erebus.world.feature.mushroom.config.population;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SmallMushroomPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public SmallMushroomPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var biome = level.getBiome(context.origin());
        if (!biome.is(ModBiomes.UNDERGROUND_JUNGLE_KEY) && !biome.is(ModBiomes.FUNGAL_FOREST_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (var mushroom : new BlockState[]{Blocks.BROWN_MUSHROOM.defaultBlockState(), Blocks.RED_MUSHROOM.defaultBlockState()}) {
            var center = new BlockPos(context.origin().getX() + 8 + random.nextInt(16),
                    level.getMinY() + random.nextInt(120), context.origin().getZ() + 8 + random.nextInt(16));
            for (int attempt = 0; attempt < 64; attempt++) {
                var target = center.offset(random.nextInt(8) - random.nextInt(8),
                        random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
                placed |= placeMushroom(level, target, mushroom);
            }
        }
        return placed;
    }

    public boolean placeMushroom(WorldGenLevel level, BlockPos pos, BlockState state) {
        return !level.isOutsideBuildHeight(pos) && !level.isOutsideBuildHeight(pos.below())
                && level.isEmptyBlock(pos) && state.canSurvive(level, pos) && level.setBlock(pos, state, 2);
    }
}
