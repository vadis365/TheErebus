package erebus.world.feature.mushroom.config;

import erebus.registries.blocks.ModBlocks;
import erebus.utils.MathUtil;
import erebus.world.util.FeatureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class KaizersFingersMushroomFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final int[] offsetX = {0, -1, 0, 1};
    private final int[] offsetZ = {1, 0, -1, 0};
    private final FeatureUtils Utils = new FeatureUtils();

    public KaizersFingersMushroomFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        int height = 4 + random.nextInt(4);

        BlockState STEM = ModBlocks.KAIZERS_FINGERS_MUSHROOM_STEM.get().defaultBlockState()
                .setValue(HugeMushroomBlock.UP, true)
                .setValue(HugeMushroomBlock.DOWN, true);
        BlockState SHROOM = ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK.get().defaultBlockState();

        if(!level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)) return false;

        if(!Utils.checkAirCube(level, pos.offset(-1, 3, -1), pos.offset(1, height + 1, 1))) return false;
        if(!Utils.checkAirCube(level, pos.offset(-4, 0, -4), pos.offset(4, 2, 4))) return false;
        if(!Utils.checkAirCube(level, pos.offset(-4, -1, -4), pos.offset(4, -1, 4))) return false;

        for(int y = 0, sidesPlaced = 0; y <= height; y++) {
            setBlock(level, pos.above(y), STEM);

            if(y >= 2 && y < height - 1 && random.nextInt(4 + sidesPlaced * 2) == 0) {
                int dir = random.nextInt(4);
                setBlock(level, pos.offset(offsetX[dir], y, offsetZ[dir]), SHROOM);
                ++sidesPlaced;
            }
        }

        setBlock(level, pos.above(height + 1), SHROOM);
        for(int c = 0; c < 4; c++) setBlock(level, pos.offset(offsetX[c], height, offsetZ[c]), SHROOM);
        List<BlockPos> connectList = new ArrayList<>();
        connectList.add(pos.below());
        int x, z;

        for(int smallShroomAttempt = 0; smallShroomAttempt < 4 + random.nextInt(7); smallShroomAttempt++) {
            x = random.nextInt(4) - random.nextInt(4);
            z = random.nextInt(4) - random.nextInt(4);

            if(!level.isEmptyBlock(pos.offset(x, 0, z)) || !level.isEmptyBlock(pos.offset(x - 1, 0, z)) || !level.isEmptyBlock(pos.offset(x + 1, 0, z)) || !level.isEmptyBlock(pos.offset(x, 0, z + 1)) || !level.isEmptyBlock(pos.offset(x + 1, 0, z - 1)))
                continue;

            int smallShroomHeight = random.nextBoolean() ? 1 : 1 + random.nextInt(2);
            Utils.setBlockPillar(level, pos.offset(x, 0, z), smallShroomHeight - 1, STEM);
            setBlock(level, pos.offset(x, smallShroomHeight, z), SHROOM);
            connectList.add(pos.offset(x, -1, z));
        }

        int coordAmount = connectList.size();
        int dir;

        if(coordAmount != 0) {
            for (int connectionAttempt = 0; connectionAttempt < 48; connectionAttempt++) {
                BlockPos coords1 = connectList.get(random.nextInt(coordAmount));
                BlockPos coords2 = random.nextInt(3) != 0 ? connectList.getFirst() : connectList.get(random.nextInt(coordAmount));

                if (coords1.equals(coords2)) continue;

                double dist = MathUtil.distance(coords1.getX() - coords2.getX(), coords1.getZ() - coords2.getZ());
                if (dist < 1.0D) continue;

                dir = random.nextInt(4);
                x = coords1.getX() + offsetX[dir];
                z = coords1.getZ() + offsetZ[dir];

                if (MathUtil.distance(x - coords2.getX(), z - coords2.getZ()) < dist) {
                    setBlock(level, new BlockPos(x, pos.getY() - 1, z), STEM);
                    if (random.nextInt(16) == 0) setBlock(level, new BlockPos(x, pos.getY(), z), STEM);
                    connectList.remove(coords1);
                    coords1 = coords1.offset(offsetX[dir], 0, offsetZ[dir]);
                    connectList.add(coords1);
                }
            }
        }

        return true;
    }
}
