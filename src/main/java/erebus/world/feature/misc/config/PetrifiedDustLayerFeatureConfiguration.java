package erebus.world.feature.misc.config;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class PetrifiedDustLayerFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public PetrifiedDustLayerFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    public static boolean isHost(BlockState state) {
        // These eight blocks used BlockPetrifiedWoodRock in 1.12; ordinary logs and inner wood did not.
        return state == ModBlocks.VOLCANIC_ROCK.get().defaultBlockState()
                || state.is(ModBlocks.PETRIFIED_WOOD_ROCK) || state.is(ModBlocks.PETRIFIED_WOOD_ROCK_2)
                || state.is(ModBlocks.PETRIFIED_WOOD_ROCK_3) || state.is(ModBlocks.PETRIFIED_WOOD_ROCK_4)
                || state.is(ModBlocks.PETRIFIED_WOOD_ROCK_5) || state.is(ModBlocks.PETRIFIED_WOOD_ROCK_6)
                || state.is(ModBlocks.PETRIFIED_BARK_RED) || state.is(ModBlocks.PETRIFIED_BARK_BROWN);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.PETRIFIED_FOREST_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 240; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            for (int y = 20; y < 100; y += random.nextInt(2) + 1) {
                var soil = new BlockPos(x, level.getMinY() + y, z);
                if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
                if (isHost(level.getBlockState(soil)) && level.isEmptyBlock(soil.above())) {
                    var state = ModBlocks.DUST_LAYER.get().defaultBlockState().setValue(SnowLayerBlock.LAYERS, random.nextInt(3) + 1);
                    placed |= level.setBlock(soil.above(), state, 2);
                    break;
                }
            }
        }
        return placed;
    }
}
