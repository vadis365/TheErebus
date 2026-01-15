package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.DragonflyModel;
import erebus.entity.Dragonfly;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class DragonflyLayer extends RenderLayer<Dragonfly, DragonflyModel<Dragonfly>> {

	private final DragonflyModel<Dragonfly> dragonflyModel;

    public DragonflyLayer(RenderLayerParent<Dragonfly, DragonflyModel<Dragonfly>> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.dragonflyModel = new DragonflyModel<>(modelSet.bakeLayer(ModEntityRendering.DRAGON_FLY));
    }

    @Override
   	public void render(PoseStack matrix, MultiBufferSource buffer, int packedLight, Dragonfly entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		dragonflyModel.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
		dragonflyModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		matrix.pushPose();
		RenderSystem.enableBlend();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		dragonflyModel.renderWings(matrix, buffer.getBuffer(RenderType.entityTranslucent(getTextureLocation(entity))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		RenderSystem.disableBlend();
	    RenderSystem.defaultBlendFunc();
	    matrix.popPose();
	}
}
