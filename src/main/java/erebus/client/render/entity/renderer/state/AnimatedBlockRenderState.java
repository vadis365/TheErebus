package erebus.client.render.entity.renderer.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.level.block.state.BlockState;

public class AnimatedBlockRenderState extends LivingEntityRenderState {

    public ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
    public BlockState blockState;
}
