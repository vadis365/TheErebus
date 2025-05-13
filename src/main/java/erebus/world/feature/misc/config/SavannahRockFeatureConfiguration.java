package erebus.world.feature.misc.config;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class SavannahRockFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public SavannahRockFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        for (int x = -3; x < 3; x++) {
            for (int z = -3; z < 3; z++) {
                if (!level.getBlockState(pos.offset(x, 0, z)).is(Blocks.GRASS_BLOCK)) {
                    return false;
                }
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
            int randXI = (int) Math.ceil(randX);
            int randYI = (int) Math.ceil(randY);
            int randZI = (int) Math.ceil(randZ);

            for (int attempt = 0; attempt < 10 && diamonds < diamondAmount; attempt++) {
                int xAtt = x + random.nextInt(randXI * 2) - randXI;
                int yAtt = y + random.nextInt(randYI * 2) - randYI;
                int zAtt = z + random.nextInt(randZI * 2) - randZI;
                state = level.getBlockState(new BlockPos(xAtt, yAtt, zAtt));

                if (state.is(Blocks.STONE) || state.is(Blocks.INFESTED_STONE)) {
                    setBlock(level, new BlockPos(xAtt, yAtt, zAtt), Blocks.DIAMOND_ORE.defaultBlockState());
                    ++diamonds;
                }
            }
        }

        return true;
    }

    private void generateEllipsoidAt(WorldGenLevel level, RandomSource random, int x, int y, int z, float radX, float radY, float radZ) {
        for (float xf = x - radX; xf <= x + radX; xf++)
            for (float zf = z - radZ; zf <= z + radZ; zf++)
                for (float yf = y - radY; yf <= y + radY; yf++)
                    if (Math.pow(xf - x, 2) / (radX * radX) + Math.pow(yf - y, 2) / (radY * radY) + Math.pow(zf - z, 2) / (radZ * radZ) <= 1.1)
                        setBlock(level, new BlockPos((int) Math.floor(xf), (int) Math.floor(yf), (int) Math.floor(zf)), random.nextInt(6) == 0 ? Blocks.INFESTED_STONE.defaultBlockState() : Blocks.STONE.defaultBlockState());
    }
}
