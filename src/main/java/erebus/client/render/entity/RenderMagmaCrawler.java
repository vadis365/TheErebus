package erebus.client.render.entity;

import erebus.client.model.entity.ModelMagmaCrawler;
import erebus.entity.EntityMagmaCrawler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMagmaCrawler extends RenderLiving<EntityMagmaCrawler> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/magma_crawler.png");

	public RenderMagmaCrawler(RenderManager rendermangerIn) {
		super(rendermangerIn, new ModelMagmaCrawler(), 0.0F);
	}

	@Override
	protected void preRenderCallback(EntityMagmaCrawler crawler, float partialTickTime) {
		scaleCrawler();
		if (crawler.isOnCeiling())
			rotate(crawler);
	}

	protected void rotate(EntityMagmaCrawler crawler) {
		GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(180.0F, 0F, 1.0F, 0.0F);
		GlStateManager.translate(0.0F, 1.25F, 0.0F);
	}

	protected void scaleCrawler() {
		float size = 0.9F;
		GlStateManager.scale(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMagmaCrawler crawler) {
		return TEXTURE;
	}
}