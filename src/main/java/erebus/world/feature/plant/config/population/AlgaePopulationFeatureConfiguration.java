package erebus.world.feature.plant.config.population;

import erebus.registries.world.ModBiomes;
import erebus.world.feature.plant.config.AlgaeFeatureConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class AlgaePopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final AlgaeFeatureConfiguration algae = new AlgaeFeatureConfiguration();

    public AlgaePopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.SUBMERGED_SWAMP_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 5; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + 20 + random.nextInt(80);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            placed |= algae.place(NoneFeatureConfiguration.INSTANCE, level, context.chunkGenerator(), random, new BlockPos(x, y, z));
        }
        return placed;
    }
}
