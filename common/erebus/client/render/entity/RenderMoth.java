package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelMoth;
import erebus.entity.EntityMoth;

@SideOnly(Side.CLIENT)
public class RenderMoth extends RenderLiving {
	private static final ResourceLocation resource1 = new ResourceLocation("erebus:textures/entity/Moth.png");
	private static final ResourceLocation resource2 = new ResourceLocation("erebus:textures/entity/Moth2.png");
	private static final ResourceLocation resource3 = new ResourceLocation("erebus:textures/entity/Moth3.png");

	public RenderMoth() {
		super(new ModelMoth(), 0.3F);
	}

	public void renderMoth(EntityMoth entityMoth, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityMoth, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderMoth((EntityMoth) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderMoth((EntityMoth) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		EntityMoth entityMoth = (EntityMoth) entityliving;
		if (entityMoth.getIsMothHanging())
			GL11.glRotatef(180.0F, -1.0F, 0.0F, 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityMoth entityMoth = (EntityMoth) entity;
		switch (entityMoth.skin) {
			case 0:
				return resource1;
			case 1:
				return resource2;
			case 2:
				return resource3;
		}
		return resource1;
	}
}
