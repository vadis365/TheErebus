package erebus.world.feature.plant.config.population;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class OutbackGrassFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public OutbackGrassFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.ULTERIOR_OUTBACK_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 50; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16), z = context.origin().getZ() + 8 + random.nextInt(16);
            for (int y = 20; y < 100; y += random.nextBoolean() ? 2 : 1) {
                var soil = new BlockPos(x, level.getMinY() + y, z);
                if (level.isOutsideBuildHeight(soil)) continue;
                var state = level.getBlockState(soil);
                if (state != Blocks.GRASS_BLOCK.defaultBlockState() && state != Blocks.MYCELIUM.defaultBlockState()) continue;
                var lower = soil.above();
                var upper = lower.above();
                if (random.nextInt(10) == 0 && !level.isOutsideBuildHeight(lower) && level.isEmptyBlock(lower)
                        && !level.isOutsideBuildHeight(upper) && level.isEmptyBlock(upper)) {
                    DoublePlantBlock.placeAt(level, Blocks.TALL_GRASS.defaultBlockState(), lower, 2);
                    placed |= level.getBlockState(lower).is(Blocks.TALL_GRASS) && level.getBlockState(upper).is(Blocks.TALL_GRASS);
                } else if (random.nextInt(80) == 0 && !level.isOutsideBuildHeight(lower) && level.isEmptyBlock(lower)) {
                    placed |= level.setBlock(lower, ModBlocks.FIRE_BLOOM.get().defaultBlockState(), 2);
                } else if (!level.isOutsideBuildHeight(lower) && level.isEmptyBlock(lower)) {
                    placed |= level.setBlock(lower, Blocks.SHORT_GRASS.defaultBlockState(), 2);
                }
                break;
            }
        }
        return placed;
    }
}
