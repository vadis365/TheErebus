package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelRhinoBeetle;
import erebus.entity.EntityRhinoBeetle;

public class RenderRhinoBeetle extends RenderLiving
{
	private static final ResourceLocation[] textures = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/rhinoBeetle.png"), new ResourceLocation("erebus:textures/entity/rhinoBeetleKit.png") };

	public RenderRhinoBeetle(ModelRhinoBeetle modelBase, float shadowSize)
	{
		super(modelBase, shadowSize);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime)
	{
		float size = 1.5F;
		GL11.glScalef(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		EntityRhinoBeetle beetle = (EntityRhinoBeetle) entity;
		if (beetle.getTameState() < 2)
		{
			return textures[0];
		} else
		{
			return textures[1];
		}
	}
}