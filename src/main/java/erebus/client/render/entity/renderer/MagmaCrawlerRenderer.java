package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.MagmaCrawlerModel;
import erebus.client.render.entity.renderer.state.MagmaCrawlerRenderState;
import erebus.entity.MagmaCrawler;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class MagmaCrawlerRenderer extends MobRenderer<MagmaCrawler, MagmaCrawlerRenderState, MagmaCrawlerModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/magma_crawler.png");

	public MagmaCrawlerRenderer(EntityRendererProvider.Context context) {
        super(context, new MagmaCrawlerModel(context.bakeLayer(ModEntityRendering.MAGMA_CRAWLER)), 0.45F);
	}

	@Override
	public void extractRenderState(MagmaCrawler entity, MagmaCrawlerRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public MagmaCrawlerRenderState createRenderState() {
		return new MagmaCrawlerRenderState();
	}

	@Override
	public Identifier getTextureLocation(MagmaCrawlerRenderState state) {
		return TEXTURE;
	}
}
