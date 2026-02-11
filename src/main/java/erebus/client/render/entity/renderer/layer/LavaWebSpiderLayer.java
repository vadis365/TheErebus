package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.LavaWebSpiderModel;
import erebus.client.render.entity.renderer.state.LavaWebSpiderRenderState;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class LavaWebSpiderLayer extends RenderLayer<LavaWebSpiderRenderState, LavaWebSpiderModel> {
    private static final Identifier LIGHTING_TEXTURE = Erebus.prefix("textures/entity/lava_web_spider_flow.png");
    private final LavaWebSpiderModel model;

    public LavaWebSpiderLayer(RenderLayerParent<LavaWebSpiderRenderState, LavaWebSpiderModel> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.model = new LavaWebSpiderModel(modelSet.bakeLayer(ModEntityRendering.LAVA_WEB_SPIDER_FLOW));
    }

	@Override
	public void submit(@NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, int lightCoords, LavaWebSpiderRenderState state, float xRot, float yRot) {
		submit.submitModelPart(model.ThxTop, pose, RenderTypes.entityCutout(LIGHTING_TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, null, false, false, -1, null, state.outlineColor);
		submit.submitModelPart(model.ThxS, pose, RenderTypes.entityCutout(LIGHTING_TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, null, false, false, -1, null, state.outlineColor);
		submit.submitModelPart(model.AbTop1, pose, RenderTypes.entityCutout(LIGHTING_TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, null, false, false, -1, null, state.outlineColor);
		submit.submitModelPart(model.AbTop2, pose, RenderTypes.entityCutout(LIGHTING_TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, null, false, false, -1, null, state.outlineColor);
		submit.submitModelPart(model.ABot1, pose, RenderTypes.entityCutout(LIGHTING_TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, null, false, false, -1, null, state.outlineColor);
		submit.submitModelPart(model.AbBack, pose, RenderTypes.entityCutout(LIGHTING_TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, null, false, false, -1, null, state.outlineColor);
		submit.submitModelPart(model.AbCore1, pose, RenderTypes.entityCutout(LIGHTING_TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, null, false, false, -1, null, state.outlineColor);
		submit.submitModelPart(model.AbCore2, pose, RenderTypes.entityCutout(LIGHTING_TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, null, false, false, -1, null, state.outlineColor);
		submit.submitModelPart(model.AbCore3, pose, RenderTypes.entityCutout(LIGHTING_TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, null, false, false, -1, null, state.outlineColor);
	}
}
