package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.BlackAntModel;
import erebus.client.render.entity.renderer.state.BlackAntRenderState;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.jspecify.annotations.NonNull;

public class BlackAntLayer extends RenderLayer<BlackAntRenderState, BlackAntModel> {

    private final BlackAntModel blackAntModel;

    public BlackAntLayer(RenderLayerParent<BlackAntRenderState, BlackAntModel> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.blackAntModel = new BlackAntModel(modelSet.bakeLayer(ModEntityRendering.BLACK_ANT));
    }

	@Override
	public void submit(PoseStack pose, @NonNull SubmitNodeCollector submit, int lightCoords, BlackAntRenderState state, float xRot, float yRot) {
		pose.pushPose();
		submit.submitModel(blackAntModel, state, pose, RenderTypes::entitySolid, lightCoords, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor, null);
		pose.popPose();
	}

	/*@Override
   	public void render(PoseStack matrix, MultiBufferSource buffer, int packedLight, BlackAnt blackAnt, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    	blackAntModel.prepareMobModel(blackAnt, limbSwing, limbSwingAmount, partialTicks);
		blackAntModel.setupAnim(blackAnt, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		matrix.pushPose();
		if (blackAnt.getAntRole() == blackAnt.PLANTER)
			blackAntModel.renderPlanter(matrix, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(blackAnt))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);

		if (blackAnt.getAntRole() == blackAnt.HARVESTER)
			blackAntModel.renderHarvester(matrix, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(blackAnt))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);

		if (blackAnt.getAntRole() == blackAnt.COLLECTOR)
			blackAntModel.renderCollector(matrix, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(blackAnt))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);

		if (blackAnt.getAntRole() == blackAnt.FERTILIZER)
			blackAntModel.renderFertilizer(matrix, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(blackAnt))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);

	    matrix.popPose();
	}*/
}
