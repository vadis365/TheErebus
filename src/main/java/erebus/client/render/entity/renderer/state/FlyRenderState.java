package erebus.client.render.entity.renderer.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class FlyRenderState extends LivingEntityRenderState {
    public boolean isHanging;
    public int animationTicks;
    public int prevAnimationTicks;
}
