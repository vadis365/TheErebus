package erebus.client.render.block.renderer.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.neoforged.neoforge.transfer.fluid.FluidResource;

public class FluidJarBlockEntityRenderState extends BlockEntityRenderState {
    public FluidResource tankResource = FluidResource.EMPTY;
    public int tankAmount;
    public int tankCapacity;
}
