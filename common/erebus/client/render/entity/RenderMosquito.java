package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelMosquito;
import erebus.entity.EntityMosquito;

@SideOnly(Side.CLIENT)
public class RenderMosquito extends RenderLiving {
	private final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/Mosquito.png");

	public RenderMosquito() {
		super(new ModelMosquito(), 0.5F);
	}

	public void renderMosquito(EntityMosquito entityMosquito, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityMosquito, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderMosquito((EntityMosquito) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderMosquito((EntityMosquito) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glTranslatef(0.0F, -1.4F, -0.5F);
		if (entityliving.ridingEntity != null)
			GL11.glTranslatef(0.0F, 0.0F, 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
