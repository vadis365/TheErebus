package erebus.world.feature.misc.config;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class RockSpikePopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final RockSpikeFeatureConfiguration spike = new RockSpikeFeatureConfiguration();

    public RockSpikePopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if (!context.level().getBiome(context.origin()).is(ModBiomes.PETRIFIED_FOREST_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 3; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = context.level().getMinY() + random.nextInt(100) + 1;
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            placed |= placeSpike(context, new BlockPos(x, y, z));
        }
        return placed;
    }

    protected boolean placeSpike(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return spike.place(NoneFeatureConfiguration.INSTANCE, context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
