package erebus.world.feature.plant.config;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BambooFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    private int bambooAmount = -1;
    private boolean checkForWater = true;
    private boolean isFarmed = false;

    public BambooFeatureConfiguration(int bambooAmount, boolean checkForWater) {
        super(NoneFeatureConfiguration.CODEC);
        this.bambooAmount = bambooAmount;
        this.checkForWater = checkForWater;
        this.isFarmed = false;
    }

    public BambooFeatureConfiguration(boolean isFarmed, boolean checkForWater) {
        super(NoneFeatureConfiguration.CODEC);
        this.isFarmed = isFarmed;
        this.checkForWater = checkForWater;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        if (checkForWater) {
            boolean canSpawn = false;
            for (int c = 0; c < 40; c++) {
                int xOff = random.nextInt(8) - random.nextInt(8);
                int yOff = random.nextInt(3) - random.nextInt(6);
                int zOff = random.nextInt(8) - random.nextInt(8);

                if (level.getBlockState(pos.offset(xOff, yOff, zOff)).is(Blocks.WATER)) {
                    canSpawn = true;
                    break;
                }
            }

            if (!canSpawn) return false;
        }

        if (!isFarmed) {
            int xOff, zOff;
            int bambooPlaced = 0;
            for (int c = 0; c < bambooAmount * 2 && bambooPlaced < bambooAmount; c++) {
                xOff = random.nextInt(8) - random.nextInt(8);
                zOff = random.nextInt(8) - random.nextInt(8);

                for (int yOff = -4; yOff < 4; yOff++) {
                    BlockPos check = pos.offset(xOff, yOff, zOff);

                    if (level.isEmptyBlock(check) && level.getBlockState(check.below()).is(Blocks.GRASS_BLOCK)) {
                        setBlock(level, check, ModBlocks.COLOSSAL_BAMBOO.get().defaultBlockState());
                        placeBambooShaft(level, check, random);

                        bambooPlaced++;
                        break;
                    }
                }
            }
        } else {
            placeBambooShaft(level, pos, random);
        }

        return true;
    }

    private void placeBambooShaft(WorldGenLevel level, BlockPos pos, RandomSource random) {
        int height = random.nextInt(6) + 4;
        for (int y = 0; y < height; y++) {
            if (level.isEmptyBlock(pos.above(y))) {
                setBlock(level, pos.above(y), ModBlocks.COLOSSAL_BAMBOO.get().defaultBlockState());
            } else {
                break;
            }
        }
    }
}
