package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.BeetleLarvaModel;
import erebus.client.render.entity.renderer.state.BeetleLarvaRenderState;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public class BeetleLarvaLayer extends RenderLayer<BeetleLarvaRenderState, BeetleLarvaModel> {

	private final BeetleLarvaModel model;

	public BeetleLarvaLayer(RenderLayerParent<BeetleLarvaRenderState, BeetleLarvaModel> parentLayer, EntityModelSet modelSet) {
		super(parentLayer);
		this.model = new BeetleLarvaModel(modelSet.bakeLayer(ModEntityRendering.BEETLE_LARVA));
	}

	/*@Override
	public void render(PoseStack matrix, MultiBufferSource buffer, int packedLight, BeetleLarva larva, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		model.prepareMobModel(larva, limbSwing, limbSwingAmount, partialTicks);
		model.setupAnim(larva, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		if (larva.getLarvaType() == 2)
			model.renderLarvaRhino(matrix, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(larva))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		if (larva.getLarvaType() == 3)
			model.renderLarvaTitan(matrix, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(larva))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		if (larva.getLarvaType() == 5)
			model.renderLarvaStag(matrix, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(larva))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
	}*/

	@Override
	public void submit(PoseStack pose, SubmitNodeCollector submit, int lightCoords, BeetleLarvaRenderState state, float yRot, float xRot) {
		//TODO: We will need a model for each variant but only the additions
	}
}
