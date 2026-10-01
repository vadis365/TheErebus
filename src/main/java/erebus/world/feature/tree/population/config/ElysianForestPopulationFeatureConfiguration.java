package erebus.world.feature.tree.population.config;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ElysianForestPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public ElysianForestPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.ELYSIAN_FOREST_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 400; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var soil = new BlockPos(x, level.getMinY() + 20 + random.nextInt(80), z);
            if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
            var state = level.getBlockState(soil);
            if (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.MYCELIUM)) {
                boolean oak = random.nextBoolean();
                float variant = random.nextFloat();
                var tree = oak
                        ? (variant < 0.0125F ? TreeFeatures.FALLEN_OAK_TREE
                        : variant < 0.1125F ? TreeFeatures.FANCY_OAK_BEES_0002_LEAF_LITTER : TreeFeatures.OAK_BEES_0002_LEAF_LITTER)
                        : (variant < 0.0025F ? TreeFeatures.FALLEN_BIRCH_TREE : TreeFeatures.BIRCH_BEES_0002_LEAF_LITTER);
                placed |= placeTree(context, soil.above(), tree);
            }
        }
        return placed;
    }

    protected boolean placeTree(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos, ResourceKey<ConfiguredFeature<?, ?>> tree) {
        return context.level().registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE)
                .getOrThrow(tree).value()
                .place(context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
