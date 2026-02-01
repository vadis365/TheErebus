package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.ZombieAntModel;
import erebus.client.render.entity.renderer.state.ZombieAntRenderState;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import org.jspecify.annotations.NonNull;

public class ZombieAntLayer extends RenderLayer<ZombieAntRenderState, ZombieAntModel> {

    private final ZombieAntModel antModel;

    public ZombieAntLayer(RenderLayerParent<ZombieAntRenderState, ZombieAntModel> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.antModel = new ZombieAntModel(modelSet.bakeLayer(ModEntityRendering.ZOMBIE_ANT));
    }

	@Override
	public void submit(@NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, int lightCoords, ZombieAntRenderState state, float xRot, float yRot) {

	}

    /*@Override
   	public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, ZombieAnt ant, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    	antModel.prepareMobModel(ant, limbSwing, limbSwingAmount, partialTicks);
		antModel.setupAnim(ant, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		int overlay = LivingEntityRenderer.getOverlayCoords(ant, 0F);
		stack.pushPose();
		antModel.renderAbdomen(stack, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(ant))), packedLight, overlay, 0xFFFFFFFF);
	    stack.popPose();
	}*/
}
