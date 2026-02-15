package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.BlackAntModel;
import erebus.client.render.entity.model.layer.ant.BlackAntCollectorModel;
import erebus.client.render.entity.model.layer.ant.BlackAntFertilizerModel;
import erebus.client.render.entity.model.layer.ant.BlackAntHarvesterModel;
import erebus.client.render.entity.model.layer.ant.BlackAntPlanterModel;
import erebus.client.render.entity.renderer.state.BlackAntRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class BlackAntLayer extends RenderLayer<BlackAntRenderState, BlackAntModel> {

	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/black_ant_kit.png");

	private final BlackAntCollectorModel collectorModel;
	private final BlackAntFertilizerModel fertilizerModel;
	private final BlackAntHarvesterModel harvesterModel;
	private final BlackAntPlanterModel planterModel;

	public BlackAntLayer(RenderLayerParent<BlackAntRenderState, BlackAntModel> parent, BlackAntCollectorModel collectorModel, BlackAntFertilizerModel fertilizerModel, BlackAntHarvesterModel harvesterModel, BlackAntPlanterModel planterModel) {
		super(parent);
		this.collectorModel = collectorModel;
		this.fertilizerModel = fertilizerModel;
		this.harvesterModel = harvesterModel;
		this.planterModel = planterModel;
	}

	@Override
	public void submit(PoseStack pose, @NonNull SubmitNodeCollector submit, int lightCoords, BlackAntRenderState state, float xRot, float yRot) {
		pose.pushPose();

		if(state.isPlanter) {
			submit.submitModel(planterModel, state, pose, getParentModel().renderType(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
		}

		if(state.isHarvester) {
			submit.submitModel(harvesterModel, state, pose, getParentModel().renderType(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
		}

		if(state.isCollector) {
			submit.submitModel(collectorModel, state, pose, getParentModel().renderType(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
		}

		if(state.isFertilizer) {
			submit.submitModel(fertilizerModel, state, pose, getParentModel().renderType(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
		}

		pose.popPose();
	}
}
