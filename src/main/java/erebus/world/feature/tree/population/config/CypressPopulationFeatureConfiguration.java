package erebus.world.feature.tree.population.config;

import erebus.registries.world.ModBiomes;
import erebus.registries.world.feature.TreeFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CypressPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public CypressPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var biome = level.getBiome(context.origin());
        if (!biome.is(ModBiomes.ELYSIAN_FIELDS_KEY) && !biome.is(ModBiomes.ELYSIAN_FOREST_KEY)) return false;
        var random = context.random();

        for (int attempt = 0; attempt < 105; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var soil = new BlockPos(x, level.getMinY() + 20 + random.nextInt(80), z);
            if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
            var state = level.getBlockState(soil);
            if (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.MYCELIUM)) return placeTree(context, soil.above());
        }
        return false;
    }

    protected boolean placeTree(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return context.level().registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE)
                .getOrThrow(TreeFeatures.CYPRESS_TREE.getConfiguredResourceKey()).value()
                .place(context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
