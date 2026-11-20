package erebus.world.feature.mushroom.config.population;

import erebus.registries.world.ModBiomes;
import erebus.world.biome.util.ErebusBiomeProvider;
import erebus.world.biome.util.TerrainBuilder;
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
        boolean layeredGrove = ErebusBiomeProvider.isColumn(context.chunkGenerator(), context.origin(), ModBiomes.GLOWROT_GROVES_KEY);
        var random = context.random();
        boolean placed = false;
        int centerRange = layeredGrove ? TerrainBuilder.GLOWROT_UPPER_START_Y - 11 : 120;
        for (var mushroom : new BlockState[]{Blocks.BROWN_MUSHROOM.defaultBlockState(), Blocks.RED_MUSHROOM.defaultBlockState()}) {
            var center = new BlockPos(context.origin().getX() + 8 + random.nextInt(16),
                    level.getMinY() + (layeredGrove ? 8 : 0) + random.nextInt(centerRange), context.origin().getZ() + 8 + random.nextInt(16));
            for (int attempt = 0; attempt < 76; attempt++) {
                var target = center.offset(random.nextInt(8) - random.nextInt(8),
                        random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
                if (layeredGrove && !level.getBiome(target).is(ModBiomes.FUNGAL_FOREST_KEY)) continue;
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
