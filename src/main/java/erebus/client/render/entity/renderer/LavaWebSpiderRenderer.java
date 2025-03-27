package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import erebus.Erebus;
import erebus.client.render.entity.model.LavaWebSpiderModel;
import erebus.entity.LavaWebSpider;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LavaWebSpiderRenderer extends MobRenderer<LavaWebSpider, LavaWebSpiderModel<LavaWebSpider>> {
	public static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/lava_web_spider.png");

	public LavaWebSpiderRenderer(EntityRendererProvider.Context context) {
        super(context, new LavaWebSpiderModel<>(context.bakeLayer(ModEntityRendering.LAVA_WEB_SPIDER)), 0.5F);
        addLayer(new LavaWebSpiderLayer(this, context.getModelSet()));
    }

	@Override
	protected void scale(LavaWebSpider entity, PoseStack matrix, float partialTickTime) {
		matrix.scale(1.8F, 1.8F, 1.8F);
	}

	@Override
	public  ResourceLocation getTextureLocation(LavaWebSpider entity) {
		return TEXTURE;
	}
}
