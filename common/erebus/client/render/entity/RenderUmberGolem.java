package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelUmberGolem;
import erebus.entity.EntityUmberGolem;

public class RenderUmberGolem extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/UmberGolem.png");

	public RenderUmberGolem(ModelUmberGolem model, float shadowSize) {
		super(model, shadowSize);

	}

	public void renderUmberGolem(EntityUmberGolem entityUmberGolem, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityUmberGolem, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderUmberGolem((EntityUmberGolem) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderUmberGolem((EntityUmberGolem) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleGolem((EntityUmberGolem) entityliving, f);
	}

	protected void scaleGolem(EntityUmberGolem entityUmberGolem, float f) {
		float f1 = 0.75F;
		shadowSize = 0.5F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}

}
