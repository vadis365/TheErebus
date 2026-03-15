package erebus.client.render.block.renderer.state;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;

public class BambooExtenderBlockEntityRenderState extends BlockEntityRenderState {
    public Direction facing;
    public final BlockModelRenderState planks = new BlockModelRenderState();
}
