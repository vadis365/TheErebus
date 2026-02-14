package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.CicadaModel;
import erebus.client.render.entity.renderer.state.CicadaRenderState;
import erebus.entity.Cicada;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class CicadaRenderer extends MobRenderer<Cicada, CicadaRenderState, CicadaModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/cicada.png");

	public CicadaRenderer(EntityRendererProvider.Context context) {
        super(context, new CicadaModel(context.bakeLayer(ModEntityRendering.CICADA)), 0.5F);
	}

	@Override
	public void extractRenderState(Cicada entity, CicadaRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public CicadaRenderState createRenderState() {
		return new CicadaRenderState();
	}

	@Override
	public Identifier getTextureLocation(CicadaRenderState state) {
		return TEXTURE;
	}
}
