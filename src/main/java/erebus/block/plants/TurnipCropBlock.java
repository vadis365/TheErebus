package erebus.block.plants;

import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class TurnipCropBlock extends ModCropBlock {
    public TurnipCropBlock(Properties properties) {
        super(properties, ModItems.TURNIP);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (isMaxAge(state) && level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)) return true;
        return super.canSurvive(state, level, pos);
    }
}
