package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.TarantulaMiniBossModel;
import erebus.client.render.entity.renderer.state.TarantulaMiniBossRenderState;
import erebus.entity.TarantulaMiniBoss;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class TarantulaMiniBossRenderer extends MobRenderer<TarantulaMiniBoss, TarantulaMiniBossRenderState, TarantulaMiniBossModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/tarantula_mini_boss.png");

	public TarantulaMiniBossRenderer(EntityRendererProvider.Context context) {
        super(context, new TarantulaMiniBossModel(context.bakeLayer(ModEntityRendering.TARANTULA_MINI_BOSS)), 1.2F);
	}

	@Override
	public void extractRenderState(TarantulaMiniBoss entity, TarantulaMiniBossRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public TarantulaMiniBossRenderState createRenderState() {
		return new TarantulaMiniBossRenderState();
	}

	@Override
	public Identifier getTextureLocation(TarantulaMiniBossRenderState state) {
		return TEXTURE;
	}
}
