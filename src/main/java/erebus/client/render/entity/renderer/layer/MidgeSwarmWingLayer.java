package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.MidgeSwarmModel;
import erebus.client.render.entity.renderer.state.MidgeSwarmRenderState;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class MidgeSwarmWingLayer extends RenderLayer<MidgeSwarmRenderState, MidgeSwarmModel> {
    private final MidgeSwarmModel model;

    public MidgeSwarmWingLayer(RenderLayerParent<MidgeSwarmRenderState, MidgeSwarmModel> parent, EntityModelSet models) {
        super(parent);
        model = new MidgeSwarmModel(models.bakeLayer(ModEntityRendering.MIDGE_SWARM), true);
    }

    @Override
    public void submit(PoseStack pose, SubmitNodeCollector submit, int light, MidgeSwarmRenderState state, float xRot, float yRot) {
        if (state.isInvisible) return;
        submit.submitModel(model, state, pose, RenderTypes.entityTranslucent(Erebus.prefix("textures/entity/midge_swarm.png")),
                light, LivingEntityRenderer.getOverlayCoords(state, 0), 0xBFFFFFFF, null, state.outlineColor, null);
    }
}
