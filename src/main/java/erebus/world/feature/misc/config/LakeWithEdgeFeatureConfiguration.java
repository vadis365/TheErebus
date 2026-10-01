package erebus.world.feature.misc.config;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class LakeWithEdgeFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final Block lakeFluidBlock;
    private final Supplier<? extends Block> lakeBorderBlock;

    public LakeWithEdgeFeatureConfiguration(Block lakeFluidBlock, Supplier<? extends Block> lakeBorderBlock) {
        super(NoneFeatureConfiguration.CODEC);
        this.lakeFluidBlock = lakeFluidBlock;
        this.lakeBorderBlock = lakeBorderBlock;
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var pos = context.origin();
        var random = context.random();

        if (level.isOutsideBuildHeight(pos.below(5))) return false;

        pos = pos.below(4);
        boolean[] isLakeBlock = new boolean[2048];
        int ellipsoidCount = random.nextInt(4) + 4;

        for (int c = 0; c < ellipsoidCount; c++) {
            double ellipsoidWidth = random.nextDouble() * 6.0D + 3.0D;
            double ellipsoidHeight = random.nextDouble() * 4.0D + 2.0D;
            double ellipsoidDepth = random.nextDouble() * 6.0D + 3.0D;

            double centerX = random.nextDouble() * (16.0D - ellipsoidWidth - 2.0D) + 1.0D + ellipsoidWidth / 2.0D;
            double centerY = random.nextDouble() * (8.0D - ellipsoidHeight - 4.0D) + 2.0D + ellipsoidHeight / 2.0D;
            double centerZ = random.nextDouble() * (16.0D - ellipsoidDepth - 2.0D) + 1.0D + ellipsoidDepth / 2.0D;

            for (int x = 1; x < 15; x++) {
                for (int z = 1; z < 15; z++) {
                    for (int y = 1; y < 7; y++) {
                        double xDistance = ((double) x - centerX) / (ellipsoidWidth / 2.0D);
                        double yDistance = ((double) y - centerY) / (ellipsoidHeight / 2.0D);
                        double zDistance = ((double) z - centerZ) / (ellipsoidDepth / 2.0D);

                        double distanceSquared = xDistance * xDistance + yDistance * yDistance + zDistance * zDistance;

                        if (distanceSquared < 1.0D) {
                            isLakeBlock[(x * 16 + z) * 8 + y] = true;
                        }
                    }
                }
            }
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 8; y++) {
                    boolean isNotLakeBlock = !isLakeBlock[(x * 16 + z) * 8 + y];
                    boolean hasAdjacentLakeBlock =
                            (x < 15 && isLakeBlock[((x + 1) * 16 + z) * 8 + y]) ||
                                    (x > 0 && isLakeBlock[((x - 1) * 16 + z) * 8 + y]) ||
                                    (z < 15 && isLakeBlock[(x * 16 + z + 1) * 8 + y]) ||
                                    (z > 0 && isLakeBlock[(x * 16 + (z - 1)) * 8 + y]) ||
                                    (y < 7 && isLakeBlock[(x * 16 + z) * 8 + y + 1]) ||
                                    (y > 0 && isLakeBlock[(x * 16 + z) * 8 + (y - 1)]);

                    if (isNotLakeBlock && hasAdjacentLakeBlock) {
                        BlockState state = level.getBlockState(pos.offset(x, y, z));
                        if (y >= 4 && !state.getFluidState().isEmpty()) {
                            return false;
                        }

                        if (y < 4 && !state.isSolid() && !state.is(lakeFluidBlock)) {
                            return false;
                        }
                    }
                }
            }
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 8; y++) {
                    if (isLakeBlock[(x * 16 + z) * 8 + y]) {
                        level.setBlock(pos.offset(x, y, z), y >= 4 ? Blocks.AIR.defaultBlockState() : lakeFluidBlock.defaultBlockState(), 2);
                    }
                }
            }
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 8; y++) {
                    if (isLakeBlock[(x * 16 + z) * 8 + y]) {
                        BlockPos pos2 = pos.offset(x, y - 1, z);

                        if (level.getBlockState(pos2).is(Blocks.DIRT) && level.getBlockState(pos2).getLightEmission(level, pos2) > 0) {
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

        if (lakeFluidBlock.defaultBlockState().getFluidState().is(Fluids.LAVA)) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = 0; y < 8; y++) {
                        boolean isLakeBorderPosition = !isLakeBlock[(x * 16 + z) * 8 + y] && (
                                (x < 15 && isLakeBlock[((x + 1) * 16 + z) * 8 + y]) ||
                                        (x > 0 && isLakeBlock[((x - 1) * 16 + z) * 8 + y]) ||
                                        (z < 15 && isLakeBlock[(x * 16 + z + 1) * 8 + y]) ||
                                        (z > 0 && isLakeBlock[(x * 16 + (z - 1)) * 8 + y]) ||
                                        (y < 7 && isLakeBlock[(x * 16 + z) * 8 + y + 1]) ||
                                        (y > 0 && isLakeBlock[(x * 16 + z) * 8 + (y - 1)])
                        );

                        if (isLakeBorderPosition && (y < 4 || random.nextInt(2) != 0) && level.getBlockState(pos.offset(x, y, z)).getBlock().defaultBlockState().isSolid()) {
                            level.setBlock(pos.offset(x, y, z), lakeBorderBlock.get().defaultBlockState(), 2);
                        }
                    }
                }
            }
        }

        if (lakeFluidBlock.defaultBlockState().getFluidState().is(Fluids.WATER)) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    int waterSurfaceY = 4;
                    var surfacePos = pos.offset(x, waterSurfaceY, z);

                    if (level.getBiome(surfacePos).value().shouldFreeze(level, surfacePos, false)) {
                        level.setBlock(surfacePos, Blocks.ICE.defaultBlockState(), 2);
                    }
                }
            }
        }

        return true;
    }
}
