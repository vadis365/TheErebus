package erebus.world.feature.misc.config;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction.Axis;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class ScorchedWoodFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    private static final int[] offsetX = {-1, 1, 0, 0};
    private static final int[] offsetZ = {0, 0, -1, 1};

    public ScorchedWoodFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        BlockState log = ModBlocks.LOG_SCORCHED.get().defaultBlockState();

        int partAmount = random.nextInt(6) + 2;
        int x = origin.getX(), y = origin.getY(), z = origin.getZ();
        int height = partAmount * 2;

        if (level.isOutsideBuildHeight(y)) return false;

        for (int testY = y + 1; testY <= y + height; testY++) {
            for (int testX = x - 1; testX <= x + 1; testX++) {
                for (int testZ = z - 1; testZ <= z + 1; testZ++) {
                    if (level.getBlockState(new BlockPos(testX, testY, testZ)).isAir()) return false;
                }
            }
        }

        for (int part = 0; part < partAmount; part++) {
            for (int c = 0; c < 2; c++) {
                BlockPos pos = new BlockPos(x, y + part * 2 + c, z);
                setBlock(level, pos, log);
            }

            for (int c = 0; c < 4; c++) {
                BlockPos pos = new BlockPos(x + offsetX[c], y + part * 2, z + offsetZ[c]);
                setBlock(level, pos, log.setValue(BlockStateProperties.AXIS, c < 2 ? Axis.X : Axis.Z));
            }
        }

        return true;
    }
}
