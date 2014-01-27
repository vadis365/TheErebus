package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelBlackWidow;
import erebus.entity.EntityBlackWidow;

@SideOnly(Side.CLIENT)
public class RenderBlackWidow extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/ModelBlackWidow.png");

	public RenderBlackWidow() {
		super(new ModelBlackWidow(), 0.3F);

	}

	public void renderBlackWidow(EntityBlackWidow entityBlackWidow, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityBlackWidow, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderBlackWidow((EntityBlackWidow) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderBlackWidow((EntityBlackWidow) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		shadowSize = ((EntityBlackWidow) entityliving).getWidowSize() * 0.3F;
		GL11.glScalef(shadowSize, shadowSize, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
