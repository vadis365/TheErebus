package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.DragonflyModel;
import erebus.client.render.entity.renderer.DragonflyRenderer;
import erebus.client.render.entity.renderer.state.DragonflyRenderState;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.jspecify.annotations.NonNull;

public class DragonflyLayer extends RenderLayer<DragonflyRenderState, DragonflyModel> {

    private final DragonflyModel model;
    private final DragonflyRenderer renderer;

    public DragonflyLayer(DragonflyRenderer entity, EntityModelSet modelSet) {
        super(entity);
        this.renderer = entity;
        this.model = new DragonflyModel(modelSet.bakeLayer(ModEntityRendering.DRAGON_FLY), true);
    }

    @Override
    public void submit(PoseStack pose, @NonNull SubmitNodeCollector submit, int lightCoords, DragonflyRenderState state, float xRot, float yRot) {
        if (state.isInvisible) return;
        submit.submitModel(model, state, pose, RenderTypes.entityTranslucent(renderer.getTextureLocation(state)),
                lightCoords, OverlayTexture.NO_OVERLAY, 0xBFFFFFFF, null, state.outlineColor, null);
    }
}
