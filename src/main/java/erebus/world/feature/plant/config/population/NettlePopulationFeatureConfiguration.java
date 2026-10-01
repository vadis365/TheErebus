package erebus.world.feature.plant.config.population;

import erebus.registries.world.ModBiomes;
import erebus.world.feature.plant.config.NettlePatchFeatureConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class NettlePopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final NettlePatchFeatureConfiguration patch = new NettlePatchFeatureConfiguration();

    public NettlePopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var biome = level.getBiome(context.origin());
        if (!biome.is(ModBiomes.ELYSIAN_FIELDS_KEY) && !biome.is(ModBiomes.ELYSIAN_FOREST_KEY)) return false;
        var random = context.random();

        for (int attempt = 0; attempt < random.nextInt(4); attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + 25 + random.nextInt(75);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var soil = new BlockPos(x, y, z);
            if (level.isOutsideBuildHeight(soil)) continue;
            var surface = level.getBlockState(soil);
            if (surface.is(Blocks.GRASS_BLOCK) || surface.is(Blocks.MYCELIUM)) {
                return placePatch(context, soil.above());
            }
        }
        return false;
    }

    protected boolean placePatch(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos origin) {
        return patch.place(NoneFeatureConfiguration.INSTANCE, context.level(), context.chunkGenerator(), context.random(), origin);
    }
}
