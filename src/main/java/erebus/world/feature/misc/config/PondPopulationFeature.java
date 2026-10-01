package erebus.world.feature.misc.config;

import erebus.registries.world.ModBiomes;
import erebus.registries.world.feature.PlantFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class PondPopulationFeature extends Feature<NoneFeatureConfiguration> {
    private final PondFeatureConfiguration pond = new PondFeatureConfiguration();

    public PondPopulationFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var biome = context.level().getBiome(context.origin());
        var random = context.random();
        if (biome.is(ModBiomes.SUBMERGED_SWAMP_KEY)) return pass(context, 800, 0, 120, true, false);
        if (biome.is(ModBiomes.UNDERGROUND_JUNGLE_KEY)) return pass(context, 35, 0, 120, false, false);
        if (biome.is(ModBiomes.SUBTERRANEAN_SAVANNAH_KEY)) {
            if (!random.nextBoolean() || !random.nextBoolean()) return false;
            boolean placed = pass(context, 8, 0, 120, false, false);
            if (random.nextInt(3) != 0) {
                for (int y = 100; y > 20; y--) {
                    var soil = new BlockPos(context.origin().getX() + 8 + random.nextInt(16),
                            context.level().getMinY() + y, context.origin().getZ() + 8 + random.nextInt(16));
                    if (context.level().isOutsideBuildHeight(soil) || context.level().isOutsideBuildHeight(soil.above())) continue;
                    var state = context.level().getBlockState(soil);
                    if (state == Blocks.GRASS_BLOCK.defaultBlockState() || state == Blocks.MYCELIUM.defaultBlockState()) {
                        placed |= placeBamboo(context, soil.above());
                    }
                }
            }
            return placed;
        }
        boolean placed = false;
        if (biome.is(ModBiomes.ELYSIAN_FOREST_KEY)) {
            if (random.nextInt(4) == 0) placed = pass(context, 45, 20, 90, false, true);
        } else if (!biome.is(ModBiomes.ELYSIAN_FIELDS_KEY)) {
            return false;
        }
        if (random.nextInt(3) == 0) placed |= pass(context, 25, 20, 90, false, true);
        return placed;
    }

    private boolean pass(FeaturePlaceContext<NoneFeatureConfiguration> context, int attempts, int minY, int range, boolean mixed, boolean earlyExit) {
        var level = context.level();
        var random = context.random();
        boolean placed = false;
        for (int i = 0; i < attempts; i++) {
            BlockPos probe = new BlockPos(context.origin().getX() + 16, level.getMinY() + minY + random.nextInt(range), context.origin().getZ() + 16);
            if (level.isOutsideBuildHeight(probe)) continue;
            var state = level.getBlockState(probe);
            boolean matches = state.is(Blocks.GRASS_BLOCK) || (mixed
                    ? state.is(Blocks.DIRT) || state.is(Blocks.SAND) || state.is(Blocks.RED_SAND)
                    : state.is(Blocks.MYCELIUM));
            if (!matches) continue;
            BlockPos surface = probe.offset(-8, 1, -8);
            while (surface.getY() > level.getMinY() + 5 && level.isEmptyBlock(surface)) {
                surface = surface.below();
            }
            placed |= placePond(context, surface);
            if (earlyExit && random.nextBoolean()) break;
        }
        return placed;
    }

    protected boolean placeBamboo(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return context.level().registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE)
                .getOrThrow(PlantFeatures.BAMBOO_SAVANNAH.getConfiguredResourceKey()).value()
                .place(context.level(), context.chunkGenerator(), context.random(), pos);
    }

    protected boolean placePond(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos surface) {
        return pond.place(NoneFeatureConfiguration.INSTANCE, context.level(), context.chunkGenerator(),
                context.random(), surface);
    }
}
