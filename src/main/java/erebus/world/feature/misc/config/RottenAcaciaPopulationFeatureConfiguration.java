package erebus.world.feature.misc.config;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class RottenAcaciaPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final RottenAcaciaFeatureConfiguration log = new RottenAcaciaFeatureConfiguration();

    public RottenAcaciaPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var biome = level.getBiome(context.origin());
        boolean savannah = biome.is(ModBiomes.SUBTERRANEAN_SAVANNAH_KEY);
        boolean sparse = biome.is(ModBiomes.SUBMERGED_SWAMP_KEY) || biome.is(ModBiomes.ULTERIOR_OUTBACK_KEY);
        if (!savannah && !sparse && !biome.is(ModBiomes.FUNGAL_FOREST_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < (sparse ? random.nextInt(3) : 28); attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + (sparse ? 20 + random.nextInt(25) * (1 + random.nextInt(3)) : 15 + random.nextInt(90));
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var soil = new BlockPos(x, y, z);
            if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
            var state = level.getBlockState(soil);

            if (savannah || state == Blocks.GRASS_BLOCK.defaultBlockState() || state == Blocks.DIRT.defaultBlockState()
                    || state.is(Blocks.SAND) || state.is(Blocks.RED_SAND)) placed |= placeLog(context, soil.above());
        }
        return placed;
    }

    protected boolean placeLog(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return log.place(NoneFeatureConfiguration.INSTANCE, context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
