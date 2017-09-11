package erebus.client.render.entity;

import erebus.client.model.entity.ModelJumpingSpider;
import erebus.entity.EntityJumpingSpider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderJumpingSpider extends RenderLiving<EntityJumpingSpider> {

	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
			new ResourceLocation("erebus:textures/entity/jumping_spider.png"),
			new ResourceLocation("erebus:textures/entity/jumping_spider_green.png"),
			new ResourceLocation("erebus:textures/entity/jumping_spider_red.png") };

	public RenderJumpingSpider(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelJumpingSpider(), 0.7F);
	}

	@Override
	protected void preRenderCallback(EntityJumpingSpider spider, float partialTickTime) {
		GlStateManager.scale(0.7F, 0.7F, 0.7F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityJumpingSpider spider) {
		return TEXTURES[spider.getSkin()];
	}
}