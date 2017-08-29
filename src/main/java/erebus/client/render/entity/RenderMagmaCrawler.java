package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelMagmaCrawler;
import erebus.entity.EntityMagmaCrawler;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMagmaCrawler extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/magmaCrawler.png");

	public RenderMagmaCrawler() {
		super(new ModelMagmaCrawler(), 0.0F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
		scaleCrawler();
		EntityMagmaCrawler crawler = (EntityMagmaCrawler) entity;
		if (crawler.isOnCeiling())
			rotate(entity);
	}

	protected void rotate(EntityLivingBase entityliving) {
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180.0F, 0F, 1.0F, 0.0F);
		GL11.glTranslatef(0.0F, 1.25F, 0.0F);
	}

	protected void scaleCrawler() {
		float size = 0.9F;
		GL11.glScalef(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}