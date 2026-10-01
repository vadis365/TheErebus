package erebus.world.feature.plant.config.population;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class DroughtedShrubFeatureConfiguration extends TallFlowerPopulationFeatureConfiguration {
    public DroughtedShrubFeatureConfiguration() {
        super(ModBlocks.DROUGHTED_SHRUB, 420, List.of(ModBiomes.ULTERIOR_OUTBACK_KEY));
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.ULTERIOR_OUTBACK_KEY)) return false;
        var random = context.random();
        for (int attempt = 0; attempt < 420; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + 20 + random.nextInt(80);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            if (placeFlower(level, new BlockPos(x, y + 1, z))) return true;
        }
        return false;
    }
}
