package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.LocustModel;
import erebus.client.render.entity.renderer.state.LocustRenderState;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import org.jspecify.annotations.NonNull;

public class LocustLayer extends RenderLayer<LocustRenderState, LocustModel> {

    private final LocustModel locustModel;

    public LocustLayer(RenderLayerParent<LocustRenderState, LocustModel> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.locustModel = new LocustModel(modelSet.bakeLayer(ModEntityRendering.LOCUST));
    }

	@Override
	public void submit(@NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, int lightCoords, LocustRenderState state, float xRot, float yRot) {

	}

	/*@Override
   	public void render(PoseStack matrix, MultiBufferSource buffer, int packedLight, Locust entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    	locustModel.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
		locustModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		matrix.pushPose();
		RenderSystem.enableBlend();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		locustModel.renderWings(matrix, buffer.getBuffer(RenderType.entityTranslucentCull(getTextureLocation(entity))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		RenderSystem.disableBlend();
	    RenderSystem.defaultBlendFunc();
	    matrix.popPose();
	}*/
}
