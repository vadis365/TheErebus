package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.LavaWebSpiderModel;
import erebus.client.render.entity.renderer.layer.LavaWebSpiderLayer;
import erebus.entity.LavaWebSpider;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class LavaWebSpiderRenderer extends MobRenderer<LavaWebSpider, LavaWebSpiderModel<LavaWebSpider>> {
	public static final Identifier TEXTURE = Erebus.prefix("textures/entity/lava_web_spider.png");

	public LavaWebSpiderRenderer(EntityRendererProvider.Context context) {
        super(context, new LavaWebSpiderModel<>(context.bakeLayer(ModEntityRendering.LAVA_WEB_SPIDER)), 1.8F);
        addLayer(new LavaWebSpiderLayer(this, context.getModelSet()));
    }

	@Override
	protected void scale(LavaWebSpider entity, PoseStack matrix, float partialTickTime) {
		matrix.scale(1.8F, 1.8F, 1.8F);
	}

	@Override
	public  Identifier getTextureLocation(LavaWebSpider entity) {
		return TEXTURE;
	}
}
