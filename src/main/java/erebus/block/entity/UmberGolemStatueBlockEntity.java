package erebus.block.entity;

import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class UmberGolemStatueBlockEntity extends BlockEntity {
    public UmberGolemStatueBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.UMBER_GOLEM_STATUE.get(), pos, state);
    }
}
