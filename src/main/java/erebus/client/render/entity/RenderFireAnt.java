package erebus.client.render.entity;

import erebus.client.model.entity.ModelFireAnt;
import erebus.entity.EntityFireAnt;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderFireAnt extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/fireAnt.png");

	public RenderFireAnt(ModelFireAnt modelBase, float shadowSize) {
		super(modelBase, shadowSize);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		scaleFireAnt((EntityFireAnt) entityliving, partialTickTime);
		EntityFireAnt entityFireAnt = (EntityFireAnt) entityliving;
		if (entityFireAnt.isClimbing())
			rotateAnt(entityliving);
	}

	protected void rotateAnt(EntityLivingBase entityliving) {
		GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
	}

	protected void scaleFireAnt(EntityFireAnt entityFireAnt, float partialTickTime) {
		float size = 0.5F;
		shadowSize = 0.3F;
		GL11.glScalef(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}