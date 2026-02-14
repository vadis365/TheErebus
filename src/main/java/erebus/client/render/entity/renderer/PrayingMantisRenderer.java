package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.PrayingMantisModel;
import erebus.client.render.entity.renderer.state.PrayingMantisRenderState;
import erebus.entity.PrayingMantis;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class PrayingMantisRenderer extends MobRenderer<PrayingMantis, PrayingMantisRenderState, PrayingMantisModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/praying_mantis.png");

	public PrayingMantisRenderer(EntityRendererProvider.Context context) {
        super(context, new PrayingMantisModel(context.bakeLayer(ModEntityRendering.PRAYING_MANTIS)), 0.7F);
	}

	@Override
	public void extractRenderState(PrayingMantis entity, PrayingMantisRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public PrayingMantisRenderState createRenderState() {
		return new PrayingMantisRenderState();
	}

	@Override
	public Identifier getTextureLocation(PrayingMantisRenderState state) {
		return TEXTURE;
	}
}
