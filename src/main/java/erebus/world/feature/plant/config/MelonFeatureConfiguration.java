package erebus.world.feature.plant.config;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class MelonFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public MelonFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        boolean canSpawn = random.nextBoolean() && random.nextBoolean();

        if (!canSpawn) {
            for (int c = 0; c < 30; c++) {
                int x = random.nextInt(8) - random.nextInt(8);
                int y = random.nextInt(4) - random.nextInt(4);
                int z = random.nextInt(8) - random.nextInt(8);

                if (level.getBlockState(pos.offset(x, y, z)).is(Blocks.WATER)) {
                    canSpawn = true;
                    break;
                }
            }
        }

        if (!canSpawn) return false;

        for (int c = 0; c < 64; c++) {
            int x = random.nextInt(8) - random.nextInt(8);
            int y = random.nextInt(4) - random.nextInt(4);
            int z = random.nextInt(8) - random.nextInt(8);
            BlockPos melon = pos.offset(x, y, z);

            if (level.isEmptyBlock(melon) && level.getBlockState(melon.below()).is(Blocks.GRASS_BLOCK)) {
                setBlock(level, melon, Blocks.MELON.defaultBlockState());
            }
        }

        return true;
    }
}
