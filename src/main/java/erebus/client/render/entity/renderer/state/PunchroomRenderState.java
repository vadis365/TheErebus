package erebus.client.render.entity.renderer.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.Nullable;

public class PunchroomRenderState extends LivingEntityRenderState {
    public float prevSquishFactor;
    public float squishFactor;
    public @Nullable Component name;
    public boolean hasCustomName;
}
