package erebus.client.render.entity.renderer.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.Direction;

public class ClimbingRenderState extends LivingEntityRenderState {
    public Direction climbingSurface = Direction.DOWN;
}
