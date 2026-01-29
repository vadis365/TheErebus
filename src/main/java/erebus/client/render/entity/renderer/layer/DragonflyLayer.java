package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.model.DragonflyModel;
import erebus.client.render.entity.renderer.state.DragonflyRenderState;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import org.jspecify.annotations.NonNull;

public class DragonflyLayer extends RenderLayer<DragonflyRenderState, DragonflyModel> {

	private final DragonflyModel model;

    public DragonflyLayer(RenderLayerParent<DragonflyRenderState, DragonflyModel> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.model = new DragonflyModel(modelSet.bakeLayer(ModEntityRendering.DRAGON_FLY));
    }

	@Override
	public void submit(PoseStack pose, @NonNull SubmitNodeCollector submit, int lightCoords, DragonflyRenderState state, float xRot, float yRot) {
		model.setupAnim(state);
		pose.pushPose();
		//TODO: Setup Wings Model
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
