package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelBlackAnt;
import erebus.entity.EntityBlackAnt;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderBlackAnt extends RenderLiving {

	private static ResourceLocation texture = new ResourceLocation("erebus:textures/entity/blackAntKit.png");

	public RenderBlackAnt() {
		super(new ModelBlackAnt(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		scaleBlackAnt((EntityBlackAnt) entityliving, partialTickTime);
	}

	public void renderBlackAnt(EntityBlackAnt entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		// double a = Math.toRadians(entity.renderYawOffset);
		// double offSetX = -Math.sin(a) * 1D;
		// double offSetZ = Math.cos(a) * 1D;
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
		return texture;
	}
}