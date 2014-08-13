package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelWasp;
import erebus.entity.EntityWasp;

@SideOnly(Side.CLIENT)
public class RenderWasp extends RenderLiving
{
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/wasp.png");

	public RenderWasp()
	{
		super(new ModelWasp(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		scaleWasp((EntityWasp) entityliving, f);
	}

	protected void scaleWasp(EntityWasp entityWasp, float f)
	{
		if (entityWasp.getIsBoss() == 0)
		{
			float f1 = 1F;
			shadowSize = f1;
			GL11.glScalef(f1, f1, f1);
		}
		if (entityWasp.getIsBoss() == 1)
		{
			float f1 = 2.0F;
			shadowSize = f1;
			GL11.glScalef(f1, f1, f1);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
}