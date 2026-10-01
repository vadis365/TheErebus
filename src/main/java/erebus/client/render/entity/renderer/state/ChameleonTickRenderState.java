package erebus.client.render.entity.renderer.state;

import net.minecraft.client.renderer.block.MovingBlockRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class ChameleonTickRenderState extends LivingEntityRenderState {
    public final MovingBlockRenderState camouflage = new MovingBlockRenderState();
    public float unfold;
    public Identifier texture;
}
