package erebus.world.feature.misc.config;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ScorchedWoodPopulationFeature extends Feature<NoneFeatureConfiguration> {
    private final ScorchedWoodFeatureConfiguration tree = new ScorchedWoodFeatureConfiguration();

    public ScorchedWoodPopulationFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var biome = level.getBiome(context.origin());
        boolean petrified = biome.is(ModBiomes.PETRIFIED_FOREST_KEY);
        if (!petrified && !biome.is(ModBiomes.VOLCANIC_DESERT_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 22; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + random.nextInt(120);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var pos = new BlockPos(x, y, z);
            if (level.isOutsideBuildHeight(pos) || level.isOutsideBuildHeight(pos.below(2))) continue;
            var host = level.getBlockState(pos);
            boolean valid = petrified ? host == ModBlocks.VOLCANIC_ROCK.get().defaultBlockState()
                    : host == Blocks.SAND.defaultBlockState() || host == Blocks.RED_SAND.defaultBlockState();
            if (valid && !level.isEmptyBlock(pos.below(2))) {
                placed |= placeTree(context, pos);
                if (random.nextInt(4) != 0) break;
            }
        }
        return placed;
    }

    protected boolean placeTree(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return tree.place(NoneFeatureConfiguration.INSTANCE, context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
