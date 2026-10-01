package erebus.world.feature.plant.config.population;

import erebus.registries.world.ModBiomes;
import erebus.registries.world.feature.PlantFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class JungleBambooPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public JungleBambooPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var random = context.random();
        if (!level.getBiome(context.origin()).is(ModBiomes.UNDERGROUND_JUNGLE_KEY) || random.nextInt(11) != 0) return false;
        int x = context.origin().getX() + 8 + random.nextInt(16), z = context.origin().getZ() + 8 + random.nextInt(16);
        for (int y = 90; y > 20; y--) {
            var soil = new BlockPos(x, level.getMinY() + y, z);
            if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
            var state = level.getBlockState(soil);
            if (state == Blocks.GRASS_BLOCK.defaultBlockState() || state == Blocks.MYCELIUM.defaultBlockState()) {
                return placePatch(context, soil.above());
            }
        }
        return false;
    }

    protected boolean placePatch(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return context.level().registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(PlantFeatures.BAMBOO.getConfiguredResourceKey()).value()
                .place(context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
