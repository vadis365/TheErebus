package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelRhinoBeetle;
import erebus.entity.EntityRhinoBeetle;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderRhinoBeetle extends RenderLiving {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/rhinoBeetle.png");
	private static final ResourceLocation TEXTURE_UNTAME = new ResourceLocation("erebus:textures/entity/rhinoBeetleKit.png");

	public RenderRhinoBeetle() {
		super(new ModelRhinoBeetle(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		GL11.glScalef(1.5F, 1.5F, 1.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((EntityRhinoBeetle) entity).getTameState() < 2 ? TEXTURE : TEXTURE_UNTAME;
	}
}