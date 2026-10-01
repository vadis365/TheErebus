package erebus.world.feature.plant.config.population;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import erebus.world.feature.plant.config.SwampBushFeatureConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SwampBushPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final SwampBushFeatureConfiguration patch = new SwampBushFeatureConfiguration();

    public SwampBushPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.SUBMERGED_SWAMP_KEY)) return false;
        var random = context.random();
        if (random.nextInt(10) != 0) return false;
        boolean placed = false;
        for (int attempt = 0; attempt < random.nextInt(4); attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int startY = 25 + random.nextInt(75);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            for (int y = startY; y > 20; y--) {
                var soil = new BlockPos(x, level.getMinY() + y, z);
                if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
                var host = level.getBlockState(soil);
                if (host.is(Blocks.GRASS_BLOCK) || host.is(Blocks.MYCELIUM)
                        || host.is(ModBlocks.UMBERSTONE) && level.isEmptyBlock(soil.above())) {
                    placed |= placePatch(context, soil.above());
                    break;
                }
            }
        }
        return placed;
    }

    protected boolean placePatch(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return patch.place(NoneFeatureConfiguration.INSTANCE, context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
