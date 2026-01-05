package erebus.world.feature.misc.config;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class AmberUmberstoneFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    private static final float BUGGED_AMBER_CHANCE = 0.01F;
    private static final float WAND_CHANCE = 0.05F;

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

                    if (dist <= rad + random.nextFloat() * 0.4F) {
                        setAmberBlock(level, pos.offset(x, y, z), random);
                    }
                }
            }
        }

        return true;
    }

    protected void setAmberBlock(WorldGenLevel level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() > BUGGED_AMBER_CHANCE) {
            level.setBlock(pos, ModBlocks.AMBER.get().defaultBlockState(), 2);
        } else {
            level.setBlock(pos, ModBlocks.PRESERVED_AMBER.get().defaultBlockState(), 3);
            // TODO: Implement Preserved Block Entity
        }
    }
}
