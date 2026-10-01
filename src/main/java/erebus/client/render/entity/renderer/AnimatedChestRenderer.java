package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.AnimatedChestModel;
import erebus.client.render.entity.renderer.state.AnimatedChestRenderState;
import erebus.entity.AnimatedChest;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class AnimatedChestRenderer extends MobRenderer<AnimatedChest, AnimatedChestRenderState, AnimatedChestModel> {
    private static final Identifier TEXTURE = Erebus.prefix("textures/entity/animated_chest.png");

    public AnimatedChestRenderer(EntityRendererProvider.Context context) {
        super(context, new AnimatedChestModel(context.bakeLayer(ModEntityRendering.ANIMATED_CHEST)), 0.5F);
    }

    @Override
    public @NonNull AnimatedChestRenderState createRenderState() {
        return new AnimatedChestRenderState();
    }

    @Override
    public void extractRenderState(@NonNull AnimatedChest chest, @NonNull AnimatedChestRenderState state, float partialTick) {
        super.extractRenderState(chest, state, partialTick);
        state.openness = chest.getOpenness(partialTick);
    }

    @Override
    public @NonNull Identifier getTextureLocation(@NonNull AnimatedChestRenderState state) {
        return TEXTURE;
    }
}
