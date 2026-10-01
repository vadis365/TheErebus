package erebus.world.feature.plant.config;

import erebus.block.plants.PricklyPearBlock;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class PricklyPearPatchFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public PricklyPearPatchFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        BlockState cactus = ModBlocks.PRICKLY_PEAR.get().defaultBlockState();

        float angle, length;
        int x, y, z;
        int candidates = 0;
        boolean placed = false;

        for (int c = 0; c < 48 && candidates < 15; c++) {
            angle = (float) (random.nextDouble() * Math.PI * 2.0D);
            length = random.nextFloat() * (0.3F + random.nextFloat() * 0.7F) * 7.0F;

            x = (int) (pos.getX() + 0.5F + Mth.cos(angle) * length);
            y = random.nextInt(3) - random.nextInt(3);
            z = (int) (pos.getZ() + 0.5F + Mth.sin(angle) * length);
            BlockPos check = new BlockPos(x, pos.getY() + y, z);

            if (level.isOutsideBuildHeight(check) || level.isOutsideBuildHeight(check.below())) continue;
            var host = level.getBlockState(check.below());
            if (level.isEmptyBlock(check) && (host.is(Blocks.SAND) || host.is(Blocks.RED_SAND))) {
                candidates++;
                // The reference resamples the height limit on every loop condition.
                int height = 0;
                while (height < 1 + random.nextInt(3)) height++;
                boolean clear = true;
                for (int part = 0; part < height; part++) {
                    if (level.isOutsideBuildHeight(check.above(part)) || !level.isEmptyBlock(check.above(part))) {
                        clear = false;
                        break;
                    }
                }
                if (!clear) continue;
                for (int part = 0; part < height; part++) {
                    var state = part == 2 ? cactus.setValue(PricklyPearBlock.AGE, 11) : cactus;
                    if (!level.setBlock(check.above(part), state, 2)) break;
                    placed = true;
                }
            }
        }

        return placed;
    }
}
