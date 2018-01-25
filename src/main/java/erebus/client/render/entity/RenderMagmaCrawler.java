package erebus.client.render.entity;

import erebus.client.model.entity.ModelMagmaCrawler;
import erebus.entity.EntityMagmaCrawler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumFacing;
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
		scaleCrawler(0.75F);
		if (crawler.getOnCeiling()) {
			rotate(180F, 180F, 0);
			GlStateManager.translate(0.0F, 1.2F, 0.0F);
		}
		else
			GlStateManager.translate(0.0F, 0F, 0.0F);
	}

	protected void rotate(float crawlerAngleX, float crawlerAngleY, float crawlerAngleZ) {
		GlStateManager.rotate(crawlerAngleX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(crawlerAngleY, 0F, 1.0F, 0.0F);
		GlStateManager.rotate(crawlerAngleZ, 0F, 0.0F, 1.0F);
	}

	protected void scaleCrawler(float size) {
		GlStateManager.scale(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMagmaCrawler crawler) {
		return TEXTURE;
	}
}