package erebus.client.render.entity;

import erebus.client.model.entity.ModelLavaWebSpider;
import erebus.entity.EntityLavaWebSpider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLavaWebSpider extends RenderLiving<EntityLavaWebSpider> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/lava_web_spider.png");

	public RenderLavaWebSpider(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelLavaWebSpider(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityLavaWebSpider spider, float partialTickTime) {
		GlStateManager.scale(1.8F, 1.8F, 1.8F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLavaWebSpider spider) {
		return TEXTURE;
	}
}