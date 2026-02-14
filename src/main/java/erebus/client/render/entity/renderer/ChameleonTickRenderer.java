package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.ChameleonTickModel;
import erebus.client.render.entity.renderer.state.ChameleonTickRenderState;
import erebus.entity.ChameleonTick;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class ChameleonTickRenderer extends MobRenderer<ChameleonTick, ChameleonTickRenderState, ChameleonTickModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/chameleon_tick.png");

	public ChameleonTickRenderer(EntityRendererProvider.Context context) {
        super(context, new ChameleonTickModel(context.bakeLayer(ModEntityRendering.CHAMELEON_TICK)), 0.5F);
	}

	@Override
	public void extractRenderState(ChameleonTick entity, ChameleonTickRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public ChameleonTickRenderState createRenderState() {
		return new ChameleonTickRenderState();
	}

	@Override
	public Identifier getTextureLocation(ChameleonTickRenderState state) {
		return TEXTURE;
	}
}
