package erebus.world.feature.mushroom.config.population;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class VanillaGiantMushroomPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public VanillaGiantMushroomPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var biome = context.level().getBiome(context.origin());
        boolean forest = biome.is(ModBiomes.FUNGAL_FOREST_KEY);
        if (!forest && !biome.is(ModBiomes.UNDERGROUND_JUNGLE_KEY)) return false;
        boolean placed = pass(context, true, forest ? 40 : 12, !forest);
        return pass(context, false, forest ? 40 : 20, !forest) || placed;
    }

    private boolean pass(FeaturePlaceContext<NoneFeatureConfiguration> context, boolean brown, int attempts, boolean stopOnSoil) {
        var level = context.level();
        var random = context.random();
        boolean placed = false;
        for (int i = 0; i < attempts; i++) {
            int x = context.origin().getX() + random.nextInt(16) + 8;
            int y = level.getMinY() + 15 + random.nextInt(90);
            int z = context.origin().getZ() + random.nextInt(16) + 8;
            var soil = new BlockPos(x, y, z);
            if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
            var state = level.getBlockState(soil);
            if (!state.is(Blocks.GRASS_BLOCK) && !state.is(Blocks.MYCELIUM)) continue;
            placed |= placeMushroom(context, brown, soil.above());
            if (stopOnSoil) break;
        }
        return placed;
    }

    protected boolean placeMushroom(FeaturePlaceContext<NoneFeatureConfiguration> context, boolean brown, BlockPos origin) {
        return context.level().registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE)
                .getOrThrow(brown ? TreeFeatures.HUGE_BROWN_MUSHROOM : TreeFeatures.HUGE_RED_MUSHROOM).value()
                .place(context.level(), context.chunkGenerator(), context.random(), origin);
    }
}
