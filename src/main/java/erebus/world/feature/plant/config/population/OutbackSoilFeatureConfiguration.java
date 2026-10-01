package erebus.world.feature.plant.config.population;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class OutbackSoilFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public OutbackSoilFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.ULTERIOR_OUTBACK_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 240; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            for (int y = 20; y < 100; y += random.nextInt(2) + 1) {
                var pos = new BlockPos(x, level.getMinY() + y, z);
                if (level.isOutsideBuildHeight(pos) || level.isOutsideBuildHeight(pos.above())) continue;
                if (level.getBlockState(pos) == Blocks.RED_SAND.defaultBlockState() && level.getBlockState(pos.above()).isAir()) {
                    var soil = random.nextInt(3) == 0 ? Blocks.GRASS_BLOCK : Blocks.DIRT;
                    placed |= level.setBlock(pos, soil.defaultBlockState(), 2);
                    break;
                }
            }
        }
        return placed;
    }
}
