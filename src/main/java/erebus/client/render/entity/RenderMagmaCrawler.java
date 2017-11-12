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
		scaleCrawler();
		if (crawler.sideWalkingOn() == EnumFacing.UP) {
			rotate(180F, 180F, 0);
			GlStateManager.translate(0.0F, 1.25F, 0.0F);
		}
		if (crawler.sideWalkingOn() == EnumFacing.EAST) {
			if(crawler.getHorizontalFacing() == EnumFacing.SOUTH) {
				rotate(0F, 30F, -90F);
				GlStateManager.translate(0.5F, 0.7F, 0.0F);
			}
			if(crawler.getHorizontalFacing() == EnumFacing.NORTH) {
				rotate(0F, -30F, 90F);
				GlStateManager.translate(-0.5F, 0.7F, 0.0F);
			}
		}
		
		if (crawler.sideWalkingOn() == EnumFacing.WEST) {
			if(crawler.getHorizontalFacing() == EnumFacing.NORTH) {
				rotate(0F, 30F, -90F);
				GlStateManager.translate(0.5F, 0.7F, 0.0F);
			}
			if(crawler.getHorizontalFacing() == EnumFacing.SOUTH) {
				rotate(0F, -30F, 90F);
				GlStateManager.translate(-0.5F, 0.7F, 0.0F);
			}
		}
		
		if (crawler.sideWalkingOn() == EnumFacing.NORTH) {
			if(crawler.getHorizontalFacing() == EnumFacing.WEST) {
				rotate(0F, -30F, 90F);
				GlStateManager.translate(-0.5F, 0.7F, 0.0F);
			}
			if(crawler.getHorizontalFacing() == EnumFacing.EAST) {
				rotate(0F, 30F, -90F);
				GlStateManager.translate(0.5F, 0.7F, 0.0F);
			}
		}
		
		if (crawler.sideWalkingOn() == EnumFacing.SOUTH) {
			if(crawler.getHorizontalFacing() == EnumFacing.WEST) {
				rotate(0F, 30F, -90F);
				GlStateManager.translate(0.5F, 0.7F, 0.0F);
			}
			if(crawler.getHorizontalFacing() == EnumFacing.EAST) {
				rotate(0F, -30F, 90F);
				GlStateManager.translate(-0.5F, 0.7F, 0.0F);
			}
		}
		/*if (crawler.sideWalkingOn() == EnumFacing.WEST || crawler.sideWalkingOn() == EnumFacing.NORTH) {
			rotate(0F, 0F, 90F);
			GlStateManager.translate(0.0F, 0.0F, 1.25F);
		}*/
	}

	protected void rotate(float crawlerAngleX, float crawlerAngleY, float crawlerAngleZ) {
		GlStateManager.rotate(crawlerAngleX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(crawlerAngleY, 0F, 1.0F, 0.0F);
		GlStateManager.rotate(crawlerAngleZ, 0F, 0.0F, 1.0F);
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