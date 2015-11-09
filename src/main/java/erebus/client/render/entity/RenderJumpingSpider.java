package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelJumpingSpider;
import erebus.entity.EntityJumpingSpider;

public class RenderJumpingSpider extends RenderLiving {

	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/jumpingSpider.png"), new ResourceLocation("erebus:textures/entity/jumpingSpiderGreen.png"), new ResourceLocation("erebus:textures/entity/jumpingSpiderRed.png") };

	public RenderJumpingSpider() {
		super(new ModelJumpingSpider(), 0.7F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		GL11.glScalef(0.7F, 0.7F, 0.7F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURES[Math.min(TEXTURES.length - 1, ((EntityJumpingSpider) entity).getSkin())];
	}
}