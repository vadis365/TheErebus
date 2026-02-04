package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.WaspModel;
import erebus.client.render.entity.model.layer.WaspWingsModel;
import erebus.client.render.entity.renderer.state.WaspRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class WaspLayer extends RenderLayer<WaspRenderState, WaspModel> {

    private final WaspWingsModel model;
	public static final Identifier WASP = Erebus.prefix("textures/entity/wasp.png");
	private static final Identifier HORNET = Erebus.prefix("textures/entity/hornet.png");

    public WaspLayer(RenderLayerParent<WaspRenderState, WaspModel> parent, WaspWingsModel wings) {
    	super(parent);
    	model = wings;
    }

	@Override
	public void submit(@NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, int lightCoords, WaspRenderState state, float xRot, float yRot) {
		submit.submitModel(
				model,
				state,
				pose,
				RenderTypes.entityTranslucent(state.isBoss ? HORNET : WASP),
				state.lightCoords,
				OverlayTexture.NO_OVERLAY,
				state.outlineColor,
				null
		);
	}

    /*@Override
   	public void render(PoseStack matrix, MultiBufferSource buffer, int packedLight, Wasp entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    	waspModel.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
		waspModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		matrix.pushPose();
		RenderSystem.enableBlend();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		waspModel.renderWings(matrix, buffer.getBuffer(RenderType.entityTranslucentCull(getTextureLocation(entity))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		RenderSystem.disableBlend();
	    RenderSystem.defaultBlendFunc();
	    matrix.popPose();
	}*/
}
