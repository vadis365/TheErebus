package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import erebus.client.render.entity.model.AntModel;
import erebus.entity.ZombieAnt;
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
public class ZombieAntLayer extends RenderLayer<ZombieAnt, AntModel<ZombieAnt>> {

    private final AntModel<ZombieAnt> antModel;

    public ZombieAntLayer(RenderLayerParent<ZombieAnt, AntModel<ZombieAnt>> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.antModel = new AntModel<>(modelSet.bakeLayer(ModEntityRendering.ZOMBIE_ANT));
    }

    @Override
   	public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, ZombieAnt ant, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    	antModel.prepareMobModel(ant, limbSwing, limbSwingAmount, partialTicks);
		antModel.setupAnim(ant, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		stack.pushPose();
		antModel.renderAbdomen(stack, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(ant))), packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
	    stack.popPose();
	}
}