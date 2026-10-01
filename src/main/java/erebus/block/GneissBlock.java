package erebus.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class GneissBlock extends Block {
    public static final MapCodec<GneissBlock> CODEC = simpleCodec(GneissBlock::new);

    public GneissBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends GneissBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player,
                                       ItemStack tool, boolean willHarvest, FluidState fluid) {
        boolean removed = super.onDestroyedByPlayer(state, level, pos, player, tool, willHarvest, fluid);
        if (removed && !level.isClientSide()) level.setBlockAndUpdate(pos, Blocks.LAVA.defaultBlockState());
        return removed;
    }
}
