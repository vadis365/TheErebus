package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelBlackAnt;
import erebus.entity.EntityBlackAnt;

public class RenderBlackAnt extends RenderLiving {

	private static final ResourceLocation[] textures = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/blackAntKitBone.png"), new ResourceLocation("erebus:textures/entity/blackAntKitPlant.png") };

	public RenderBlackAnt() {
		super(new ModelBlackAnt(), 1.0F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		scaleBlackAnt((EntityBlackAnt) entityliving, partialTickTime);
	}

	public void renderBlackAnt(EntityBlackAnt entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		//		double a = Math.toRadians(entity.renderYawOffset);
		//		double offSetX = -Math.sin(a) * 1D;
		//		double offSetZ = Math.cos(a) * 1D;
		// TODO add item render code for carrying or maybe some nice hats
		super.doRender(entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderBlackAnt((EntityBlackAnt) entity, x, y, z, rotationYaw, partialTickTime);
	}

	protected void scaleBlackAnt(EntityBlackAnt entityBlackAnt, float partialTickTime) {
		float size = 0.5F;
		GL11.glScalef(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityBlackAnt ant = (EntityBlackAnt) entity;
		if (ant.getSkin() > 0)
			return textures[1];
		else
			return textures[0];
	}
}