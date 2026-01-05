package erebus.world.feature.plant.config;

import erebus.block.plants.ModCropBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class TurnipFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public TurnipFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        for (int c = 0; c < 64; c++) {
            int x = random.nextInt(8) - random.nextInt(8);
            int y = random.nextInt(4) - random.nextInt(4);
            int z = random.nextInt(8) - random.nextInt(8);
            BlockPos turnip = pos.offset(x, y, z);

            if (level.isEmptyBlock(turnip) && level.getBlockState(turnip.below()).is(Blocks.GRASS_BLOCK)) {
                setBlock(level, turnip, ModBlocks.CROP_TURNIP.get().defaultBlockState().setValue(ModCropBlock.AGE, 3));
            }
        }

        return true;
    }
}
