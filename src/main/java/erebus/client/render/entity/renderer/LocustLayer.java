package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import erebus.client.render.entity.model.LocustModel;
import erebus.entity.Locust;
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
public class LocustLayer extends RenderLayer<Locust, LocustModel<Locust>> {

    private final LocustModel<Locust> locustModel;

    public LocustLayer(RenderLayerParent<Locust, LocustModel<Locust>> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.locustModel = new LocustModel<>(modelSet.bakeLayer(ModEntityRendering.LOCUST));
    }

    @Override
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
	}
}