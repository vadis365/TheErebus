package erebus.world.feature.misc.config;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SwampMudFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public SwampMudFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.SUBMERGED_SWAMP_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 240; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            for (int y = 20; y < 100; y += random.nextInt(2) + 1) {
                var soil = new BlockPos(x, level.getMinY() + y, z);
                if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
                if (level.getBlockState(soil) == Blocks.GRASS_BLOCK.defaultBlockState() && level.isEmptyBlock(soil.above())) {
                    if (random.nextInt(3) == 0) placed |= level.setBlock(soil, ModBlocks.MUD.get().defaultBlockState(), 2);
                    break;
                }
            }
        }
        return placed;
    }
}
