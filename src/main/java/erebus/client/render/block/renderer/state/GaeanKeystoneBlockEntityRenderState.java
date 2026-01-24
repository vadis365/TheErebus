package erebus.client.render.block.renderer.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

public class GaeanKeystoneBlockEntityRenderState extends BlockEntityRenderState {
    public double now;
    public float rotation;
    public final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
}
