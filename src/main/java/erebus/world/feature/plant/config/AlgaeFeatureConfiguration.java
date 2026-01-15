package erebus.world.feature.plant.config;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class AlgaeFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public AlgaeFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        for (int attempt = 0; attempt < 300; attempt++) {
            int xOff = random.nextInt(8) - random.nextInt(8);
            int yOff = random.nextInt(4) - random.nextInt(4);
            int zOff = random.nextInt(8) - random.nextInt(8);

            BlockPos check = pos.offset(xOff, yOff, zOff);

            if (level.isEmptyBlock(check) && level.getBlockState(check.below()).is(Blocks.WATER)) {
                setBlock(level, check, ModBlocks.ALGAE.get().defaultBlockState());
            }
        }

        return true;
    }
}
