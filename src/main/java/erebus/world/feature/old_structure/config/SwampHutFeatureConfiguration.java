package erebus.world.feature.old_structure.config;

import erebus.registries.blocks.providers.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class SwampHutFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    private final BlockState LOG = WoodBlocks.LOG_MOSSBARK.get().defaultBlockState();
    private final BlockState PLANK = WoodBlocks.PLANKS_MOSSBARK.get().defaultBlockState();
    private final BlockState STAIR = StairBlocks.STAIRS_MOSSBARK.get().defaultBlockState();
    private final BlockState BRICKS = UmberstoneBlocks.UMBERCOBBLE.get().defaultBlockState();
    private final BlockState FENCE = FenceBlocks.FENCE_MOSSBARK.get().defaultBlockState();
    private final BlockState DOOR = DoorBlocks.DOOR_MOSSBARK.get().defaultBlockState();

    private final int LENGTH = 16;
    private final int WIDTH = 16;

    public SwampHutFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        if(!level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)) return false;

        verticalBeam(level, pos.offset(5, 0, 5), LOG, 4);
        verticalBeam(level, pos.offset(10, 0, 5), LOG, 4);
        verticalBeam(level, pos.offset(5, 0, 10), LOG, 4);
        verticalBeam(level, pos.offset(10, 0, 10), LOG, 4);

        verticalBeam(level, pos.offset(4, 4, 4), LOG, 3);
        verticalBeam(level, pos.offset(11, 4, 4), LOG, 3);
        verticalBeam(level, pos.offset(4, 4, 11), LOG, 3);
        verticalBeam(level, pos.offset(11, 4, 11), LOG, 3);

        verticalBeam(level, pos.offset(4, 5, 5), LOG, 4);
        verticalBeam(level, pos.offset(11, 5, 5), LOG, 4);
        verticalBeam(level, pos.offset(4, 5, 10), LOG, 4);
        verticalBeam(level, pos.offset(11, 5, 10), LOG, 4);

        verticalBeam(level, pos.offset(5, 5, 4), LOG, 4);
        verticalBeam(level, pos.offset(10, 5, 4), LOG, 4);
        verticalBeam(level, pos.offset(5, 5, 11), LOG, 4);
        verticalBeam(level, pos.offset(10, 5, 11), LOG, 4);

        verticalBeam(level, pos.offset(4, 8, 6), LOG, 2);
        verticalBeam(level, pos.offset(11, 8, 6), LOG, 2);
        verticalBeam(level, pos.offset(4, 8, 9), LOG, 2);
        verticalBeam(level, pos.offset(11, 8, 9), LOG, 2);

        verticalBeam(level, pos.offset(6, 8, 4), LOG, 2);
        verticalBeam(level, pos.offset(9, 8, 4), LOG, 2);
        verticalBeam(level, pos.offset(6, 8, 11), LOG, 2);
        verticalBeam(level, pos.offset(9, 8, 11), LOG, 2);

        verticalBeam(level, pos.offset(4, 9, 7), LOG, 2);
        verticalBeam(level, pos.offset(11, 9, 7), LOG, 2);
        verticalBeam(level, pos.offset(4, 9, 8), LOG, 2);
        verticalBeam(level, pos.offset(11, 9, 8), LOG, 2);

        verticalBeam(level, pos.offset(7, 9, 4), LOG, 2);
        verticalBeam(level, pos.offset(7, 9, 11), LOG, 2);
        verticalBeam(level, pos.offset(8, 9, 4), LOG, 2);
        verticalBeam(level, pos.offset(8, 9, 11), LOG, 2);

        for(int direction = 0; direction < 4; direction++) {
            rotatedBeam(level, pos, 6, 5, BRICKS, 4, direction);

            // bottom window
            rotatedBeam(level, pos.above(), 6, 5, PLANK, 1, direction);
            rotatedBeam(level, pos.above(), 7, 5, FENCE, 2, direction);
            rotatedBeam(level, pos.above(), 9, 5, PLANK, 1, direction);

            rotatedBeam(level, pos.above(2), 6, 5, PLANK, 4, direction);
            rotatedBeam(level, pos.above(3), 6, 5, PLANK, 4, direction);
            rotatedBeam(level, pos.above(4), 5, 5, PLANK, 6, direction);
            rotatedBeam(level, pos.above(4), 5, 4, PLANK, 6, direction);
            rotatedBeam(level, pos.above(3), 4, 4, getStairRotations(STAIR, direction == 0 ? 6 : direction == 1 ? 4 : direction == 2 ? 7 : 5), 8, direction);

            // mid window
            rotatedBeam(level, pos.above(5), 6, 4, PLANK, 1, direction);
            rotatedBeam(level, pos.above(5), 7, 4, FENCE, 2, direction);
            rotatedBeam(level, pos.above(5), 9, 4, PLANK, 1, direction);
            rotatedBeam(level, pos.above(6), 6, 4, FENCE, 4, direction);
            rotatedBeam(level, pos.above(7), 6, 4, PLANK, 4, direction);

            // top window
            rotatedBeam(level, pos.above(8), 7, 4, FENCE, 2, direction);

            rotatedBeam(level, pos.above(7), 4, 4, PLANK, 1, direction);
            rotatedBeam(level, pos.above(7), 11, 4, PLANK, 1, direction);

            // STAIR left
            int dir = direction == 0 ? 2 : direction == 1 ? 0 : direction == 2 ? 3 : 1;
            rotatedBeam(level, pos.above(11), 3, 7, getStairRotations(STAIR, dir), 4, direction);
            rotatedBeam(level, pos.above(10), 3, 6, getStairRotations(STAIR, dir), 4, direction);
            rotatedBeam(level, pos.above(9), 3, 5, getStairRotations(STAIR, dir), 3, direction);
            rotatedBeam(level, pos.above(8), 3, 4, getStairRotations(STAIR, dir), 2, direction);
            dir = direction == 0 ? 7 : direction == 1 ? 5 : direction == 2 ? 6 : 4;
            rotatedBeam(level, pos.above(10), 3, 7, getStairRotations(STAIR, dir), 1, direction);
            rotatedBeam(level, pos.above(9), 3, 6, getStairRotations(STAIR, dir), 1, direction);
            rotatedBeam(level, pos.above(8), 3, 5, getStairRotations(STAIR, dir), 1, direction);

            // STAIR right
            dir = direction == 0 ? 3 : direction == 1 ? 1 : direction == 2 ? 2 : 0;
            rotatedBeam(level, pos.above(11), 3, 8, getStairRotations(STAIR, dir), 4, direction);
            rotatedBeam(level, pos.above(10), 3, 9, getStairRotations(STAIR, dir), 4, direction);
            rotatedBeam(level, pos.above(9), 3, 10, getStairRotations(STAIR, dir), 3, direction);
            rotatedBeam(level, pos.above(8), 3, 11, getStairRotations(STAIR, dir), 2, direction);
            dir = direction == 0 ? 6 : direction == 1 ? 4 : direction == 2 ? 7 : 5;
            rotatedBeam(level, pos.above(10), 3, 8, getStairRotations(STAIR, dir), 1, direction);
            rotatedBeam(level, pos.above(9), 3, 9, getStairRotations(STAIR, dir), 1, direction);
            rotatedBeam(level, pos.above(8), 3, 10, getStairRotations(STAIR, dir), 1, direction);

            //STAIR apex
            rotatedBeam(level, pos.above(11), 7, 7, PLANK, 1, direction);
            rotatedBeam(level, pos.above(11), 7, 8, PLANK, 1, direction);
        }

        boolean leftExtension = random.nextBoolean();
        boolean rightExtension = random.nextBoolean();
        boolean backExtension = random.nextBoolean();

        if(!leftExtension && !rightExtension && !backExtension) {
            int direction = random.nextInt(4);

            rotatedBeam(level, pos, 7, 5, getDoorRotations(DOOR, direction == 1 ? 1 : direction == 3 ? 3 : direction == 2 ? 2 : 0).setValue(DoorBlock.HINGE, direction == 0 || direction == 2 ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT), 1, direction);
            rotatedBeam(level, pos.above(), 7, 5, getDoorRotations(DOOR, direction == 1 ? 5 : direction == 3 ? 7 : direction == 2 ? 6 : 4).setValue(DoorBlock.HINGE, direction == 0 || direction == 2 ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT), 1, direction);
            rotatedBeam(level, pos, 8, 5, getDoorRotations(DOOR, direction == 1 ? 1 : direction == 3 ? 3 : direction == 2 ? 2 : 0).setValue(DoorBlock.HINGE, direction == 0 || direction == 2 ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT), 1, direction);
            rotatedBeam(level, pos.above(), 8, 5, getDoorRotations(DOOR, direction == 1 ? 5 : direction == 3 ? 7 : direction == 2 ? 6 : 4).setValue(DoorBlock.HINGE, direction == 0 || direction == 2 ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT), 1, direction);
        }

        if(leftExtension) addExtension(level, pos, 1);
        if(backExtension) addExtension(level, pos, 2);
        if(rightExtension) addExtension(level, pos, 3);

        return true;
    }

    private void addExtension(WorldGenLevel level, BlockPos pos, int direction) {
        rotatedBeam(level, pos.above(2), 2, 6, getLogRotations(LOG, direction == 0 || direction == 2 ? 4 : 8), 3, direction);
        rotatedBeam(level, pos.above(2), 2, 9, getLogRotations(LOG, direction == 0 || direction == 2 ? 4 : 8), 3, direction);

        for(int beamHeight = 0; beamHeight < 2; beamHeight++) {
            rotatedBeam(level, pos.above(beamHeight), 1, 6, LOG, 1, direction);
            rotatedBeam(level, pos.above(beamHeight), 1, 9, LOG, 1, direction);
            rotatedBeam(level, pos.above(beamHeight), 4, 6, LOG, 1, direction);
            rotatedBeam(level, pos.above(beamHeight), 4, 9, LOG, 1, direction);
        }

        for(int l = 0; l < 4; l++) {
            rotatedBeam(level, pos.above(2), 1, 6 + l, getLogRotations(LOG, direction == 0 || direction == 2 ? 4 : 8), 1, direction);
        }

        for(int l = 0; l < 2; l++) {
            rotatedBeam(level, pos.above(3), 1, 7 + l, PLANK, 1, direction);
        }

        for(int l = 0; l < 2; l++ ) {
            for(int h = 0; h < 3; h++) {
                rotatedBeam(level, pos.above(h), 5, 7 + l, Blocks.AIR.defaultBlockState(), 1, direction);
            }
        }

        for(int l = 0; l < 2; l++) {
            rotatedBeam(level, pos, 1, 7 + l, BRICKS, 1, direction);
            rotatedBeam(level, pos.above(), 1, 7 + l, FENCE, 1, direction);
        }

        rotatedBeam(level, pos, 2, 6, BRICKS, 2, direction);
        rotatedBeam(level, pos.above(), 2, 6, FENCE, 2, direction);

        rotatedBeam(level, pos, 2, 9, BRICKS, 1, direction);
        rotatedBeam(level, pos.above(), 2, 9, PLANK, 1, direction);

        rotatedBeam(level, pos, 3, 9, getDoorRotations(DOOR, direction == 1 ? 1 : direction == 2 ? 2 : direction == 3 ? 3 : 0).setValue(DoorBlock.HINGE, direction == 0 || direction == 2 ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT), 1, direction);
        rotatedBeam(level, pos.above(), 3, 9, getDoorRotations(DOOR, direction == 1 ? 5 : direction == 2 ? 6 : direction == 3 ? 7 : 4).setValue(DoorBlock.HINGE, direction == 0 || direction == 2 ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT), 1, direction);

        int lower = direction == 0 ? 2 : direction == 1 ? 0 : direction == 2 ? 3 : 1;
        int upper = direction == 0 ? 7 : direction == 1 ? 5 : direction == 2 ? 6 : 4;
        rotatedBeam(level, pos.above(2), 0, 5, getStairRotations(STAIR, lower), 5, direction);
        rotatedBeam(level, pos.above(3), 0, 6, getStairRotations(STAIR, lower), 5, direction);
        rotatedBeam(level, pos.above(4), 0, 7, getStairRotations(STAIR, lower), 5, direction);
        rotatedBeam(level, pos.above(2), 0, 8, getStairRotations(STAIR, upper), 5, direction);
        rotatedBeam(level, pos.above(3), 0, 9, getStairRotations(STAIR, upper), 5, direction);

        lower = direction == 0 ? 3 : direction == 1 ? 1 : direction == 2 ? 2 : 0;
        upper = direction == 0 ? 6 : direction == 1 ? 4: direction == 2 ? 7 : 5;
        rotatedBeam(level, pos.above(4), 0, 8, getStairRotations(STAIR, lower), 4, direction);
        rotatedBeam(level, pos.above(3), 0, 9, getStairRotations(STAIR, lower), 4, direction);
        rotatedBeam(level, pos.above(2), 0, 10, getStairRotations(STAIR, lower), 5, direction);
        rotatedBeam(level, pos.above(3), 0, 8, getStairRotations(STAIR, upper), 1, direction);
        rotatedBeam(level, pos.above(2), 0, 9, getStairRotations(STAIR, upper), 1, direction);

        rotatedBeam(level, pos.above(3), 4, 6, PLANK, 1, direction);
        rotatedBeam(level, pos.above(3), 4, 9, PLANK, 1, direction);
    }

    private void rotatedBeam(WorldGenLevel level, BlockPos pos, int a, int b, BlockState block, int size, int direction) {
        switch(direction) {
            case 0 -> {
                for(int x = a; x < a + size; x++) setBlock(level, pos.offset(x, 0, b), block);
            }
            case 1 -> {
                for(int z = a; z < a + size; z++) setBlock(level, pos.offset(b, 0, z), block);
            }
            case 2 -> {
                for(int x = LENGTH - a - 1; x > LENGTH - a - size - 1; x--) setBlock(level, pos.offset(x, 0, LENGTH - b - 1), block);
            }
            case 3 -> {
                for(int z = LENGTH - a - 1; z > LENGTH - a - size - 1; z--) setBlock(level, pos.offset(LENGTH - b - 1, 0, z), block);
            }
        }
    }

    private void verticalBeam(WorldGenLevel level, BlockPos pos, BlockState block, int size) {
        for(int y = 0; y < size; y++) {
            setBlock(level, pos.above(y), block);
        }
    }

    private void horizontalBeam(WorldGenLevel level, BlockPos pos, BlockState block, int size, Axis axis) {
        switch (axis) {
            case X -> {
                for(int x = 0; x < size; x++) {
                    setBlock(level, pos.offset(x, 0, 0), block);
                }
            }
            case Z -> {
                for(int z = 0; z < size; z++) {
                    setBlock(level, pos.offset(0, 0, z), block);
                }
            }
        }
    }

    private BlockState getStairRotations(BlockState state, int direction) {
        return switch (direction) {
            case 0 -> state.setValue(StairBlock.FACING, Direction.EAST);
            case 1 -> state.setValue(StairBlock.FACING, Direction.WEST);
            case 2 -> state.setValue(StairBlock.FACING, Direction.SOUTH);
            case 3 -> state.setValue(StairBlock.FACING, Direction.NORTH);
            case 4 -> state.setValue(StairBlock.FACING, Direction.EAST).setValue(StairBlock.HALF, Half.TOP);
            case 5 -> state.setValue(StairBlock.FACING, Direction.WEST).setValue(StairBlock.HALF, Half.TOP);
            case 6 -> state.setValue(StairBlock.FACING, Direction.SOUTH).setValue(StairBlock.HALF, Half.TOP);
            case 7 -> state.setValue(StairBlock.FACING, Direction.NORTH).setValue(StairBlock.HALF, Half.TOP);
            default -> state;
        };
    }

    private BlockState getDoorRotations(BlockState state, int direction) {
        return switch (direction) {
            case 0 -> state.setValue(DoorBlock.FACING, Direction.SOUTH).setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER);
            case 1 -> state.setValue(DoorBlock.FACING, Direction.EAST).setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER);
            case 2 -> state.setValue(DoorBlock.FACING, Direction.NORTH).setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER);
            case 3 -> state.setValue(DoorBlock.FACING, Direction.WEST).setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER);
            case 4 -> state.setValue(DoorBlock.FACING, Direction.SOUTH).setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER);
            case 5 -> state.setValue(DoorBlock.FACING, Direction.EAST).setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER);
            case 6 -> state.setValue(DoorBlock.FACING, Direction.NORTH).setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER);
            case 7 -> state.setValue(DoorBlock.FACING, Direction.WEST).setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER);
            default -> state;
        };
    }

    private BlockState getLogRotations(BlockState state, int axis) {
        return switch (axis) {
            case 0 -> state.setValue(RotatedPillarBlock.AXIS, Axis.Z);
            case 4 -> state.setValue(RotatedPillarBlock.AXIS, Axis.X);
            default -> state;
        };
    }
}
