package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.BeetleModel;
import erebus.client.render.entity.renderer.state.StagBeetleRenderState;
import erebus.entity.StagBeetle;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class StagBeetleRenderer extends MobRenderer<StagBeetle, StagBeetleRenderState, BeetleModel<StagBeetleRenderState>> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/stag_beetle.png");

	public StagBeetleRenderer(EntityRendererProvider.Context context) {
        super(context, new BeetleModel<>(context.bakeLayer(ModEntityRendering.BEETLE)), 1.2F);
	}

	@Override
	public void extractRenderState(StagBeetle entity, StagBeetleRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public StagBeetleRenderState createRenderState() {
		return new StagBeetleRenderState();
	}

	@Override
	public Identifier getTextureLocation(StagBeetleRenderState state) {
		return TEXTURE;
	}
}
