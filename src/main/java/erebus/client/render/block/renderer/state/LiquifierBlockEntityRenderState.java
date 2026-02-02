package erebus.client.render.block.renderer.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

public class LiquifierBlockEntityRenderState extends BlockEntityRenderState {
    public float partialTicks;
    public final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
    public net.neoforged.neoforge.transfer.fluid.FluidResource tankResource = net.neoforged.neoforge.transfer.fluid.FluidResource.EMPTY;
    public int tankAmount;
    public int tankCapacity;
}
