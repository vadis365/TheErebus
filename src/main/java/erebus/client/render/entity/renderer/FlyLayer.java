package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.FlyModel;
import erebus.entity.Fly;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class FlyLayer extends RenderLayer<Fly, FlyModel<Fly>> {

    private final FlyModel<Fly> flyModel;

    public FlyLayer(RenderLayerParent<Fly, FlyModel<Fly>> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.flyModel = new FlyModel<>(modelSet.bakeLayer(ModEntityRendering.FLY));
    }

    @Override
   	public void render(PoseStack matrix, MultiBufferSource buffer, int packedLight, Fly entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    	flyModel.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
		flyModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		matrix.pushPose();
		RenderSystem.enableBlend();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		flyModel.renderWings(matrix, buffer.getBuffer(RenderType.entityTranslucentCull(getTextureLocation(entity))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		RenderSystem.disableBlend();
	    RenderSystem.defaultBlendFunc();
	    matrix.popPose();
	}
}
