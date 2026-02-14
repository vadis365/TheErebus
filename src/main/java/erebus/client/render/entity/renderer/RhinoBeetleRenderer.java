package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.RhinoBeetleModel;
import erebus.client.render.entity.renderer.state.RhinoBeetleRenderState;
import erebus.entity.RhinoBeetle;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class RhinoBeetleRenderer extends MobRenderer<RhinoBeetle, RhinoBeetleRenderState, RhinoBeetleModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/rhino_beetle.png");

	public RhinoBeetleRenderer(EntityRendererProvider.Context context) {
        super(context, new RhinoBeetleModel(context.bakeLayer(ModEntityRendering.RHINO_BEETLE)), 1.2F);
	}

	@Override
	public void extractRenderState(RhinoBeetle entity, RhinoBeetleRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public RhinoBeetleRenderState createRenderState() {
		return new RhinoBeetleRenderState();
	}

	@Override
	public Identifier getTextureLocation(RhinoBeetleRenderState state) {
		return TEXTURE;
	}
}
