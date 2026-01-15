package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.ScytodesModel;
import erebus.entity.MoneySpider;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MoneySpiderRenderer extends MobRenderer<MoneySpider, ScytodesModel<MoneySpider>> {

	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
			Erebus.prefix("textures/entity/money_spider.png"),
			Erebus.prefix("textures/entity/money_spider_euro.png"),
			Erebus.prefix("textures/entity/money_spider_pound.png") };

	public MoneySpiderRenderer(EntityRendererProvider.Context context) {
		super(context, new ScytodesModel<>(context.bakeLayer(ModEntityRendering.MONEY_SPIDER)), 0.15F);
	}

	@Override
	protected void scale(MoneySpider entity, PoseStack matrix, float partialTickTime) {
		matrix.scale(0.3F, 0.3F, 0.3F);
	}

	@Override
	public ResourceLocation getTextureLocation(MoneySpider spider) {
		return TEXTURES[spider.getSkin()];
	}
}
