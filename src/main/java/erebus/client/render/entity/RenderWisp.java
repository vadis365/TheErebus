package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityWisp;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderWisp extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/wisp.png");

	public RenderWisp() {
		super(null, 0.5F);
	}

	@Override
	public void doRender(EntityLiving entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glDepthMask(false);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 1);
		bindTexture(texture);
		EntityWisp wisp = (EntityWisp) entity;
		GL11.glScalef(wisp.particleSize / 3 + 0.25F, wisp.particleSize / 3 + 0.25F, wisp.particleSize / 3 + 0.25F);
		func_77026_a(Tessellator.instance, 0, 0xFFEC810E);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(3042);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y + 0.05F, (float) z);
		GL11.glDepthMask(false);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 1);
		bindTexture(texture);
		GL11.glScalef(wisp.particleSize / 6 + 0.25F, wisp.particleSize / 6 + 0.25F, wisp.particleSize / 6 + 0.2F);
		func_77026_a(Tessellator.instance, 0, 0xFFEC810E);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(3042);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
	}

	private void func_77026_a(Tessellator tess, int x, int color) {
		float var3 = (x % 32 * 32 + 0) / 32.0F;
		float var4 = (x % 32 * 32 + 32) / 32.0F;
		float var5 = (x / 32 * 32 + 0) / 32.0F;
		float var6 = (x / 32 * 32 + 32) / 32.0F;
		float var7 = 1.0F;
		float var8 = 0.5F;
		float var9 = 0.25F;
		GL11.glRotatef(180.0F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		tess.startDrawingQuads();

		float o = (color >> 24 & 0xff) / 255F;
		float r = (color >> 16 & 0xff) / 255F;
		float g = (color >> 8 & 0xff) / 255F;
		float b = (color & 0xff) / 255F;

		tess.setColorRGBA_F(r, g, b, o);
		tess.setNormal(0.0F, 1.0F, 0.0F);
		tess.setBrightness(50);
		tess.addVertexWithUV(0.0F - var8, 0.0F - var9, 0.0D, var3, var6);
		tess.addVertexWithUV(var7 - var8, 0.0F - var9, 0.0D, var4, var6);
		tess.addVertexWithUV(var7 - var8, var7 - var9, 0.0D, var4, var5);
		tess.addVertexWithUV(0.0F - var8, var7 - var9, 0.0D, var3, var5);
		tess.draw();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}