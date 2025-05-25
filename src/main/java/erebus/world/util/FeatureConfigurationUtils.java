package erebus.world.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;

public class FeatureConfigurationUtils {

    public final boolean checkAirCube(WorldGenLevel level, BlockPos originalPos, BlockPos offset) {
        for (int y = originalPos.getY(); y <= offset.getY(); y++)
            for (int x = originalPos.getX(); x <= offset.getX(); x++)
                for (int z = originalPos.getZ(); z <= offset.getZ(); z++) {
                    if (!level.isEmptyBlock(new BlockPos(x, y, z)))
                        return false;
                }

        return true;
    }

    public final void setBlockPillar(WorldGenLevel level, BlockPos pos, int size, BlockState block) {
        for(int c = 0; c < size; c++)
            level.setBlock(pos.above(c), block, 2);
    }

    public final void setBlockCube(WorldGenLevel level, BlockPos pos, BlockPos corner, BlockState state) {
        for (int y = pos.getY(); y <= corner.getY(); y++)
            for (int x = pos.getX(); x <= corner.getX(); x++)
                for (int z = pos.getZ(); z <= corner.getZ(); z++)
                    level.setBlock(new BlockPos(x, y, z), state, 2);
    }
}
