package erebus.world.feature.tree.population.config;

import erebus.registries.world.ModBiomes;
import erebus.registries.world.feature.TreeFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SwampTreePopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public SwampTreePopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.SUBMERGED_SWAMP_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 600; attempt++) {
            int x = context.origin().getX() + 12 + random.nextInt(5);
            int y = level.getMinY() + 15 + random.nextInt(90);
            int z = context.origin().getZ() + 12 + random.nextInt(5);
            var soil = new BlockPos(x, y, z);
            if (grass(level, soil) && grass(level, soil.east(2)) && grass(level, soil.west(2))
                    && grass(level, soil.north(2)) && grass(level, soil.south(2))) {
                placed |= placeTree(context, soil.above(), TreeFeatures.MARSHWOOD_TREE.getConfiguredResourceKey());
            }
        }
        if (random.nextBoolean()) {
            for (int attempt = 0; attempt < 50; attempt++) {
                int x = context.origin().getX() + 8 + random.nextInt(16);
                int y = level.getMinY() + 20 + random.nextInt(80);
                int z = context.origin().getZ() + 8 + random.nextInt(16);
                var soil = new BlockPos(x, y, z);
                if (grass(level, soil)) {
                    placed |= placeTree(context, soil.above(), TreeFeatures.MOSSBARK_TREE.getConfiguredResourceKey());
                    break;
                }
            }
        }
        return placed;
    }

    private boolean grass(WorldGenLevel level, BlockPos pos) {
        if (level.isOutsideBuildHeight(pos) || level.isOutsideBuildHeight(pos.above())) return false;
        var state = level.getBlockState(pos);
        return state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.MYCELIUM);
    }

    protected boolean placeTree(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos, ResourceKey<ConfiguredFeature<?, ?>> tree) {
        return context.level().registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(tree).value()
                .place(context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
