package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.BotFlyModel;
import erebus.client.render.entity.renderer.BotFlyRenderer;
import erebus.client.render.entity.renderer.state.BotFlyRenderState;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class BotFlyLayer extends RenderLayer<BotFlyRenderState, BotFlyModel> {
    private final BotFlyModel model;
    private final BotFlyRenderer renderer;

    public BotFlyLayer(BotFlyRenderer renderer, EntityModelSet modelSet) {
        super(renderer);
        this.renderer = renderer;
        this.model = new BotFlyModel(modelSet.bakeLayer(ModEntityRendering.BOT_FLY), true);
    }

    @Override
    public void submit(PoseStack pose, SubmitNodeCollector submit, int lightCoords, BotFlyRenderState state, float xRot, float yRot) {
        if (state.isInvisible) return;
        submit.submitModel(model, state, pose, RenderTypes.entityTranslucent(renderer.getTextureLocation(state)),
                lightCoords, OverlayTexture.NO_OVERLAY, 0xBFFFFFFF, null, state.outlineColor, null);
    }
}
