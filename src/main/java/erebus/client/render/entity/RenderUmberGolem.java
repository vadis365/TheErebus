package erebus.client.render.entity;

import erebus.client.model.entity.ModelUmberGolem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderUmberGolem extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/umberGolem.png");

	public RenderUmberGolem(ModelUmberGolem model, float shadowSize) {
		super(model, shadowSize);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		float f1 = 0.75F;
		shadowSize = 0.5F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}