package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.LocustModel;
import erebus.client.render.entity.renderer.LocustRenderer;
import erebus.client.render.entity.renderer.state.LocustRenderState;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class LocustLayer extends RenderLayer<LocustRenderState, LocustModel> {

    private final LocustModel model;
    private final LocustRenderer renderer;

    public LocustLayer(LocustRenderer entity, EntityModelSet modelSet) {
        super(entity);
        this.renderer = entity;
        this.model = new LocustModel(modelSet.bakeLayer(ModEntityRendering.LOCUST), true);
    }

    @Override
    public void submit(PoseStack pose, SubmitNodeCollector submit, int lightCoords, LocustRenderState state, float xRot, float yRot) {
        if (state.isInvisible) return;
        submit.submitModel(model, state, pose, RenderTypes.entityTranslucent(renderer.getTextureLocation(state)),
                lightCoords, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF, null, state.outlineColor, null);
    }
}
