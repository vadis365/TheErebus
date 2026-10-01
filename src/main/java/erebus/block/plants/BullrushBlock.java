package erebus.block.plants;

import com.mojang.serialization.MapCodec;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class BullrushBlock extends DoublePlantBlock {
    public static final MapCodec<BullrushBlock> CODEC = simpleCodec(BullrushBlock::new);

    public BullrushBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull MapCodec<BullrushBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos) {
        return state.is(ModBlocks.MUD.get()) || super.mayPlaceOn(state, level, pos);
    }
}
