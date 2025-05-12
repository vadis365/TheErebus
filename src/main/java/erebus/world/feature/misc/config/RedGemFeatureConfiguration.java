package erebus.world.feature.misc.config;

import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.UmberstoneBlocks;
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

        if (!level.isEmptyBlock(pos)) return false;

        BlockState state = level.getBlockState(pos.above());
        if (!state.is(UmberstoneBlocks.UMBERSTONE.get())) return false;

        level.setBlock(pos, OtherBlocks.RED_GEM_BLOCK.get().defaultBlockState(), 2);

        for (int c = 0; c < 1500; c++) {
            int dx = random.nextInt(8) - random.nextInt(8);
            int dy = -random.nextInt(12);
            int dz = random.nextInt(8) - random.nextInt(8);
            BlockPos check = pos.offset(dx, dy, dz);
            int d = 0;

            for (Direction dir : Direction.values()) {
                if (level.getBlockState(check.relative(dir)).is(OtherBlocks.RED_GEM_BLOCK.get())) d++;
                if (d > 1) break;
            }

            if (d == 1) {
                level.setBlock(check, OtherBlocks.RED_GEM_BLOCK.get().defaultBlockState(), 2);
            }
        }

        return true;
    }
}
