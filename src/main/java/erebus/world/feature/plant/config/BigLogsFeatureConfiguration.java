package erebus.world.feature.plant.config;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.function.Supplier;

public class BigLogsFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    private final int length;
    private final int baseRadius;
    private final Direction direction;
    private final boolean genOres;
    protected Supplier<? extends Block> log;
    protected Supplier<? extends Block> core;
    protected Supplier<? extends Block> ore;

    public BigLogsFeatureConfiguration(int length, int baseRadius, Direction direction, Supplier<? extends Block> log, Supplier<? extends Block> core, Supplier<? extends Block> ore, boolean genOres) {
        super(NoneFeatureConfiguration.CODEC);
        this.length = length;
        this.baseRadius = baseRadius;
        this.direction = direction;
        this.log = log;
        this.core = core;
        this.ore = ore;
        this.genOres = genOres;
    }

    public BigLogsFeatureConfiguration(int length, int baseRadius, Direction direction, Supplier<? extends Block> outerLayer) {
        super(NoneFeatureConfiguration.CODEC);
        this.length = length;
        this.baseRadius = baseRadius;
        this.direction = direction;
        this.log = outerLayer;
        this.ore = outerLayer;
        this.core = outerLayer;
        this.genOres = false;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        boolean isNorthSouth = direction == Direction.NORTH || direction == Direction.SOUTH;

        // Check if the area is clear
        if (!checkAreaClear(level, pos, isNorthSouth)) {
            return false;
        }

        // Generate the log structure
        generateLogStructure(level, pos, random, isNorthSouth);

        return true;
    }

    private boolean checkAreaClear(WorldGenLevel level, BlockPos pos, boolean isNorthSouth) {
        if (isNorthSouth) {
            for (int x = -baseRadius; x <= baseRadius; x++) {
                for (int z = -length; z <= length - 1; z++) {
                    for (int y = 1; y <= baseRadius * 2; y++) {
                        if (!level.isEmptyBlock(pos.offset(x, y, z))) return false;
                    }
                }
            }
        } else {
            for (int x = -length; x <= length - 1; x++) {
                for (int z = -baseRadius; z <= baseRadius; z++) {
                    for (int y = 1; y <= baseRadius * 2; y++) {
                        if (!level.isEmptyBlock(pos.offset(x, y, z))) return false;
                    }
                }
            }
        }
        return true;
    }

    private void generateLogStructure(WorldGenLevel level, BlockPos pos, RandomSource random, boolean isNorthSouth) {
        if (isNorthSouth) {
            for (int z = -length; z <= length - 1; z++) {
                generateLogCrossSection(level, pos, random, z, true);
            }
        } else {
            for (int x = -length; x <= length - 1; x++) {
                generateLogCrossSection(level, pos, random, x, false);
            }
        }
    }

    private void generateLogCrossSection(WorldGenLevel level, BlockPos pos, RandomSource random, int axisPos, boolean isNorthSouth) {
        for (int i = -baseRadius; i <= baseRadius; i++) {
            for (int y = -baseRadius; y <= baseRadius; y++) {
                double dSq = i * i + y * y;
                long rounded = Math.round(Math.sqrt(dSq));

                BlockPos blockPos = isNorthSouth ?
                        pos.offset(i, y + baseRadius, axisPos) :
                        pos.offset(axisPos, y + baseRadius, i);

                if (rounded == baseRadius) {
                    setBlock(level, blockPos, log.get().defaultBlockState());

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
