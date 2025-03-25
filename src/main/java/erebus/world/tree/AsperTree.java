package erebus.world.tree;

import com.mojang.serialization.Codec;
import erebus.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class AsperTree extends Feature<NoneFeatureConfiguration> {
    private final BlockState log = ModBlocks.LOG_ASPER.get().defaultBlockState();
    private final BlockState leaves = ModBlocks.LEAVES_ASPER.get().defaultBlockState();

    private static final int[] offsetX = new int[] { -1, 1, 0, 0 };
    private static final int[] offsetZ = new int[] { 0, 0, -1, 1 };

    public AsperTree(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        return generate(context.level(), context.origin(), context.random());
    }

    public boolean generate(LevelAccessor level, BlockPos pos, RandomSource random) {
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        int height = random.nextInt(2) + 4;

        if(canGenerate(level, x, y, z, height)) {
            for(int yy = 0; yy < height; yy++) {
                // Build the Trunk
                level.setBlock(new BlockPos(x, y + yy, z), log, 2);

                if(yy == height - 1) continue;

                // Add some Branches
                for(int extraWood = 0, extraWoodAttempt = 0; extraWoodAttempt < 5 && extraWood < 3; extraWoodAttempt++) {
                    int dir = random.nextInt(4);

                    if(yy > 0 && level.getBlockState(new BlockPos(x + offsetX[dir], y + yy - 1, z + offsetZ[dir])).isAir()) continue;

                    level.setBlock(new BlockPos(x + offsetX[dir], y + yy, z + offsetZ[dir]), log.setValue(BlockStateProperties.AXIS, dir < 2 ? Direction.Axis.X : Direction.Axis.Z), 3);

                    // Add leaves to the branches
                    if(yy > 0 && random.nextBoolean()) {
                        level.setBlock(new BlockPos(x + offsetX[dir] * 2, y + yy, z + offsetZ[dir] * 2), leaves, 2);
                    }

                    ++extraWood;
                }
            }

            double centerY = 2D + (height - 2D) / 2;

            // Decorate with leaves
            for(int yy = 1; yy < height; yy++) {
                for(int xx = -1; xx <= 1; xx++) {
                    for(int zz = -1; zz <= 1; zz++) {
                        if(xx == 0 && zz == 0) continue;

                        double dist = Math.sqrt(xx * xx + Math.pow(centerY - yy, 2) + zz * zz);

                        if((dist <= 1.5D || random.nextDouble() > dist - 1.5D) && level.getBlockState(new BlockPos(x + xx, y + yy, z + zz)).isAir()) {
                            level.setBlock(new BlockPos(x + xx, y + yy, z + zz), leaves, 2);
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean canGenerate(LevelAccessor level, int x, int y, int z, int height) {
        // We gotta be inside the world
        if(y <= -64 || y + height >= 320) return false;

        for(int testY = y + 1; testY <= y + height; ++testY) {
            for(int testX = x - 2; testX <= x + 2; ++testX) {
                for(int testZ = z - 2; testZ <= z + 2; ++testZ) {
                    if(!level.getBlockState(new BlockPos(testX, testY, testZ)).isAir()) return false;
                }
            }
        }

        return level.getBlockState(new BlockPos(x, y, z).below()) == Blocks.DIRT.defaultBlockState() || level.getBlockState(new BlockPos(x, y, z).below()) == Blocks.GRASS_BLOCK.defaultBlockState();
    }
}
