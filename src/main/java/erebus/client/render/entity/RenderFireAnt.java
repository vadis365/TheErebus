package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelFireAnt;
import erebus.entity.EntityFireAnt;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderFireAnt extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/fireAnt.png");

	public RenderFireAnt() {
		super(new ModelFireAnt(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		if (((EntityFireAnt) entityliving).isClimbing())
			GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}