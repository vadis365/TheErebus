package erebus.world.feature.misc.config;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class RedGemFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public RedGemFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        if (level.isOutsideBuildHeight(pos) || level.isOutsideBuildHeight(pos.above())
                || !level.ensureCanWrite(pos) || !level.isEmptyBlock(pos)) return false;

        BlockState state = level.getBlockState(pos.above());
        if (!state.is(ModBlocks.UMBERSTONE.get())) return false;

        if (!level.setBlock(pos, ModBlocks.RED_GEM_BLOCK.get().defaultBlockState(), 2)) return false;

        int distance = 2;
        int distanceUpdates = 0;
        int attempts = random.nextInt(100) + 300;
        for (int c = 0; c < attempts; c++) {
            int dx = random.nextInt(distance) - random.nextInt(distance);
            int dy = -random.nextInt(distance + 4);
            int dz = random.nextInt(distance) - random.nextInt(distance);
            BlockPos check = pos.offset(dx, dy, dz);
            if (!level.isOutsideBuildHeight(check) && level.isEmptyBlock(check)) {
                int adjacent = 0;

                for (Direction dir : Direction.values()) {
                    if (level.getBlockState(check.relative(dir)).is(ModBlocks.RED_GEM_BLOCK.get())) adjacent++;
                    if (adjacent > 1) break;
                }

                if (adjacent == 1 && level.ensureCanWrite(check)) {
                    level.setBlock(check, ModBlocks.RED_GEM_BLOCK.get().defaultBlockState(), 2);
                }
            }

            if (++distanceUpdates > 22 + distance * 30) {
                distance = Math.min(8, distance + 1);
                distanceUpdates = 0;
            }
        }

        return true;
    }
}
