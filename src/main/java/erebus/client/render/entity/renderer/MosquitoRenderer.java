package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.MosquitoModel;
import erebus.client.render.entity.renderer.state.MosquitoRenderState;
import erebus.entity.Mosquito;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class MosquitoRenderer extends MobRenderer<Mosquito, MosquitoRenderState, MosquitoModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/mosquito.png");

	public MosquitoRenderer(EntityRendererProvider.Context context) {
        super(context, new MosquitoModel(context.bakeLayer(ModEntityRendering.MOSQUITO)), 0.5F);
	}

	@Override
	public void extractRenderState(Mosquito entity, MosquitoRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public MosquitoRenderState createRenderState() {
		return new MosquitoRenderState();
	}

	@Override
	public Identifier getTextureLocation(MosquitoRenderState state) {
		return TEXTURE;
	}
}
