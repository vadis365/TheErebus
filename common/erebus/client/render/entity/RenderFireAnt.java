package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelFireAnt;
import erebus.entity.EntityFireAnt;

public class RenderFireAnt extends RenderLiving {
	protected ModelFireAnt model;
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/ModelFireant.png");

	public RenderFireAnt(ModelFireAnt modelBase, float shadowSize) {
		super(modelBase, shadowSize);
		model = (ModelFireAnt) mainModel;
	}

	public void renderFireAnt(EntityFireAnt entityFireAnt, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityFireAnt, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderFireAnt((EntityFireAnt) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderFireAnt((EntityFireAnt) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		scaleFireAnt((EntityFireAnt) entityliving, partialTickTime);
		EntityFireAnt entityFireAnt = (EntityFireAnt) entityliving;
		if (entityFireAnt.isClimbing())
			rotateAnt(entityliving);
	}

	protected void rotateAnt(EntityLivingBase entityliving) {
		GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
	}

	protected void scaleFireAnt(EntityFireAnt entityFireAnt, float partialTickTime) {
		float size = 0.5F;
		shadowSize = 0.3F;
		GL11.glScalef(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
