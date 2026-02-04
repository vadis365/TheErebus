package erebus.client.render.entity.renderer.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class WaspRenderState extends LivingEntityRenderState {
    public boolean isBoss;
    public int animationTicks;
    public int prevAnimationTicks;
    public boolean isFlying;
}
