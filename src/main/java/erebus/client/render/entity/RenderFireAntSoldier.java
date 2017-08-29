package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelFireAntSoldier;
import erebus.entity.EntityFireAntSoldier;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderFireAntSoldier extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/fireAntSoldier.png");

	public RenderFireAntSoldier() {
		super(new ModelFireAntSoldier(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		GL11.glScalef(0.75F, 0.75F, 0.75F);
		if (((EntityFireAntSoldier) entityliving).isClimbing())
			GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}