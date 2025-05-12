package erebus.world.feature.misc.config;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * A feature that generates lakes with special edge blocks.
 * This feature creates ellipsoidal lakes with customizable fluid blocks and edge blocks.
 * It handles both water and lava lakes with appropriate edge treatments.
 */
public class LakeWithEdgeFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final Block lakeFluidBlock;
    private final Supplier<? extends Block> lakeBorderBlock;

    /**
     * Creates a new lake feature with customizable fluid and edge blocks.
     *
     * @param lakeFluidBlock  The block to use for the lake's fluid (water or lava)
     * @param lakeBorderBlock The block to use for the lake's edge/border
     */
    public LakeWithEdgeFeatureConfiguration(Block lakeFluidBlock, Supplier<? extends Block> lakeBorderBlock) {
        super(NoneFeatureConfiguration.CODEC);
        this.lakeFluidBlock = lakeFluidBlock;
        this.lakeBorderBlock = lakeBorderBlock;
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        // Don't generate lakes too close to the bottom of the world
        if (pos.getY() <= 4) return false;

        // Adjust position downward to create the lake below the surface
        pos = pos.below(4);

        // This array tracks which positions will be part of the lake (true = lake block)
        // Size is 16x16x8 = 2048 blocks (the maximum size of our lake feature)
        boolean[] isLakeBlock = new boolean[2048];

        // Generate multiple overlapping ellipsoids to create an interesting lake shape
        int ellipsoidCount = random.nextInt(4) + 4;

        for (int c = 0; c < ellipsoidCount; c++) {
            // Define ellipsoid dimensions (width, height, depth)
            double ellipsoidWidth = random.nextDouble() * 6.0D + 3.0D;
            double ellipsoidHeight = random.nextDouble() * 4.0D + 2.0D;
            double ellipsoidDepth = random.nextDouble() * 6.0D + 3.0D;

            // Define ellipsoid center position
            double centerX = random.nextDouble() * (16.0D - ellipsoidWidth - 2.0D) + 1.0D + ellipsoidWidth / 2.0D;
            double centerY = random.nextDouble() * (8.0D - ellipsoidHeight - 4.0D) + 2.0D + ellipsoidHeight / 2.0D;
            double centerZ = random.nextDouble() * (16.0D - ellipsoidDepth - 2.0D) + 1.0D + ellipsoidDepth / 2.0D;

            // Iterate through a 14x6x14 area to define the ellipsoid
            for (int x = 1; x < 15; x++) {
                for (int z = 1; z < 15; z++) {
                    for (int y = 1; y < 7; y++) {
                        // Calculate normalized distance from current point to ellipsoid center
                        double xDistance = ((double) x - centerX) / (ellipsoidWidth / 2.0D);
                        double yDistance = ((double) y - centerY) / (ellipsoidHeight / 2.0D);
                        double zDistance = ((double) z - centerZ) / (ellipsoidDepth / 2.0D);

                        // Calculate squared distance (for ellipsoid equation)
                        double distanceSquared = xDistance * xDistance + yDistance * yDistance + zDistance * zDistance;

                        // If point is inside the ellipsoid, mark it as part of the lake
                        if (distanceSquared < 1.0D) {
                            isLakeBlock[(x * 16 + z) * 8 + y] = true;
                        }
                    }
                }
            }
        }

        // First pass: validate lake placement by checking surrounding blocks
        // We don't want lakes to generate in invalid locations (e.g., exposing air to lava)
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 8; y++) {
                    // Check if this position is not a lake block but is adjacent to one
                    boolean isNotLakeBlock = !isLakeBlock[(x * 16 + z) * 8 + y];
                    boolean hasAdjacentLakeBlock =
                            (x < 15 && isLakeBlock[((x + 1) * 16 + z) * 8 + y]) || // Check block to the east
                                    (x > 0 && isLakeBlock[((x - 1) * 16 + z) * 8 + y]) ||  // Check block to the west
                                    (z < 15 && isLakeBlock[(x * 16 + z + 1) * 8 + y]) ||   // Check block to the south
                                    (z > 0 && isLakeBlock[(x * 16 + (z - 1)) * 8 + y]) ||  // Check block to the north
                                    (y < 7 && isLakeBlock[(x * 16 + z) * 8 + y + 1]) ||    // Check block above
                                    (y > 0 && isLakeBlock[(x * 16 + z) * 8 + (y - 1)]);    // Check block below

                    if (isNotLakeBlock && hasAdjacentLakeBlock) {
                        BlockState state = level.getBlockState(pos.offset(x, y, z));
                        if (y >= 4 && state.getFluidState().isEmpty()) {
                            return false;
                        }

                        if (y < 4 && !state.getBlock().defaultBlockState().isSolid() && !state.is(lakeFluidBlock)) {
                            return false;
                        }
                    }
                }
            }
        }

        // Second pass: place lake blocks (fluid below water level, air above)
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 8; y++) {
                    if (isLakeBlock[(x * 16 + z) * 8 + y]) {
                        level.setBlock(pos.offset(x, y, z), y >= 4 ? Blocks.AIR.defaultBlockState() : lakeFluidBlock.defaultBlockState(), 2);
                    }
                }
            }
        }

        // Third pass: handle special blocks under the lake (convert dirt to appropriate biome surface)
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 8; y++) {
                    if (isLakeBlock[(x * 16 + z) * 8 + y]) {
                        BlockPos pos2 = pos.offset(x, y - 1, z);

                        if (level.getBlockState(pos2).is(Blocks.DIRT) && level.getBlockState(pos2).getLightEmission(level, pos2) > 0) {
                            // Use mycelium in fungal forest biome, grass block elsewhere
                            if (level.getBiome(pos2).is(ModBiomes.FUNGAL_FOREST.getResourceKey())) {
                                level.setBlock(pos2, Blocks.MYCELIUM.defaultBlockState(), 2);
                            } else {
                                level.setBlock(pos2, Blocks.GRASS_BLOCK.defaultBlockState(), 2);
                            }
                        }
                    }
                }
            }
        }

        // Fourth pass: handle special edge blocks for lava lakes
        if (lakeFluidBlock.defaultBlockState().getFluidState().is(Fluids.LAVA)) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = 0; y < 8; y++) {
                        // Check if this block is adjacent to a lava block but not a lava block itself
                        boolean isLakeBorderPosition = !isLakeBlock[(x * 16 + z) * 8 + y] && (
                                (x < 15 && isLakeBlock[((x + 1) * 16 + z) * 8 + y]) ||     // Check block to the east
                                        (x > 0 && isLakeBlock[((x - 1) * 16 + z) * 8 + y]) ||      // Check block to the west
                                        (z < 15 && isLakeBlock[(x * 16 + z + 1) * 8 + y]) ||       // Check block to the south
                                        (z > 0 && isLakeBlock[(x * 16 + (z - 1)) * 8 + y]) ||      // Check block to the north
                                        (y < 7 && isLakeBlock[(x * 16 + z) * 8 + y + 1]) ||        // Check block above
                                        (y > 0 && isLakeBlock[(x * 16 + z) * 8 + (y - 1)])         // Check block below
                        );

                        // Place border blocks around lava lakes (with some randomness for upper layers)
                        // Only replace solid blocks to avoid replacing other fluids or air
                        if (isLakeBorderPosition &&
                                (y < 4 || random.nextInt(2) != 0) &&
                                level.getBlockState(pos.offset(x, y, z)).getBlock().defaultBlockState().isSolid()) {
                            level.setBlock(pos.offset(x, y, z), lakeBorderBlock.get().defaultBlockState(), 2);
                        }
                    }
                }
            }
        }

        // Fifth pass: handle ice formation on water lakes in cold biomes
        if (lakeFluidBlock.defaultBlockState().getFluidState().is(Fluids.WATER)) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    // Check only at water level (y=4)
                    int waterSurfaceY = 4;
                    BlockPos surfacePos = pos.offset(x, waterSurfaceY, z);

                    // If the biome is cold enough to freeze water, place ice blocks on the surface
                    if (level.getBiome(surfacePos).value().shouldFreeze(level, surfacePos, false)) {
                        level.setBlock(surfacePos, Blocks.ICE.defaultBlockState(), 2);
                    }
                }
            }
        }

        // Lake generation successful
        return true;
    }
}
