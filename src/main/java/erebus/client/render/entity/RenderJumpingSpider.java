package erebus.client.render.entity;

import erebus.client.model.entity.ModelJumpingSpider;
import erebus.entity.EntityJumpingSpider;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderJumpingSpider extends RenderLiving {
	private static final ResourceLocation[] textures = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/jumpingSpider.png"), new ResourceLocation("erebus:textures/entity/jumpingSpiderGreen.png"), new ResourceLocation("erebus:textures/entity/jumpingSpiderRed.png") };

	public RenderJumpingSpider(ModelJumpingSpider model, float shadowSize) {
		super(model, shadowSize);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		float f1 = 0.7F;
		shadowSize = f1;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return textures[Math.min(textures.length - 1, ((EntityJumpingSpider) entity).skin)];
	}
}