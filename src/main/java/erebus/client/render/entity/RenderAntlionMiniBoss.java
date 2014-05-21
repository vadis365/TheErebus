package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelAntlion;
import erebus.entity.EntityAntlionMiniBoss;

@SideOnly(Side.CLIENT)
public class RenderAntlionMiniBoss extends RenderLiving {

	private static ResourceLocation texture = new ResourceLocation("erebus:textures/entity/antlionSandstone.png");

	public RenderAntlionMiniBoss(ModelAntlion model, float shadowSize) {
		super(model, shadowSize);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving((EntityAntlionMiniBoss) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleAntlionMiniBoss((EntityAntlionMiniBoss) entityliving, f);
	}

	protected void scaleAntlionMiniBoss(EntityAntlionMiniBoss entityAntlionMiniBoss, float f) {
		float f1 = 1.0F;
		shadowSize = f1;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
