package erebus.world.feature.plant.config;

import erebus.block.plants.ModBerryBushBlock;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SwampBushFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    public SwampBushFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        BlockState[] bushes = {
                ModBlocks.HEART_BERRY_BUSH.get().defaultBlockState().setValue(ModBerryBushBlock.AGE, 2),
                ModBlocks.SWAMP_BERRY_BUSH.get().defaultBlockState().setValue(ModBerryBushBlock.AGE, 2),
                ModBlocks.JADE_BERRY_BUSH.get().defaultBlockState().setValue(ModBerryBushBlock.AGE, 2),
        };

        float angle, length;
        int randomBush = random.nextInt(2);
        int placed = 0;

        for (int c = 0; c < 10 && placed < 5; c++) {
            angle = (float) (random.nextDouble() * Math.PI * 2.0D);
            length = random.nextFloat() * (0.3F + random.nextFloat() * 0.7F) * 7.0F;

            int x = (int) (0.5F + Math.cos(angle) * length);
            int y = random.nextInt(3) - random.nextInt(3);
            int z = (int) (0.5F + Math.sin(angle) * length);
            BlockPos check = pos.offset(x, y, z);

            if (level.isEmptyBlock(check) && level.getBlockState(check.below()).is(ModBlocks.UMBERSTONE)) {
                setBlock(level, check.below(), Blocks.DIRT.defaultBlockState());
                setBlock(level, check, bushes[randomBush]);
                placed++;
            } else if (level.isEmptyBlock(check) && level.getBlockState(check.below()).is(Blocks.GRASS_BLOCK)) {
                setBlock(level, check, bushes[randomBush]);
                placed++;
            }
        }

        return true;
    }
}
