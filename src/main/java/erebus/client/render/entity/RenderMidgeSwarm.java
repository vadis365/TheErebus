package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelMidgeSwarm;
import erebus.entity.EntityMidgeSwarm;

@SideOnly(Side.CLIENT)
public class RenderMidgeSwarm extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/midgeSwarm.png");

	public RenderMidgeSwarm() {
		super(new ModelMidgeSwarm(), 0.5F);
	}

	public void renderMidgeSwarm(EntityMidgeSwarm entityMidgeSwarm, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityMidgeSwarm, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderMidgeSwarm((EntityMidgeSwarm) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderMidgeSwarm((EntityMidgeSwarm) entity, x, y, z, rotationYaw, partialTickTime);
	}
	
	@Override
	protected void preRenderCallback(EntityLivingBase entityLiving, float partialTickTime) {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}