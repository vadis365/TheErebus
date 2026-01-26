package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.AntModel;
import erebus.entity.HoneyPotAnt;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class HoneyPotAntLayer extends RenderLayer<HoneyPotAnt, AntModel<HoneyPotAnt>> {

    private final AntModel<HoneyPotAnt> antModel;

    public HoneyPotAntLayer(RenderLayerParent<HoneyPotAnt, AntModel<HoneyPotAnt>> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.antModel = new AntModel<>(modelSet.bakeLayer(ModEntityRendering.HONEY_POT_ANT));
    }

    @Override
   	public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, HoneyPotAnt ant, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    	antModel.prepareMobModel(ant, limbSwing, limbSwingAmount, partialTicks);
		antModel.setupAnim(ant, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		stack.pushPose();
		stack.scale(1F + ant.getHoneyBelly(), 1F + ant.getHoneyBelly(), 1F + ant.getHoneyBelly());
		stack.translate(0F, 0F -ant.getHoneyBelly() * 0.625F, 0F - 0.0625F * ant.getHoneyBelly());
		antModel.renderAbdomen(stack, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(ant))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
	    stack.popPose();
	}
}
