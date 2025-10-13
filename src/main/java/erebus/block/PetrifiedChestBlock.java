package erebus.block;

import erebus.block.entity.PetrifiedChestBlockEntity;
import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PetrifiedChestBlock extends ChestBlock {

    public PetrifiedChestBlock(Properties properties) {
        super(properties, ModBlockEntities.PETRIFIED_CHEST::get);
    }

    @Override
    public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new PetrifiedChestBlockEntity(pos, state);
    }
}