package erebus.world.feature.misc.config;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.function.Supplier;

public class PetrifiedTreeFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    private final int height;
    private final int baseRadius;
    protected Supplier<? extends Block> bark;
    protected Supplier<? extends Block> core;
    protected Supplier<? extends Block> ore;

    public PetrifiedTreeFeatureConfiguration(int height, int baseRadius, Supplier<? extends Block> bark, Supplier<? extends Block> fillerBlock, Supplier<? extends Block> ore) {
        super(NoneFeatureConfiguration.CODEC);
        this.height = height;
        this.baseRadius = baseRadius;
        this.bark = bark;
        this.core = fillerBlock;
        this.ore = ore;
    }

    public PetrifiedTreeFeatureConfiguration(int height, int baseRadius, Supplier<? extends Block> bark) {
        super(NoneFeatureConfiguration.CODEC);
        this.height = height;
        this.baseRadius = baseRadius;
        this.bark = bark;
        this.core = bark;
        this.ore = bark;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        if (!level.getBlockState(pos.below()).is(ModBlocks.VOLCANIC_ROCK)) return false;

        boolean alternate = random.nextBoolean();
        BlockState barkState = bark.get().defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Y);

        for (int x = -baseRadius; x <= baseRadius; x++) {
            for (int z = -baseRadius; z <= baseRadius; z++) {
                for (int y = 1; y < height; y++) {
                    if (!level.getBlockState(pos.offset(x, y, z)).isAir()) return false;
                }
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = -baseRadius; x <= baseRadius; x++) {
                for (int z = -baseRadius; z <= baseRadius; z++) {
                    double dSq = x * x + z * z;
                    if (Math.round(Math.sqrt(dSq)) == baseRadius) {
                        if (y < height / 10 && height > 10) {
                            level.setBlock(pos.offset(x, y, z), barkState, 2);
                        }

                        if (y > height / 10 && y <= height - 1 && height > 14) {
                            if (random.nextInt(15) == 0) level.setBlock(pos.offset(0, y, baseRadius), barkState, 2);
                            if (random.nextInt(15) == 0) level.setBlock(pos.offset(baseRadius, y, 0), barkState, 2);
                            if (random.nextInt(15) == 0) level.setBlock(pos.offset(0, y, -baseRadius), barkState, 2);
                            if (random.nextInt(15) == 0) level.setBlock(pos.offset(-baseRadius, y, 0), barkState, 2);

                            if (random.nextInt(25) == 0)
                                level.setBlock(pos.offset(1 - random.nextInt(3), y, baseRadius), barkState, 2);
                            if (random.nextInt(25) == 0)
                                level.setBlock(pos.offset(baseRadius, y, 1 - random.nextInt(3)), barkState, 2);
                            if (random.nextInt(25) == 0)
                                level.setBlock(pos.offset(-1 + random.nextInt(3), y, -baseRadius), barkState, 2);
                            if (random.nextInt(25) == 0)
                                level.setBlock(pos.offset(-baseRadius, y, -1 + random.nextInt(3)), barkState, 2);
                        }

                        if (y == 1 + height / 10 && random.nextInt(4) == 0) {
                            level.setBlock(pos.offset(x, y, z), barkState, 2);
                        }
                    }

                    if (Math.round(Math.sqrt(dSq)) == baseRadius - 1) {
                        if (y == height && random.nextInt(3) == 0) {
                            level.setBlock(pos.offset(x, y, z), Blocks.AIR.defaultBlockState(), 2);
                        } else {
                            level.setBlock(pos.offset(x, y, z), barkState, 2);
                        }
                    }

                    if (Math.round(Math.sqrt(dSq)) < baseRadius - 1) {
                        level.setBlock(pos.offset(x, y, z), random.nextInt(8) == 0 ? ore.get().defaultBlockState() : core.get().defaultBlockState(), 2);
                    }
                }
            }

            if (y == height - 1) {
                if (alternate) {
                    createBranch(level, pos.offset(baseRadius, y - random.nextInt(2), 0), Direction.NORTH, height / 8, false);
                    createBranch(level, pos.offset(-baseRadius, y - random.nextInt(2), 0), Direction.SOUTH, height / 8, false);
                    alternate = false;
                } else {
                    createBranch(level, pos.offset(0, y - random.nextInt(2), baseRadius), Direction.EAST, height / 8, false);
                    createBranch(level, pos.offset(0, y - random.nextInt(2), -baseRadius), Direction.WEST, height / 8, false);
                    alternate = true;
                }
            }

            if (y == height - height / 3 || y == height - height / 4) {
                if (alternate) {
                    createBranch(level, pos.offset(baseRadius, y - random.nextInt(2), 0), Direction.NORTH, height / 8, false);
                    createBranch(level, pos.offset(-baseRadius, y - random.nextInt(2), 0), Direction.SOUTH, height / 8, false);
                    alternate = false;
                } else {
                    createBranch(level, pos.offset(0, y - random.nextInt(2), baseRadius), Direction.EAST, height / 8, false);
                    createBranch(level, pos.offset(0, y - random.nextInt(2), -baseRadius), Direction.WEST, height / 8, false);
                    alternate = true;
                }
            }

            if (y == height - height / 2) {
                if (alternate) {
                    createBranch(level, pos.offset(baseRadius, y - random.nextInt(2), 0), Direction.NORTH, height / 8, false);
                    createBranch(level, pos.offset(-baseRadius, y - random.nextInt(2), 0), Direction.SOUTH, height / 8, false);
                    alternate = false;
                } else {
                    createBranch(level, pos.offset(0, y - random.nextInt(2), baseRadius), Direction.EAST, height / 8, false);
                    createBranch(level, pos.offset(0, y - random.nextInt(2), -baseRadius), Direction.WEST, height / 8, false);
                    alternate = true;
                }
            }

            if (y == 1) {
                createBranch(level, pos.offset(baseRadius, y - random.nextInt(2), 0), Direction.NORTH, height / 8, true);
                createBranch(level, pos.offset(-baseRadius, y - random.nextInt(2), 0), Direction.SOUTH, height / 8, true);
                createBranch(level, pos.offset(0, y - random.nextInt(2), baseRadius), Direction.EAST, height / 8, true);
                createBranch(level, pos.offset(0, y - random.nextInt(2), -baseRadius), Direction.WEST, height / 8, true);
            }
        }
        return true;
    }

    private void createBranch(WorldGenLevel level, BlockPos pos, Direction direction, int length, boolean down) {
        int y = 0;

        for (int c = 0; c < length; c++) {
            if (c >= height / 8 && !down) y++;
            if (c >= height / 8 && down) y--;

            if (direction == Direction.NORTH)
                level.setBlock(pos.offset(c, y, 0), bark.get().defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.X), 2);
            if (direction == Direction.SOUTH)
                level.setBlock(pos.offset(-c, y, 0), bark.get().defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.X), 2);
            if (direction == Direction.WEST)
                level.setBlock(pos.offset(0, y, c), bark.get().defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Z), 2);
            if (direction == Direction.EAST)
                level.setBlock(pos.offset(0, y, -c), bark.get().defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Z), 2);
        }
    }
}
