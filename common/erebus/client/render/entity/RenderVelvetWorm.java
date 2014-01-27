package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelVelvetWorm;
import erebus.entity.EntityVelvetWorm;

@SideOnly(Side.CLIENT)
public class RenderVelvetWorm extends RenderLiving {
	private final ResourceLocation resource1 = new ResourceLocation("erebus:textures/entity/Velvet worm.png");
	private final ResourceLocation resource2 = new ResourceLocation("erebus:textures/entity/Velvet worm2.png");

	private final float scale = 1F;

	public RenderVelvetWorm() {
		super(new ModelVelvetWorm(), 0.6F);
	}

	public void renderEntityVelvetWorm(EntityVelvetWorm entityEntityVelvetWorm, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityEntityVelvetWorm, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderEntityVelvetWorm((EntityVelvetWorm) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderEntityVelvetWorm((EntityVelvetWorm) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLiving, float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		if (((EntityVelvetWorm) entity).skin <= 10)
			return resource2;
		else
			return resource1;
	}
}
