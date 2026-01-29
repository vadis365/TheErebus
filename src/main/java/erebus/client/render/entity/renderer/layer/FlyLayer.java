package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.FlyModel;
import erebus.client.render.entity.renderer.state.FlyRenderState;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public class FlyLayer extends RenderLayer<FlyRenderState, FlyModel> {

    private final FlyModel model;

    public FlyLayer(RenderLayerParent<FlyRenderState, FlyModel> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.model = new FlyModel(modelSet.bakeLayer(ModEntityRendering.FLY));
    }

	@Override
	public void submit(PoseStack pose, SubmitNodeCollector submit, int lightCoords, FlyRenderState state, float xRot, float yRot) {
		pose.pushPose();
		/*submit.submitModel(
				model,
				state,
				pose,
				renderType,
				state.lightCoords,
				OverlayTexture.NO_OVERLAY,
				colour,
				null,
				state.outlineColor,
				null
		);*/
		pose.popPose();
	}
}
