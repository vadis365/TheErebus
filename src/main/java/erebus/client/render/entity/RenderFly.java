package erebus.client.render.entity;

import erebus.client.model.entity.ModelFly;
import erebus.entity.EntityFly;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFly extends RenderLiving<EntityFly> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/fly.png");

	public RenderFly(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelFly(), 0.25F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFly entity) {
		return TEXTURE;
	}

	@Override
	protected void preRenderCallback(EntityFly entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(0.75F, 0.75F, 0.75F);
	}

	@Override
	protected void applyRotations(EntityFly entityLiving, float rotationPitch, float rotationYaw, float partialTicks) {
		EntityFly fly = (EntityFly) entityLiving;
		if (fly.getIsFlyHanging()) {
			GlStateManager.translate(0F, 0.5F, 0F);
			GlStateManager.rotate(180F, 1F, 0F, 0F);
		}
		else
			GlStateManager.translate(0.0F, MathHelper.cos(rotationPitch * 0.3F) * 0.1F, 0.0F);
		super.applyRotations(entityLiving, rotationPitch, rotationYaw, partialTicks);
	}
}