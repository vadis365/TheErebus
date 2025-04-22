package erebus.block.entity;

import erebus.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GaeanKeystoneBlockEntity extends BlockEntity {

    private float rotation;

    public GaeanKeystoneBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.GAEAN_KEYSTONE.get(), pos, blockState);
    }

    public float getRenderingRotation() {
        rotation += 0.5F;
        if (rotation >= 360) rotation = 0;
        return rotation;
    }
}
