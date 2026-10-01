package erebus.world.feature.misc.config;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;

public class CeilingLavaFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public CeilingLavaFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.VOLCANIC_DESERT_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 10; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + random.nextInt(120);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            placed |= placeSource(level, new BlockPos(x, y, z));
        }
        return placed;
    }

    public boolean placeSource(WorldGenLevel level, BlockPos pos) {
        if (level.isOutsideBuildHeight(pos) || level.isOutsideBuildHeight(pos.below())
                || level.getBlockState(pos) != ModBlocks.UMBERSTONE.get().defaultBlockState()
                || !level.isEmptyBlock(pos.below())) return false;
        if (!level.setBlock(pos, Blocks.LAVA.defaultBlockState(), 2)) return false;
        level.scheduleTick(pos, Fluids.LAVA, 0);
        return true;
    }
}
