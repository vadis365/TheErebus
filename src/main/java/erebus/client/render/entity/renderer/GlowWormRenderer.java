package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.GlowWormModel;
import erebus.client.render.entity.renderer.state.GlowWormRenderState;
import erebus.entity.GlowWorm;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class GlowWormRenderer extends MobRenderer<GlowWorm, GlowWormRenderState, GlowWormModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/glow_worm.png");

	public GlowWormRenderer(EntityRendererProvider.Context context) {
        super(context, new GlowWormModel(context.bakeLayer(ModEntityRendering.GLOW_WORM)), 0.5F);
	}

	@Override
	public void extractRenderState(GlowWorm entity, GlowWormRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public GlowWormRenderState createRenderState() {
		return new GlowWormRenderState();
	}

	@Override
	public Identifier getTextureLocation(GlowWormRenderState state) {
		return TEXTURE;
	}
}
