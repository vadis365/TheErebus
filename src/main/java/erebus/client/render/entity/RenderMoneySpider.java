package erebus.client.render.entity;

import erebus.client.model.entity.ModelScytodes;
import erebus.entity.EntityMoneySpider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMoneySpider extends RenderLiving<EntityMoneySpider> {

	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
			new ResourceLocation("erebus:textures/entity/money_spider.png"),
			new ResourceLocation("erebus:textures/entity/money_spider_euro.png"),
			new ResourceLocation("erebus:textures/entity/money_spider_pound.png") };

	public RenderMoneySpider(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelScytodes(), 0.15F);
	}

	@Override
	protected void preRenderCallback(EntityMoneySpider spider, float partialTickTime) {
		GlStateManager.scale(0.3F, 0.3F, 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMoneySpider spider) {
		return TEXTURES[spider.getSkin()];
	}
}