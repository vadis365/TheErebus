package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelFireAnt;
import erebus.entity.EntityBlackAnt;

public class RenderBlackAnt extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/blackAnt.png");

	public RenderBlackAnt() {
		super(new ModelFireAnt(), 1.0F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		scaleBlackAnt((EntityBlackAnt) entityliving, partialTickTime);
		EntityBlackAnt entityBlackAnt = (EntityBlackAnt) entityliving;
		if (entityBlackAnt.isClimbing())
			rotateAnt(entityliving);
	}

	protected void rotateAnt(EntityLivingBase entityliving) {
		GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
	}

	protected void scaleBlackAnt(EntityBlackAnt entityBlackAnt, float partialTickTime) {
		float size = 0.75F;
		GL11.glScalef(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}