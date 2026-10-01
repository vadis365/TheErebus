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

import java.util.LinkedHashMap;
import java.util.Map;

public class SavannahRockFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public SavannahRockFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        for (int x = pos.getX() - 3; x <= pos.getX() + 3; x++) {
            for (int z = pos.getZ() - 3; z <= pos.getZ() + 3; z++) {
                var ground = new BlockPos(x, pos.getY(), z);
                if (level.isOutsideBuildHeight(ground) || level.getBlockState(ground) != Blocks.GRASS_BLOCK.defaultBlockState()) return false;
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

        var planned = new LinkedHashMap<BlockPos, BlockState>();
        for (int c = 0; c < 2; c++) {
            generateEllipsoidAt(planned, random, x, y, z, randX, randY, randZ);
            ++y;
            if (randX > randZ) {
                x += random.nextInt(2) * 2 - 1;
            } else {
                z += random.nextInt(2) * 2 - 1;
            }
        }

        for (var target : planned.keySet()) {
            if (level.isOutsideBuildHeight(target)) return false;
            var existing = level.getBlockState(target);
            if (!existing.isAir() && !(existing.is(Blocks.GRASS_BLOCK) || existing.is(Blocks.DIRT))) return false;
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
                int yAtt = y + random.nextInt(checkRandY * 2) - checkRandY;
                int zAtt = z + random.nextInt(checkRandZ * 2) - checkRandZ;
                state = planned.getOrDefault(new BlockPos(xAtt, yAtt, zAtt), Blocks.AIR.defaultBlockState());

                if (state.is(Blocks.STONE) || state.is(Blocks.INFESTED_STONE)) {
                    planned.put(new BlockPos(xAtt, yAtt, zAtt), Blocks.DIAMOND_ORE.defaultBlockState());
                    ++diamonds;
                }
            }
        }

        boolean placed = false;
        for (var entry : planned.entrySet()) placed |= level.setBlock(entry.getKey(), entry.getValue(), 2);
        return placed;
    }

    private void generateEllipsoidAt(Map<BlockPos, BlockState> planned, RandomSource random, int x, int y, int z, float randX, float randY, float randZ) {
        for (float xf = x - randX; xf <= x + randX; xf++) {
            for (float zf = z - randZ; zf <= z + randZ; zf++) {
                for (float yf = y - randY; yf <= y + randY; yf++) {
                    double a = Math.pow(xf - x, 2) / (randX * randX);
                    double b = Math.pow(yf - y, 2) / (randY * randY);
                    double c = Math.pow(zf - z, 2) / (randZ * randZ);
                    BlockPos pos = new BlockPos((int) Math.floor(xf), (int) Math.floor(yf), (int) Math.floor(zf));
                    if (a + b + c <= 1.1) {
                        BlockState state = random.nextInt(6) == 0 ? Blocks.INFESTED_STONE.defaultBlockState() : Blocks.STONE.defaultBlockState();
                        planned.put(pos, state);
                    }
                }
            }
        }
    }
}
