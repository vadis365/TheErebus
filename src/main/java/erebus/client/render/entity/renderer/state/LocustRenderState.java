package erebus.client.render.entity.renderer.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class LocustRenderState extends LivingEntityRenderState {
    public float jumpPose;
    public float flyingPose;
    public float flyingTicks;
    public float jumpAngle;
    public float flightAngle;
    public float antSin;
    public float antCos;
    public float flapSin;
    public float flapCos;

    public boolean isFlying;
    public boolean isOnGround;
}
