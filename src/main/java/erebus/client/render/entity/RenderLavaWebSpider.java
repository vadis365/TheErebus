package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelLavaWebSpider;
import erebus.entity.EntityLavaWebSpider;

public class RenderLavaWebSpider extends RenderLiving {
	protected ModelLavaWebSpider model;
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/lavaWebSpider.png");

	public RenderLavaWebSpider(ModelLavaWebSpider modelBase, float shadowSize) {
		super(modelBase, shadowSize);
		model = (ModelLavaWebSpider) mainModel;
	}

	public void renderLavaWebSpider(EntityLavaWebSpider entityLavaWebSpider, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRender(entityLavaWebSpider, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderLavaWebSpider((EntityLavaWebSpider) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderLavaWebSpider((EntityLavaWebSpider) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		scaleLavaWebSpider((EntityLavaWebSpider) entityliving, partialTickTime);
	}

	protected void scaleLavaWebSpider(EntityLavaWebSpider entityLavaWebSpider, float partialTickTime) {
		float size = 1.8F;
		shadowSize = 0.3F;
		GL11.glScalef(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}