package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelBloodSnail;
import erebus.entity.EntityBloodSnail;

@SideOnly(Side.CLIENT)
public class RenderBloodSnail extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/bloodSnail.png");

	public RenderBloodSnail() {
		super(new ModelBloodSnail(), 0.5F);
	}

	public void renderBloodSnail(EntityBloodSnail entityBloodSnail, double x, double y, double z, float rotationYaw, float partialTickTime) {
		super.doRenderLiving(entityBloodSnail, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderBloodSnail((EntityBloodSnail) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderBloodSnail((EntityBloodSnail) entity, x, y, z, rotationYaw, partialTickTime);
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