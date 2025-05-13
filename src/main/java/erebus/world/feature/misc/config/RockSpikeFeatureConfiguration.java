package erebus.world.feature.misc.config;

import erebus.registries.blocks.providers.OreBlocks;
import erebus.registries.blocks.providers.UmberstoneBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction.Axis;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

/**
 * Feature that generates rock spikes made of petrified wood.
 * Creates both upward and downward spikes with a diamond ore base.
 */
public class RockSpikeFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public RockSpikeFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos originPos = context.origin();
        RandomSource random = context.random();

        // Find the ground level
        BlockPos groundPos = findGroundPosition(level, originPos);

        // Only generate on volcanic rock
        if (!level.getBlockState(groundPos).is(UmberstoneBlocks.VOLCANIC_ROCK)) {
            return false;
        }

        // Adjust the starting position slightly upward
        groundPos = groundPos.above(random.nextInt(4));

        // Determine spike dimensions
        int spikeHeight = random.nextInt(15) + 5;
        int spikeRadius = spikeHeight / 4 + random.nextInt(2);

        // Generate the main spike structure
        generateSpikeStructure(level, groundPos, spikeHeight, spikeRadius, random);

        // Generate the diamond ore base
        generateOreBase(level, groundPos, spikeRadius, random);

        return true;
    }

    /**
     * Finds the ground position by moving down until hitting a non-air block.
     */
    private BlockPos findGroundPosition(WorldGenLevel level, BlockPos startPos) {
        BlockPos currentPos = startPos;
        while (level.getBlockState(currentPos).isAir() && currentPos.getY() > 2) {
            currentPos = currentPos.below();
        }
        return currentPos;
    }

    /**
     * Generates the main spike structure with both upward and downward components.
     */
    private void generateSpikeStructure(WorldGenLevel level, BlockPos centerPos, int spikeHeight, int maxRadius, RandomSource random) {
        for (int heightIndex = 0; heightIndex < spikeHeight; ++heightIndex) {
            // Calculate radius at current height (tapers as height increases)
            float radiusAtHeight = (1.0F - (float) heightIndex / (float) spikeHeight) * (float) maxRadius;
            int radius = Mth.ceil(radiusAtHeight);

            for (int xOffset = -radius; xOffset <= radius; ++xOffset) {
                float xDistanceFromEdge = (float) Mth.abs(xOffset) - 0.25F;

                for (int zOffset = -radius; zOffset <= radius; ++zOffset) {
                    float zDistanceFromEdge = (float) Mth.abs(zOffset) - 0.25F;

                    // Check if the position is within the circular radius of the spike
                    boolean isWithinSpikeRadius = (xOffset == 0 && zOffset == 0) ||
                            (xDistanceFromEdge * xDistanceFromEdge +
                                    zDistanceFromEdge * zDistanceFromEdge <= radiusAtHeight * radiusAtHeight);

                    // Add randomness to the edge blocks
                    boolean isEdgeBlock = (xOffset == -radius || xOffset == radius ||
                            zOffset == -radius || zOffset == radius);
                    boolean shouldPlaceEdgeBlock = !isEdgeBlock || random.nextFloat() <= 0.75F;

                    if (isWithinSpikeRadius && shouldPlaceEdgeBlock) {
                        // Generate upward spike
                        BlockPos upwardPos = centerPos.offset(xOffset, heightIndex, zOffset);
                        BlockState existingBlockUp = level.getBlockState(upwardPos);

                        if (existingBlockUp.isAir() || existingBlockUp.is(UmberstoneBlocks.VOLCANIC_ROCK)) {
                            placePetrifiedWoodBlock(level, upwardPos, heightIndex);
                        }

                        // Generate downward spike (mirror of the upward spike)
                        if (heightIndex != 0 && radius > 0) {
                            BlockPos downwardPos = centerPos.offset(xOffset, -heightIndex, zOffset);
                            BlockState existingBlockDown = level.getBlockState(downwardPos);

                            if (existingBlockDown.isAir() || existingBlockDown.is(UmberstoneBlocks.VOLCANIC_ROCK)) {
                                placePetrifiedWoodBlock(level, downwardPos, heightIndex);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Places the appropriate petrified wood block based on height.
     * Different heights use different variants of petrified wood.
     */
    private void placePetrifiedWoodBlock(WorldGenLevel level, BlockPos pos, int height) {
        BlockState blockState;

        if (height <= 3) {
            blockState = UmberstoneBlocks.PETRIFIED_WOOD_ROCK.get().defaultBlockState();
        } else if (height <= 6) {
            blockState = UmberstoneBlocks.PETRIFIED_WOOD_ROCK_2.get().defaultBlockState();
        } else if (height <= 9) {
            blockState = UmberstoneBlocks.PETRIFIED_WOOD_ROCK_3.get().defaultBlockState();
        } else if (height <= 12) {
            blockState = UmberstoneBlocks.PETRIFIED_WOOD_ROCK_4.get().defaultBlockState();
        } else if (height <= 15) {
            blockState = UmberstoneBlocks.PETRIFIED_WOOD_ROCK_5.get().defaultBlockState();
        } else {
            blockState = UmberstoneBlocks.PETRIFIED_WOOD_ROCK_6.get().defaultBlockState();
        }

        setBlock(level, pos, blockState.setValue(BlockStateProperties.AXIS, Axis.Y));
    }

    /**
     * Generates the diamond ore base beneath the spike.
     */
    private void generateOreBase(WorldGenLevel level, BlockPos centerPos, int maxRadius, RandomSource random) {
        // Calculate base radius (smaller than the spike radius)
        int baseRadius = Math.min(Math.max(maxRadius - 1, 0), 1);

        for (int xOffset = -baseRadius; xOffset <= baseRadius; ++xOffset) {
            for (int zOffset = -baseRadius; zOffset <= baseRadius; ++zOffset) {
                BlockPos currentPos = centerPos.offset(xOffset, -1, zOffset);

                // Determine how many ore blocks to place in this column
                int oreColumnHeight = 50; // Default maximum

                // Corner positions get fewer ore blocks
                if (Math.abs(xOffset) == 1 && Math.abs(zOffset) == 1) {
                    oreColumnHeight = random.nextInt(5);
                }

                // Place ore column
                while (currentPos.getY() > 50) {
                    BlockState existingBlock = level.getBlockState(currentPos);

                    // Stop if we hit solid blocks (except certain replaceable blocks)
                    if (!existingBlock.isAir() &&
                            !existingBlock.is(Blocks.DIRT) &&
                            !existingBlock.is(Blocks.SNOW_BLOCK) &&
                            !existingBlock.is(Blocks.ICE) &&
                            !existingBlock.is(Blocks.PACKED_ICE)) {
                        break;
                    }

                    // Place a diamond ore block
                    setBlock(level, currentPos, OreBlocks.ORE_ENCRUSTED_DIAMOND.get().defaultBlockState());
                    currentPos = currentPos.below();
                    --oreColumnHeight;

                    // Add gaps in the ore column for natural appearance
                    if (oreColumnHeight <= 0) {
                        currentPos = currentPos.below(random.nextInt(5) + 1);
                        oreColumnHeight = random.nextInt(5);
                    }
                }
            }
        }
    }
}
