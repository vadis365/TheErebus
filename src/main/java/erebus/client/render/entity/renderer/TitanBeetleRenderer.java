package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.TitanBeetleModel;
import erebus.client.render.entity.renderer.state.TitanBeetleRenderState;
import erebus.entity.TitanBeetle;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class TitanBeetleRenderer extends MobRenderer<TitanBeetle, TitanBeetleRenderState, TitanBeetleModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/titan_beetle.png");

	public TitanBeetleRenderer(EntityRendererProvider.Context context) {
        super(context, new TitanBeetleModel(context.bakeLayer(ModEntityRendering.TITAN_BEETLE)), 1.2F);
	}

	@Override
	public void extractRenderState(TitanBeetle entity, TitanBeetleRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public TitanBeetleRenderState createRenderState() {
		return new TitanBeetleRenderState();
	}

	@Override
	public Identifier getTextureLocation(TitanBeetleRenderState state) {
		return TEXTURE;
	}
}
