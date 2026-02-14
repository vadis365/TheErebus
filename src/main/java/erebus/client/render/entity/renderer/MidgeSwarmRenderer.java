package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.FlyModel;
import erebus.client.render.entity.renderer.state.FlyRenderState;
import erebus.entity.MidgeSwarm;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class MidgeSwarmRenderer extends MobRenderer<MidgeSwarm, FlyRenderState, FlyModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/midge_swarm.png");

	public MidgeSwarmRenderer(EntityRendererProvider.Context context) {
        super(context, new FlyModel(context.bakeLayer(ModEntityRendering.FLY)), 0.45F);
	}

	@Override
	public void extractRenderState(MidgeSwarm entity, FlyRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public FlyRenderState createRenderState() {
		return new FlyRenderState();
	}

	@Override
	public Identifier getTextureLocation(FlyRenderState state) {
		return TEXTURE;
	}
}
