package erebus.world.feature.mushroom.config.population;

import erebus.Config;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GlowshroomPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public GlowshroomPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!Config.generateGlowshrooms || !level.getBiome(context.origin()).is(ModBiomes.FUNGAL_FOREST_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int i = 0; i < 10; i++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + 30 + random.nextInt(90);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var ceiling = new BlockPos(x, y, z);
            var target = ceiling.below();
            if (level.isOutsideBuildHeight(ceiling) || level.isOutsideBuildHeight(target)) continue;
            if (level.getBlockState(ceiling).is(ModBlocks.UMBERSTONE) && level.isEmptyBlock(target)) {
                placed |= level.setBlock(target, ModBlocks.GLOWSHROOM_STALK.get().defaultBlockState(), 2);
            }
        }
        return placed;
    }
}
