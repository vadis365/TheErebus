package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.ScytodesModel;
import erebus.client.render.entity.renderer.state.ScytodesRenderState;
import erebus.entity.Scytodes;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class ScytodesRenderer extends MobRenderer<Scytodes, ScytodesRenderState, ScytodesModel> {

	private static final Identifier[] TEXTURES = new Identifier[] {
			Erebus.prefix("textures/entity/scytodes_1.png"),
			Erebus.prefix("textures/entity/scytodes_2.png"),
			Erebus.prefix("textures/entity/scytodes_3.png"),
			Erebus.prefix("textures/entity/scytodes_4.png") };

	public ScytodesRenderer(EntityRendererProvider.Context context) {
		super(context, new ScytodesModel(context.bakeLayer(ModEntityRendering.SCYTODES)), 1F);
	}

	@Override
	public ScytodesRenderState createRenderState() {
		return new ScytodesRenderState();
	}

	@Override
	public void extractRenderState(Scytodes entity, ScytodesRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.skin = entity.getSkin();
	}

	@Override
	public @NonNull Identifier getTextureLocation(ScytodesRenderState state) {
		return TEXTURES[state.skin];
	}
}
