package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelLavaWebSpider;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderLavaWebSpider extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/lavaWebSpider.png");

	public RenderLavaWebSpider() {
		super(new ModelLavaWebSpider(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		GL11.glScalef(1.8F, 1.8F, 1.8F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}