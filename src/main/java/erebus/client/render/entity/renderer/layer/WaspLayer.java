package erebus.client.render.entity.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.WaspModel;
import erebus.client.render.entity.model.layer.WaspWingsModel;
import erebus.client.render.entity.renderer.state.WaspRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class WaspLayer extends RenderLayer<WaspRenderState, WaspModel> {

    private final WaspWingsModel model;
	public static final Identifier WASP = Erebus.prefix("textures/entity/wasp.png");
	private static final Identifier HORNET = Erebus.prefix("textures/entity/hornet.png");

    public WaspLayer(RenderLayerParent<WaspRenderState, WaspModel> parent, WaspWingsModel wings) {
    	super(parent);
    	model = wings;
    }

	@Override
	public void submit(@NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, int lightCoords, WaspRenderState state, float xRot, float yRot) {
		submit.submitModel(
				model,
				state,
				pose,
				RenderTypes.entityTranslucent(state.isBoss ? HORNET : WASP),
				state.lightCoords,
				OverlayTexture.NO_OVERLAY,
				state.outlineColor,
				null
		);
	}
}
