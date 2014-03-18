package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelTitanBeetle;
import erebus.entity.EntityTitanBeetle;

@SideOnly(Side.CLIENT)
public class RenderTitanBeetle extends RenderLiving {
	private static final ResourceLocation[] textures = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/titanBeetle.png"), new ResourceLocation("erebus:textures/entity/titanBeetleKit.png"), new ResourceLocation("erebus:textures/entity/titanBeetleChested.png") };

	public RenderTitanBeetle() {
		super(new ModelTitanBeetle(), 1.5F);
	}

	public void renderTitanBeetle(EntityTitanBeetle entityTitanBeetle, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityTitanBeetle, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderTitanBeetle((EntityTitanBeetle) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderTitanBeetle((EntityTitanBeetle) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleTitanBeetle((EntityTitanBeetle) entityliving, f);
		
	}

	protected void scaleTitanBeetle(EntityTitanBeetle entityTitanBeetle, float f) {
		float f1 = 1.5F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityTitanBeetle beetle = (EntityTitanBeetle) entity;
		if(beetle.getTameState()<2)
			return textures[0];
		if(beetle.getTameState()==2)
			return textures[1];
		else 
			return textures[2];
	}
}
