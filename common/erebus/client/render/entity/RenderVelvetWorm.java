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
	private final ResourceLocation resource1 = new ResourceLocation("erebus:textures/entity/velvetworm.png");
	private final ResourceLocation resource2 = new ResourceLocation("erebus:textures/entity/velvetworm2.png");

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
		EntityVelvetWorm worm = (EntityVelvetWorm) entityLiving;
		int size = worm.getInflateSize();
		GL11.glScalef((float) (size*0.005 +1F),(float) (size*0.005 +1F), (float) (-size*0.0025 +1F));
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		if (((EntityVelvetWorm) entity).skin == 0)
			return resource2;
		else
			return resource1;
	}
}
