package erebus.client.render.block.renderer.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

public class FluidJarBlockEntityRenderState extends BlockEntityRenderState {
    public net.neoforged.neoforge.transfer.fluid.FluidResource tankResource = net.neoforged.neoforge.transfer.fluid.FluidResource.EMPTY;
    public int tankAmount;
    public int tankCapacity;
}
