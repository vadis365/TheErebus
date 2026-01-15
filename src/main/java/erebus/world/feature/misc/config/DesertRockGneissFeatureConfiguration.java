package erebus.world.feature.misc.config;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class DesertRockGneissFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public DesertRockGneissFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        for (int x = pos.getX() - 3; x <= pos.getX() + 3; x++) {
            for (int z = pos.getZ() - 3; z <= pos.getZ() + 3; z++) {
                if (!level.getBlockState(new BlockPos(x, pos.getY(), z)).is(Blocks.SAND)) return false;
            }
        }

        float randX, randY, randZ;
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();

        if (random.nextBoolean()) {
            randX = random.nextFloat() * 0.3F + 1.85F;
            randZ = random.nextFloat() * 0.2F + 1.5F;
        } else {
            randX = random.nextFloat() * 0.2F + 1.5F;
            randZ = random.nextFloat() * 0.3F + 1.85F;
        }
        randY = random.nextFloat() * 0.7F + 2.0F;
        y += (int) Math.floor(randY);

        for (int c = 0; c < 2; c++) {
            generateEllipsoidAt(level, random, x, y, z, randX, randY, randZ);
            ++y;
            if (randX > randZ) {
                x += random.nextInt(2) * 2 - 1;
            } else {
                z += random.nextInt(2) * 2 - 1;
            }
        }

        if (random.nextInt(5) == 0) {
            BlockState state;
            int diamonds = 0;
            int diamondAmount = random.nextInt(2) + 1;
            int checkRandX = (int) Math.ceil(randX);
            int checkRandY = (int) Math.ceil(randY);
            int checkRandZ = (int) Math.ceil(randZ);

            for (int attempt = 0; attempt < 10 && diamonds < diamondAmount; attempt++) {
                int xAtt = x + random.nextInt(checkRandX * 2) - checkRandX;
                int yAtt = y + random.nextInt(checkRandY * 2) - checkRandZ;
                int zAtt = z + random.nextInt(checkRandZ * 2) - checkRandY;
                state = level.getBlockState(new BlockPos(xAtt, yAtt, zAtt));

                if (state.is(ModBlocks.GNEISS.get()) || state.is(ModBlocks.GNEISS_VENT.get())) {
                    level.setBlock(new BlockPos(xAtt, yAtt, zAtt), ModBlocks.ORE_ENCRUSTED_DIAMOND.get().defaultBlockState(), 2);
                    ++diamonds;
                }
            }
        }

        return true;
    }

    private void generateEllipsoidAt(WorldGenLevel level, RandomSource random, int x, int y, int z, float randX, float randY, float randZ) {
        for (float xf = x - randX; xf <= x + randX; xf++) {
            for (float zf = z - randZ; zf <= z + randZ; zf++) {
                for (float yf = y - randY; yf < y + randY; yf++) {
                    double a = Math.pow(xf - x, 2) / Math.pow(randX, 2);
                    double b = Math.pow(yf - y, 2) / Math.pow(randY, 2);
                    double c = Math.pow(zf - z, 2) / Math.pow(randZ, 2);
                    BlockPos pos = new BlockPos((int) Math.floor(xf), (int) Math.floor(yf), (int) Math.floor(zf));
                    BlockState state = random.nextInt(6) == 0 ? ModBlocks.GNEISS_VENT.get().defaultBlockState() : ModBlocks.GNEISS.get().defaultBlockState();

                    if (a + b + c <= 1.1) {
                        level.setBlock(pos, state, 2);
                    }
                }
            }
        }
    }
}
