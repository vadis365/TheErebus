package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.SolifugeModel;
import erebus.client.render.entity.renderer.state.SolifugeRenderState;
import erebus.entity.Solifuge;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class SolifugeRenderer extends MobRenderer<Solifuge, SolifugeRenderState, SolifugeModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/solifuge.png");

	public SolifugeRenderer(EntityRendererProvider.Context context) {
        super(context, new SolifugeModel(context.bakeLayer(ModEntityRendering.SOLIFUGE)), 0.7F);
	}

	@Override
	public void extractRenderState(Solifuge entity, SolifugeRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public SolifugeRenderState createRenderState() {
		return new SolifugeRenderState();
	}

	@Override
	public Identifier getTextureLocation(SolifugeRenderState state) {
		return TEXTURE;
	}
}
