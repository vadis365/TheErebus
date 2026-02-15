package erebus.client.render.entity.renderer.layer.mosquito;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.MosquitoModel;
import erebus.client.render.entity.model.layer.mosquito.MosquitoHeadModel;
import erebus.client.render.entity.renderer.state.MosquitoRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class MosquitoHeadLayer extends RenderLayer<MosquitoRenderState, MosquitoModel> {
    private final MosquitoHeadModel model;
    private final Identifier TEXTURE = Erebus.prefix("textures/entity/mosquito.png");

    public MosquitoHeadLayer(RenderLayerParent<MosquitoRenderState, MosquitoModel> renderer, MosquitoHeadModel model) {
        super(renderer);
        this.model = model;
    }

    @Override
    public void submit(@NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, int lightCoords, MosquitoRenderState state, float xRot, float yRot) {
        if(!state.isRiding) {
            pose.pushPose();
            pose.scale(state.suck, 1.0F, 1.0F);
            submit.submitModel(model, state, pose, getParentModel().renderType(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
            pose.popPose();
        }
    }
}
