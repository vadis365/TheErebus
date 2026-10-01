package erebus.world.feature.misc.config;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class RedGemPopulationFeature extends Feature<NoneFeatureConfiguration> {
    private final RedGemFeatureConfiguration cluster = new RedGemFeatureConfiguration();

    public RedGemPopulationFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var biome = context.level().getBiome(context.origin());
        boolean elysian = biome.is(ModBiomes.ELYSIAN_FIELDS_KEY) || biome.is(ModBiomes.ELYSIAN_FOREST_KEY);
        boolean low = biome.is(ModBiomes.VOLCANIC_DESERT_KEY) || biome.is(ModBiomes.PETRIFIED_FOREST_KEY);
        int count;
        if (elysian) count = 2;
        else if (low) count = 10;
        else if (biome.is(ModBiomes.SUBMERGED_SWAMP_KEY) || biome.is(ModBiomes.ULTERIOR_OUTBACK_KEY)) count = 8;
        else if (biome.is(ModBiomes.SUBTERRANEAN_SAVANNAH_KEY) || biome.is(ModBiomes.UNDERGROUND_JUNGLE_KEY)
                || biome.is(ModBiomes.FUNGAL_FOREST_KEY)) count = 5;
        else return false;
        var random = context.random();
        boolean placed = false;

        for (int attempt = 0; attempt < count + (elysian ? random.nextInt(2) : 0); attempt++) {
            var pos = new BlockPos(context.origin().getX() + 8 + random.nextInt(16),
                    context.level().getMinY() + (low ? random.nextInt(64) : 64 + random.nextInt(60)),
                    context.origin().getZ() + 8 + random.nextInt(16));
            if (!context.level().isOutsideBuildHeight(pos) && !context.level().isOutsideBuildHeight(pos.above())) {
                placed |= placeCluster(context, pos);
            }
        }
        return placed;
    }

    protected boolean placeCluster(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return cluster.place(NoneFeatureConfiguration.INSTANCE, context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
