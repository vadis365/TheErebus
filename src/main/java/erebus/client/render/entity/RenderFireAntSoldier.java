package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelFireAntSoldier;
import erebus.entity.EntityFireAntSoldier;

public class RenderFireAntSoldier extends RenderLiving {
	protected ModelFireAntSoldier model;
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/fireAntSoldier.png");

	public RenderFireAntSoldier(ModelFireAntSoldier modelBase, float shadowSize) {
		super(modelBase, shadowSize);
		model = (ModelFireAntSoldier) mainModel;
	}

	public void renderFireAntSoldier(EntityFireAntSoldier entityFireAntSoldier, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRender(entityFireAntSoldier, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderFireAntSoldier((EntityFireAntSoldier) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderFireAntSoldier((EntityFireAntSoldier) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		scaleFireAntSoldier((EntityFireAntSoldier) entityliving, partialTickTime);
		EntityFireAntSoldier entityFireAntSoldier = (EntityFireAntSoldier) entityliving;
		if (entityFireAntSoldier.isClimbing())
			rotateAnt(entityliving);
	}

	protected void rotateAnt(EntityLivingBase entityliving) {
		GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
	}

	protected void scaleFireAntSoldier(EntityFireAntSoldier entityFireAntSoldier, float partialTickTime) {
		float f1 = 0.75F;
		shadowSize = 0.5F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}