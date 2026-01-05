package erebus.world.feature.mushroom.config;

import erebus.world.util.FeatureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class DutchCapMushroomFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final FeatureUtils Utils = new FeatureUtils();
    private final BlockState STEM = ModBlocks.DUTCH_CAP_MUSHROOM_STEM.get().defaultBlockState()
            .setValue(HugeMushroomBlock.UP, true)
            .setValue(HugeMushroomBlock.DOWN, true);
    private final BlockState SHROOM = ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK.get().defaultBlockState();

    public DutchCapMushroomFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin().west();
        RandomSource random = context.random();
        int height = 9 + random.nextInt(8);

        if(!level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)) return false;

        if(!Utils.checkAirCube(level, pos.offset(-2, 0, -2), pos.offset(3, 4, 3))) return false;
        if(!Utils.checkAirCube(level, pos.offset(-3, 5, -3), pos.offset(5, height + 1, 5))) return false;
        if(!Utils.checkAirCube(level, pos.offset(-4, height + 2, -4), pos.offset(7, height + 4, 7))) return false;

        for(int c = 0; c < 2; c++) {
            for(int d = 0; d < 2; d++) {
                Utils.setBlockPillar(level, pos.offset(-1 + 3 * c, 0, d), 2, STEM);
                Utils.setBlockPillar(level, pos.offset(d, 0, -1 + 3 * c), 2, STEM);

                setBlock(level, pos.offset(-1 + 3 * c, 0, -1 + 3 * d), STEM);
                setBlock(level, pos.offset(-2 + 5 * c, 0, d), STEM);
                setBlock(level, pos.offset(d, 0, -2 + 5 * c), STEM);
            }
        }

        Utils.setBlockCube(level, pos, pos.offset(1, height, 1), STEM);

        for(int y = 4; y <= height; y++) {
            boolean isTop = y >= height - 1;

            if(random.nextInt(3) == 0 || isTop) {
                for(int attempt = 0; attempt < (isTop ? 2 : 1); attempt++) {
                    int x = 0, z = 0;

                    for(int branchAttempt = 0; branchAttempt < 12 && x == 0 && z == 0; branchAttempt++) {
                        x = random.nextInt(3) != 0 ? 0 : random.nextInt(3) - 1;
                        z = random.nextInt(3) != 0 ? 0 : random.nextInt(3) - 1;
                    }

                    if(x == 0 && z == 0) continue;
                    if(!Utils.checkAirCube(level, pos.offset(x * 2, y - 2, z * 2), pos.offset(x * 2 + 1, y, z * 2 + 1))) continue;
                    int size = isTop ? 3 + random.nextInt(2) : 2 + random.nextInt(2 + random.nextInt(2));

                    for(int branch = 1; branch <= size; branch++) {
                        BlockPos branchPos = pos.offset(x * branch, y - 1 + branch, z * branch);
                        Utils.setBlockCube(level, branchPos, branchPos.offset(1, 0, 1), STEM);
                        if(isTop)
                            Utils.setBlockCube(level, branchPos.above(), branchPos.offset(1, 1, 1), STEM);
                    }

                    BlockPos top = pos.offset(x * size, y + size, z * size);

                    if(isTop) {
                        Utils.setBlockCube(level, top.above(), top.offset(1, 1, 1), STEM);
                        top = top.above(2);

                        for(int a = 0; a < 2; a++) {
                            for(int b = 0; b < 2; b++) {
                                if(level.isEmptyBlock(top.offset(a, 0, b)))
                                    setBlock(level, top.offset(a, 0, b), SHROOM);

                                if(level.isEmptyBlock(top.offset(-1 + 3 * a, -1, b)))
                                    setBlock(level, top.offset(-1 + 3 * a, -1, b), SHROOM);

                                if(level.isEmptyBlock(top.offset(b, -1, -1 + 3 * a)))
                                    setBlock(level, top.offset(b, -1, -1 + 3 * a), SHROOM);

                                if(level.isEmptyBlock(top.offset(-1 + 3 * a, -2, -1 + 3 * b)))
                                    setBlock(level, top.offset(-1 + 3 * a, -2, -1 + 3 * b), SHROOM);

                                if(level.isEmptyBlock(top.offset(-2 + 5 * a, -2, b)))
                                    setBlock(level, top.offset(-2 + 5 * a, -2, b), SHROOM);

                                if(level.isEmptyBlock(top.offset(b, -2, -2 + 5 *  a)))
                                    setBlock(level, top.offset(b, -2, -2 + 5 *  a), SHROOM);
                            }
                        }
                    } else {
                        for(int a = 0; a < 2; a++) {
                            for(int b = 0; b < 2; b++) {
                                if(level.isEmptyBlock(top.offset(a, 0, b)))
                                    setBlock(level, top.offset(a, 0, b), SHROOM);

                                if(level.isEmptyBlock(top.offset(-1 + 3 * a, -1, b)))
                                    setBlock(level, top.offset(-1 + 3 * a, -1, b), SHROOM);

                                if(level.isEmptyBlock(top.offset(b, -1, -1 + 3 * a)))
                                    setBlock(level, top.offset(b, -1, -1 + 3 * a), SHROOM);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
