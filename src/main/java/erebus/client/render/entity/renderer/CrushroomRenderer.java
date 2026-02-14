package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.CrushroomModel;
import erebus.client.render.entity.renderer.state.CrushroomRenderState;
import erebus.entity.Crushroom;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class CrushroomRenderer extends MobRenderer<Crushroom, CrushroomRenderState, CrushroomModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/crushroom.png");

	public CrushroomRenderer(EntityRendererProvider.Context context) {
        super(context, new CrushroomModel(context.bakeLayer(ModEntityRendering.CRUSHROOM)), 1.5F);
	}

	@Override
	public void extractRenderState(Crushroom entity, CrushroomRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public CrushroomRenderState createRenderState() {
		return new CrushroomRenderState();
	}

	@Override
	public Identifier getTextureLocation(CrushroomRenderState state) {
		return TEXTURE;
	}
}
