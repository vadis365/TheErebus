package erebus.world.feature.plant.config;

import erebus.registries.blocks.providers.PlantBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class NettlePatchFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public NettlePatchFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        BlockState nettle = PlantBlocks.NETTLE.get().defaultBlockState();
        BlockState nettleFlowered = PlantBlocks.NETTLE_FLOWERED.get().defaultBlockState();

        float angle, length;
        int x, y, z;
        int placed = 0;

        for (int c = 0; c < 10 && placed < 5; c++) {
            angle = (float) (random.nextDouble() * Math.PI * 2.0D);
            length = random.nextFloat() * (0.3F + random.nextFloat() * 0.7F) * 7.0F;

            x = (int) (0.5F + Mth.cos(angle) * length);
            y = random.nextInt(3) - random.nextInt(3);
            z = (int) (0.5F + Mth.sin(angle) * length);
            BlockPos check = pos.offset(x, y, z);

            if (level.isEmptyBlock(check) && level.getBlockState(check.below()).is(Blocks.GRASS_BLOCK)) {
                setBlock(level, check, random.nextBoolean() ? nettle : nettleFlowered);
                placed++;
            }
        }

        return true;
    }
}
