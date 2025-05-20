package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.WaspModel;
import erebus.entity.Wasp;
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
public class WaspLayer extends RenderLayer<Wasp, WaspModel<Wasp>> {

    private final WaspModel<Wasp> waspModel;

    public WaspLayer(RenderLayerParent<Wasp, WaspModel<Wasp>> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.waspModel = new WaspModel<>(modelSet.bakeLayer(ModEntityRendering.WASP));
    }

    @Override
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
	}
}