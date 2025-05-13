package erebus.world.feature.misc.config;

import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.PlantBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

/**
 * Feature that generates ponds with water, sand/mud edges, and vegetation.
 * The ponds are created using overlapping ellipsoids to create natural-looking shapes.
 * Decorative elements like lily pads, sugar cane, and bullrush plants are added around the pond.
 */
public class PondFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    /**
     * Controls the overall size of the pond
     */
    private final double size;

    public PondFeatureConfiguration(double size) {
        super(NoneFeatureConfiguration.CODEC);
        this.size = size;
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos originPos = context.origin();
        RandomSource random = context.random();

        // Don't generate ponds too close to the bottom of the world
        if (originPos.getY() <= 4) return false;

        // Adjust position downward to have space for the pond
        BlockPos basePos = originPos.below(4);
        // 16x8x16 area for pond generation (2048 blocks total)
        boolean[] waterBlockPositions = new boolean[2048];
        int clayPatchSize = random.nextInt(4) + 4;

        // Generate multiple overlapping ellipsoids to create natural-looking pond shapes
        for (int ellipsoidCount = 0, totalEllipsoids = random.nextInt(3) + 5; ellipsoidCount < totalEllipsoids; ellipsoidCount++) {
            // Calculate ellipsoid dimensions (width, height, depth)
            double ellipsoidWidth = (random.nextDouble() * 6.0D + 3.0D) * size * (0.4D + random.nextDouble() * 0.6D);
            double ellipsoidHeight = (random.nextDouble() * 4.0D + 2.0D) * size / 2.5D;
            double ellipsoidDepth = (random.nextDouble() * 6.0D + 3.0D) * size * (0.4D + random.nextDouble() * 0.6D);

            // Calculate ellipsoid center position
            double centerX = random.nextDouble() * (16.0D - ellipsoidWidth - 2.0D) + 1.0D + ellipsoidWidth / 2.0D;
            double centerY = random.nextDouble() * (8.0D - ellipsoidHeight - 4.0D) + 2.0D + ellipsoidHeight / 2.0D;
            double centerZ = random.nextDouble() * (16.0D - ellipsoidDepth - 2.0D) + 1.0D + ellipsoidDepth / 2.0D;

            // Mark blocks inside the ellipsoid for water placement
            for (int x = 1; x < 15; x++) {
                for (int z = 1; z < 15; z++) {
                    for (int y = 1; y < 7; y++) {
                        // Calculate normalized distance from current point to ellipsoid center
                        double normalizedX = (x - centerX) / (ellipsoidWidth / 2.0D);
                        double normalizedY = (y - centerY) / (ellipsoidHeight / 2.0D);
                        double normalizedZ = (z - centerZ) / (ellipsoidDepth / 2.0D);

                        // Calculate squared distance (ellipsoid equation)
                        double squaredDistance = normalizedX * normalizedX + normalizedY * normalizedY + normalizedZ * normalizedZ;

                        // If point is inside the ellipsoid, mark for water placement
                        if (squaredDistance < 1.0D) {
                            waterBlockPositions[(x * 16 + z) * 8 + y] = true;
                        }
                    }
                }
            }
        }

        // Validate pond location - check if it would intersect with existing water or non-solid blocks
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 8; y++) {
                    // Check if this block is at the edge of the water area (adjacent to water but not water itself)
                    boolean isEdgeBlock = !waterBlockPositions[(x * 16 + z) * 8 + y] && (
                            (x < 15 && waterBlockPositions[((x + 1) * 16 + z) * 8 + y]) ||
                                    (x > 0 && waterBlockPositions[((x - 1) * 16 + z) * 8 + y]) ||
                                    (z < 15 && waterBlockPositions[(x * 16 + z + 1) * 8 + y]) ||
                                    (z > 0 && waterBlockPositions[(x * 16 + z - 1) * 8 + y]) ||
                                    (y < 7 && waterBlockPositions[(x * 16 + z) * 8 + y + 1]) ||
                                    (y > 0 && waterBlockPositions[(x * 16 + z) * 8 + y - 1])
                    );
                    if (!isEdgeBlock) continue;

                    // Check if the location is valid for pond generation
                    BlockState state = level.getBlockState(basePos.offset(x, y, z));
                    if (y >= 4 && !state.getFluidState().isEmpty() || y < 4 && !state.isSolid() && state.is(Blocks.WATER)) {
                        return false;
                    }
                }
            }
        }

        // Place water blocks in the pond
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 8; y++) {
                    if (waterBlockPositions[(x * 16 + z) * 8 + y]) {
                        BlockPos waterPos = basePos.offset(x, y, z);
                        // Place water below Y=4, air above (creates proper water surface)
                        level.setBlock(waterPos, waterPos.getY() >= 4 ? Blocks.AIR.defaultBlockState() : Blocks.WATER.defaultBlockState(), 3);
                    }
                }
            }
        }

        // Convert dirt under water to grass or mycelium based on biome
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 8; y++) {
                    if (waterBlockPositions[(x * 16 + z) * 8 + y]) {
                        BlockPos belowWaterPos = basePos.offset(x, y, z).below();
                        BlockState state = level.getBlockState(belowWaterPos);
                        if (state.is(Blocks.DIRT)) {
                            // In Fungal Forest biome, use mycelium instead of grass
                            if (level.getBiome(belowWaterPos).is(ModBiomes.FUNGAL_FOREST.getResourceKey())) {
                                level.setBlock(belowWaterPos, Blocks.MYCELIUM.defaultBlockState(), 2);
                            } else {
                                level.setBlock(belowWaterPos, Blocks.GRASS_BLOCK.defaultBlockState(), 2);
                            }
                        }
                    }
                }
            }
        }

        // Check biome type for edge block selection (mud or sand)
        boolean isJungle = level.getBiome(basePos).is(ModBiomes.UNDERGROUND_JUNGLE.getResourceKey());
        boolean isSwamp = level.getBiome(basePos).is(ModBiomes.SUBMERGED_SWAMP.getResourceKey());

        // Create sand/mud edges around the pond
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 8; y++) {
                    // Check if this block is at the edge of the water area
                    boolean isEdgeBlock = !waterBlockPositions[(x * 16 + z) * 8 + y] && (
                            (x < 15 && waterBlockPositions[((x + 1) * 16 + z) * 8 + y]) ||
                                    (x > 0 && waterBlockPositions[((x - 1) * 16 + z) * 8 + y]) ||
                                    (z < 15 && waterBlockPositions[(x * 16 + z + 1) * 8 + y]) ||
                                    (z > 0 && waterBlockPositions[(x * 16 + z - 1) * 8 + y]) ||
                                    (y < 7 && waterBlockPositions[(x * 16 + z) * 8 + y + 1]) ||
                                    (y > 0 && waterBlockPositions[(x * 16 + z) * 8 + y - 1])
                    );
                    if (!isEdgeBlock) continue;

                    // Place mud in jungle/swamp biomes, sand in others
                    if (y < 4 || random.nextBoolean() && level.getBlockState(basePos.offset(x, y, z)).isSolid()) {
                        level.setBlock(basePos.offset(x, y, z),
                                isJungle || isSwamp ? OtherBlocks.MUD.get().defaultBlockState() : Blocks.SAND.defaultBlockState(), 2);
                    }
                }
            }
        }

        // 50% chance to add clay deposits in the sand
        if (random.nextBoolean()) {
            for (int clayCount = 0, clayX, clayZ; clayCount < 2 + random.nextInt(6); clayCount++) {
                clayX = basePos.getX() + random.nextInt(8) + 4;
                clayZ = basePos.getZ() + random.nextInt(8) + 4;

                // Find a sand block to replace with clay
                for (int y = 0; y < 8; y++) {
                    BlockPos clayPos = new BlockPos(clayX, basePos.getY() + y, clayZ);
                    if (level.getBlockState(clayPos).is(Blocks.SAND)) {
                        // Calculate clay deposit size
                        double clayRadius = random.nextDouble() * 1.3D + 1.8D;
                        int clayRange = (int) Math.ceil(clayPatchSize);

                        // Replace sand with clay in a spherical pattern
                        for (int px = clayX - clayRange; px <= clayX + clayRange; px++) {
                            for (int pz = clayZ - clayRange; pz <= clayZ + clayRange; pz++) {
                                for (int py = basePos.getY() + y - clayRange; py <= basePos.getY() + y + clayRange; py++) {
                                    BlockPos checkPos = new BlockPos(px, py, pz);
                                    // Check if block is sand and within the clay sphere radius
                                    if (level.getBlockState(checkPos).is(Blocks.SAND) &&
                                            Math.sqrt(Math.pow(px - clayX, 2) + Math.pow(py - basePos.getY() + y, 2) + Math.pow(pz - clayZ, 2)) <=
                                                    clayRadius + random.nextFloat() * 0.3F) {
                                        level.setBlock(checkPos, Blocks.CLAY.defaultBlockState(), 2);
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }

        // Add lily pads on water surface
        for (int lilyCount = 0; lilyCount < 5; lilyCount++) {
            // Random position near the center of the pond
            BlockPos lilyPadPos = new BlockPos(
                    basePos.getX() + random.nextInt(8) - random.nextInt(8) + 8,
                    basePos.getY() + 2 + random.nextInt(6),
                    basePos.getZ() + random.nextInt(8) - random.nextInt(8) + 8
            );
            // Place lily pad if there's water below
            if (level.getBlockState(lilyPadPos.below()).is(Blocks.WATER)) {
                level.setBlock(lilyPadPos, Blocks.LILY_PAD.defaultBlockState(), 2);
            }
        }

        // Add sugar cane around the pond edges
        for (int caneCount = 0; caneCount < 30; caneCount++) {
            int x = basePos.getX() + random.nextInt(16);
            int y = basePos.getY() + 3 + random.nextInt(5);
            int z = basePos.getZ() + random.nextInt(16);
            BlockPos groundPos = new BlockPos(x, y, z).below();
            BlockState state = level.getBlockState(groundPos);

            // Sugar cane can grow on grass, sand, or mud
            if (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.SAND) || state.is(OtherBlocks.MUD.get())) {
                // Create a sugar cane plant of random height
                for (int height = 0; height < 1 + random.nextInt(7); height++) {
                    BlockPos canePos = new BlockPos(x, y + height, z);
                    if (level.getBlockState(canePos).isAir()) {
                        level.setBlock(canePos, Blocks.SUGAR_CANE.defaultBlockState(), 2);
                    } else {
                        break;
                    }
                }
            }
        }

        // Add bullrush plants around the pond edges
        for (int rushCount = 0; rushCount < 50; rushCount++) {
            int x = basePos.getX() + random.nextInt(16);
            int y = basePos.getY() + 3 + random.nextInt(5);
            int z = basePos.getZ() + random.nextInt(16);
            BlockPos groundPos = new BlockPos(x, y, z).below();
            BlockState state = level.getBlockState(groundPos);

            // Bullrush can only grow on sand or mud
            if (state.is(Blocks.SAND) || state.is(OtherBlocks.MUD.get())) {
                for (int height = 0; height < 1; height++) {
                    BlockPos rushPos = new BlockPos(x, y + height, z);
                    if (level.getBlockState(rushPos).isAir()) {
                        level.setBlock(new BlockPos(x, y, z), PlantBlocks.BULLRUSH.get().defaultBlockState(), 2);
                    } else {
                        break;
                    }
                }
            }
        }

        return true;
    }
}