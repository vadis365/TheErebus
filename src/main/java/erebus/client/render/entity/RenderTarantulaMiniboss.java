package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelTarantula;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityTarantulaMiniboss;

@SideOnly(Side.CLIENT)
public class RenderTarantulaMiniboss extends RenderLiving
{

	private final ResourceLocation resource1 = new ResourceLocation("erebus:textures/entity/tarantula.png");
	private final ResourceLocation resource2 = new ResourceLocation("erebus:textures/entity/tarantulaTurqoise.png");

	public RenderTarantulaMiniboss()
	{
		super(new ModelTarantula(), 0.5F);
		
	}
	
	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime)
	{
		EntityTarantulaMiniboss tarantula = (EntityTarantulaMiniboss) entityliving;
		BossStatus.setBossStatus(tarantula, false);
		float size = 2.0F;
		shadowSize = 0.3F;
		GL11.glScalef(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		EntityTarantulaMiniboss tarantula = (EntityTarantulaMiniboss) entity;
		if (tarantula.skin <= 4)
		{
			return resource2;
		} else
		{
			return resource1;
		}
	}
}