package erebus.world.feature.misc.config;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class AmberUmberstoneFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    public AmberUmberstoneFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        if (!level.getBlockState(pos).is(ModBlocks.UMBERSTONE)) return false;

        float rad = random.nextFloat() + 2.6F;
        int ceilRad = 1 + ((int) Math.ceil(rad));

        for (int x = -ceilRad; x <= ceilRad; x++) {
            for (int y = -ceilRad; y <= ceilRad; y++) {
                for (int z = -ceilRad; z <= ceilRad; z++) {
                    float dist = (float) Math.sqrt((x * x) + (y * y) + (z * z));

                    if (dist <= rad + random.nextFloat() * 0.4F
                            && level.getBlockState(pos.offset(x, y, z)).is(ModBlocks.UMBERSTONE)) {
                        setAmberBlock(level, pos.offset(x, y, z), random);
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
