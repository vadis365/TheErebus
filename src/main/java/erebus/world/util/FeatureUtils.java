package erebus.world.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class FeatureUtils {

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

    public static BlockState transferAllStateKeys(BlockState stateIn, Block blockOut) {
        return transferAllStateKeys(stateIn, blockOut.defaultBlockState());
    }

    public static BlockState transferAllStateKeys(BlockState stateIn, BlockState stateOut) {
        for (Property<?> property : stateOut.getProperties()) {
            stateOut = transferStateKey(stateIn, stateOut, property);
        }
        return stateOut;
    }

    public static <T extends Comparable<T>> BlockState transferStateKey(BlockState stateIn, BlockState stateOut, Property<T> property) {
        if (!stateIn.hasProperty(property) || !stateOut.hasProperty(property)) return stateOut;
        return stateOut.setValue(property, stateIn.getValue(property));
    }
}
