package erebus.world.feature.misc.config;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class PondFeatureConfiguration extends Feature<NoneFeatureConfiguration> {

    public PondFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var originPos = context.origin();
        var random = context.random();

        double size = level.getBiome(originPos).is(ModBiomes.SUBTERRANEAN_SAVANNAH.getResourceKey())
                ? (random.nextDouble() + 0.75D) * 1.2D
                : (random.nextDouble() + 0.7D) * 1.5D;

        if (level.isOutsideBuildHeight(originPos.below(5))) return false;

        var basePos = originPos.below(4);
        boolean[] waterBlockPositions = new boolean[2048];
        int clayPatchSize = random.nextInt(4) + 4;

        for (int ellipsoidCount = 0, totalEllipsoids = random.nextInt(3) + 5; ellipsoidCount < totalEllipsoids; ellipsoidCount++) {
            double ellipsoidWidth = (random.nextDouble() * 6.0D + 3.0D) * size * (0.4D + random.nextDouble() * 0.6D);
            double ellipsoidHeight = (random.nextDouble() * 4.0D + 2.0D) * size / 2.5D;
            double ellipsoidDepth = (random.nextDouble() * 6.0D + 3.0D) * size * (0.4D + random.nextDouble() * 0.6D);

            double centerX = random.nextDouble() * (16.0D - ellipsoidWidth - 2.0D) + 1.0D + ellipsoidWidth / 2.0D;
            double centerY = random.nextDouble() * (8.0D - ellipsoidHeight - 4.0D) + 2.0D + ellipsoidHeight / 2.0D;
            double centerZ = random.nextDouble() * (16.0D - ellipsoidDepth - 2.0D) + 1.0D + ellipsoidDepth / 2.0D;

            for (int x = 1; x < 15; x++) {
                for (int z = 1; z < 15; z++) {
                    for (int y = 1; y < 7; y++) {
                        double normalizedX = (x - centerX) / (ellipsoidWidth / 2.0D);
                        double normalizedY = (y - centerY) / (ellipsoidHeight / 2.0D);
                        double normalizedZ = (z - centerZ) / (ellipsoidDepth / 2.0D);

                        double squaredDistance = normalizedX * normalizedX + normalizedY * normalizedY + normalizedZ * normalizedZ;

                        if (squaredDistance < 1.0D) {
                            waterBlockPositions[(x * 16 + z) * 8 + y] = true;
                        }
                    }
                }
            }
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 8; y++) {
                    boolean isEdgeBlock = !waterBlockPositions[(x * 16 + z) * 8 + y] && (
                            (x < 15 && waterBlockPositions[((x + 1) * 16 + z) * 8 + y]) ||
                                    (x > 0 && waterBlockPositions[((x - 1) * 16 + z) * 8 + y]) ||
                                    (z < 15 && waterBlockPositions[(x * 16 + z + 1) * 8 + y]) ||
                                    (z > 0 && waterBlockPositions[(x * 16 + z - 1) * 8 + y]) ||
                                    (y < 7 && waterBlockPositions[(x * 16 + z) * 8 + y + 1]) ||
                                    (y > 0 && waterBlockPositions[(x * 16 + z) * 8 + y - 1])
                    );
                    if (!isEdgeBlock) continue;

                    BlockState state = level.getBlockState(basePos.offset(x, y, z));
                    if (y >= 4 && !state.getFluidState().isEmpty() || y < 4 && !state.isSolid() && !state.is(Blocks.WATER)) {
                        return false;
                    }
                }
            }
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 8; y++) {
                    if (waterBlockPositions[(x * 16 + z) * 8 + y]) {
                        BlockPos waterPos = basePos.offset(x, y, z);
                        level.setBlock(waterPos, y >= 4 ? Blocks.AIR.defaultBlockState() : Blocks.WATER.defaultBlockState(), 3);
                    }
                }
            }
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 8; y++) {
                    if (waterBlockPositions[(x * 16 + z) * 8 + y]) {
                        BlockPos belowWaterPos = basePos.offset(x, y, z).below();
                        BlockState state = level.getBlockState(belowWaterPos);
                        if (state.is(Blocks.DIRT)) {
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

        boolean isJungle = level.getBiome(basePos).is(ModBiomes.UNDERGROUND_JUNGLE.getResourceKey());
        boolean isSwamp = level.getBiome(basePos).is(ModBiomes.SUBMERGED_SWAMP.getResourceKey());

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 8; y++) {
                    boolean isEdgeBlock = !waterBlockPositions[(x * 16 + z) * 8 + y] && (
                            (x < 15 && waterBlockPositions[((x + 1) * 16 + z) * 8 + y]) ||
                                    (x > 0 && waterBlockPositions[((x - 1) * 16 + z) * 8 + y]) ||
                                    (z < 15 && waterBlockPositions[(x * 16 + z + 1) * 8 + y]) ||
                                    (z > 0 && waterBlockPositions[(x * 16 + z - 1) * 8 + y]) ||
                                    (y < 7 && waterBlockPositions[(x * 16 + z) * 8 + y + 1]) ||
                                    (y > 0 && waterBlockPositions[(x * 16 + z) * 8 + y - 1])
                    );
                    if (!isEdgeBlock) continue;

                    if ((y < 4 || random.nextBoolean()) && level.getBlockState(basePos.offset(x, y, z)).isSolid()) {
                        level.setBlock(basePos.offset(x, y, z), isJungle || isSwamp ? ModBlocks.MUD.get().defaultBlockState() : Blocks.SAND.defaultBlockState(), 2);
                    }
                }
            }
        }

        if (random.nextBoolean()) {
            for (int clayCount = 0, clayX, clayZ; clayCount < 2 + random.nextInt(6); clayCount++) {
                clayX = basePos.getX() + random.nextInt(8) + 4;
                clayZ = basePos.getZ() + random.nextInt(8) + 4;

                for (int y = 0; y < 8; y++) {
                    var clayPos = new BlockPos(clayX, basePos.getY() + y, clayZ);
                    if (level.getBlockState(clayPos).is(Blocks.SAND)) {
                        double clayRadius = random.nextDouble() * 1.3D + 1.8D;
                        int clayRange = (int) Math.ceil(clayPatchSize);

                        for (int px = clayX - clayRange; px <= clayX + clayRange; px++) {
                            for (int pz = clayZ - clayRange; pz <= clayZ + clayRange; pz++) {
                                for (int py = basePos.getY() + y - clayRange; py <= basePos.getY() + y + clayRange; py++) {
                                    var checkPos = new BlockPos(px, py, pz);
                                    if (level.getBlockState(checkPos).is(Blocks.SAND) && Math.sqrt(Math.pow(px - clayX, 2) + Math.pow(py - basePos.getY() + y, 2) + Math.pow(pz - clayZ, 2)) <= clayRadius + random.nextFloat() * 0.3F) {
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

        for (int lilyCount = 0; lilyCount < 5; lilyCount++) {
            var lilyPadPos = new BlockPos(
                    basePos.getX() + random.nextInt(8) - random.nextInt(8) + 8,
                    basePos.getY() + 2 + random.nextInt(6),
                    basePos.getZ() + random.nextInt(8) - random.nextInt(8) + 8
            );

            if (level.isEmptyBlock(lilyPadPos) && Blocks.LILY_PAD.defaultBlockState().canSurvive(level, lilyPadPos)) {
                level.setBlock(lilyPadPos, Blocks.LILY_PAD.defaultBlockState(), 2);
            }
        }

        for (int caneCount = 0; caneCount < 30; caneCount++) {
            int x = basePos.getX() + random.nextInt(16);
            int y = basePos.getY() + 3 + random.nextInt(5);
            int z = basePos.getZ() + random.nextInt(16);
            var groundPos = new BlockPos(x, y, z).below();
            var state = level.getBlockState(groundPos);

            if (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.SAND) || state.is(ModBlocks.MUD.get())) {
                for (int height = 0; height < 1 + random.nextInt(7); height++) {
                    var canePos = new BlockPos(x, y + height, z);
                    if (level.getBlockState(canePos).isAir()) {
                        level.setBlock(canePos, Blocks.SUGAR_CANE.defaultBlockState(), 2);
                    } else {
                        break;
                    }
                }
            }
        }

        for (int rushCount = 0; rushCount < 50; rushCount++) {
            int x = basePos.getX() + random.nextInt(16);
            int y = basePos.getY() + 3 + random.nextInt(5);
            int z = basePos.getZ() + random.nextInt(16);
            var groundPos = new BlockPos(x, y, z).below();
            var state = level.getBlockState(groundPos);

            if (state.is(Blocks.SAND) || state.is(ModBlocks.MUD.get())) {
                for (int height = 0; height < 1; height++) {
                    var rushPos = new BlockPos(x, y + height, z);
                    if (level.getBlockState(rushPos).isAir()) {
                        level.setBlock(new BlockPos(x, y, z), ModBlocks.BULLRUSH.get().defaultBlockState(), 2);
                    } else {
                        break;
                    }
                }
            }
        }

        return true;
    }
}
