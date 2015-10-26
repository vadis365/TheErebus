package erebus.client.gui;

import java.awt.Rectangle;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import org.lwjgl.opengl.GL11;

import erebus.inventory.ContainerSmoothieMaker;
import erebus.tileentity.TileEntitySmoothieMaker;

public class GuiSmoothieMaker extends GuiContainer {

	private TileEntitySmoothieMaker tile;
	private static final ResourceLocation gui = new ResourceLocation("erebus:textures/gui/container/smoothieMaker.png");
	private static final Rectangle[] tankPositions = new Rectangle[] { new Rectangle(8, 6, 9, 73), new Rectangle(25, 6, 9, 73), new Rectangle(142, 6, 9, 73), new Rectangle(159, 6, 9, 73) };

	public GuiSmoothieMaker(InventoryPlayer inv, TileEntitySmoothieMaker tile) {
		super(new ContainerSmoothieMaker(inv, tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(gui);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		FluidTank[] tanks = tile.getTanks();
		for (int i = 0; i < tankPositions.length; i++)
			if (tanks[i].getFluidAmount() > 0) {
				Rectangle rect = tankPositions[i];
				drawFluid(tanks[i].getFluid(), (int) (rect.height * (float) tanks[i].getFluidAmount() / tanks[i].getCapacity()), rect.x + guiLeft, rect.y + guiTop, rect.width, rect.height);
			}

		mc.renderEngine.bindTexture(gui);
		for (Rectangle rect : tankPositions)
			drawTexturedModalRect(guiLeft + rect.x, guiTop + 3 + rect.y, 176, 41, rect.width, rect.height);

		if (tile.isBlending()) {
			int i1 = tile.getBlendProgress();
			drawTexturedModalRect(guiLeft + 52, guiTop + 26, 176, 0, 73, i1 + 1);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
	}

	private void drawFluid(FluidStack fluid, int fluidHeight, int x, int y, int width, int height) {
		if (fluid == null || fluid.amount <= 0)
			return;
		if (fluid.getFluid().getDensity(fluid) < 0)
			y += fluidHeight - height;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		IIcon icon = fluid.getFluid().getStillIcon();
		mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
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

	private void drawCutIcon(IIcon icon, int x, int y, int width, int height, int cut) {
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		tess.addVertexWithUV(x, y + height, zLevel, icon.getMinU(), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + height, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + cut, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(cut));
		tess.addVertexWithUV(x, y + cut, zLevel, icon.getMinU(), icon.getInterpolatedV(cut));
		tess.draw();
	}

	private void glColour(int colour) {
		float r = (colour >> 16 & 255) / 255F;
		float g = (colour >> 8 & 255) / 255F;
		float b = (colour & 255) / 255F;
		int a = colour >> 24 & 255;
		if (a <= 0)
			a = 255;

		GL11.glColor4f(r, g, b, a / 255F);
	}
}