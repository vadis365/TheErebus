package erebus.world.feature.plant.config.population;

import erebus.registries.world.ModBiomes;
import erebus.world.feature.plant.config.FernFeatureConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class FernPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final FernFeatureConfiguration fern = new FernFeatureConfiguration();

    public FernPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var biome = level.getBiome(context.origin());
        int attempts;
        if (biome.is(ModBiomes.FUNGAL_FOREST_KEY)) attempts = 100;
        else if (biome.is(ModBiomes.SUBMERGED_SWAMP_KEY)) attempts = 20;
        else if (biome.is(ModBiomes.UNDERGROUND_JUNGLE_KEY)) attempts = 30;
        else if (biome.is(ModBiomes.ELYSIAN_FIELDS_KEY) || biome.is(ModBiomes.ELYSIAN_FOREST_KEY)) attempts = 10;
        else return false;
        var random = context.random();
        boolean placed = false;
        for (int i = 0; i < attempts; i++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            for (int y = 20; y < 100; y += random.nextBoolean() ? 2 : 1) {
                var soil = new BlockPos(x, level.getMinY() + y, z);
                if (level.isOutsideBuildHeight(soil)) continue;
                var state = level.getBlockState(soil);
                if (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.MYCELIUM)) {
                    boolean tall = random.nextInt(10) == 0;
                    placed |= placeFern(context, soil.above(), tall);
                    break;
                }
            }
        }
        return placed;
    }

    protected boolean placeFern(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos origin, boolean tall) {
        return fern.placeSelected(context.level(), origin, tall);
    }
}
