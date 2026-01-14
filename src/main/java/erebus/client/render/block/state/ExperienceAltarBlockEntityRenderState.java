package erebus.client.render.block.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

public class ExperienceAltarBlockEntityRenderState extends BlockEntityRenderState {
    public int animationTicks;

    public int getStep() {
        if (animationTicks <= 4)
            return 0;
        else if (animationTicks <= 8)
            return 1;
        else if (animationTicks <= 12)
            return 2;
        else if (animationTicks <= 16)
            return 3;
        else if (animationTicks <= 20)
            return 4;
        else
            return 0;
    }
}
