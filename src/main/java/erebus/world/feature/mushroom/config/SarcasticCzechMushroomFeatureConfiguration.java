package erebus.world.feature.mushroom.config;

import erebus.registries.blocks.ModBlocks;
import erebus.world.util.FeatureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class SarcasticCzechMushroomFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final FeatureUtils Utils = new FeatureUtils();
    private final Direction[][] DIRECTIONS = {
            new Direction[]{
                    Direction.EAST,
                    Direction.SOUTH
            },
            new Direction[]{
                    Direction.EAST,
                    Direction.NORTH
            },
            new Direction[]{
                    Direction.WEST,
                    Direction.SOUTH
            },
            new Direction[]{
                    Direction.WEST,
                    Direction.NORTH
            }
    };

    public SarcasticCzechMushroomFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    private static void planCube(Map<BlockPos, BlockState> placement, BlockPos min, BlockPos max, BlockState state) {
        for (var target : BlockPos.betweenClosed(min, max)) placement.put(target.immutable(), state);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var pos = context.origin().west();
        var random = context.random();
        int height = 2 + random.nextInt(3);
        int armLength = 4 + random.nextInt(3);

        var STEM = ModBlocks.SARCASTIC_CZECH_MUSHROOM_STEM.get().defaultBlockState()
                .setValue(HugeMushroomBlock.UP, true)
                .setValue(HugeMushroomBlock.DOWN, true);
        var SHROOM = ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK.get().defaultBlockState();

        if (!level.getBlockState(context.origin().below()).is(Blocks.GRASS_BLOCK)
                && !level.getBlockState(context.origin().below()).is(Blocks.MYCELIUM)) return false;

        if (!Utils.checkAirCube(level, pos, pos.above(height)) || !Utils.checkAirCube(level, pos.offset(-armLength, height, -armLength), pos.offset(armLength, height + 1, armLength))) {
            return false;
        }

        Map<BlockPos, BlockState> placement = new LinkedHashMap<>();
        planCube(placement, pos, pos.offset(1, height, 1), STEM);

        pos = pos.above(height);

        for (Direction[] dirs : DIRECTIONS) {
            int x = pos.getX() + dirs[0].getUnitVec3i().getX();
            int y = pos.getY();
            int z = pos.getZ() + dirs[0].getUnitVec3i().getZ();

            for (int c = 0; c < armLength; c++) {
                if (c % 2 == 0) {
                    y++;
                } else {
                    Direction direction = dirs[random.nextInt(dirs.length)];
                    x += direction.getUnitVec3i().getX();
                    z += direction.getUnitVec3i().getZ();
                }
                placement.put(new BlockPos(x, y, z), STEM);
            }

            placement.put(new BlockPos(x, y + 1, z), SHROOM);

            for (int c = -1; c <= 1; c++) {
                for (int d = -1; d <= 1; d++) {
                    placement.put(new BlockPos(x + c, y, z + d), SHROOM);
                }
            }
        }

        planCube(placement, pos.above(), pos.offset(1, 1, 1), SHROOM);
        planCube(placement, pos.offset(-1, 0, -1), pos.offset(2, 0, 2), SHROOM);

        for (var target : placement.keySet()) {
            if (level.isOutsideBuildHeight(target) || !level.isEmptyBlock(target)) return false;
        }
        placement.forEach((target, state) -> setBlock(level, target, state));

        return true;
    }
}
