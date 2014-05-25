package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelCicada;

public class RenderCicada extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/cicada.png");

	public RenderCicada(ModelCicada modelBase, float shadowSize) {
		super(modelBase, shadowSize);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		float size = 0.7F;
		shadowSize = 0.3F;
		GL11.glScalef(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}