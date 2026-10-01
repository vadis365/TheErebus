package erebus.world.feature.misc.config;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class AmberGroundFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    public AmberGroundFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        boolean plausible = false;

        for (int c = 0; c < 10; c++) {
            if (level.getBlockState(pos).is(Blocks.AIR) && level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)) {
                plausible = true;
                break;
            }

            pos = pos.below();
            if (level.isOutsideBuildHeight(pos.below())) {
                return false;
            }
        }

        if (!plausible) return false;

        float rad = random.nextFloat() + 2.6F;
        int ceilRad = 1 + ((int) Math.ceil(rad));

        for (int x = -ceilRad; x <= ceilRad; x++) {
            for (int yOff = -ceilRad; yOff <= ceilRad; yOff++) {
                for (int z = -ceilRad; z <= ceilRad; z++) {
                    float dist = (float) Math.sqrt((x * x) + (yOff * yOff) + (z * z));

                    if (dist <= rad + random.nextFloat() * 0.4F) {
                        setAmberBlock(level, pos.offset(x, yOff, z), random);
                    }
                }
            }
        }

        return true;
    }

    protected void setAmberBlock(WorldGenLevel level, BlockPos pos, RandomSource random) {
        AmberContents.place(level, pos, random);
    }
}
