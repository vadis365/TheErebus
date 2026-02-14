package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.PondSkaterModel;
import erebus.client.render.entity.renderer.state.PondSkaterRenderState;
import erebus.entity.PondSkater;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class PondSkaterRenderer extends MobRenderer<PondSkater, PondSkaterRenderState, PondSkaterModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/pond_skater.png");

	public PondSkaterRenderer(EntityRendererProvider.Context context) {
        super(context, new PondSkaterModel(context.bakeLayer(ModEntityRendering.POND_SKATER)), 0.5F);
	}

	@Override
	public void extractRenderState(PondSkater entity, PondSkaterRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public PondSkaterRenderState createRenderState() {
		return new PondSkaterRenderState();
	}

	@Override
	public Identifier getTextureLocation(PondSkaterRenderState state) {
		return TEXTURE;
	}
}
