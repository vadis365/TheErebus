package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelWheatWeevil;
import erebus.entity.EntityWheatWeevil;

@SideOnly(Side.CLIENT)
public class RenderWheatWeevil extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/wheatWeevil.png");

	public RenderWheatWeevil(ModelWheatWeevil model, float shadowSize) {
		super(model, shadowSize);

		setRenderPassModel(new ModelWheatWeevil());
	}

	public void renderWheatWeevil(EntityWheatWeevil entityWheatWeevil, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityWheatWeevil, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderWheatWeevil((EntityWheatWeevil) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderWheatWeevil((EntityWheatWeevil) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleWheatWeevil((EntityWheatWeevil) entityliving, f);
	}

	protected void scaleWheatWeevil(EntityWheatWeevil entityWheatWeevil, float f) {
		float f1 = 0.6F;
		shadowSize = 0.0F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
