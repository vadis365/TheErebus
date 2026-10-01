package erebus.world.feature.plant.config.population;

import erebus.registries.world.ModBiomes;
import erebus.world.feature.plant.config.MelonFeatureConfiguration;
import erebus.world.feature.plant.config.TurnipFeatureConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class JungleCropPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final TurnipFeatureConfiguration turnips = new TurnipFeatureConfiguration();
    private final MelonFeatureConfiguration melons = new MelonFeatureConfiguration();

    public JungleCropPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.UNDERGROUND_JUNGLE_KEY)) return false;
        var random = context.random();
        boolean turnip = random.nextInt(3) == 0;
        if (!turnip && !(random.nextBoolean() || random.nextBoolean())) return false;
        boolean placed = false;
        for (int attempt = 0; attempt < (turnip ? 20 : 10); attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + 15 + random.nextInt(90);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var soil = new BlockPos(x, y, z);
            if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
            var state = level.getBlockState(soil);
            if (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.MYCELIUM)) {
                placed |= placePatch(context, soil.above(), turnip);
            }
        }
        return placed;
    }

    protected boolean placePatch(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos, boolean turnip) {
        return (turnip ? turnips : melons).place(NoneFeatureConfiguration.INSTANCE, context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
