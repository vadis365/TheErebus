package erebus.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public class BlockPropUtils {

    public static Boolean never(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }
    public static Boolean always(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }
}
