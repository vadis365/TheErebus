package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.BeetleLarvaModel;
import erebus.entity.BeetleLarva;
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
public class BeetleLarvaLayer extends RenderLayer<BeetleLarva, BeetleLarvaModel<BeetleLarva>> {

    private final BeetleLarvaModel<BeetleLarva> model;

    public BeetleLarvaLayer(RenderLayerParent<BeetleLarva, BeetleLarvaModel<BeetleLarva>> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.model = new BeetleLarvaModel<>(modelSet.bakeLayer(ModEntityRendering.BEETLE_LARVA));
    }

    @Override
   	public void render(PoseStack matrix, MultiBufferSource buffer, int packedLight, BeetleLarva larva, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    	model.prepareMobModel(larva, limbSwing, limbSwingAmount, partialTicks);
		model.setupAnim(larva, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		if (larva.getLarvaType() == 2)
			model.renderLarvaRhino(matrix, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(larva))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		if (larva.getLarvaType() == 3)
			model.renderLarvaTitan(matrix, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(larva))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		if (larva.getLarvaType() == 5)
			model.renderLarvaStag(matrix, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(larva))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
	}
}