package erebus.world.feature.misc.config;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class QuickSandPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final QuickSandFeatureConfiguration patch = new QuickSandFeatureConfiguration();

    public QuickSandPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var biome = level.getBiome(context.origin());
        boolean jungle = biome.is(ModBiomes.UNDERGROUND_JUNGLE_KEY);
        if (!jungle && !biome.is(ModBiomes.SUBMERGED_SWAMP_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 10; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + random.nextInt(120);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var soil = new BlockPos(x, y, z);
            if (level.isOutsideBuildHeight(soil)) continue;
            var host = level.getBlockState(soil);
            if (host == Blocks.GRASS_BLOCK.defaultBlockState() || host == Blocks.MYCELIUM.defaultBlockState()) {
                placed |= placePatch(context, soil);
                if (jungle) break;
            }
        }
        return placed;
    }

    protected boolean placePatch(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return patch.place(NoneFeatureConfiguration.INSTANCE, context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
