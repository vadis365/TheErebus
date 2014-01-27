package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelBombardierBeetle;
import erebus.entity.EntityBombardierBeetle;

@SideOnly(Side.CLIENT)
public class RenderBombardierBeetle extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/ModelBombardierBeetle.png");

	public RenderBombardierBeetle(ModelBombardierBeetle model, float shadowSize) {
		super(model, shadowSize);

	}

	public void renderBombardierBeetle(EntityBombardierBeetle entityBombardierBeetle, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityBombardierBeetle, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderBombardierBeetle((EntityBombardierBeetle) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderBombardierBeetle((EntityBombardierBeetle) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleBombardierBeetle((EntityBombardierBeetle) entityliving, f);
	}

	protected void scaleBombardierBeetle(EntityBombardierBeetle entityBombardierBeetle, float f) {
		float f1 = 1.5F;
		shadowSize = 0.6F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
