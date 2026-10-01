package erebus.world.feature.misc.config;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class AmberPopulationFeature extends Feature<NoneFeatureConfiguration> {
    private final boolean ground;
    private final Feature<NoneFeatureConfiguration> deposit;

    public AmberPopulationFeature(boolean ground) {
        super(NoneFeatureConfiguration.CODEC);
        this.ground = ground;
        deposit = ground ? new AmberGroundFeatureConfiguration() : new AmberUmberstoneFeatureConfiguration();
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var biome = context.level().getBiome(context.origin());
        boolean jungle = biome.is(ModBiomes.UNDERGROUND_JUNGLE_KEY);
        if (!jungle && !biome.is(ModBiomes.SUBTERRANEAN_SAVANNAH_KEY)) return false;
        var random = context.random();
        int chance = ground ? (jungle ? 6 : 24) : (jungle ? 3 : 12);
        if (random.nextInt(chance) != 0) return false;
        for (int attempt = 0; attempt < (ground ? 4 : 5); attempt++) {
            var pos = new BlockPos(context.origin().getX() + 8 + random.nextInt(16),
                    context.level().getMinY() + (ground ? 10 + random.nextInt(40) : random.nextInt(120)),
                    context.origin().getZ() + 8 + random.nextInt(16));
            if (!context.level().isOutsideBuildHeight(pos) && placeDeposit(context, pos)) return true;
        }
        return false;
    }

    protected boolean placeDeposit(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return deposit.place(NoneFeatureConfiguration.INSTANCE, context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
