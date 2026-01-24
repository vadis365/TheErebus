package erebus.client.render.block.renderer.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import org.jspecify.annotations.Nullable;

public class PreservedBlockEntityRenderState extends BlockEntityRenderState {
    public @Nullable EntityRenderState displayEntity;
}
