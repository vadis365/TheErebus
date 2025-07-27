package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.BlackAntModel;
import erebus.entity.BlackAnt;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlackAntLayer extends RenderLayer<BlackAnt, BlackAntModel<BlackAnt>> {

    private final BlackAntModel<BlackAnt> blackAntModel;

    public BlackAntLayer(RenderLayerParent<BlackAnt, BlackAntModel<BlackAnt>> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.blackAntModel = new BlackAntModel<>(modelSet.bakeLayer(ModEntityRendering.BLACK_ANT));
    }

    @Override
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
	}
}