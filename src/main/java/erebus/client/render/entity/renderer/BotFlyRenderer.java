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
import org.jspecify.annotations.NonNull;

public class BotFlyRenderer extends MobRenderer<BotFly, BotFlyRenderState, BotFlyModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/bot_fly.png");

	public BotFlyRenderer(EntityRendererProvider.Context context) {
        super(context, new BotFlyModel(context.bakeLayer(ModEntityRendering.BOT_FLY)), 0.3F);
        addLayer(new BotFlyLayer(this, context.getModelSet()));
	}

	@Override
	public void extractRenderState(BotFly entity, BotFlyRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		float smoothedTicks = entity.animationTicks + (entity.animationTicks - entity.prevAnimationTicks) * partialTicks;
		state.flap = (float) (Math.sin((smoothedTicks) * 1.2F) * 0.5F);
		if(entity.onGround()) state.flap = 0;
	}

	@Override
	public BotFlyRenderState createRenderState() {
		return new BotFlyRenderState();
	}

	@Override
	public @NonNull Identifier getTextureLocation(BotFlyRenderState state) {
		return TEXTURE;
	}
}
