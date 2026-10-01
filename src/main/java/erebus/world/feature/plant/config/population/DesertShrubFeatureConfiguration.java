package erebus.world.feature.plant.config.population;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DesertShrubFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public DesertShrubFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.ULTERIOR_OUTBACK_KEY)) return false;
        var random = context.random();
        for (int attempt = 0; attempt < 16; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + 20 + random.nextInt(80);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            if (placeFlower(level, new BlockPos(x, y + 1, z))) return true;
        }
        return false;
    }

    public boolean placeFlower(WorldGenLevel level, BlockPos pos) {
        if (level.isOutsideBuildHeight(pos) || level.isOutsideBuildHeight(pos.below())) return false;
        var soil = level.getBlockState(pos.below());
        if (soil != Blocks.GRASS_BLOCK.defaultBlockState() && soil != Blocks.MYCELIUM.defaultBlockState()) return false;
        return level.isEmptyBlock(pos) && level.setBlock(pos, ModBlocks.DESERT_SHRUB.get().defaultBlockState(), 2);
    }
}
