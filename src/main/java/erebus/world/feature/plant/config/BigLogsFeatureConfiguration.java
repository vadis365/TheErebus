package erebus.world.feature.plant.config;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.function.Supplier;

public class BigLogsFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    protected final Supplier<? extends Block> log;
    protected final Supplier<? extends Block> core;
    protected final Supplier<? extends Block> ore;
    private final Direction direction;
    private final boolean genOres;

    public BigLogsFeatureConfiguration(Direction direction, Supplier<? extends Block> log, Supplier<? extends Block> core, Supplier<? extends Block> ore, boolean genOres) {
        super(NoneFeatureConfiguration.CODEC);
        this.direction = direction;
        this.log = log;
        this.core = core;
        this.ore = ore;
        this.genOres = genOres;
    }

    public BigLogsFeatureConfiguration(Direction direction, Supplier<? extends Block> outerLayer) {
        super(NoneFeatureConfiguration.CODEC);
        this.direction = direction;
        this.log = outerLayer;
        this.ore = outerLayer;
        this.core = outerLayer;
        this.genOres = false;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        RandomSource random = context.random();
        int length = random.nextInt(5) + 4;
        int baseRadius = random.nextInt(3) + 2;
        return placeSized(context.level(), context.origin(), random, length, baseRadius);
    }

    public boolean placeSized(WorldGenLevel level, BlockPos pos, RandomSource random, int length, int baseRadius) {
        boolean isNorthSouth = direction == Direction.NORTH || direction == Direction.SOUTH;

        if (!checkAreaClear(level, pos, isNorthSouth, length, baseRadius)) {
            return false;
        }

        generateLogStructure(level, pos, random, isNorthSouth, length, baseRadius);

        return true;
    }

    private boolean checkAreaClear(WorldGenLevel level, BlockPos pos, boolean isNorthSouth, int length, int baseRadius) {
        if (isNorthSouth) {
            for (int x = -baseRadius; x <= baseRadius; x++) {
                for (int z = -length; z <= length - 1; z++) {
                    for (int y = 0; y <= baseRadius * 2; y++) {
                        if (level.isOutsideBuildHeight(pos.offset(x, y, z)) || !level.isEmptyBlock(pos.offset(x, y, z))) return false;
                    }
                }
            }
        } else {
            for (int x = -length; x <= length - 1; x++) {
                for (int z = -baseRadius; z <= baseRadius; z++) {
                    for (int y = 0; y <= baseRadius * 2; y++) {
                        if (level.isOutsideBuildHeight(pos.offset(x, y, z)) || !level.isEmptyBlock(pos.offset(x, y, z))) return false;
                    }
                }
            }
        }

        return !level.isOutsideBuildHeight(pos.below()) && isValidGround(level.getBlockState(pos.below()));
    }

    protected boolean isValidGround(BlockState state) {
        return state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.MYCELIUM);
    }

    private void generateLogStructure(WorldGenLevel level, BlockPos pos, RandomSource random, boolean isNorthSouth, int length, int baseRadius) {
        if (isNorthSouth) {
            for (int z = -length; z <= length - 1; z++) {
                generateLogCrossSection(level, pos, random, z, true, length, baseRadius);
            }
        } else {
            for (int x = -length; x <= length - 1; x++) {
                generateLogCrossSection(level, pos, random, x, false, length, baseRadius);
            }
        }
    }

    private void generateLogCrossSection(WorldGenLevel level, BlockPos pos, RandomSource random, int axisPos, boolean isNorthSouth, int length, int baseRadius) {
        for (int i = -baseRadius; i <= baseRadius; i++) {
            for (int y = -baseRadius; y <= baseRadius; y++) {
                double dSq = i * i + y * y;
                long rounded = Math.round(Math.sqrt(dSq));

                BlockPos blockPos = isNorthSouth ?
                        pos.offset(i, y + baseRadius, axisPos) :
                        pos.offset(axisPos, y + baseRadius, i);

                if (rounded == baseRadius) {
                    setBlock(level, blockPos, log.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, isNorthSouth ? Direction.Axis.Z : Direction.Axis.X));

                    if (random.nextInt(12) == 0) {
                        setBlock(level, blockPos, Blocks.AIR.defaultBlockState());
                    }

                    if (axisPos == -length && random.nextInt(2) == 0 || axisPos == length - 1 && random.nextInt(2) == 0) {
                        setBlock(level, blockPos, Blocks.AIR.defaultBlockState());
                    }
                } else if (rounded < baseRadius && genOres) {
                    setBlock(level, blockPos, random.nextInt(6) == 0 ? ore.get().defaultBlockState() : core.get().defaultBlockState());

                    if (axisPos == -length && random.nextInt(2) == 0 || axisPos == length - 1 && random.nextInt(2) == 0) {
                        setBlock(level, blockPos, Blocks.AIR.defaultBlockState());
                    }
                } else {
                    setBlock(level, blockPos, Blocks.AIR.defaultBlockState());
                }
            }
        }
    }
}
