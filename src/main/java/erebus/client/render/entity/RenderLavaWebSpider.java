package erebus.client.render.entity;

import erebus.client.model.entity.ModelLavaWebSpider;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderLavaWebSpider extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/lavaWebSpider.png");

	public RenderLavaWebSpider(ModelLavaWebSpider modelBase, float shadowSize) {
		super(modelBase, shadowSize);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		float size = 1.8F;
		shadowSize = 0.3F;
		GL11.glScalef(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}