package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.ScorpionModel;
import erebus.client.render.entity.renderer.state.ScorpionRenderState;
import erebus.entity.Scorpion;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class ScorpionRenderer extends MobRenderer<Scorpion, ScorpionRenderState, ScorpionModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/scorpion.png");

	public ScorpionRenderer(EntityRendererProvider.Context context) {
        super(context, new ScorpionModel(context.bakeLayer(ModEntityRendering.SCORPION)), 0.7F);
	}

	@Override
	public void extractRenderState(Scorpion entity, ScorpionRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public ScorpionRenderState createRenderState() {
		return new ScorpionRenderState();
	}

	@Override
	public Identifier getTextureLocation(ScorpionRenderState state) {
		return TEXTURE;
	}
}
