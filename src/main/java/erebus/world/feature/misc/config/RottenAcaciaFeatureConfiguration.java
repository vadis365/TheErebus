package erebus.world.feature.misc.config;

import erebus.block.HollowLogBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class RottenAcaciaFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public RottenAcaciaFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        int length = random.nextInt(3) + 3;
        int offsetX = random.nextInt(2);
        int offsetZ = 1 - offsetX;

        for (int c = 0; c < length; c++) {
            BlockPos pos = origin.offset(offsetX * c, 0, offsetZ * c);
            if (!level.getBlockState(pos).isAir() || level.getBlockState(pos.below()).isAir()) {
                return false;
            }
        }

        for (int c = 0; c < length; c++) {
            BlockPos pos = origin.offset(offsetX * c, 0, offsetZ * c);
            setBlock(level, pos, ModBlocks.LOG_HOLLOW.get().defaultBlockState().setValue(HollowLogBlock.FACING, offsetX == 0 ? Direction.SOUTH : Direction.EAST));
        }

        return true;
    }
}
