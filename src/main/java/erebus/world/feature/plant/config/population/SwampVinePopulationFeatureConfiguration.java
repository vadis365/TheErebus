package erebus.world.feature.plant.config.population;

import erebus.registries.world.ModBiomes;
import erebus.world.feature.plant.config.VinesFeatureConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SwampVinePopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public SwampVinePopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.SUBMERGED_SWAMP_KEY)) return false;
        var random = context.random();
        var vines = new VinesFeatureConfiguration(level.getMinY() + 35, 5);
        boolean placed = false;
        for (int attempt = 0; attempt < 30; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var start = new BlockPos(x, level.getMinY() + 20, z);
            placed |= vines.place(NoneFeatureConfiguration.INSTANCE, level, context.chunkGenerator(), random, start);
        }
        return placed;
    }
}
