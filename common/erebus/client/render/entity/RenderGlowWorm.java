package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelGlowWorm;
import erebus.entity.EntityGlowWorm;

@SideOnly(Side.CLIENT)
public class RenderGlowWorm extends RenderLiving {
	private static final ResourceLocation texGlowing = new ResourceLocation("erebus:textures/entity/ModelGlowWormGlow.png"), texNormal = new ResourceLocation("erebus:textures/entity/ModelGlowWorm.png");

	public RenderGlowWorm(ModelGlowWorm model, float shadowSize) {
		super(model, shadowSize);

		setRenderPassModel(new ModelGlowWorm());
	}

	public void renderGlowWorm(EntityGlowWorm entityGlowWorm, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityGlowWorm, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderGlowWorm((EntityGlowWorm) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderGlowWorm((EntityGlowWorm) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleGlowWorm((EntityGlowWorm) entityliving, f);
	}

	protected void rotateGlowWorm(EntityLivingBase entityliving) {
		GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
	}

	protected void scaleGlowWorm(EntityGlowWorm entityGlowWorm, float f) {
		float f1 = 0.75F;
		shadowSize = 0.0F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((EntityGlowWorm) entity).isGlowing() ? texGlowing : texNormal;
	}
}
