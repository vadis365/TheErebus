package erebus.client.render.entity.renderer.layer.mosquito;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.MosquitoModel;
import erebus.client.render.entity.model.layer.mosquito.MosquitoAppendageModel;
import erebus.client.render.entity.renderer.state.MosquitoRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class MosquitoAppendageLayer extends RenderLayer<MosquitoRenderState, MosquitoModel> {
    private final MosquitoAppendageModel model;
    private final Identifier TEXTURE = Erebus.prefix("textures/entity/mosquito.png");

    public MosquitoAppendageLayer(RenderLayerParent<MosquitoRenderState, MosquitoModel> renderer, MosquitoAppendageModel model) {
        super(renderer);
        this.model = model;
    }

    @Override
    public void submit(@NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, int lightCoords, MosquitoRenderState state, float xRot, float yRot) {
        if(!state.isRiding) {
            submit.submitModel(model, state, pose, getParentModel().renderType(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
        }
    }
}
