package erebus.world.feature.plant.config.population;

import erebus.block.plants.ModCropBlock;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class WildMandrakePopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public WildMandrakePopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.PETRIFIED_FOREST_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 5; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            for (int y = 20; y < 100; y += random.nextBoolean() ? 2 : 1) {
                var soil = new BlockPos(x, level.getMinY() + y, z);
                var target = soil.above();
                if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(target)) continue;
                if (level.getBlockState(soil).is(ModBlocks.DUST) && level.isEmptyBlock(target)) {
                    var state = ModBlocks.CROP_MANDRAKE.get().defaultBlockState().setValue(ModCropBlock.AGE, random.nextInt(ModCropBlock.MAX_AGE + 1));
                    placed |= level.setBlock(target, state, 2);
                    break;
                }
            }
        }
        return placed;
    }
}
