package erebus.world.feature.plant.config;

import erebus.registries.blocks.providers.PlantBlocks;
import erebus.world.feature.plant.config.util.PetalShape;
import erebus.world.feature.plant.config.util.StemShape;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GiantFlowerFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    private static final int[] offsetX = {-1, 1, 0, 0};
    private static final int[] offsetZ = {0, 0, -1, 1};
    private int primaryPetalColor = -1;
    private int secondaryPetalColor = -1;

    public GiantFlowerFeatureConfiguration(int color) {
        super(NoneFeatureConfiguration.CODEC);
        this.primaryPetalColor = color;
        this.secondaryPetalColor = color;
    }

    public GiantFlowerFeatureConfiguration(int primaryPetalColor, int secondaryPetalColor) {
        super(NoneFeatureConfiguration.CODEC);
        this.primaryPetalColor = primaryPetalColor;
        this.secondaryPetalColor = secondaryPetalColor;
    }

    public GiantFlowerFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        BlockState stem = PlantBlocks.STEM.get().defaultBlockState();
        StemShape stemShape = StemShape.values()[random.nextInt(StemShape.values().length)];
        PetalShape petalShape = PetalShape.values()[random.nextInt(PetalShape.values().length)];
        BlockState petal = getPetalForColor(primaryPetalColor);
        BlockState stigma = getStigmaForColor(secondaryPetalColor);
        int stemHeight = random.nextInt(6) + 2;
        int top = stemHeight + 1;
        if (primaryPetalColor == -1) primaryPetalColor = random.nextInt(13);
        if (secondaryPetalColor == -1)
            secondaryPetalColor = petalShape.canHaveSecondaryColor && random.nextInt(8) == 0 ? random.nextInt(13) : primaryPetalColor;

        // Check if the area is clear
        if (!checkAreaClear(level, pos, stemHeight, stemShape, petalShape)) {
            return false;
        }

        generateStem(level, pos, stemHeight, stemShape, stem);
        generatePetal(level, pos, random, petalShape, top, stem, petal, stigma);

        return true;
    }

    private boolean checkAreaClear(WorldGenLevel level, BlockPos pos, int stemHeight, StemShape stemShape, PetalShape petalShape) {
        for (int y = 2; y < stemHeight; y++) {
            if (!level.isEmptyBlock(pos.above(y))) {
                return false;
            }
        }

        for (int x = -stemShape.radius; x <= stemShape.radius; x++) {
            for (int z = -stemShape.radius; z <= stemShape.radius; z++) {
                for (int y = 0; y < stemShape.height; y++) {
                    if (!level.isEmptyBlock(pos.offset(x, y, z))) return false;
                }

                if (level.isEmptyBlock(pos.offset(x, -1, z))) return false;
            }
        }


        for (int x = -petalShape.radius; x <= petalShape.radius; x++) {
            for (int z = -petalShape.radius; z <= petalShape.radius; z++) {
                for (int y = stemHeight + 1; y < stemShape.height + 1 + petalShape.height; y++) {
                    if (!level.isEmptyBlock(pos.offset(x, y, z))) return false;
                }

                if (level.isEmptyBlock(pos.offset(x, -1, z))) return false;
            }
        }

        return true;
    }

    private void generateStem(WorldGenLevel level, BlockPos pos, int stemHeight, StemShape stemShape, BlockState stem) {
        switch (stemShape) {
            case SMALL_X -> {
                for (int x = 0; x < 2; x++) {
                    for (int z = 0; z < 2; z++) {
                        setBlock(level, pos.offset(-1 + x * 2, 0, -1 + z * 2), stem);
                    }
                }
            }
            case SMALL_PLUS -> {
                for (int c = 0; c < 4; c++) {
                    setBlock(level, pos.offset(offsetX[c], 0, offsetZ[c]), stem);
                }
            }
            case LARGE_PLUS -> {
                for (int c = 0; c < 4; c++) {
                    setBlock(level, pos.offset(offsetX[c] * 2, 0, offsetZ[c] * 2), stem);
                    setBlock(level, pos.offset(offsetX[c], 1, offsetZ[c]), stem);
                }
            }
        }

        for (int y = 0; y < stemHeight; y++) {
            setBlock(level, pos.above(y), stem);
        }
    }

    private void generatePetal(WorldGenLevel level, BlockPos pos, RandomSource random, PetalShape petalShape, int top, BlockState stem, BlockState petal, BlockState stigma) {
        switch (petalShape) {
            case DENSE_HEMISPHERE -> generateDenseHemisphere(level, pos, random, top, stem, petal, stigma);
            case DISPERSE_HEMISPHERE -> generateDisperseHemisphere(level, pos, random, top, stem, petal, stigma);
            case UMBRELLA -> generateUmbrella(level, pos, random, top, stem, petal, stigma);
        }
    }

    private void generateDenseHemisphere(WorldGenLevel level, BlockPos pos, RandomSource random, int top, BlockState stem, BlockState petal, BlockState stigma) {
        setBlock(level, pos.above(top), stem);

        for (int c = 0; c < 4; c++) {
            setBlock(level, pos.offset(offsetX[c], top, offsetZ[c]), stem);
            setBlock(level, pos.offset(offsetX[c] * 2, top + 1, offsetZ[c] * 2), stem);
            if (random.nextInt(3) == 0) {
                setBlock(level, pos.offset(offsetX[c] * 3, top + 2, offsetZ[c] * 3), stem);
            }
        }

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                setBlock(level, pos.offset(x, top + 1, z), stem);
            }
        }

        for (int c = 0; c < 3; c++) {
            for (int d = 0; d < 2; d++) {
                setBlock(level, pos.offset(-2 + d * 4, top + 2, -1 + c), petal);
                setBlock(level, pos.offset(-1 + c, top + 2, -2 + d * 4), petal);
            }
            setBlock(level, pos.offset(-1 + c, top + 2, 0), petal);
        }

        for (int c = 0; c < 2; c++) {
            setBlock(level, pos.offset(0, top + 2, -1 + c * 2), petal);
        }

        setBlock(level, pos.above(top + 3), random.nextInt(10) == 0 ? PlantBlocks.EXPLODING_STIGMA.get().defaultBlockState() : stigma);

        for (int c = 0; c < 3; c++) {
            for (int d = 0; d < 2; d++) {
                setBlock(level, pos.offset(-3 + d * 6, top + 3, -1 + c), petal);
                setBlock(level, pos.offset(-1 + c, top + 3, -3 + d * 6), petal);
            }
        }

        for (int c = -1; c <= 1; c++) {
            for (int d = -1; d <= 1; d++) {
                if (c == 0 && d == 0) continue;
                setBlock(level, pos.offset(c * 2, top + 3, d * 2), petal);
            }
        }

        for (int c = 0; c < 2; c++) {
            for (int d = 0; d < 2; d++) {
                if (random.nextBoolean()) {
                    setBlock(level, pos.offset(-3 + c * 6, top + 4, -1 + d * 2), petal);
                    setBlock(level, pos.offset(-1 + c * 2, top + 4, -3 + d * 6), petal);
                } else {
                    setBlock(level, pos.offset(-2 + c * 4, top + 4, -2 + d * 4), petal);
                    setBlock(level, pos.offset(offsetX[c * 2 + d] * 3, top + 4, offsetZ[c * 2 + d] * 3), petal);
                }
            }
        }
    }

    private void generateDisperseHemisphere(WorldGenLevel level, BlockPos pos, RandomSource random, int top, BlockState stem, BlockState petal, BlockState stigma) {
        setBlock(level, pos.above(top), stem);
        setBlock(level, pos.above(top + 1), random.nextInt(10) == 0 ? PlantBlocks.EXPLODING_STIGMA.get().defaultBlockState() : stigma);
        for (int c = 0; c < 4; c++) {
            for (int d = 1; d <= 3; d++) {
                setBlock(level, pos.offset(offsetX[c] * d, top + d - 1, offsetZ[c] * d), petal);
            }
        }

        for (int c = 0; c < 2; c++) {
            for (int d = 0; d < 2; d++) {
                setBlock(level, pos.offset(-1 + c * 2, top + 1, -1 + d * 2), petal);
                setBlock(level, pos.offset(-2 + c * 4, top + 2, -2 + d * 4), petal);
            }
        }
    }

    private void generateUmbrella(WorldGenLevel level, BlockPos pos, RandomSource random, int top, BlockState stem, BlockState petal, BlockState stigma) {
        setBlock(level, pos.above(top), stem);

        for (int c = 0; c < 4; c++) {
            setBlock(level, pos.offset(offsetX[c], top, offsetZ[c]), stem);
        }

        setBlock(level, pos.above(top + 1), petal);
        setBlock(level, pos.above(top + 2), random.nextInt(10) == 0 ? PlantBlocks.EXPLODING_STIGMA.get().defaultBlockState() : stigma);

        for (int c = 0; c < 3; c++) {
            setBlock(level, pos.offset(c - 3, top + 1, 0), petal);
            setBlock(level, pos.offset(c + 3, top + 1, 0), petal);
            setBlock(level, pos.offset(0, top + 1, c - 3), petal);
            setBlock(level, pos.offset(0, top + 1, c + 3), petal);
        }

        boolean reallyStrongWind = random.nextInt(3) == 0;

        for (int c = 0; c < 2; c++) {
            for (int d = 0; d < 2; d++) {
                setBlock(level, pos.offset(-1 + c * 2, top + 1, -1 + d * 2), petal);
                setBlock(level, pos.offset(-1 + c * 4, top + 1, -2 + d * 4), petal);
                setBlock(level, pos.offset(-3 + c * 6, top + (reallyStrongWind ? 2 : 0), -3 + d * 6), petal);
                setBlock(level, pos.offset(offsetX[c * 2 + d] * 4, top + (reallyStrongWind ? 2 : 0), offsetZ[c * 2 + d] * 4), petal);
            }
        }
    }

    private BlockState getPetalForColor(int color) {
        return switch (color) {
            case 0 -> PlantBlocks.PETAL_BLACK.get().defaultBlockState();
            case 1 -> PlantBlocks.PETAL_RED.get().defaultBlockState();
            case 2 -> PlantBlocks.PETAL_BROWN.get().defaultBlockState();
            case 3 -> PlantBlocks.PETAL_BLUE.get().defaultBlockState();
            case 4 -> PlantBlocks.PETAL_PURPLE.get().defaultBlockState();
            case 5 -> PlantBlocks.PETAL_CYAN.get().defaultBlockState();
            case 6 -> PlantBlocks.PETAL_LIGHT_GRAY.get().defaultBlockState();
            case 7 -> PlantBlocks.PETAL_GRAY.get().defaultBlockState();
            case 8 -> PlantBlocks.PETAL_PINK.get().defaultBlockState();
            case 9 -> PlantBlocks.PETAL_YELLOW.get().defaultBlockState();
            case 10 -> PlantBlocks.PETAL_LIGHT_BLUE.get().defaultBlockState();
            case 11 -> PlantBlocks.PETAL_MAGENTA.get().defaultBlockState();
            case 12 -> PlantBlocks.PETAL_ORANGE.get().defaultBlockState();
            case 13 -> PlantBlocks.PETAL_WHITE.get().defaultBlockState();
            default -> throw new IllegalStateException("Unexpected value: " + color);
        };
    }

    private BlockState getStigmaForColor(int color) {
        return switch (color) {
            case 0 -> PlantBlocks.STIGMA_BLACK.get().defaultBlockState();
            case 1 -> PlantBlocks.STIGMA_RED.get().defaultBlockState();
            case 2 -> PlantBlocks.STIGMA_BROWN.get().defaultBlockState();
            case 3 -> PlantBlocks.STIGMA_BLUE.get().defaultBlockState();
            case 4 -> PlantBlocks.STIGMA_PURPLE.get().defaultBlockState();
            case 5 -> PlantBlocks.STIGMA_CYAN.get().defaultBlockState();
            case 6 -> PlantBlocks.STIGMA_LIGHT_GRAY.get().defaultBlockState();
            case 7 -> PlantBlocks.STIGMA_GRAY.get().defaultBlockState();
            case 8 -> PlantBlocks.STIGMA_PINK.get().defaultBlockState();
            case 9 -> PlantBlocks.STIGMA_YELLOW.get().defaultBlockState();
            case 10 -> PlantBlocks.STIGMA_LIGHT_BLUE.get().defaultBlockState();
            case 11 -> PlantBlocks.STIGMA_MAGENTA.get().defaultBlockState();
            case 12 -> PlantBlocks.STIGMA_ORANGE.get().defaultBlockState();
            case 13 -> PlantBlocks.STIGMA_WHITE.get().defaultBlockState();
            default -> throw new IllegalStateException("Unexpected value: " + color);
        };
    }
}
