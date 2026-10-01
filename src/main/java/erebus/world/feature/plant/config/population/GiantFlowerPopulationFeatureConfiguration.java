package erebus.world.feature.plant.config.population;

import erebus.registries.world.ModBiomes;
import erebus.registries.world.feature.PlantFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GiantFlowerPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public GiantFlowerPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.ELYSIAN_FIELDS_KEY)) return false;
        var random = context.random();
        if (!random.nextBoolean()) return false;
        for (int attempt = 0; attempt < 65; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + 15 + random.nextInt(90);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var soil = new BlockPos(x, y, z);
            if (level.isOutsideBuildHeight(soil)) continue;
            var surface = level.getBlockState(soil);
            if (surface.is(Blocks.GRASS_BLOCK) || surface.is(Blocks.MYCELIUM)) {
                return placeFlower(context, soil.above());
            }
        }
        return false;
    }

    protected boolean placeFlower(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return context.level().registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE)
                .getOrThrow(PlantFeatures.GIANT_FLOWER.getConfiguredResourceKey()).value()
                .place(context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
