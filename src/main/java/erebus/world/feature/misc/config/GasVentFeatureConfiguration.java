package erebus.world.feature.misc.config;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class GasVentFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public GasVentFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        float angle, length;

        int placed = 0;
        for (int attempt = 0, xx, yy, zz; attempt < 10 && placed < 5; attempt++) {
            angle = (float) (random.nextDouble() * Math.PI * 2.0D);
            length = random.nextFloat() * (0.3F + random.nextFloat() * 0.7F) * 7.0F;

            xx = (int) (x + 0.5F + Mth.cos(angle) * length);
            yy = y + random.nextInt(3) - random.nextInt(3);
            zz = (int) (z + 0.5F + Mth.sin(angle) * length);
            BlockPos newPos = new BlockPos(xx, yy, zz);

            if (!level.isOutsideBuildHeight(newPos) && !level.isOutsideBuildHeight(newPos.above())
                    && level.isEmptyBlock(newPos.above()) && level.getBlockState(newPos) == Blocks.GRASS_BLOCK.defaultBlockState()
                    && level.setBlock(newPos, ModBlocks.SWAMP_VENT.get().defaultBlockState(), 2)) ++placed;
        }

        return placed > 0;
    }
}
