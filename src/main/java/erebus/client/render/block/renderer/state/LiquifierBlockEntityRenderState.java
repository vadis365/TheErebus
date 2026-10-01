package erebus.client.render.block.renderer.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.neoforged.neoforge.transfer.fluid.FluidResource;

public class LiquifierBlockEntityRenderState extends BlockEntityRenderState {
    public final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
    public float facingRotation;
    public float animationRotation;
    public FluidResource tankResource = FluidResource.EMPTY;
    public int tankAmount;
    public int tankCapacity;
}
