package erebus.client.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class GuiErebus extends GuiContainer {

	public GuiErebus(Container container) {
		super(container);
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

	protected void drawTexturedModalRectFloat(float p_73729_1_, float p_73729_2_, float p_73729_3_, float p_73729_4_, float p_73729_5_, float p_73729_6_) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuffer();
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(p_73729_1_ + 0, p_73729_2_ + p_73729_6_, zLevel).tex((p_73729_3_ + 0) * f, (p_73729_4_ + p_73729_6_) * f1).endVertex();
		buffer.pos(p_73729_1_ + p_73729_5_, p_73729_2_ + p_73729_6_, zLevel).tex((p_73729_3_ + p_73729_5_) * f, (p_73729_4_ + p_73729_6_) * f1).endVertex();
		buffer.pos(p_73729_1_ + p_73729_5_, p_73729_2_ + 0, zLevel).tex((p_73729_3_ + p_73729_5_) * f, (p_73729_4_ + 0) * f1).endVertex();
		buffer.pos(p_73729_1_ + 0, p_73729_2_ + 0, zLevel).tex((p_73729_3_ + 0) * f, (p_73729_4_ + 0) * f1).endVertex();
		tessellator.draw();
	}

	protected void drawToolTip(int mouseX, int mouseY, String text) {
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		int k = 0;
		int l = fontRenderer.getStringWidth(text);

		if (l > k)
			k = l;

		int i1 = mouseX + 12;
		int j1 = mouseY - 12;
		int k1 = 8;

		if (i1 + k > width)
			i1 -= 28 + k;

		if (j1 + k1 + 6 > height)
			j1 = height - k1 - 6;

		zLevel = 300.0F;
		itemRender.zLevel = 300.0F;
		int l1 = -267386864;
		drawGradientRect(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
		drawGradientRect(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
		drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
		drawGradientRect(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
		drawGradientRect(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
		int i2 = 1347420415;
		int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
		drawGradientRect(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
		drawGradientRect(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
		drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
		drawGradientRect(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);

		fontRenderer.drawStringWithShadow(text, i1, j1, -1);

		zLevel = 0.0F;
		itemRender.zLevel = 0.0F;
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		RenderHelper.enableStandardItemLighting();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	}
/*
	protected void drawFluid(FluidStack fluid, int fluidHeight, int x, int y, int width, int height) {
		if (fluid == null || fluid.amount <= 0)
			return;
		if (fluid.getFluid().getDensity(fluid) < 0)
			y += fluidHeight - height;
		
		
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluid.getFluid().getStill().toString());
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuffer();
		mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(xOffSet + 156, yOffSet + 128, zLevel).tex(sprite.getMinU(), sprite.getMinV()).endVertex();
		buffer.pos(xOffSet + 168, yOffSet + 128, zLevel).tex(sprite.getMaxU(), sprite.getMinV()).endVertex();
		buffer.pos(xOffSet + 168, yOffSet + 128 - fluid, zLevel).tex(sprite.getMaxU(), sprite.getMaxV()).endVertex();
		buffer.pos(xOffSet + 156, yOffSet + 128 - fluid, zLevel).tex(sprite.getMinU(), sprite.getMaxV()).endVertex();
		tessellator.draw();

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		IIcon icon = fluid.getFluid().getStill();
		mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		glColour(fluid.getFluid().getColor(fluid) | 0xff000000);
		int fullX = width / 16;
		int fullY = height / 16;
		int lastX = width - fullX * 16;
		int lastY = height - fullY * 16;
		int fullLvl = (height - fluidHeight) / 16;
		int lastLvl = height - fluidHeight - fullLvl * 16;
		for (int i = 0; i < fullX; i++)
			for (int j = 0; j < fullY; j++)
				if (j >= fullLvl)
					drawCutIcon(icon, x + i * 16, y + j * 16, 16, 16, j == fullLvl ? lastLvl : 0);
		for (int i = 0; i < fullX; i++)
			drawCutIcon(icon, x + i * 16, y + fullY * 16, 16, lastY, fullLvl == fullY ? lastLvl : 0);
		for (int i = 0; i < fullY; i++)
			if (i >= fullLvl)
				drawCutIcon(icon, x + fullX * 16, y + i * 16, lastX, 16, i == fullLvl ? lastLvl : 0);
		drawCutIcon(icon, x + fullX * 16, y + fullY * 16, lastX, lastY, fullLvl == fullY ? lastLvl : 0);
		GL11.glColor3f(1, 1, 1);
	}

	protected void drawCutIcon(IIcon icon, int x, int y, int width, int height, int cut) {
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		tess.addVertexWithUV(x, y + height, zLevel, icon.getMinU(), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + height, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + cut, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(cut));
		tess.addVertexWithUV(x, y + cut, zLevel, icon.getMinU(), icon.getInterpolatedV(cut));
		tess.draw();
	}
*/
	protected void glColour(int colour) {
		float r = (colour >> 16 & 255) / 255F;
		float g = (colour >> 8 & 255) / 255F;
		float b = (colour & 255) / 255F;
		int a = colour >> 24 & 255;
		if (a <= 0)
			a = 255;

		GL11.glColor4f(r, g, b, a / 255F);
	}
}