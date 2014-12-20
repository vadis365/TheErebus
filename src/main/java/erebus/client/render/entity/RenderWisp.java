package erebus.client.render.entity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityWisp;

@SideOnly(Side.CLIENT)
public class RenderWisp extends RenderLiving
{
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/wisp.png");
	
    public RenderWisp() {
    super(null, 0.5F);
    }

    public void doRender(EntityLiving entity, double x, double y, double z, float rotationYaw, float partialTickTime)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    	GL11.glDepthMask(false);
    	GL11.glEnable(3042);
    	GL11.glBlendFunc(770, 1);
        this.bindTexture(texture);
        Tessellator var10 = Tessellator.instance;
        EntityWisp wisp = (EntityWisp)entity;
        GL11.glScalef(wisp.particleSize / 3 + 0.25F, wisp.particleSize / 3 + 0.25F, wisp.particleSize / 3 + 0.25F);
        this.func_77026_a(var10, 0, 0xFFEC810E);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y + 0.05F, (float)z);
    	GL11.glDepthMask(false);
    	GL11.glEnable(3042);
    	GL11.glBlendFunc(770, 1);
    	this.bindTexture(texture);
        GL11.glScalef(wisp.particleSize / 6 + 0.25F, wisp.particleSize / 6 + 0.25F, wisp.particleSize / 6 + 0.2F);
        this.func_77026_a(var10, 0, 0xFFEC810E);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
    }

    private void func_77026_a(Tessellator par1Tessellator, int x, int color)
    {
    	float var3 = (float)(x % 32 * 32 + 0) / 32.0F;
        float var4 = (float)(x % 32 * 32 + 32) / 32.0F;
        float var5 = (float)(x / 32 * 32 + 0) / 32.0F;
        float var6 = (float)(x / 32 * 32 + 32) / 32.0F;
        float var7 = 1.0F;
        float var8 = 0.5F;
        float var9 = 0.25F;
        GL11.glRotatef(180.0F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        par1Tessellator.startDrawingQuads();

        float o = (float)(color >> 24 & 0xff) / 255F;
        float r = (float)(color >> 16 & 0xff) / 255F;
        float g = (float)(color >> 8 & 0xff) / 255F;
        float b = (float)(color & 0xff) / 255F;

        par1Tessellator.setColorRGBA_F(r, g, b, o);
        par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
        par1Tessellator.setBrightness(50);
        par1Tessellator.addVertexWithUV((double)(0.0F - var8), (double)(0.0F - var9), 0.0D, (double)var3, (double)var6);
        par1Tessellator.addVertexWithUV((double)(var7 - var8), (double)(0.0F - var9), 0.0D, (double)var4, (double)var6);
        par1Tessellator.addVertexWithUV((double)(var7 - var8), (double)(var7 - var9), 0.0D, (double)var4, (double)var5);
        par1Tessellator.addVertexWithUV((double)(0.0F - var8), (double)(var7 - var9), 0.0D, (double)var3, (double)var5);
        par1Tessellator.draw();
    }

    private void func_77026_a2(Tessellator par1Tessellator, int x, int color)
    {
    	float var3 = (float)(x % 32 * 32 + 0) / 32.0F;
        float var4 = (float)(x % 32 * 32 + 32) / 32.0F;
        float var5 = (float)(x / 32 * 32 + 0) / 32.0F;
        float var6 = (float)(x / 32 * 32 + 32) / 32.0F;
        float var7 = 1.0F;
        float var8 = 0.5F;
        float var9 = 0.25F;
        GL11.glRotatef(180.0F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(- renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        par1Tessellator.startDrawingQuads();

        float o = (float)(color >> 24 & 0xff) / 255F;
        float r = (float)(color >> 16 & 0xff) / 255F;
        float g = (float)(color >> 8 & 0xff) / 255F;
        float b = (float)(color & 0xff) / 255F;

        par1Tessellator.setColorRGBA_F(r, g, b, o);
        par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
        par1Tessellator.setBrightness(50);
        par1Tessellator.addVertexWithUV((double)(0.0F - var8), (double)(0.0F - var9), 0.0D, (double)var3, (double)var6);
        par1Tessellator.addVertexWithUV((double)(var7 - var8), (double)(0.0F - var9), 0.0D, (double)var4, (double)var6);
        par1Tessellator.addVertexWithUV((double)(var7 - var8), (double)(var7 - var9), 0.0D, (double)var4, (double)var5);
        par1Tessellator.addVertexWithUV((double)(0.0F - var8), (double)(var7 - var9), 0.0D, (double)var3, (double)var5);
        par1Tessellator.draw();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
