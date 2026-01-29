package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.BotFlyModel;
import erebus.client.render.entity.renderer.state.BotFlyRenderState;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import org.jspecify.annotations.NonNull;

public class BotFlyLayer extends RenderLayer<BotFlyRenderState, BotFlyModel> {

    private final BotFlyModel model;

    public BotFlyLayer(RenderLayerParent<BotFlyRenderState, BotFlyModel> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.model = new BotFlyModel(modelSet.bakeLayer(ModEntityRendering.BOT_FLY));
    }

	@Override
	public void submit(PoseStack pose, @NonNull SubmitNodeCollector submit, int lightCoords, BotFlyRenderState state, float xRot, float yRot) {
		model.setupAnim(state);
		pose.pushPose();
		//TODO: Make wings their own model
		pose.popPose();
	}

	 /*
	@Override
   	public void render(PoseStack matrix, MultiBufferSource buffer, int packedLight, BotFly entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    	model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
		model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		matrix.pushPose();
		RenderSystem.enableBlend();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		model.renderWings(matrix, buffer.getBuffer(RenderType.entityTranslucentCull(getTextureLocation(entity))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		RenderSystem.disableBlend();
	    RenderSystem.defaultBlendFunc();
	    matrix.popPose();
	}*/
}
