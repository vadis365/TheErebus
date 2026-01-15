package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.BotFlyModel;
import erebus.entity.BotFly;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BotFlyRenderer extends MobRenderer<BotFly, BotFlyModel<BotFly>> {
	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/bot_fly.png");

	public BotFlyRenderer(EntityRendererProvider.Context context) {
        super(context, new BotFlyModel<>(context.bakeLayer(ModEntityRendering.BOT_FLY)), 0.3F);
        addLayer(new BotFlyLayer(this, context.getModelSet()));
	}

	@Override
	public  ResourceLocation getTextureLocation(BotFly entity) {
		return TEXTURE;
	}
}
