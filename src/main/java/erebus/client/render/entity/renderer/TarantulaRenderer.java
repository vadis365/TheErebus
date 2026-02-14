package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.TarantulaModel;
import erebus.client.render.entity.renderer.state.TarantulaRenderState;
import erebus.entity.Tarantula;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class TarantulaRenderer extends MobRenderer<Tarantula, TarantulaRenderState, TarantulaModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/tarantula.png");

	public TarantulaRenderer(EntityRendererProvider.Context context) {
        super(context, new TarantulaModel(context.bakeLayer(ModEntityRendering.TARANTULA)), 0.6F);
	}

	@Override
	public void extractRenderState(Tarantula entity, TarantulaRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public TarantulaRenderState createRenderState() {
		return new TarantulaRenderState();
	}

	@Override
	public Identifier getTextureLocation(TarantulaRenderState state) {
		return TEXTURE;
	}
}
