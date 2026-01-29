package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.BotFlyModel;
import erebus.client.render.entity.renderer.layer.BotFlyLayer;
import erebus.client.render.entity.renderer.state.BotFlyRenderState;
import erebus.entity.BotFly;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class BotFlyRenderer extends MobRenderer<BotFly, BotFlyRenderState, BotFlyModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/bot_fly.png");

	public BotFlyRenderer(EntityRendererProvider.Context context) {
        super(context, new BotFlyModel(context.bakeLayer(ModEntityRendering.BOT_FLY)), 0.3F);
        addLayer(new BotFlyLayer(this, context.getModelSet()));
	}

	@Override
	public BotFlyRenderState createRenderState() {
		return new BotFlyRenderState();
	}

	@Override
	public Identifier getTextureLocation(BotFlyRenderState state) {
		return TEXTURE;
	}
}
