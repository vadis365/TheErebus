package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.FireAntModel;
import erebus.client.render.entity.renderer.state.FireAntRenderState;
import erebus.entity.FireAnt;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class FireAntRenderer extends MobRenderer<FireAnt, FireAntRenderState, FireAntModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/fire_ant.png");

	public FireAntRenderer(EntityRendererProvider.Context context) {
        super(context, new FireAntModel(context.bakeLayer(ModEntityRendering.FIRE_ANT)), 0.3F);
	}

	@Override
	public void extractRenderState(FireAnt entity, FireAntRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public FireAntRenderState createRenderState() {
		return new FireAntRenderState();
	}

	@Override
	public Identifier getTextureLocation(FireAntRenderState state) {
		return TEXTURE;
	}
}
