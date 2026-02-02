package erebus.world.feature.mushroom.config;

import erebus.registries.blocks.ModBlocks;
import erebus.world.util.FeatureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

public class SarcasticCzechMushroomFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final int[] offsetX = {0, -1, 0, 1};
    private final int[] offsetZ = {1, 0, -1, 0};
    private final FeatureUtils Utils = new FeatureUtils();
    private final Direction[][] DIRECTIONS = {
            new Direction[] {
                    Direction.EAST,
                    Direction.SOUTH
            },
            new Direction[] {
                    Direction.EAST,
                    Direction.NORTH
            },
            new Direction[] {
                    Direction.WEST,
                    Direction.SOUTH
            },
            new Direction[] {
                    Direction.WEST,
                    Direction.NORTH
            }
    };

    public SarcasticCzechMushroomFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin().west();
        RandomSource random = context.random();
        int height = 2 + random.nextInt(3);
        int armLength = 4 + random.nextInt(3);

        BlockState STEM = ModBlocks.SARCASTIC_CZECH_MUSHROOM_STEM.get().defaultBlockState()
                .setValue(HugeMushroomBlock.UP, true)
                .setValue(HugeMushroomBlock.DOWN, true);
        BlockState SHROOM = ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK.get().defaultBlockState();

        if(!level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)) return false;

        if(!Utils.checkAirCube(level, pos, pos.above(height)) || !Utils.checkAirCube(level, pos.offset(-armLength, height, -armLength), pos.offset(armLength, height + 1, armLength))) {
            return false;
        }

        Utils.setBlockPillar(level, pos, height, STEM);
        Utils.setBlockPillar(level, pos.east(), height, SHROOM);
        Utils.setBlockPillar(level, pos.south(), height, SHROOM);
        Utils.setBlockPillar(level, pos.south().east(), height, SHROOM);

        pos = pos.above(height);

        for(Direction[] dirs : DIRECTIONS) {
            int x = pos.getX() + dirs[0].getUnitVec3i().getX();
            int y = pos.getY();
            int z = pos.getZ() + dirs[0].getUnitVec3i().getZ();

            for(int c = 0; c < armLength; c++) {
                if(c % 2 == 0) {
                    y++;
                } else {
                    Direction direction = dirs[random.nextInt(dirs.length)];
                    x += direction.getUnitVec3i().getX();
                    z += direction.getUnitVec3i().getZ();
                }
                setBlock(level, new BlockPos(x, y, z), STEM);
            }

            setBlock(level, new BlockPos(x, y + 1, z), SHROOM);

            for(int c = -1; c <= 1; c++) {
                for(int d = -1; d <= 1; d++) {
                    setBlock(level, new BlockPos(x + c, y, z + d), SHROOM);
                }
            }
        }

        Utils.setBlockCube(level, pos.above(), pos.offset(1, 1, 1), SHROOM);
        Utils.setBlockCube(level, pos.offset(-1, 0, -1), pos.offset(2, 0, 2), SHROOM);

        return true;
    }
}
