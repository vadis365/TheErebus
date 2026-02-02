package erebus.client.render.entity.renderer.state;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.level.block.state.BlockState;

public class ThrownBlockAsItemRenderState extends EntityRenderState {
	public BlockState blockState;
	public final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
}
