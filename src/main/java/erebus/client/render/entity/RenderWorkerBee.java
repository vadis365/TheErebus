package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelWorkerBee;
import erebus.entity.EntityWorkerBee;

@SideOnly(Side.CLIENT)
public class RenderWorkerBee extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/workerBee.png");

	public RenderWorkerBee() {
		super(new ModelWorkerBee(), 0.5F);
	}

	public void renderWorkerBee(EntityWorkerBee entityWorkerBee, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRender(entityWorkerBee, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderWorkerBee((EntityWorkerBee) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderWorkerBee((EntityWorkerBee) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleWorkerBee((EntityWorkerBee) entityliving, f);
	}

	protected void scaleWorkerBee(EntityWorkerBee entityWorkerBee, float f) {
		float f1 = 1F;
		shadowSize = f1;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}