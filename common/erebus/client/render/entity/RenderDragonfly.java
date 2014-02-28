package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelDragonfly;
import erebus.entity.EntityDragonfly;

@SideOnly(Side.CLIENT)
public class RenderDragonfly extends RenderLiving {
	private static final ResourceLocation texture1 = new ResourceLocation("erebus:textures/entity/dragonflyGreen.png");
	private static final ResourceLocation texture2 = new ResourceLocation("erebus:textures/entity/dragonflyRed.png");
	private static final ResourceLocation texture3 = new ResourceLocation("erebus:textures/entity/dragonflyPurple.png");
	private static final ResourceLocation texture4 = new ResourceLocation("erebus:textures/entity/dragonflyBlue.png");
	private static final ResourceLocation texture5 = new ResourceLocation("erebus:textures/entity/dragonflyTan.png");

	public RenderDragonfly() {
		super(new ModelDragonfly(), 0.3F);
	}

	public void renderDragonfly(EntityDragonfly entityDragonfly, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityDragonfly, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderDragonfly((EntityDragonfly) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderDragonfly((EntityDragonfly) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		//Other sizes to be added 
		GL11.glScalef(1.0F, 1.0F, 1.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityDragonfly entityDragonfly = (EntityDragonfly) entity;
		switch (entityDragonfly.skin) {
			case 0:
				return texture1;
			case 1:
				return texture2;
			case 2:
				return texture3;
			case 3:
				return texture4;
			case 4:
				return texture5;
		}
		return texture1;
	}
}
