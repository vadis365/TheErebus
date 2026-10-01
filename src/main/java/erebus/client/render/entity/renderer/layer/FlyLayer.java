package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.FlyModel;
import erebus.client.render.entity.renderer.FlyRenderer;
import erebus.client.render.entity.renderer.state.FlyRenderState;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class FlyLayer extends RenderLayer<FlyRenderState, FlyModel> {

    private final FlyModel model;
    private final FlyRenderer renderer;

    public FlyLayer(FlyRenderer entity, EntityModelSet modelSet) {
        super(entity);
        this.renderer = entity;
        this.model = new FlyModel(modelSet.bakeLayer(ModEntityRendering.FLY), true);
    }

    @Override
    public void submit(PoseStack pose, SubmitNodeCollector submit, int lightCoords, FlyRenderState state, float xRot, float yRot) {
        if (state.isInvisible) return;
        submit.submitModel(model, state, pose, RenderTypes.entityTranslucent(renderer.getTextureLocation(state)),
                lightCoords, OverlayTexture.NO_OVERLAY, 0x7FFFFFFF, null, state.outlineColor, null);
    }
}
