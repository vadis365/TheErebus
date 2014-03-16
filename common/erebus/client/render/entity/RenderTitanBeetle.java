package erebus.client.render.entity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelTitanBeetle;
import erebus.entity.EntityTitanBeetle;

@SideOnly(Side.CLIENT)
public class RenderTitanBeetle extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/titanBeetle.png");

	public RenderTitanBeetle() {
		super(new ModelTitanBeetle(), 1.5F);
	}

	public void renderTitanBeetle(EntityTitanBeetle entityTitanBeetle, double x, double y, double z, float rotationYaw, float partialTickTime) {
		if(entityTitanBeetle.getTameState()==3) {
			double a = Math.toRadians(entityTitanBeetle.renderYawOffset);
			double offSetX = -Math.sin(a) * -1.D;
			double offSetZ = Math.cos(a) * -1.D;
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x, (float) y, (float) z);
			GL11.glTranslated(offSetX, 1.5D, offSetZ);
			GL11.glRotatef(-entityTitanBeetle.renderYawOffset+90F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-5F, 0.0F, 0.0F, 1.0F);
			bindTexture(TextureMap.locationBlocksTexture);
			renderBlocks.renderBlockAsItem(Block.chest, 0, 1.0F);
			GL11.glPopMatrix();
		}
		super.doRenderLiving(entityTitanBeetle, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderTitanBeetle((EntityTitanBeetle) entityLiving, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderTitanBeetle((EntityTitanBeetle) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		scaleTitanBeetle((EntityTitanBeetle) entityliving, f);
		
	}

	protected void scaleTitanBeetle(EntityTitanBeetle entityTitanBeetle, float f) {
		float f1 = 1.5F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
