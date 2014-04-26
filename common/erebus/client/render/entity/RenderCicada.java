package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelCicada;
import erebus.entity.EntityCicada;

public class RenderCicada extends RenderLiving {
	protected ModelCicada model;
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/cicada.png");

	public RenderCicada(ModelCicada modelBase, float shadowSize) {
		super(modelBase, shadowSize);
		model = (ModelCicada) mainModel;
	}

	public void renderCicada(EntityCicada entityCicada, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityCicada, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderCicada((EntityCicada) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderCicada((EntityCicada) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		scaleCicada((EntityCicada) entityliving, partialTickTime);
	}

	protected void scaleCicada(EntityCicada entityCicada, float partialTickTime) {
		float size = 0.7F;
		shadowSize = 0.3F;
		GL11.glScalef(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
