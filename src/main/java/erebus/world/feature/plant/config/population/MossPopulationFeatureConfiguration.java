package erebus.world.feature.plant.config.population;

import erebus.Config;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import erebus.world.feature.plant.config.MossPatchFeatureConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jspecify.annotations.NonNull;

public class MossPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final MossPatchFeatureConfiguration patch = new MossPatchFeatureConfiguration(ModBlocks.MOSS);

    private final boolean swampUpperPass;

    public MossPopulationFeatureConfiguration() {
        this(false);
    }

    public MossPopulationFeatureConfiguration(boolean swampUpperPass) {
        super(NoneFeatureConfiguration.CODEC);
        this.swampUpperPass = swampUpperPass;
    }

    @Override
    public boolean place(@NonNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if (!Config.generateMoss) return false;
        var level = context.level();
        var biome = level.getBiome(context.origin());
        int attempts;
        int minimum = 30, range = 80;
        if (swampUpperPass) {
            if (!biome.is(ModBiomes.SUBMERGED_SWAMP_KEY)) return false;
            attempts = 10;
        } else if (biome.is(ModBiomes.ELYSIAN_FIELDS_KEY) || biome.is(ModBiomes.ELYSIAN_FOREST_KEY)) attempts = 15;
        else if (biome.is(ModBiomes.FUNGAL_FOREST_KEY)) attempts = 10;
        else if (biome.is(ModBiomes.SUBMERGED_SWAMP_KEY)) {
            attempts = 10;
            minimum = 24;
            range = 12;
        } else return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < attempts; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + minimum + random.nextInt(range);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var pos = new BlockPos(x, y, z);
            if (!level.isOutsideBuildHeight(pos) && level.isEmptyBlock(pos)) placed |= placePatch(context, pos);
        }
        return placed;
    }

    protected boolean placePatch(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return patch.place(NoneFeatureConfiguration.INSTANCE, context.level(), context.chunkGenerator(), context.random(), pos);
    }
}
